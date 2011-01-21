/*
 * Copyright (c) 2010 Ronny R�hricht
 * 
 * This file is part of Moloko.
 * 
 * Moloko is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Moloko is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Moloko. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 * Ronny R�hricht - implementation
 */

package dev.drsoran.moloko.activities;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.mdt.rtm.data.RtmAuth;
import com.mdt.rtm.data.RtmTask;
import com.mdt.rtm.data.RtmTask.Priority;

import dev.drsoran.moloko.R;
import dev.drsoran.moloko.content.Modification;
import dev.drsoran.moloko.content.ModificationsProviderPart;
import dev.drsoran.moloko.content.RtmTaskSeriesProviderPart;
import dev.drsoran.moloko.content.TasksProviderPart;
import dev.drsoran.moloko.layouts.TitleWithEditTextLayout;
import dev.drsoran.moloko.layouts.TitleWithSpinnerLayout;
import dev.drsoran.moloko.layouts.WrappingLayout;
import dev.drsoran.moloko.layouts.TitleWithSpinnerLayout.StringConverter;
import dev.drsoran.moloko.util.AccountUtils;
import dev.drsoran.moloko.util.LogUtils;
import dev.drsoran.moloko.util.MolokoDateUtils;
import dev.drsoran.moloko.util.Queries;
import dev.drsoran.moloko.util.Strings;
import dev.drsoran.moloko.util.UIUtils;
import dev.drsoran.moloko.util.parsing.RecurrenceParsing;
import dev.drsoran.provider.Rtm.Lists;
import dev.drsoran.provider.Rtm.Locations;
import dev.drsoran.provider.Rtm.TaskSeries;
import dev.drsoran.provider.Rtm.Tasks;
import dev.drsoran.rtm.Task;


public class TaskEditActivity extends Activity
{
   private final static String TAG = "Moloko."
      + TaskEditActivity.class.getSimpleName();
   
   public final int FULL_DATE_FLAGS = MolokoDateUtils.FORMAT_WITH_YEAR;
   
   
   private abstract class SpinnerChangedListener implements
            OnItemSelectedListener
   {
      
      abstract public void onItemSelected( AdapterView< ? > parent,
                                           View view,
                                           int pos,
                                           long row );
      


      public void onNothingSelected( AdapterView< ? > arg0 )
      {
      }
   }
   
   private Task task;
   
   private ViewGroup taskContainer;
   
   private TextView addedDate;
   
   private TextView completedDate;
   
   private TextView source;
   
   private EditText nameEdit;
   
   private TitleWithSpinnerLayout list;
   
   private TitleWithSpinnerLayout priority;
   
   private WrappingLayout tagsLayout;
   
   private ImageButton addTagButton;
   
   private ImageButton removeTagButton;
   
   private EditText dueEdit;
   
   private ImageButton duePickerButton;
   
   private EditText recurrEdit;
   
   private ImageButton recurrPickerButton;
   
   private EditText estimateEdit;
   
   private ImageButton estimatePickerButton;
   
   private TitleWithSpinnerLayout location;
   
   private TitleWithEditTextLayout url;
   
   private final Set< Modification< ? > > modifications = new TreeSet< Modification< ? > >();
   
   

   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      
      final Intent intent = getIntent();
      
      if ( intent.getAction().equals( Intent.ACTION_EDIT ) )
      {
         final Uri taskUri = intent.getData();
         String taskId = null;
         
         if ( taskUri != null )
         {
            final ContentProviderClient client = getContentResolver().acquireContentProviderClient( Tasks.CONTENT_URI );
            
            if ( client != null )
            {
               taskId = taskUri.getLastPathSegment();
               task = TasksProviderPart.getTask( client, taskId );
               
               client.release();
            }
            else
            {
               LogUtils.logDBError( this, TAG, "Task" );
            }
         }
         
         if ( task != null )
         {
            setContentView( R.layout.task_edit_activity );
            
            try
            {
               taskContainer = (ViewGroup) findViewById( R.id.task_edit_container );
               addedDate = (TextView) taskContainer.findViewById( R.id.task_edit_added_date );
               completedDate = (TextView) taskContainer.findViewById( R.id.task_edit_completed_date );
               source = (TextView) taskContainer.findViewById( R.id.task_edit_src );
               nameEdit = (EditText) taskContainer.findViewById( R.id.task_edit_desc );
               list = (TitleWithSpinnerLayout) taskContainer.findViewById( R.id.task_edit_list );
               priority = (TitleWithSpinnerLayout) taskContainer.findViewById( R.id.task_edit_priority );
               tagsLayout = (WrappingLayout) taskContainer.findViewById( R.id.task_edit_tags_container );
               addTagButton = (ImageButton) taskContainer.findViewById( R.id.task_edit_tags_btn_add );
               removeTagButton = (ImageButton) taskContainer.findViewById( R.id.task_edit_tags_btn_remove );
               dueEdit = (EditText) taskContainer.findViewById( R.id.task_edit_due_text );
               duePickerButton = (ImageButton) taskContainer.findViewById( R.id.task_edit_due_btn_picker );
               recurrEdit = (EditText) taskContainer.findViewById( R.id.task_edit_recurrence_text );
               recurrPickerButton = (ImageButton) taskContainer.findViewById( R.id.task_edit_recurrence_btn_picker );
               estimateEdit = (EditText) taskContainer.findViewById( R.id.task_edit_estim_text );
               estimatePickerButton = (ImageButton) taskContainer.findViewById( R.id.task_edit_estim_btn_picker );
               location = (TitleWithSpinnerLayout) taskContainer.findViewById( R.id.task_edit_location );
               url = (TitleWithEditTextLayout) taskContainer.findViewById( R.id.task_edit_url );
            }
            catch ( final ClassCastException e )
            {
               Log.e( TAG, "Invalid layout spec.", e );
               throw e;
            }
            
            initializeListSpinner();
            initializePrioritySpinner();
            initializeLocationSpinner();
         }
         else
         {
            UIUtils.initializeErrorWithIcon( this,
                                             R.string.err_entity_not_found,
                                             getResources().getQuantityString( R.plurals.g_task,
                                                                               1 ) );
         }
      }
      else
      {
         UIUtils.initializeErrorWithIcon( this,
                                          R.string.err_unsupported_intent_action,
                                          intent.getAction() );
      }
   }
   


   @Override
   protected void onResume()
   {
      super.onResume();
      
      if ( task != null )
      {
         addedDate.setText( MolokoDateUtils.formatDateTime( task.getAdded()
                                                                .getTime(),
                                                            FULL_DATE_FLAGS ) );
         
         if ( task.getCompleted() != null )
            completedDate.setText( MolokoDateUtils.formatDateTime( task.getCompleted()
                                                                       .getTime(),
                                                                   FULL_DATE_FLAGS ) );
         else
            completedDate.setVisibility( View.GONE );
         
         if ( !TextUtils.isEmpty( task.getSource() ) )
         {
            String sourceStr = task.getSource();
            if ( sourceStr.equalsIgnoreCase( "js" ) )
               sourceStr = "web";
            
            source.setText( getString( R.string.task_source, sourceStr ) );
         }
         else
            source.setVisibility( View.GONE );
         
         nameEdit.setText( task.getName() );
         
         refeshListSpinner();
         refeshPrioritySpinner();
         refeshTags();
         refreshDue();
         refreshRecurrence();
         refreshEstimate();
         refreshLocationSpinner();
         refreshUrl();
         
         evaluateRtmAccessLevel();
      }
   }
   


   public void onDone( View v )
   {
      if ( validateInput() )
      {
         // Task name
         if ( Strings.hasStringChanged( task.getName(), nameEdit.getText()
                                                                .toString() ) )
         {
            modifications.add( Modification.newModification( Queries.contentUriWithId( TaskSeries.CONTENT_URI,
                                                                                       task.getTaskSeriesId() ),
                                                             RtmTaskSeriesProviderPart.COL_INDICES.get( TaskSeries.TASKSERIES_NAME ),
                                                             String.class,
                                                             nameEdit.getText()
                                                                     .toString() ) );
         }
         
         // Apply the modifications to the DB.
         if ( modifications.size() > 0 )
         {
            ModificationsProviderPart.applyModifications( getContentResolver(),
                                                          modifications );
         }
      }
   }
   


   public void onCancel( View v )
   {
      finish();
   }
   


   private void initializeListSpinner()
   {
      try
      {
         final Cursor c = managedQuery( Lists.CONTENT_URI,
                                        new String[]
                                        { Lists._ID, Lists.LIST_NAME,
                                         Lists.IS_SMART_LIST },
                                        Lists.IS_SMART_LIST + " = 0",
                                        null,
                                        Lists.DEFAULT_SORT_ORDER );
         
         if ( c != null )
         {
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter( this,
                                                                         android.R.layout.simple_spinner_item,
                                                                         c,
                                                                         new String[]
                                                                         { Lists.LIST_NAME },
                                                                         new int[]
                                                                         { android.R.id.text1 } );
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            adapter.setStringConversionColumn( 1 );
            
            list.setAdapter( adapter );
            list.setStringConverter( new StringConverter()
            {
               public String convertToString( Object object )
               {
                  return adapter.convertToString( (Cursor) object ).toString();
               }
            } );
            
            list.setOnItemSelectedListener( new SpinnerChangedListener()
            {
               @Override
               public void onItemSelected( AdapterView< ? > parent,
                                           View view,
                                           int pos,
                                           long row )
               {
                  final String selectedListName = adapter.convertToString( (Cursor) adapter.getItem( pos ) )
                                                         .toString();
                  
                  if ( !TaskEditActivity.this.task.getListName()
                                                  .equals( selectedListName ) )
                  {
                     onListChanged( selectedListName );
                  }
               }
            } );
         }
         else
            throw new Exception();
      }
      catch ( Exception e )
      {
         LogUtils.logDBError( this, TAG, "Lists", e );
      }
   }
   


   private void initializePrioritySpinner()
   {
      priority.setOnItemSelectedListener( new SpinnerChangedListener()
      {
         @Override
         public void onItemSelected( AdapterView< ? > parent,
                                     View view,
                                     int pos,
                                     long row )
         {
            final Priority value = RtmTask.convertPriority( priority.getValueAtPos( pos ) );
            
            if ( value != task.getPriority() )
               onPriorityChanged( value );
         }
      } );
   }
   


   private void initializeLocationSpinner()
   {
      Cursor c = null;
      
      try
      {
         c = getContentResolver().query( Locations.CONTENT_URI,
                                         new String[]
                                         { Locations._ID,
                                          Locations.LOCATION_NAME },
                                         null,
                                         null,
                                         Locations.DEFAULT_SORT_ORDER );
         if ( c != null )
         {
            String[] locationNames = new String[ c.getCount() + 1 ];
            // Add the "nowhere" entry as first
            locationNames[ 0 ] = getString( R.string.task_edit_location_no );
            // Add the locations to the array
            locationNames = Queries.fillStringArray( c, 1, locationNames, 1 );
            
            if ( locationNames != null )
            {
               final ArrayAdapter< String > adapter = new ArrayAdapter< String >( this,
                                                                                  android.R.layout.simple_spinner_item,
                                                                                  android.R.id.text1,
                                                                                  locationNames );
               adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
               location.setAdapter( adapter );
               location.setOnItemSelectedListener( new SpinnerChangedListener()
               {
                  @Override
                  public void onItemSelected( AdapterView< ? > parent,
                                              View view,
                                              int pos,
                                              long row )
                  {
                     final String selectedLocationName = parent.getItemAtPosition( pos )
                                                               .toString();
                     
                     if ( Strings.hasStringChanged( task.getLocationName(),
                                                    selectedLocationName ) )
                     {
                        onLocationChanged( selectedLocationName );
                     }
                  }
               } );
            }
            else
               throw new Exception();
         }
         else
            throw new Exception();
      }
      catch ( Exception e )
      {
         LogUtils.logDBError( this, TAG, "Locations", e );
      }
      finally
      {
         if ( c != null )
            c.close();
      }
   }
   


   private void refeshListSpinner()
   {
      list.setSelection( task.getListName(), -1 );
   }
   


   private void refeshPrioritySpinner()
   {
      priority.setSelection( RtmTask.convertPriority( task.getPriority() ), -1 );
   }
   


   private void refeshTags()
   {
      UIUtils.inflateTags( this, tagsLayout, task, null, null );
   }
   


   private void refreshLocationSpinner()
   {
      location.setSelection( task.getLocationName(), 0 );
   }
   


   private void refreshDue()
   {
      final Date due = task.getDue();
      
      if ( due == null )
      {
         dueEdit.setText( null );
      }
      else if ( task.hasDueTime() )
      {
         dueEdit.setText( MolokoDateUtils.formatDateTime( due.getTime(),
                                                          MolokoDateUtils.FORMAT_NUMERIC
                                                             | MolokoDateUtils.FORMAT_WITH_YEAR
                                                             | MolokoDateUtils.FORMAT_SHOW_WEEKDAY
                                                             | MolokoDateUtils.FORMAT_ABR_ALL ) );
      }
      else
      {
         dueEdit.setText( MolokoDateUtils.formatDate( due.getTime(),
                                                      MolokoDateUtils.FORMAT_NUMERIC
                                                         | MolokoDateUtils.FORMAT_WITH_YEAR
                                                         | MolokoDateUtils.FORMAT_SHOW_WEEKDAY
                                                         | MolokoDateUtils.FORMAT_ABR_ALL ) );
      }
   }
   


   private void refreshRecurrence()
   {
      final String reccurrencePattern = task.getRecurrence();
      
      if ( TextUtils.isEmpty( reccurrencePattern ) )
      {
         recurrEdit.setText( null );
      }
      else
      {
         final String sentence = RecurrenceParsing.parseRecurrencePattern( reccurrencePattern,
                                                                           task.isEveryRecurrence() );
         recurrEdit.setText( ( sentence != null
                                               ? sentence
                                               : getString( R.string.task_datetime_err_recurr ) ) );
      }
   }
   


   private void refreshEstimate()
   {
      final String estimate = task.getEstimate();
      
      if ( TextUtils.isEmpty( estimate ) )
      {
         estimateEdit.setText( null );
      }
      else
      {
         estimateEdit.setText( MolokoDateUtils.formatEstimated( this,
                                                                task.getEstimateMillis() ) );
      }
   }
   


   private void refreshUrl()
   {
      url.setText( task.getUrl() );
   }
   


   private void evaluateRtmAccessLevel()
   {
      nameEdit.setEnabled( false );
      list.setEnabled( false );
      priority.setEnabled( false );
      addTagButton.setEnabled( false );
      removeTagButton.setEnabled( false );
      dueEdit.setEnabled( false );
      duePickerButton.setEnabled( false );
      recurrEdit.setEnabled( false );
      recurrPickerButton.setEnabled( false );
      estimateEdit.setEnabled( false );
      estimatePickerButton.setEnabled( false );
      location.setEnabled( false );
      url.setEnabled( false );
      
      final RtmAuth.Perms permission = AccountUtils.getAccessLevel( this );
      
      switch ( permission )
      {
         case delete:
            removeTagButton.setEnabled( true );
         case write:
            list.setEnabled( true );
            priority.setEnabled( true );
            addTagButton.setEnabled( true );
            dueEdit.setEnabled( true );
            duePickerButton.setEnabled( true );
            recurrEdit.setEnabled( true );
            recurrPickerButton.setEnabled( true );
            estimateEdit.setEnabled( true );
            estimatePickerButton.setEnabled( true );
            location.setEnabled( true );
            url.setEnabled( true );
         case read:
            nameEdit.setEnabled( true );
            break;
         default :
            break;
      }
   }
   


   private boolean validateInput()
   {
      // Task name
      if ( TextUtils.isEmpty( nameEdit.getText() ) )
      {
         Toast.makeText( this,
                         R.string.task_edit_validate_empty_name,
                         Toast.LENGTH_SHORT ).show();
         nameEdit.requestFocus();
         return false;
      }
      
      return true;
   }
   


   private void onListChanged( String newListName )
   {
      
   }
   


   private void onPriorityChanged( RtmTask.Priority newPriority )
   {
      
   }
   


   private void onLocationChanged( String newLocationName )
   {
      // TODO: Handle selected location "no location".
   }
}
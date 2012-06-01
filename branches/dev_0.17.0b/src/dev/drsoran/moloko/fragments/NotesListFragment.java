/* 
 *	Copyright (c) 2012 Ronny R�hricht
 *
 *	This file is part of Moloko.
 *
 *	Moloko is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Moloko is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with Moloko.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	Contributors:
 * Ronny R�hricht - implementation
 */

package dev.drsoran.moloko.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.mdt.rtm.data.RtmTaskNote;

import dev.drsoran.moloko.IOnSettingsChangedListener;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.actionmodes.BaseSelectableActionModeCallback;
import dev.drsoran.moloko.actionmodes.NotesListActionModeCallback;
import dev.drsoran.moloko.adapters.ISelectableAdapter;
import dev.drsoran.moloko.adapters.NotesListFragmentAdapter;
import dev.drsoran.moloko.annotations.InstanceState;
import dev.drsoran.moloko.fragments.base.MolokoSelectableListFragment;
import dev.drsoran.moloko.fragments.listeners.INotesListsFragmentListener;
import dev.drsoran.moloko.loaders.RtmTaskNotesLoader;


public class NotesListFragment extends
         MolokoSelectableListFragment< RtmTaskNote >
{
   
   public static class Config
   {
      public final static String TASK_ID = "task_id";
   }
   
   
   
   public final static NotesListFragment newInstance( Bundle config )
   {
      final NotesListFragment fragment = new NotesListFragment();
      
      fragment.setArguments( config );
      
      return fragment;
   }
   
   @InstanceState( key = Config.TASK_ID )
   private String taskId;
   
   private INotesListsFragmentListener listener;
   
   
   
   public NotesListFragment()
   {
      registerAnnotatedConfiguredInstance( this, NotesListFragment.class );
   }
   
   
   
   @Override
   public void onAttach( Activity activity )
   {
      super.onAttach( activity );
      
      if ( activity instanceof INotesListsFragmentListener )
         listener = (INotesListsFragmentListener) activity;
      else
         listener = null;
   }
   
   
   
   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      setHasOptionsMenu( true );
   }
   
   
   
   @Override
   public View onCreateView( LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState )
   {
      final View fragmentView = inflater.inflate( R.layout.noteslist_fragment,
                                                  container,
                                                  false );
      return fragmentView;
   }
   
   
   
   @Override
   public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
   {
      super.onCreateOptionsMenu( menu, inflater );
      
      if ( getListAdapter() != null && !isSelectionMode() && isWritableAccess() )
      {
         inflater.inflate( R.menu.noteslist_fragment_rwd, menu );
      }
   }
   
   
   
   @Override
   public boolean onOptionsItemSelected( MenuItem item )
   {
      switch ( item.getItemId() )
      {
         case R.id.menu_add_note:
            if ( listener != null )
            {
               listener.onAddNote();
            }
            return true;
            
         default :
            return super.onOptionsItemSelected( item );
      }
   }
   
   
   
   @Override
   public boolean onItemLongClick( AdapterView< ? > parent,
                                   View view,
                                   int position,
                                   long id )
   {
      if ( isWritableAccess() )
      {
         return super.onItemLongClick( parent, view, position, id );
      }
      else
      {
         return false;
      }
   }
   
   
   
   @Override
   public void onListItemClick( ListView l, View v, int position, long id )
   {
      super.onListItemClick( l, v, position, id );
      
      if ( listener != null && !isSelectionMode() )
      {
         listener.onOpenNote( getListAdapter().getItem( position ), position );
      }
   }
   
   
   
   @Override
   protected int getSettingsMask()
   {
      return super.getSettingsMask()
         | IOnSettingsChangedListener.DATE_TIME_RELATED;
   }
   
   
   
   @Override
   public Loader< List< RtmTaskNote >> newLoaderInstance( int id, Bundle config )
   {
      return new RtmTaskNotesLoader( getSherlockActivity(), taskId );
   }
   
   
   
   @Override
   public String getLoaderDataName()
   {
      return getString( R.string.app_note );
   }
   
   
   
   @Override
   public int getLoaderId()
   {
      return RtmTaskNotesLoader.ID;
   }
   
   
   
   @Override
   public ListAdapter createListAdapterForResult( List< RtmTaskNote > result )
   {
      final NotesListFragmentAdapter adapter = new NotesListFragmentAdapter( this,
                                                                             result );
      return adapter;
   }
   
   
   
   @Override
   public NotesListFragmentAdapter getListAdapter()
   {
      return (NotesListFragmentAdapter) super.getListAdapter();
   }
   
   
   
   @Override
   protected BaseSelectableActionModeCallback< RtmTaskNote > createActionModeCallback()
   {
      final NotesListActionModeCallback callback = new NotesListActionModeCallback( getSherlockActivity(),
                                                                                    getListAdapter() );
      callback.setNotesListActionModeListener( listener );
      return callback;
   }
   
   
   
   @Override
   protected ISelectableAdapter< RtmTaskNote > getSelectableListAdapter()
   {
      return getListAdapter();
   }
   
   
   
   @Override
   protected void onSelectionModeFinished( ActionMode mode,
                                           BaseSelectableActionModeCallback< RtmTaskNote > callback )
   {
      super.onSelectionModeFinished( mode, callback );
      configureListAdapterForDefaultMode();
   }
   
   
   
   @Override
   protected void configureListAdapterForSelectionMode()
   {
      super.configureListAdapterForSelectionMode();
      
      final NotesListFragmentAdapter adapter = getListAdapter();
      adapter.setSelectable( true );
   }
   
   
   
   private void configureListAdapterForDefaultMode()
   {
      final NotesListFragmentAdapter adapter = getListAdapter();
      adapter.setSelectable( false );
   }
}

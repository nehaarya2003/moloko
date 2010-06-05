package dev.drsoran.moloko.content;

import java.util.Date;
import java.util.HashMap;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.RemoteException;

import com.mdt.rtm.data.RtmTask;

import dev.drsoran.provider.Rtm;
import dev.drsoran.provider.Rtm.Tasks;


public class RtmTasksProviderPart extends AbstractRtmProviderPart
{
   @SuppressWarnings( "unused" )
   private static final String TAG = RtmTasksProviderPart.class.getSimpleName();
   
   public final static HashMap< String, String > PROJECTION_MAP = new HashMap< String, String >();
   
   public final static HashMap< String, Integer > COL_INDICES = new HashMap< String, Integer >();
   
   static
   {
      COL_INDICES.put( Tasks._ID, 0 );
      COL_INDICES.put( Tasks.DUE_DATE, 1 );
      COL_INDICES.put( Tasks.ADDED_DATE, 2 );
      COL_INDICES.put( Tasks.COMPLETED_DATE, 3 );
      COL_INDICES.put( Tasks.DELETED_DATE, 4 );
      COL_INDICES.put( Tasks.PRIORITY, 5 );
      COL_INDICES.put( Tasks.POSTPONED, 6 );
      COL_INDICES.put( Tasks.ESTIMATE, 7 );
      
      AbstractRtmProviderPart.fillProjectionMap( PROJECTION_MAP, COL_INDICES );
   }
   
   

   public final static RtmTask getTask( ContentProviderClient client, String id ) throws RemoteException
   {
      RtmTask task = null;
      
      // We query all TaskSeries rows and sort them by their list ID.
      // So we have all lists with their tasks together.
      final Cursor c = Queries.getItem( client, Rtm.Tasks.CONTENT_URI, id );
      
      boolean ok = c != null && !c.isAfterLast();
      
      if ( ok )
      {
         Date due = null;
         Date completed = null;
         Date deleted = null;
         
         if ( !c.isNull( COL_INDICES.get( Tasks.DUE_DATE ) ) )
            due = new Date( c.getLong( COL_INDICES.get( Tasks.DUE_DATE ) ) );
         if ( !c.isNull( COL_INDICES.get( Tasks.COMPLETED_DATE ) ) )
            completed = new Date( c.getLong( COL_INDICES.get( Tasks.COMPLETED_DATE ) ) );
         if ( !c.isNull( COL_INDICES.get( Tasks.DELETED_DATE ) ) )
            deleted = new Date( c.getLong( COL_INDICES.get( Tasks.DELETED_DATE ) ) );
         
         task = new RtmTask( c.getString( COL_INDICES.get( Tasks._ID ) ),
                             due,
                             due != null ? 1 : 0,
                             new Date( c.getLong( COL_INDICES.get( Tasks.ADDED_DATE ) ) ),
                             completed,
                             deleted,
                             RtmTask.convertPriority( c.getString( COL_INDICES.get( Tasks.PRIORITY ) ) ),
                             c.getInt( COL_INDICES.get( Tasks.POSTPONED ) ),
                             Queries.getOptString( c,
                                                   COL_INDICES.get( Tasks.ESTIMATE ) ) );
      }
      
      if ( c != null )
         c.close();
      
      return task;
   }
   


   public RtmTasksProviderPart( SQLiteOpenHelper dbAccess )
   {
      super( dbAccess, "tasks" );
   }
   


   public void create( SQLiteDatabase db ) throws SQLException
   {
      db.execSQL( "CREATE TABLE " + tableName + " ( " + Tasks._ID
         + " INTEGER NOT NULL, " + Tasks.DUE_DATE + " INTEGER, "
         + Tasks.ADDED_DATE + " INTEGER NOT NULL, " + Tasks.COMPLETED_DATE
         + " INTEGER, " + Tasks.DELETED_DATE + " INTEGER, " + Tasks.PRIORITY
         + " CHAR(1) NOT NULL DEFAULT 'n', " + Tasks.POSTPONED
         + " INTEGER DEFAULT 0, " + Tasks.ESTIMATE + " TEXT, "
         + "CONSTRAINT PK_TASKS PRIMARY KEY ( \"" + Tasks._ID + "\" )" + " );" );
   }
   


   @Override
   protected ContentValues getInitialValues( ContentValues initialValues )
   {
      // Make sure that the fields are all set
      if ( initialValues.containsKey( Tasks.ADDED_DATE ) == false )
      {
         final Long now = Long.valueOf( System.currentTimeMillis() );
         
         initialValues.put( Tasks.ADDED_DATE, now );
      }
      
      return initialValues;
   }
   


   @Override
   protected String getContentItemType()
   {
      return Tasks.CONTENT_ITEM_TYPE;
   }
   


   @Override
   protected String getContentType()
   {
      return Tasks.CONTENT_TYPE;
   }
   


   @Override
   protected Uri getContentUri()
   {
      return Tasks.CONTENT_URI;
   }
   


   @Override
   protected String getDefaultSortOrder()
   {
      return Tasks.DEFAULT_SORT_ORDER;
   }
   


   public HashMap< String, String > getProjectionMap()
   {
      return PROJECTION_MAP;
   }
   


   public HashMap< String, Integer > getColumnIndices()
   {
      return COL_INDICES;
   }
}

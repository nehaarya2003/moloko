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

package dev.drsoran.moloko.content.db;

import java.util.HashMap;
import java.util.Map;

import android.database.SQLException;
import dev.drsoran.moloko.content.db.Columns.RtmListsColumns;


class RtmListsTable extends Table
{
   public final static String TABLE_NAME = "lists";
   
   
   @Deprecated
   public final static class NewRtmListId
   {
      public String rtmListId;
   }
   
   private final static Map< String, String > PROJECTION_MAP = new HashMap< String, String >();
   
   private final static String[] PROJECTION =
   { RtmListsColumns._ID, RtmListsColumns.LIST_NAME,
    RtmListsColumns.CREATED_DATE, RtmListsColumns.MODIFIED_DATE,
    RtmListsColumns.LIST_DELETED, RtmListsColumns.LOCKED,
    RtmListsColumns.ARCHIVED, RtmListsColumns.POSITION,
    RtmListsColumns.IS_SMART_LIST, RtmListsColumns.FILTER };
   
   private final static HashMap< String, Integer > COL_INDICES = new HashMap< String, Integer >();
   
   public final static String SELECTION_EXCLUDE_DELETED_AND_ARCHIVED = RtmListsColumns.LIST_DELETED
      + " IS NULL AND " + RtmListsColumns.ARCHIVED + "=0";
   
   static
   {
      initProjectionDependent( PROJECTION, PROJECTION_MAP, COL_INDICES );
   }
   
   
   
   public RtmListsTable( RtmDatabase database )
   {
      super( database, TABLE_NAME );
   }
   
   
   
   @Override
   public void create() throws SQLException
   {
      final StringBuilder builder = new StringBuilder();
      
      builder.append( "CREATE TABLE " );
      builder.append( TABLE_NAME );
      builder.append( " ( " );
      builder.append( RtmListsColumns._ID );
      builder.append( " TEXT NOT NULL, " );
      builder.append( RtmListsColumns.LIST_NAME );
      builder.append( " TEXT NOT NULL, " );
      builder.append( RtmListsColumns.CREATED_DATE );
      builder.append( " INTEGER, " );
      builder.append( RtmListsColumns.MODIFIED_DATE );
      builder.append( " INTEGER, " );
      builder.append( RtmListsColumns.LIST_DELETED );
      builder.append( " INTEGER, " );
      builder.append( RtmListsColumns.LOCKED );
      builder.append( " INTEGER NOT NULL DEFAULT 0, " );
      builder.append( RtmListsColumns.ARCHIVED );
      builder.append( " INTEGER NOT NULL DEFAULT 0, " );
      builder.append( RtmListsColumns.POSITION );
      builder.append( " INTEGER NOT NULL DEFAULT 0, " );
      builder.append( RtmListsColumns.IS_SMART_LIST );
      builder.append( " INTEGER NOT NULL DEFAULT 0, " );
      builder.append( RtmListsColumns.FILTER );
      builder.append( " TEXT, " );
      builder.append( "CONSTRAINT PK_LISTS PRIMARY KEY ( \"" );
      builder.append( RtmListsColumns._ID );
      builder.append( "\" ) );" );
      
      getDatabase().getWritable().execSQL( builder.toString() );
   }
   
   
   
   @Override
   public String getDefaultSortOrder()
   {
      return RtmListsColumns.DEFAULT_SORT_ORDER;
   }
   
   
   
   @Override
   public Map< String, String > getProjectionMap()
   {
      return PROJECTION_MAP;
   }
   
   
   
   @Override
   public Map< String, Integer > getColumnIndices()
   {
      return COL_INDICES;
   }
   
   
   
   @Override
   public String[] getProjection()
   {
      return PROJECTION;
   }
}

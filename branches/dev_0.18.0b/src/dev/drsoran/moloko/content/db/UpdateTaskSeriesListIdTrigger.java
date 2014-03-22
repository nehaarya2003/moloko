/* 
 *	Copyright (c) 2013 Ronny R�hricht
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

package dev.drsoran.moloko.content.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import dev.drsoran.moloko.content.db.TableColumns.RtmTaskSeriesColumns;
import dev.drsoran.moloko.content.db.TableColumns.RtmTasksListColumns;


/**
 * @brief If a taskseries list ID gets updated, try to set the corresponding RTM list ID.
 * 
 *        The other direction, if the RTM list ID gets updated by a sync, is handled by the sync handler to avoid cyclic
 *        updates.
 */
class UpdateTaskSeriesListIdTrigger extends AbstractTrigger
{
   public UpdateTaskSeriesListIdTrigger()
   {
      super( RtmTaskSeriesTable.TABLE_NAME + "_update_list_id" );
   }
   
   
   
   @Override
   public void create( SQLiteDatabase database ) throws SQLException
   {
      final StringBuilder builder = new StringBuilder();
      
      builder.append( "CREATE TRIGGER " );
      builder.append( getTriggerName() );
      builder.append( " AFTER UPDATE OF " );
      builder.append( RtmTaskSeriesColumns.LIST_ID );
      builder.append( " ON " );
      builder.append( RtmTaskSeriesTable.TABLE_NAME );
      builder.append( " FOR EACH ROW BEGIN UPDATE " );
      builder.append( RtmTaskSeriesTable.TABLE_NAME );
      builder.append( " SET " );
      builder.append( RtmTaskSeriesColumns.RTM_LIST_ID );
      builder.append( " = (SELECT " );
      builder.append( RtmTasksListColumns.RTM_LIST_ID );
      builder.append( " FROM " );
      builder.append( RtmTasksListsTable.TABLE_NAME );
      builder.append( " WHERE " );
      builder.append( RtmTasksListColumns._ID );
      builder.append( " = new." );
      builder.append( RtmTaskSeriesColumns.LIST_ID );
      builder.append( ")" );
      builder.append( "; END;" );
      
      database.execSQL( builder.toString() );
   }
}
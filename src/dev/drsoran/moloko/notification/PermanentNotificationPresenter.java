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

package dev.drsoran.moloko.notification;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.util.Pair;

import com.mdt.rtm.data.RtmTask;

import dev.drsoran.moloko.R;
import dev.drsoran.moloko.content.TasksProviderPart;
import dev.drsoran.moloko.util.Intents;
import dev.drsoran.moloko.util.MolokoCalendar;
import dev.drsoran.moloko.util.MolokoDateUtils;
import dev.drsoran.moloko.util.Queries;
import dev.drsoran.moloko.util.Strings;
import dev.drsoran.provider.Rtm.Tasks;
import dev.drsoran.rtm.RtmSmartFilter;


class PermanentNotificationPresenter
{
   private final Context context;
   
   private PermanentNotification permanentNotification;
   
   
   
   public PermanentNotificationPresenter( Context context )
   {
      this.context = context;
   }
   
   
   
   public void showNotificationFor( Cursor tasksCursor, String filterString )
   {
      if ( permanentNotification == null )
      {
         createNotification();
      }
      
      updateAndLaunchNotification( tasksCursor, filterString );
   }
   
   
   
   public void cancelNotification()
   {
      if ( permanentNotification != null )
      {
         permanentNotification.cancel();
         permanentNotification = null;
      }
   }
   
   
   
   private void createNotification()
   {
      permanentNotification = new PermanentNotification( context );
   }
   
   
   
   private void updateAndLaunchNotification( Cursor tasksCursor,
                                             String filterString )
   {
      final String title = getNotificationTitle();
      final String text = buildPermanentNotificationRowText( tasksCursor );
      final Intent onClickIntent = createOnClickIntent( tasksCursor,
                                                        title,
                                                        filterString );
      
      permanentNotification.update( title,
                                    text,
                                    tasksCursor.getCount(),
                                    onClickIntent );
   }
   
   
   
   private String getNotificationTitle()
   {
      final int notificationType = getPermanentNotificationTypeFromPreferences();
      
      switch ( notificationType )
      {
         case PermanentNotificationType.TODAY:
            return context.getString( R.string.notification_permanent_today_title );
            
         case PermanentNotificationType.TOMORROW:
            return context.getString( R.string.notification_permanent_tomorrow_title );
            
         case PermanentNotificationType.TODAY_AND_TOMORROW:
         {
            final MolokoCalendar cal = MolokoCalendar.getInstance();
            
            final long todayMillis = cal.getTimeInMillis();
            cal.roll( Calendar.DAY_OF_YEAR, 1 );
            final long tomorrowMillis = cal.getTimeInMillis();
            
            return context.getString( R.string.notification_permanent_today_and_tomorrow_title,
                                      MolokoDateUtils.formatDate( context,
                                                                  todayMillis,
                                                                  MolokoDateUtils.FORMAT_NUMERIC ),
                                      MolokoDateUtils.formatDate( context,
                                                                  tomorrowMillis,
                                                                  MolokoDateUtils.FORMAT_NUMERIC ) );
         }
         
         case PermanentNotificationType.OFF:
         default :
            return Strings.EMPTY_STRING;
      }
   }
   
   
   
   private String buildPermanentNotificationRowText( Cursor tasksCursor )
   {
      String result = Strings.EMPTY_STRING;
      
      final int tasksCount = tasksCursor.getCount();
      
      final Pair< Integer, Integer > numHighPrioAndOverdueTasks = countHighPrioAndOverdueTasks( tasksCursor );
      final int highPrioCnt = numHighPrioAndOverdueTasks.first.intValue();
      final int overdueCnt = numHighPrioAndOverdueTasks.second.intValue();
      final int tasksDueCnt = tasksCount - overdueCnt;
      
      if ( tasksCount == 1 )
      {
         tasksCursor.moveToFirst();
         
         final String taskName = Queries.getOptString( tasksCursor,
                                                       getColumnIndex( Tasks.TASKSERIES_NAME ) );
         result = context.getString( R.string.notification_permanent_text_one_task,
                                     taskName );
      }
      
      else if ( tasksDueCnt > 0 )
      {
         if ( overdueCnt == 0 )
            result = context.getString( R.string.notification_permanent_text_multiple,
                                        tasksDueCnt,
                                        context.getResources()
                                               .getQuantityString( R.plurals.g_task,
                                                                   tasksDueCnt ),
                                        highPrioCnt );
         else if ( overdueCnt > 0 )
            result = context.getString( R.string.notification_permanent_text_multiple_w_overdue,
                                        tasksDueCnt,
                                        context.getResources()
                                               .getQuantityString( R.plurals.g_task,
                                                                   tasksDueCnt ),
                                        overdueCnt,
                                        context.getResources()
                                               .getQuantityString( R.plurals.g_task,
                                                                   overdueCnt ) );
      }
      
      else if ( tasksDueCnt == 0 && overdueCnt > 1 )
      {
         result = context.getString( R.string.notification_permanent_text_multiple_overdue,
                                     overdueCnt,
                                     context.getResources()
                                            .getQuantityString( R.plurals.g_task,
                                                                overdueCnt ) );
      }
      
      else
      {
         result = String.format( "Unhandled case tasks:%d, due:%d, overdue %d",
                                 tasksCount,
                                 tasksDueCnt,
                                 overdueCnt );
      }
      
      return result;
   }
   
   
   
   private Pair< Integer, Integer > countHighPrioAndOverdueTasks( Cursor tasksCursor )
   {
      int numOverdueTasks = 0;
      int numHighPrioTasks = 0;
      
      MolokoCalendar nowCal = null;
      
      boolean hasNext = tasksCursor.moveToFirst();
      for ( ; hasNext; hasNext = tasksCursor.moveToNext() )
      {
         final String priorityString = Queries.getOptString( tasksCursor,
                                                             getColumnIndex( Tasks.PRIORITY ) );
         
         if ( RtmTask.convertPriority( priorityString ) == RtmTask.Priority.High )
         {
            ++numHighPrioTasks;
         }
         
         final Long due = Queries.getOptLong( tasksCursor,
                                              getColumnIndex( Tasks.DUE_DATE ) );
         
         if ( due != null )
         {
            final long dueMillis = due.longValue();
            
            if ( nowCal == null )
               nowCal = MolokoCalendar.getInstance();
            
            final boolean hasDueTime = Queries.getOptBool( tasksCursor,
                                                           getColumnIndex( Tasks.HAS_DUE_TIME ),
                                                           false );
            
            // If the task has a due time then it can be overdue
            // even today.
            if ( hasDueTime
               && MolokoDateUtils.isBefore( dueMillis, nowCal.getTimeInMillis() ) )
            {
               ++numOverdueTasks;
            }
            
            // If the task has no due time then it can be overdue
            // only before today.
            else if ( !MolokoDateUtils.isToday( dueMillis )
               && !MolokoDateUtils.isAfter( dueMillis, nowCal.getTimeInMillis() ) )
            {
               ++numOverdueTasks;
            }
         }
      }
      
      return Pair.create( Integer.valueOf( numHighPrioTasks ),
                          Integer.valueOf( numOverdueTasks ) );
   }
   
   
   
   private Intent createOnClickIntent( Cursor tasksCursor,
                                       String activityTitle,
                                       String filterString )
   {
      final Intent onClickIntent;
      final int numTasks = tasksCursor.getCount();
      
      if ( numTasks == 1 )
      {
         if ( tasksCursor.moveToFirst() )
         {
            onClickIntent = Intents.createOpenTaskIntent( context,
                                                          Queries.getOptString( tasksCursor,
                                                                                getColumnIndex( Tasks.TASKSERIES_ID ) ) );
         }
         else
         {
            onClickIntent = null;
         }
      }
      else
      {
         onClickIntent = Intents.createSmartFilterIntent( context,
                                                          new RtmSmartFilter( filterString ),
                                                          activityTitle );
      }
      
      return onClickIntent;
   }
   
   
   
   private int getPermanentNotificationTypeFromPreferences()
   {
      int notificationType = 0;
      final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
      
      if ( prefs != null )
      {
         notificationType = Integer.parseInt( prefs.getString( context.getString( R.string.key_notify_permanent ),
                                                               String.valueOf( PermanentNotificationType.OFF ) ) );
      }
      
      return notificationType;
   }
   
   
   
   private static int getColumnIndex( String colName )
   {
      return TasksProviderPart.COL_INDICES.get( colName ).intValue();
   }
}

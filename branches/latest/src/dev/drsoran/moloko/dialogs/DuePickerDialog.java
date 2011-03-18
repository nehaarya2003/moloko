/* 
 *	Copyright (c) 2011 Ronny R�hricht
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

package dev.drsoran.moloko.dialogs;

import java.util.Calendar;

import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import dev.drsoran.moloko.MolokoApp;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.Settings;
import dev.drsoran.moloko.util.MolokoDateUtils;


public class DuePickerDialog extends AbstractPickerDialog
{
   private AlertDialog impl;
   
   private WheelView dateDayWheel;
   
   private WheelView dateMonthWheel;
   
   private WheelView dateYearWheel;
   
   private TimeWheelGroup timeWheelGroup;
   
   private final Calendar calendar;
   
   private int dateFormat;
   
   

   public DuePickerDialog( Context context )
   {
      this( context, System.currentTimeMillis(), false );
   }
   


   public DuePickerDialog( Context context, long initial, boolean hasDueTime )
   {
      this.calendar = Calendar.getInstance( MolokoApp.getSettings()
                                                     .getTimezone() );
      this.calendar.setTimeInMillis( initial != -1 ? initial
                                                  : System.currentTimeMillis() );
      
      if ( !hasDueTime )
         MolokoDateUtils.clearTime( calendar );
      
      init( context, hasDueTime );
   }
   


   private void init( final Context context, boolean hasTime )
   {
      final LayoutInflater inflater = LayoutInflater.from( context );
      final View view = inflater.inflate( R.layout.due_picker_dialog, null );
      
      final Settings settings = MolokoApp.getSettings();
      
      dateFormat = settings.getDateformat();
      
      if ( dateFormat == Settings.DATEFORMAT_EU )
      {
         dateDayWheel = (WheelView) view.findViewById( R.id.due_dlg_date_wheel_1 );
         dateMonthWheel = (WheelView) view.findViewById( R.id.due_dlg_date_wheel_2 );
      }
      else
      {
         dateDayWheel = (WheelView) view.findViewById( R.id.due_dlg_date_wheel_2 );
         dateMonthWheel = (WheelView) view.findViewById( R.id.due_dlg_date_wheel_1 );
      }
      
      dateYearWheel = (WheelView) view.findViewById( R.id.due_dlg_year_wheel );
      
      initDaysWheel( context );
      initMonthsWheel( context );
      initYearsWheel( context );
      
      timeWheelGroup = new TimeWheelGroup( context, view, 0, hasTime );
      
      dateMonthWheel.addScrollingListener( new OnWheelScrollListener()
      {
         public void onScrollingStarted( WheelView wheel )
         {
         }
         


         public void onScrollingFinished( WheelView wheel )
         {
            updateCalendarDate();
            initDaysWheel( context );
         }
      } );
      
      dateYearWheel.addScrollingListener( new OnWheelScrollListener()
      {
         public void onScrollingStarted( WheelView wheel )
         {
         }
         


         public void onScrollingFinished( WheelView wheel )
         {
            updateCalendarDate();
            initDaysWheel( context );
         }
      } );
      
      this.impl = new AlertDialog.Builder( context ).setIcon( R.drawable.ic_dialog_time )
                                                    .setTitle( R.string.dlg_due_picker_title )
                                                    .setView( view )
                                                    .setPositiveButton( R.string.btn_ok,
                                                                        new OnClickListener()
                                                                        {
                                                                           public void onClick( DialogInterface dialog,
                                                                                                int which )
                                                                           {
                                                                              final Calendar cal = getCalendar();
                                                                              notifyOnDialogCloseListener( CloseReason.OK,
                                                                                                           cal.getTimeInMillis(),
                                                                                                           Boolean.valueOf( cal.isSet( Calendar.HOUR )
                                                                                                              || cal.isSet( Calendar.HOUR_OF_DAY ) ) );
                                                                           }
                                                                        } )
                                                    .setNegativeButton( R.string.btn_cancel,
                                                                        new OnClickListener()
                                                                        {
                                                                           public void onClick( DialogInterface dialog,
                                                                                                int which )
                                                                           {
                                                                              notifyOnDialogCloseListener( CloseReason.CANCELED,
                                                                                                           null );
                                                                           }
                                                                        } )
                                                    .create();
   }
   


   @Override
   public void show()
   {
      impl.show();
   }
   


   public Calendar getCalendar()
   {
      updateCalendarDate();
      
      final Calendar cal = Calendar.getInstance( MolokoApp.getSettings()
                                                          .getTimezone() );
      cal.setTimeInMillis( calendar.getTimeInMillis() );
      
      return timeWheelGroup.putTime( cal );
   }
   


   private void updateCalendarDate()
   {
      // First set the calendar to the first. Otherwise we get a wrap-around
      // if we set the month and the day is beyond the maximum.
      calendar.set( Calendar.DAY_OF_MONTH, 1 );
      calendar.set( Calendar.MONTH, dateMonthWheel.getCurrentItem() );
      calendar.set( Calendar.YEAR, dateYearWheel.getCurrentItem() + 1 );
      
      int day = dateDayWheel.getCurrentItem() + 1;
      
      if ( calendar.getActualMaximum( Calendar.DAY_OF_MONTH ) < day )
         day = calendar.getActualMaximum( Calendar.DAY_OF_MONTH );
      
      calendar.set( Calendar.DAY_OF_MONTH, day );
   }
   


   private void initDaysWheel( Context context )
   {
      dateDayWheel.setViewAdapter( new DateFormatWheelTextAdapter( context,
                                                                   calendar,
                                                                   Calendar.DAY_OF_MONTH,
                                                                   "d",
                                                                   DateFormatWheelTextAdapter.TYPE_SHOW_WEEKDAY,
                                                                   DateFormatWheelTextAdapter.FLAG_MARK_TODAY ) );
      dateDayWheel.setCurrentItem( calendar.get( Calendar.DAY_OF_MONTH ) - 1 );
   }
   


   private void initMonthsWheel( Context context )
   {
      dateMonthWheel.setViewAdapter( new DateFormatWheelTextAdapter( context,
                                                                     calendar,
                                                                     Calendar.MONTH,
                                                                     "MMM",
                                                                     DateFormatWheelTextAdapter.TYPE_DEFAULT,
                                                                     0 ) );
      dateMonthWheel.setCurrentItem( calendar.get( Calendar.MONTH ) );
   }
   


   private void initYearsWheel( Context context )
   {
      dateYearWheel.setViewAdapter( new NumericWheelAdapter( context,
                                                             calendar.getMinimum( Calendar.YEAR ),
                                                             calendar.getMaximum( Calendar.YEAR ) ) );
      dateYearWheel.setCurrentItem( calendar.get( Calendar.YEAR ) - 1 );
   }
   
   
   private class TimeWheelGroup implements OnWheelScrollListener
   {
      private final WheelView[] wheelViews = new WheelView[ 3 ];
      
      private final int offIndex;
      
      private final int[] lastNonOffIndex =
      { -1, -1, -1 };
      
      private final int[] lastIndex =
      { 0, 0, 0 };
      
      private boolean notifying;
      
      private boolean hasTime;
      
      

      public TimeWheelGroup( Context context, View view, int offIndex,
         boolean hasTime )
      {
         this.offIndex = offIndex;
         this.hasTime = hasTime;
         
         wheelViews[ 0 ] = (WheelView) view.findViewById( R.id.due_dlg_time_wheel_hour );
         wheelViews[ 1 ] = (WheelView) view.findViewById( R.id.due_dlg_time_wheel_minute );
         wheelViews[ 2 ] = (WheelView) view.findViewById( R.id.due_dlg_time_wheel_am_pm );
         
         initHourWheel( context );
         initMinuteWheel( context );
         initAmPmWheel( context );
         
         setLastIndex( wheelViews[ 0 ] );
         setLastIndex( wheelViews[ 1 ] );
         setLastIndex( wheelViews[ 2 ] );
         
         wheelViews[ 0 ].addScrollingListener( this );
         wheelViews[ 1 ].addScrollingListener( this );
         wheelViews[ 2 ].addScrollingListener( this );
      }
      


      public void onScrollingStarted( WheelView wheel )
      {
      }
      


      public void onScrollingFinished( WheelView wheel )
      {
         if ( !notifying )
         {
            // a wheel has been put into off
            if ( wheel.getCurrentItem() == offIndex )
            {
               // put other wheels into off
               notifying = true;
               setOtherWheelsOff( wheel );
               notifying = false;
            }
            // a wheel has been put from off to on
            else if ( getLastIndex( wheel ) == offIndex )
            {
               // put other wheels into on
               notifying = true;
               restoreOtherWheels( wheel );
               notifying = false;
            }
         }
         
         setLastIndex( wheel );
      }
      


      public Calendar putTime( Calendar cal )
      {
         if ( !hasTime )
            return MolokoDateUtils.clearTime( cal );
         else
         {
            if ( MolokoApp.getSettings().getTimeformat() == Settings.TIMEFORMAT_24 )
               cal.set( Calendar.HOUR_OF_DAY,
                        wheelViews[ 0 ].getCurrentItem() - 1 );
            else
               cal.set( Calendar.HOUR, wheelViews[ 0 ].getCurrentItem() - 1 );
            
            cal.set( Calendar.MINUTE, wheelViews[ 1 ].getCurrentItem() - 1 );
            
            if ( MolokoApp.getSettings().getTimeformat() == Settings.TIMEFORMAT_12 )
               cal.set( Calendar.AM_PM, wheelViews[ 2 ].getCurrentItem() - 1 );
            else
               cal.clear( Calendar.AM_PM );
            
            cal.set( Calendar.SECOND, 0 );
            cal.set( Calendar.MILLISECOND, 0 );
            
            return cal;
         }
      }
      


      private void initHourWheel( Context context )
      {
         if ( MolokoApp.getSettings().getTimeformat() == Settings.TIMEFORMAT_24 )
         {
            wheelViews[ 0 ].setViewAdapter( new DueTimeWheelTextAdapter( context,
                                                                         calendar,
                                                                         DueTimeWheelTextAdapter.TYPE_HOUR_OF_DAY ) );
            if ( !hasTime || !calendar.isSet( Calendar.HOUR_OF_DAY ) )
               wheelViews[ 0 ].setCurrentItem( offIndex );
            else
               wheelViews[ 0 ].setCurrentItem( calendar.get( Calendar.HOUR_OF_DAY ) + 1 );
         }
         else
         {
            wheelViews[ 0 ].setViewAdapter( new DueTimeWheelTextAdapter( context,
                                                                         calendar,
                                                                         DueTimeWheelTextAdapter.TYPE_HOUR ) );
            if ( !hasTime || !calendar.isSet( Calendar.HOUR ) )
               wheelViews[ 0 ].setCurrentItem( offIndex );
            else
               wheelViews[ 0 ].setCurrentItem( calendar.get( Calendar.HOUR ) + 1 );
         }
      }
      


      private void initMinuteWheel( Context context )
      {
         wheelViews[ 1 ].setViewAdapter( new DueTimeWheelTextAdapter( context,
                                                                      calendar,
                                                                      DueTimeWheelTextAdapter.TYPE_MINUTE ) );
         if ( !hasTime || !calendar.isSet( Calendar.MINUTE ) )
            wheelViews[ 1 ].setCurrentItem( offIndex );
         else
            wheelViews[ 1 ].setCurrentItem( calendar.get( Calendar.MINUTE ) + 1 );
      }
      


      private void initAmPmWheel( Context context )
      {
         if ( MolokoApp.getSettings().getTimeformat() == Settings.TIMEFORMAT_12 )
         {
            wheelViews[ 2 ].setViewAdapter( new DueTimeWheelTextAdapter( context,
                                                                         calendar,
                                                                         DueTimeWheelTextAdapter.TYPE_AM_PM ) );
            
            if ( !hasTime || !calendar.isSet( Calendar.AM_PM ) )
               wheelViews[ 2 ].setCurrentItem( offIndex );
            else
               wheelViews[ 2 ].setCurrentItem( calendar.get( Calendar.AM_PM ) + 1 );
         }
         else
         {
            wheelViews[ 2 ].setCurrentItem( offIndex );
            wheelViews[ 2 ].setVisibility( View.GONE );
         }
      }
      


      private void setOtherWheelsOff( WheelView wheel )
      {
         final int wheelIdx = getWheelIndex( wheel );
         
         for ( int i = 1; wheelIdx != -1 && i < wheelViews.length - 1; i++ )
            wheelViews[ ( wheelIdx + i ) % wheelViews.length ].setCurrentItem( offIndex,
                                                                               true );
      }
      


      private void restoreOtherWheels( WheelView wheel )
      {
         final int wheelIdx = getWheelIndex( wheel );
         
         for ( int i = 1; wheelIdx != -1 && i < wheelViews.length - 1; i++ )
         {
            final int idx = ( wheelIdx + i ) % wheelViews.length;
            final WheelView otherWheel = wheelViews[ idx ];
            
            if ( otherWheel.getVisibility() != View.GONE )
            {
               if ( lastNonOffIndex[ idx ] != -1 )
                  otherWheel.setCurrentItem( lastNonOffIndex[ idx ], true );
               else
                  otherWheel.setCurrentItem( offIndex + 1, true );
            }
         }
      }
      


      private int getWheelIndex( WheelView wheel )
      {
         for ( int i = 0; i < wheelViews.length; i++ )
            if ( wheelViews[ i ] == wheel )
               return i;
         
         return -1;
      }
      


      private void setLastIndex( WheelView wheel )
      {
         final int wheelIdx = getWheelIndex( wheel );
         
         if ( wheelIdx != -1 )
         {
            lastIndex[ wheelIdx ] = wheel.getCurrentItem();
            
            if ( lastIndex[ wheelIdx ] != offIndex )
            {
               lastNonOffIndex[ wheelIdx ] = lastIndex[ wheelIdx ];
               hasTime = true;
            }
            else
            {
               hasTime = false;
            }
         }
      }
      


      private int getLastIndex( WheelView wheel )
      {
         final int wheelIdx = getWheelIndex( wheel );
         
         if ( wheelIdx != -1 )
            return lastIndex[ wheelIdx ];
         else
            return -1;
      }
   }
}

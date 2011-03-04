/*
 * Copyright 2007, MetaDimensional Technologies Inc.
 * 
 * 
 * This file is part of the RememberTheMilk Java API.
 * 
 * The RememberTheMilk Java API is free software; you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 * 
 * The RememberTheMilk Java API is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mdt.rtm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import dev.drsoran.rtm.ParcelableDate;


/**
 * 
 * @author Will Ross Jun 22, 2007
 */
public class RtmTaskList extends RtmData
{
   @SuppressWarnings( "unused" )
   private static final String TAG = "Moloko."
      + RtmTaskList.class.getSimpleName();
   
   
   private static final class LessIdComperator implements
            Comparator< RtmTaskList >
   {
      public int compare( RtmTaskList object1, RtmTaskList object2 )
      {
         return object1.id.compareTo( object2.id );
      }
      
   }
   
   public static final Parcelable.Creator< RtmTaskList > CREATOR = new Parcelable.Creator< RtmTaskList >()
   {
      
      public RtmTaskList createFromParcel( Parcel source )
      {
         return new RtmTaskList( source );
      }
      


      public RtmTaskList[] newArray( int size )
      {
         return new RtmTaskList[ size ];
      }
      
   };
   
   public final static LessIdComperator LESS_ID = new LessIdComperator();
   
   private final String id;
   
   private final List< RtmTaskSeries > series;
   
   private final ParcelableDate current;
   
   

   public RtmTaskList( String id )
   {
      this.id = id;
      
      // do not use Collections.emptyList() here cause we
      // need a mutable list.
      this.series = new ArrayList< RtmTaskSeries >( 0 );
      this.current = null;
   }
   


   public RtmTaskList( RtmTaskList other )
   {
      this.id = other.id;
      this.series = new ArrayList< RtmTaskSeries >( other.series );
      this.current = other.current;
   }
   


   public RtmTaskList( Parcel source )
   {
      id = source.readString();
      series = source.createTypedArrayList( RtmTaskSeries.CREATOR );
      current = source.readParcelable( null );
   }
   


   public RtmTaskList( Element elt )
   {
      id = elt.getAttribute( "id" );
      
      final List< Element > children = children( elt, "taskseries" );
      final List< Element > deleted = children( elt, "deleted" );
      
      series = new ArrayList< RtmTaskSeries >( children.size() + deleted.size() );
      
      // Look for taskseries element
      for ( Element seriesElt : children )
      {
         series.add( new RtmTaskSeries( seriesElt, id ) );
      }
      
      // There may also be 'deleted' elements in which are 'taskseries'
      // elements
      for ( Element deletedElement : deleted )
      {
         for ( Element seriesElt : children( deletedElement, "taskseries" ) )
         {
            series.add( new RtmTaskSeries( seriesElt, id, true ) );
         }
      }
      
      current = parseParcableDate( textNullIfEmpty( elt, "current" ) );
      
      if ( TextUtils.isEmpty( id ) )
      {
         throw new IllegalStateException( "No id found in task list." );
      }
   }
   


   public String getId()
   {
      return id;
   }
   


   public List< RtmTaskSeries > getSeries()
   {
      return Collections.unmodifiableList( series );
   }
   


   public void sortSeries()
   {
      Collections.sort( series, RtmTaskSeries.LESS_ID );
   }
   


   public void add( RtmTaskSeries taskSeries )
   {
      series.add( taskSeries );
   }
   


   public void addGeneratedSeries( Element elt )
   {
      final List< Element > generated = children( elt, "taskseries" );
      
      if ( generated.size() > 0 )
      {
         // Find the first non-deleted TaskSeries
         for ( RtmTaskSeries taskSeries : series )
         {
            if ( taskSeries.getDeletedDate() == null )
            {
               for ( Element element : generated )
               {
                  series.add( RtmTaskSeries.newGenerated( element, taskSeries ) );
               }
               
               return;
            }
         }
      }
   }
   


   public void update( RtmTaskList taskList )
   {
      if ( taskList == null )
         throw new NullPointerException( "taskList is null" );
      
      for ( RtmTaskSeries taskSeries : taskList.getSeries() )
      {
         // If the set already contains an element in respect to the Comparator,
         // then we update it by the new.
         final int pos = Collections.binarySearch( series,
                                                   taskSeries,
                                                   RtmTaskSeries.LESS_ID );
         if ( pos >= 0 )
         {
            series.remove( pos );
            series.add( pos, taskSeries );
         }
         else
         {
            series.add( ( -pos - 1 ), taskSeries );
         }
      }
   }
   


   public Date getCurrent()
   {
      return ( current != null ) ? current.getDate() : null;
   }
   


   public int describeContents()
   {
      return 0;
   }
   


   public void writeToParcel( Parcel dest, int flags )
   {
      dest.writeString( id );
      dest.writeTypedList( series );
      dest.writeParcelable( current, flags );
   }
   


   @Override
   public String toString()
   {
      return "RtmTaskList<" + id + ",#" + series.size()
         + ( ( current != null ) ? "," + current.getDate() : "" ) + ">";
   }
}

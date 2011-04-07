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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import dev.drsoran.moloko.util.Strings;
import dev.drsoran.moloko.util.parsing.RecurrenceParsing;
import dev.drsoran.provider.Rtm.TaskSeries;
import dev.drsoran.rtm.ParcelableDate;
import dev.drsoran.rtm.ParticipantList;
import dev.drsoran.rtm.RtmEntity;


/**
 * 
 * @author Will Ross Jun 22, 2007
 */
public class RtmTaskSeries extends RtmEntity
{
   private final static String TAG = "Moloko."
      + RtmTaskSeries.class.getSimpleName();
   
   public static final Parcelable.Creator< RtmTaskSeries > CREATOR = new Parcelable.Creator< RtmTaskSeries >()
   {
      public RtmTaskSeries createFromParcel( Parcel source )
      {
         return new RtmTaskSeries( source );
      }
      


      public RtmTaskSeries[] newArray( int size )
      {
         return new RtmTaskSeries[ size ];
      }
   };
   
   private final long listId;
   
   private final String rtmListId;
   
   private final ParcelableDate created;
   
   private final ParcelableDate modified;
   
   private final String name;
   
   private final String source;
   
   private final List< RtmTask > tasks;
   
   private final RtmTaskNotes notes;
   
   private final long locationId;
   
   private final String rtmLocationId;
   
   private final String url;
   
   private final String recurrence;
   
   private final boolean isEveryRecurrence;
   
   private final List< String > tags;
   
   private final ParticipantList participants;
   
   

   public RtmTaskSeries( long id, String rtmId, long listId, String rtmListId,
      Date created, Date modified, String name, String source,
      List< RtmTask > tasks, RtmTaskNotes notes, long locationId,
      String rtmLocationId, String url, String recurrence,
      boolean isEveryRecurrence, String tags, ParticipantList participants )
   {
      this( id,
            rtmId,
            listId,
            rtmListId,
            created,
            modified,
            name,
            source,
            tasks,
            notes,
            locationId,
            rtmLocationId,
            url,
            recurrence,
            isEveryRecurrence,
            tags != null
                        ? Arrays.asList( TextUtils.split( tags,
                                                          TaskSeries.TAGS_SEPARATOR ) )
                        : Collections.< String > emptyList(),
            participants );
   }
   


   public RtmTaskSeries( long id, String rtmId, long listId, String rtmListId,
      Date created, Date modified, String name, String source,
      List< RtmTask > tasks, RtmTaskNotes notes, long locationId,
      String rtmLocationId, String url, String recurrence,
      boolean isEveryRecurrence, List< String > tags,
      ParticipantList participants )
   {
      super( id, rtmId );
      
      this.listId = listId;
      this.rtmListId = rtmListId;
      this.created = ( created != null ) ? new ParcelableDate( created ) : null;
      this.modified = ( modified != null ) ? new ParcelableDate( modified )
                                          : null;
      this.name = name;
      this.source = source;
      this.tasks = new ArrayList< RtmTask >( tasks );
      this.notes = notes;
      this.locationId = locationId;
      this.rtmLocationId = rtmLocationId;
      this.url = url;
      this.recurrence = recurrence;
      this.isEveryRecurrence = isEveryRecurrence;
      this.tags = tags;
      this.participants = participants == null ? new ParticipantList( rtmId )
                                              : participants;
   }
   


   public RtmTaskSeries( Element elt, String rtmListId )
   {
      super( textNullIfEmpty( elt, "id" ) );
      
      this.listId = RtmEntity.NO_ID;
      this.rtmListId = rtmListId;
      this.created = parseParcableDate( elt.getAttribute( "created" ) );
      this.modified = parseParcableDate( elt.getAttribute( "modified" ) );
      this.name = textNullIfEmpty( elt, "name" );
      this.source = textNullIfEmpty( elt, "source" );
      
      final Element recurrenceRule = child( elt, "rrule" );
      
      String recurrence = null;
      boolean isEveryRecurrence = false;
      
      if ( recurrenceRule != null
         && recurrenceRule.getChildNodes().getLength() > 0 )
      {
         recurrence = textNullIfEmpty( recurrenceRule );
         
         try
         {
            isEveryRecurrence = Integer.parseInt( textNullIfEmpty( recurrenceRule,
                                                                   "every" ) ) != 0;
            recurrence = RecurrenceParsing.ensureRecurrencePatternOrder( recurrence );
         }
         catch ( NumberFormatException nfe )
         {
            recurrence = null;
            isEveryRecurrence = false;
            
            Log.e( TAG, "Error reading recurrence pattern from XML", nfe );
         }
      }
      
      this.recurrence = recurrence;
      this.isEveryRecurrence = isEveryRecurrence;
      
      final List< Element > tasks = children( elt, "task" );
      this.tasks = new ArrayList< RtmTask >( tasks.size() );
      
      for ( Element task : tasks )
      {
         this.tasks.add( new RtmTask( task, rtmId ) );
      }
      
      this.notes = new RtmTaskNotes( child( elt, "notes" ), rtmId );
      this.locationId = RtmEntity.NO_ID;
      this.rtmLocationId = textNullIfEmpty( elt, "location_id" );
      this.url = textNullIfEmpty( elt, "url" );
      
      final Element elementTags = child( elt, "tags" );
      this.tags = new ArrayList< String >();
      
      if ( elementTags.getChildNodes().getLength() > 0 )
      {
         final List< Element > elementTagList = children( elementTags, "tag" );
         
         for ( Element elementTag : elementTagList )
         {
            final String tag = text( elementTag );
            if ( !TextUtils.isEmpty( tag ) )
            {
               this.tags.add( tag );
            }
         }
      }
      
      final Element elementParticipants = child( elt, "participants" );
      
      if ( elementParticipants.getChildNodes().getLength() > 0 )
         this.participants = new ParticipantList( rtmId, elementParticipants );
      else
         this.participants = new ParticipantList( rtmId );
   }
   


   public RtmTaskSeries( Element elt, String rtmListId, boolean deleted )
   {
      super( textNullIfEmpty( elt, "id" ) );
      
      listId = RtmEntity.NO_ID;
      this.rtmListId = rtmListId;
      created = null;
      modified = null;
      name = null;
      
      final List< Element > tasks = children( elt, "task" );
      this.tasks = new ArrayList< RtmTask >( tasks.size() );
      
      for ( Element task : tasks )
      {
         this.tasks.add( new RtmTask( task, rtmId, deleted ) );
      }
      
      source = null;
      locationId = RtmEntity.NO_ID;
      rtmLocationId = null;
      notes = null;
      url = null;
      recurrence = null;
      isEveryRecurrence = false;
      tags = null;
      participants = null;
   }
   


   public RtmTaskSeries( Parcel source )
   {
      super( source );
      
      listId = source.readLong();
      rtmListId = source.readString();
      created = source.readParcelable( null );
      modified = source.readParcelable( null );
      name = source.readString();
      this.source = source.readString();
      tasks = source.createTypedArrayList( RtmTask.CREATOR );
      notes = new RtmTaskNotes( source );
      locationId = source.readLong();
      rtmLocationId = source.readString();
      url = source.readString();
      recurrence = source.readString();
      isEveryRecurrence = source.readInt() != 0;
      tags = source.createStringArrayList();
      participants = source.readParcelable( null );
   }
   


   public long getListId()
   {
      return listId;
   }
   


   public String getRtmListId()
   {
      return rtmListId;
   }
   


   public Date getCreatedDate()
   {
      return ( created != null ) ? created.getDate() : null;
   }
   


   public Date getModifiedDate()
   {
      return ( modified != null ) ? modified.getDate() : null;
   }
   


   public Date getDeletedDate()
   {
      return tasks.get( 0 ).getDeletedDate();
   }
   


   public String getName()
   {
      return name;
   }
   


   public String getSource()
   {
      return source;
   }
   


   public List< RtmTask > getTasks()
   {
      return tasks;
   }
   


   public RtmTask getTask( String rtmId )
   {
      for ( RtmTask task : tasks )
         if ( task.getRtmId().equals( rtmId ) )
            return task;
      
      return null;
   }
   


   public RtmTaskNotes getNotes()
   {
      return notes == null ? new RtmTaskNotes() : notes;
   }
   


   public List< String > getTags()
   {
      if ( tags == null )
         return Collections.emptyList();
      else
         return tags;
   }
   


   public String getTagsJoined()
   {
      if ( !hasTags() )
         return Strings.EMPTY_STRING;
      else
         return TextUtils.join( TaskSeries.TAGS_SEPARATOR, tags );
   }
   


   public boolean hasTags()
   {
      return tags != null && !tags.isEmpty();
   }
   


   public ParticipantList getParticipants()
   {
      return participants == null ? new ParticipantList( rtmId ) : participants;
   }
   


   public long getLocationId()
   {
      return locationId;
   }
   


   public String getRtmLocationId()
   {
      return rtmLocationId;
   }
   


   public String getURL()
   {
      return url;
   }
   


   public String getRecurrence()
   {
      return recurrence;
   }
   


   public boolean isEveryRecurrence()
   {
      return isEveryRecurrence;
   }
   


   @Override
   public int describeContents()
   {
      return 0;
   }
   


   @Override
   public void writeToParcel( Parcel dest, int flags )
   {
      super.writeToParcel( dest, flags );
      
      dest.writeLong( listId );
      dest.writeString( rtmListId );
      dest.writeParcelable( created, flags );
      dest.writeParcelable( modified, flags );
      dest.writeString( name );
      dest.writeString( source );
      dest.writeTypedList( tasks );
      notes.writeToParcel( dest, flags );
      dest.writeLong( locationId );
      dest.writeString( rtmLocationId );
      dest.writeString( url );
      dest.writeString( recurrence );
      dest.writeInt( isEveryRecurrence ? 1 : 0 );
      dest.writeStringList( tags );
      dest.writeParcelable( participants, flags );
   }
   


   @Override
   public String toString()
   {
      return "TaskSeries<" + super.toString() + "," + name + ">";
   }
}

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
package com.mdt.rtm;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Encapsulates information about an application that is a client of
 * RememberTheMilk. Includes information required by
 * RTM to connect: the API key and the shared secret.
 * 
 * @author Will Ross Jun 22, 2007
 */
public class ApplicationInfo implements Parcelable
{
   
   private final String apiKey;
   
   private final String sharedSecret;
   
   private final String name;
   
   private final String authToken;
   
   public static final Parcelable.Creator< ApplicationInfo > CREATOR = new Parcelable.Creator< ApplicationInfo >()
   {
      
      public ApplicationInfo createFromParcel( Parcel source )
      {
         return new ApplicationInfo( source );
      }
      


      public ApplicationInfo[] newArray( int size )
      {
         return new ApplicationInfo[ size ];
      }
      
   };
   
   

   public ApplicationInfo( String apiKey, String sharedSecret, String name )
   {
      this( apiKey, sharedSecret, name, null );
   }
   


   public ApplicationInfo( String apiKey, String sharedSecret, String name,
      String authToken )
   {
      super();
      this.apiKey = apiKey;
      this.sharedSecret = sharedSecret;
      this.name = name;
      this.authToken = authToken;
   }
   


   public ApplicationInfo( Parcel source )
   {
      this.apiKey = source.readString();
      this.sharedSecret = source.readString();
      this.name = source.readString();
      this.authToken = source.readString();
   }
   


   public String getApiKey()
   {
      return apiKey;
   }
   


   public String getSharedSecret()
   {
      return sharedSecret;
   }
   


   public String getName()
   {
      return name;
   }
   


   public String getAuthToken()
   {
      return authToken;
   }
   


   public void writeToParcel( Parcel dest, int flags )
   {
      dest.writeString( authToken );
      dest.writeString( sharedSecret );
      dest.writeString( name );
      dest.writeString( authToken );
   }
   


   public int describeContents()
   {
      return 0;
   }
}

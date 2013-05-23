/* 
 *	Copyright (c) 2010 Ronny R�hricht
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

package dev.drsoran.moloko.domain.model;

import dev.drsoran.moloko.content.Constants;
import dev.drsoran.moloko.util.Strings;


public class Participant
{
   private final long id;
   
   private final long contactId;
   
   private final String fullname;
   
   private final String username;
   
   
   
   public Participant( long id, long contactId, String fullname, String username )
   {
      if ( id == Constants.NO_ID )
      {
         throw new IllegalArgumentException( "id" );
      }
      
      if ( contactId == Constants.NO_ID )
      {
         throw new IllegalArgumentException( "contactId" );
      }
      
      if ( fullname == null )
      {
         throw new IllegalArgumentException( "fullname" );
      }
      
      if ( Strings.isNullOrEmpty( username ) )
      {
         throw new IllegalArgumentException( "username" );
      }
      
      this.id = id;
      this.contactId = contactId;
      this.fullname = fullname;
      this.username = username;
   }
   
   
   
   public long getId()
   {
      return id;
   }
   
   
   
   public long getContactId()
   {
      return contactId;
   }
   
   
   
   public String getFullname()
   {
      return fullname;
   }
   
   
   
   public String getUsername()
   {
      return username;
   }
   
   
   
   @Override
   public String toString()
   {
      return String.format( "Participant [id=%s, contactId=%s, fullname=%s, username=%s]",
                            id,
                            contactId,
                            fullname,
                            username );
   }
}

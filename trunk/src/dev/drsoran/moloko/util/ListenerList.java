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

package dev.drsoran.moloko.util;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;


public class ListenerList< T >
{
   private final static String TAG = ListenerList.class.getSimpleName();
   
   public final Method method;
   
   
   public static class MessgageObject< T >
   {
      public final Class< T > type;
      
      public final Object oldValue;
      
      

      public MessgageObject( Class< T > type, Object oldValue )
      {
         this.type = type;
         this.oldValue = oldValue;
      }
   }
   

   private final class ListenerEntry
   {
      public final int mask;
      
      public final WeakReference< T > listener;
      
      

      public ListenerEntry( int mask, T listener )
      {
         this.mask = mask;
         this.listener = new WeakReference< T >( listener );
      }
      


      boolean isDead()
      {
         return listener.get() == null;
      }
      


      boolean matches( int setting )
      {
         return ( ( mask & setting ) != 0 );
      }
      


      void notifyEmpty()
      {
         if ( listener.get() != null )
         {
            try
            {
               method.invoke( listener.get() );
            }
            catch ( IllegalArgumentException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( IllegalAccessException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( InvocationTargetException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
         }
      }
      


      void notify( int mask )
      {
         if ( listener.get() != null )
         {
            try
            {
               method.invoke( listener.get(), mask );
            }
            catch ( IllegalArgumentException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( IllegalAccessException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( InvocationTargetException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
         }
      }
      


      void notify( int mask, HashMap< Integer, Object > oldValues )
      {
         if ( listener.get() != null )
         {
            try
            {
               method.invoke( listener.get(), mask, oldValues );
            }
            catch ( IllegalArgumentException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( IllegalAccessException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( InvocationTargetException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
         }
      }
      


      boolean notifyIfMatches( int mask, HashMap< Integer, Object > oldValues )
      {
         boolean ok = !isDead();
         
         if ( ok && matches( mask ) )
            try
            {
               method.invoke( listener.get(), mask, oldValues );
            }
            catch ( IllegalArgumentException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( IllegalAccessException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
            catch ( InvocationTargetException e )
            {
               Log.e( TAG, Strings.EMPTY_STRING, e );
            }
         
         return ok;
      }
   }
   
   // TODO: No check for double registration, no check for registration of same
   // listener
   // with different mask. In these cases the listener gets notified multiple
   // times.
   private final ArrayList< ListenerEntry > listeners = new ArrayList< ListenerEntry >();
   
   

   public ListenerList( Method method )
   {
      this.method = method;
   }
   


   public void registerListener( int which, T listener )
   {
      if ( listener != null )
      {
         listeners.add( new ListenerEntry( which, listener ) );
      }
   }
   


   public void unregisterListener( T listener )
   {
      if ( listener != null )
      {
         removeListener( listener );
      }
   }
   


   public void notifyListeners()
   {
      for ( Iterator< ListenerEntry > i = listeners.iterator(); i.hasNext(); )
      {
         ListenerEntry entry = i.next();
         
         // Check if we have a dead entry
         if ( entry.isDead() )
            i.remove();
         else
         {
            entry.notifyEmpty();
         }
      }
   }
   


   public void notifyListeners( int mask )
   {
      for ( Iterator< ListenerEntry > i = listeners.iterator(); i.hasNext(); )
      {
         ListenerEntry entry = i.next();
         
         // Check if we have a dead entry
         if ( entry.isDead() )
            i.remove();
         else if ( entry.matches( mask ) )
         {
            entry.notify( mask );
         }
      }
   }
   


   public void notifyListeners( int mask, Object oldValue )
   {
      if ( mask > 0 )
      {
         for ( Iterator< ListenerEntry > i = listeners.iterator(); i.hasNext(); )
         {
            ListenerEntry entry = i.next();
            
            // Check if we have a dead entry
            if ( entry.isDead() )
               i.remove();
            else if ( entry.matches( mask ) )
            {
               final HashMap< Integer, Object > oldValues = new HashMap< Integer, Object >( 1 );
               oldValues.put( mask, oldValue );
               entry.notify( mask, oldValues );
            }
         }
      }
   }
   


   public void notifyListeners( int mask, HashMap< Integer, Object > oldValues )
   {
      if ( mask > 0 )
      {
         for ( Iterator< ListenerEntry > i = listeners.iterator(); i.hasNext(); )
         {
            ListenerEntry entry = i.next();
            
            // Check if we have a dead entry
            if ( !entry.notifyIfMatches( mask, oldValues ) )
            {
               i.remove();
            }
         }
      }
   }
   


   public boolean removeListener( T listener )
   {
      final int size = listeners.size();
      
      for ( int i = 0; i < size; i++ )
      {
         if ( listener == listeners.get( i ).listener )
         {
            listeners.remove( i );
            return true;
         }
      }
      
      return false;
   }
}
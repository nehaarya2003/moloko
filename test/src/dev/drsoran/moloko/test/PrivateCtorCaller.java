/* 
 *	Copyright (c) 2013 Ronny R�hricht
 *
 *	This file is part of MolokoTest.
 *
 *	MolokoTest is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	MolokoTest is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with MolokoTest.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	Contributors:
 * Ronny R�hricht - implementation
 */

package dev.drsoran.moloko.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class PrivateCtorCaller
{
   public final static < T > void callPrivateCtor( Class< T > clazz ) throws Throwable
   {
      final Constructor< T > ctor = clazz.getDeclaredConstructor( (Class< ? >[]) null );
      ctor.setAccessible( true );
      
      try
      {
         ctor.newInstance( (Object[]) null );
      }
      catch ( InvocationTargetException e )
      {
         throw e.getCause();
      }
   }
}
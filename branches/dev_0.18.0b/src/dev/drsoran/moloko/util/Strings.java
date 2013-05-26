/*
 * Copyright (c) 2012 Ronny R�hricht
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

package dev.drsoran.moloko.util;

import java.text.MessageFormat;

import android.text.Editable;


public final class Strings
{
   public final static String EMPTY_STRING = "";
   
   
   
   private Strings()
   {
      throw new AssertionError( "This class should not be instantiated." );
   }
   
   
   
   public static boolean isQuotified( String input )
   {
      if ( input == null )
      {
         throw new IllegalArgumentException( "input" );
      }
      
      return input.matches( "\"(?:[^\"\\\\]|\\\\.)*\"" );
   }
   
   
   
   public static String unquotify( String input )
   {
      if ( input == null )
      {
         throw new IllegalArgumentException( "input" );
      }
      
      return input.replaceAll( "(\")", "" );
   }
   
   
   
   public static String quotify( String input )
   {
      if ( input == null )
      {
         throw new IllegalArgumentException( "input" );
      }
      
      return MessageFormat.format( "\"{0}\"", input );
   }
   
   
   
   @Deprecated
   // TODO: This method does not belongs to Strings class. This is UI scope
   public final static String getTrimmed( Editable editable )
   {
      return editable.toString().trim();
   }
   
   
   
   public final static String emptyIfNull( String input )
   {
      return ( input == null ) ? EMPTY_STRING : input;
   }
   
   
   
   public final static CharSequence emptyIfNull( CharSequence input )
   {
      return ( input == null ) ? EMPTY_STRING : input;
   }
   
   
   
   public final static String nullIfEmpty( String input )
   {
      return (String) nullIfEmpty( (CharSequence) input );
   }
   
   
   
   public final static CharSequence nullIfEmpty( CharSequence input )
   {
      if ( input == null )
      {
         throw new IllegalArgumentException( "input" );
      }
      
      return input.length() == 0 ? null : input;
   }
   
   
   
   public final static boolean equalsNullAware( String lhs, String rhs )
   {
      return ( lhs == rhs ) || ( lhs != null && lhs.equals( rhs ) );
   }
   
   
   
   public static boolean isNullOrEmpty( CharSequence charSequence )
   {
      return charSequence == null || charSequence.length() == 0;
   }
   
   
   
   public static < T > T convertTo( String value, Class< T > valueClass ) throws NumberFormatException
   {
      Object converted;
      
      if ( value == null )
      {
         converted = null;
      }
      
      else if ( valueClass == String.class )
      {
         converted = value;
      }
      
      else if ( valueClass == Long.class || valueClass == long.class )
      {
         converted = Long.parseLong( value );
      }
      
      else if ( valueClass == Integer.class || valueClass == int.class )
      {
         converted = Integer.parseInt( value );
      }
      
      else if ( valueClass == Boolean.class || valueClass == boolean.class )
      {
         converted = Boolean.parseBoolean( value );
      }
      
      else if ( valueClass == Float.class || valueClass == float.class )
      {
         converted = Float.parseFloat( value );
      }
      
      else if ( valueClass == Double.class || valueClass == double.class )
      {
         converted = Double.parseDouble( value );
      }
      
      else
      {
         throw new IllegalArgumentException( "The type " + valueClass.getName()
            + " is not supported" );
      }
      
      @SuppressWarnings( "unchecked" )
      final T casted = (T) converted;
      return casted;
   }
   
   
   
   public static < T > String convertFrom( T value )
   {
      if ( value == null )
      {
         return null;
      }
      
      Class< ? > valueClass = value.getClass();
      String converted;
      if ( valueClass == String.class )
      {
         converted = (String) value;
      }
      
      else if ( valueClass == Long.class )
      {
         converted = Long.toString( (Long) value );
      }
      
      else if ( valueClass == Integer.class )
      {
         converted = Integer.toString( (Integer) value );
      }
      
      else if ( valueClass == Boolean.class )
      {
         converted = Boolean.toString( (Boolean) value );
      }
      
      else if ( valueClass == Float.class )
      {
         converted = Float.toString( (Float) value );
      }
      
      else if ( valueClass == Double.class )
      {
         converted = Double.toString( (Double) value );
      }
      
      else
      {
         throw new IllegalArgumentException( "The type " + valueClass.getName()
            + " is not supported" );
      }
      
      return converted;
   }
}

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

package dev.drsoran.moloko.grammar;

import java.util.Locale;


public final class RecurrenceParserFactory
{
   private final static String GRAMMAR_PACKAGE_NAME = "dev.drsoran.moloko.grammar";
   
   

   private RecurrenceParserFactory()
   {
      throw new AssertionError();
   }
   


   public final static IRecurrenceParser createRecurrenceParserForLocale( Locale locale )
   {
      IRecurrenceParser parser = null;
      
      if ( locale.getVariant() != null && locale.getVariant().length() > 0 )
      {
         final String langCodePlusVariant = getLangCodeAndVariant( locale );
         
         parser = getParserInstanceForPackage( GRAMMAR_PACKAGE_NAME + "."
            + langCodePlusVariant );
      }
      
      if ( parser == null )
         parser = getParserInstanceForPackage( GRAMMAR_PACKAGE_NAME + "."
            + locale.getLanguage() );
      
      if ( parser == null )
      {
         final Package bestMatchPackage = getBestMatchingPackageForLangCode( locale.getLanguage() );
         
         if ( bestMatchPackage != null )
            parser = getParserInstanceForPackage( bestMatchPackage.getName() );
      }
      
      if ( parser == null )
         parser = getParserInstanceForPackage( GRAMMAR_PACKAGE_NAME + ".en" );
      
      return parser;
   }
   


   private final static String getLangCodeAndVariant( Locale locale )
   {
      return ( locale.getLanguage() + "_" + locale.getVariant() ).toLowerCase();
   }
   


   private final static IRecurrenceParser getParserInstanceForPackage( String packageName )
   {
      IRecurrenceParser instance = null;
      
      try
      {
         final Class< ? > classForPackage = Class.forName( packageName
            + ".RecurrenceParserImpl" );
         
         final Object o = classForPackage.newInstance();
         
         if ( o instanceof IRecurrenceParser )
            instance = (IRecurrenceParser) o;
      }
      catch ( Throwable e )
      {
      }
      
      return instance;
   }
   


   private static Package getBestMatchingPackageForLangCode( String langCode )
   {
      Package bestMatchingPackage = null;
      final Package[] allPackages = Package.getPackages();
      
      if ( allPackages != null )
      {
         for ( int i = 0, cnt = allPackages.length; i < cnt
            && bestMatchingPackage == null; ++i )
         {
            final Package pack = allPackages[ i ];
            if ( pack.getName().startsWith( GRAMMAR_PACKAGE_NAME + "."
               + langCode ) )
            {
               bestMatchingPackage = pack;
            }
         }
      }
      
      return bestMatchingPackage;
   }
}
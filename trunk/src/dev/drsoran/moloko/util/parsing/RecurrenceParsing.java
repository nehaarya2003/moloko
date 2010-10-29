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

package dev.drsoran.moloko.util.parsing;

import java.text.ParseException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import android.content.res.Resources;
import android.util.Log;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.grammar.RecurrenceLexer;
import dev.drsoran.moloko.grammar.RecurrenceParser;
import dev.drsoran.moloko.grammar.lang.RecurrPatternLanguage;


public final class RecurrenceParsing
{
   private final static String TAG = RecurrenceParsing.class.getSimpleName();
   
   private final static RecurrenceLexer lexer = new RecurrenceLexer();
   
   private final static RecurrenceParser parser = new RecurrenceParser();
   
   private static RecurrPatternLanguage lang;
   
   

   public final static void initPatternLanguage( Resources resources )
   {
      try
      {
         lang = new RecurrPatternLanguage( resources,
                                           R.xml.parser_lang_reccur_pattern );
      }
      catch ( ParseException e )
      {
         lang = null;
         Log.e( TAG, "Unable to initialize recurrence pattern language.", e );
      }
   }
   


   public synchronized final static String parseRecurrencePattern( String pattern,
                                                                   boolean isEvery )
   {
      String result = null;
      
      if ( lang != null )
      {
         lexer.setCharStream( new ANTLRStringStream( pattern ) );
         final CommonTokenStream antlrTokens = new CommonTokenStream( lexer );
         parser.setTokenStream( antlrTokens );
         
         try
         {
            result = parser.parseRecurrencePattern( lang, isEvery );
         }
         catch ( RecognitionException e )
         {
            result = null;
         }
      }
      else
         Log.e( TAG,
                "Unable to parse recurrence pattern due to missing language." );
      
      return result;
   }
}
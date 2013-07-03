/* 
 *	Copyright (c) 2013 Ronny R�hricht
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

package dev.drsoran.moloko.grammar.datetime;

import java.util.Locale;


public class DateTimeParserFactoryEn implements IDateTimeParserFactory
{
   @Override
   public IDateParser createDateParser()
   {
      return new DateParserImpl( Locale.ENGLISH,
                                 new DateParser(),
                                 new DateLexer() );
   }
   
   
   
   @Override
   public ITimeParser createTimeParser()
   {
      return new TimeParserImpl( Locale.ENGLISH,
                                 new TimeParser(),
                                 new TimeLexer() );
   }
   
   
   
   @Override
   public Locale getParserLocale()
   {
      return Locale.ENGLISH;
   }
}
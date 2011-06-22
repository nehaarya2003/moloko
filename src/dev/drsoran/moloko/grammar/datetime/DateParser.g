// en

parser grammar DateParser;

options
{
   language=Java;
   superClass=AbstractDateParser;
   tokenVocab=DateLexer;
}

@header
{
   package dev.drsoran.moloko.grammar.datetime;

   import java.text.ParseException;
   import java.text.SimpleDateFormat;
   import java.util.Calendar;
   
   import dev.drsoran.moloko.grammar.datetime.IDateParser.ParseDateReturn;
   import dev.drsoran.moloko.grammar.datetime.IDateParser.ParseDateWithinReturn;
   import dev.drsoran.moloko.grammar.datetime.AbstractDateParser;
   import dev.drsoran.moloko.grammar.LexerException;
   import dev.drsoran.moloko.util.MolokoCalendar;
}

@members
{
   public DateParser()
   {
      super( null );
   }
   
   
   protected int numberStringToNumber( String string )
   {
      switch( string.charAt( 0 ) )
      {
         case 'o' : return 1;
         case 't' :
         {
            switch( string.charAt( 1 ) )
            {
               case 'w' : return 2;
               case 'h' : return 3;
            }
         }
         case 'f' :
         {
            switch( string.charAt( 1 ) )
            {
               case 'o' : return 4;
               case 'i' : return 5;
            }
         }
         case 's' :
         {
            switch( string.charAt( 1 ) )
            {
               case 'i' : return 6;
               case 'e' : return 7;
            }
         }         
         case 'e' : return 8;
         case 'n' : return 9;
         default  : return 10;
      }      
   }
   
   protected int weekdayStringToNumber( String string )
   {
      switch( string.charAt( 0 ) )
      {
         case 'm': return Calendar.MONDAY;
         case 't':
           switch( string.charAt( 1 ) )
            {
               case 'u' : return Calendar.TUESDAY;
               case 'h' : return Calendar.THURSDAY;
            }
         case 'w': return Calendar.WEDNESDAY;
         case 's':
            switch( string.charAt( 1 ) )
            {
               case 'a' : return Calendar.SATURDAY;
               case 'u' : return Calendar.SUNDAY;
            }
         default : return Calendar.FRIDAY;
      }
   }
   
   protected int monthStringToNumber( String string )
   {
      switch( string.charAt( 0 ) )
      {
         case 'f': return Calendar.FEBRUARY;           
         case 'm':
         	switch( string.charAt( 2 ) )
            {
               case 'r' : return Calendar.MARCH;
               case 'y' : return Calendar.MAY;
            }
         case 'j':
            switch( string.charAt( 1 ) )
            {
               case 'a' : return Calendar.JANUARY;
               default  :
                  switch( string.charAt( 2 ) )
                  {
                     case 'n' : return Calendar.JUNE;
                     case 'l' : return Calendar.JULY;
                  }
            }      
         case 'a': 
            switch( string.charAt( 2 ) )
            {
               case 'p' : return Calendar.APRIL;
               case 'u' : return Calendar.AUGUST;
            }
         case 's': return Calendar.SEPTEMBER;
         case 'o': return Calendar.OCTOBER;
         case 'n': return Calendar.NOVEMBER;
         default : return Calendar.DECEMBER;
      }
   }
}

/** RULES **/


parseDate [MolokoCalendar cal, boolean clearTime] returns [ParseDateReturn result]
   @after
   {
      result = finishedDateParsing();
   }
   : (   date_full         [$cal]
       | date_on           [$cal]
       | date_in_X_YMWD    [$cal]
       | date_end_of_the_MW[$cal]
       | date_natural      [$cal])
   // It's important to clear the time fields
   // at last step cause the Calendar methods
   // will set them again.
   {
      cal.setHasDate( true );
      
      if ( clearTime )
         cal.setHasTime( false );      
   }
   | NOW
   {
      // In case of NOW we do not respect the clearTime Parameter
      // cause NOW has always a time.
      cal.setTimeInMillis( System.currentTimeMillis() );
      cal.setHasDate( true );
      cal.setHasTime( true );
   }
   | EOF
   ;
   catch [RecognitionException e]
   {
      throw e;
   }
   catch[ LexerException e ]
   {
      throw new RecognitionException();
   }

parseDateWithin[boolean past] returns [MolokoCalendar epochStart, MolokoCalendar epochEnd]
   @init
   {
      retval.epochStart = getCalendar();
      int amount        = 1;
      int unit          = Calendar.DAY_OF_YEAR;
   }
   @after
   {
      retval.epochEnd = getCalendar();
      retval.epochEnd.setTimeInMillis( retval.epochStart.getTimeInMillis() );
      retval.epochEnd.add( unit, past ? -amount : amount );      
   }
   : (  a=INT
        {
           amount = Integer.parseInt( $a.text );
        }
      | n=NUM_STR
        {
           amount = numberStringToNumber( $n.text );
        }
      | A)
      (  DAYS
         {
            unit = Calendar.DAY_OF_YEAR;
         }
       | WEEKS
         {
            unit = Calendar.WEEK_OF_YEAR;
         }
       | MONTHS
         {
            unit = Calendar.MONTH;
         }
       | YEARS
         {
            unit = Calendar.YEAR;
         }
      )?
      (OF parseDate[retval.epochStart, false])?
   ;
   catch [NumberFormatException e]
   {
      throw new RecognitionException();
   }
   catch [RecognitionException e]
   {
      throw e;
   }
   catch[ LexerException e ]
   {
      throw new RecognitionException();
   }

date_full [MolokoCalendar cal]
   @init
   {
      String pt1Str = null;
      String pt2Str = null;
      String pt3Str = null;
   }
   : pt1=INT
     {
        pt1Str = $pt1.getText();
     }
     pt2=INT
     {
        pt2Str = $pt2.getText();
     }
     (
        pt3=INT
        {
           pt3Str = $pt3.getText();
        }
     )?
     {
        handleFullDate( cal, pt1Str, pt2Str, pt3Str );
     }
     ;

date_on [MolokoCalendar cal]
   : (   date_on_Xst_of_M[$cal]
       | date_on_M_Xst   [$cal]
       | date_on_weekday [$cal])
   ;
   catch [RecognitionException e]
   {
      throw e;
   }

date_on_Xst_of_M [MolokoCalendar cal]
   @init
   {
      boolean hasMonth = false;
      boolean hasYear  = false;
   }
   : d=INT
       {
          cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( $d.text ) );
       }
     (m=MONTH
        {
           parseTextMonth( cal, $m.text );
           hasMonth = true;
        }      
      (y=INT
         {
            parseYear( cal, $y.text );
            hasYear = true;
         })?)?
   {
      handleDateOnXstOfMonth( cal, hasYear, hasMonth );
   }
   ;
   catch [NumberFormatException e]
   {
      throw new RecognitionException();
   }
   catch [RecognitionException e]
   {
      throw e;
   }

date_on_M_Xst [MolokoCalendar cal]
   @init
   {
      boolean hasYear = false;
   }
   : m=MONTH
       {
         parseTextMonth( cal, $m.text );
       }
    ( d=INT y=INT
        {
          cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt( $d.text ) );
          parseYear( cal, $y.text );
          hasYear = true;
        }
     |v=INT
        {
           final int value = Integer.parseInt( $v.text );
           if ( isInDayRange( cal, value ) )
           {
              cal.set( Calendar.DAY_OF_MONTH, value );
           }
           else
           {
              parseYear( cal, $v.text );
              hasYear = true;
           }
        }
    )?
   {
      handleDateOnXstOfMonth( cal, hasYear, true /*hasMonth*/ );
   }
   ;
   catch [NumberFormatException e]
   {
      throw new RecognitionException();
   }
   catch [RecognitionException e]
   {
      throw e;
   }

date_on_weekday [MolokoCalendar cal]
   @init
   {
      boolean nextWeek = false;
   }
   : (NEXT { nextWeek = true; })? wd=WEEKDAY
   {
      handleDateOnWeekday( cal, $wd.text, nextWeek );
   }
   ;
   catch [RecognitionException e]
   {
      throw e;
   }

date_in_X_YMWD [MolokoCalendar cal]
   : date_in_X_YMWD_distance[$cal]+     
   ;
   catch [RecognitionException e]
   {
      throw e;
   }

date_in_X_YMWD_distance [MolokoCalendar cal]
   @init
   {
      int amount   = -1;
      int calField = Calendar.YEAR;
   }
   : (   a=NUM_STR { amount = numberStringToNumber( $a.text );}
       | a=INT     { amount = Integer.parseInt( $a.text ); })
     (     YEARS
       |   MONTHS  { calField = Calendar.MONTH;            }
       |   WEEKS   { calField = Calendar.WEEK_OF_YEAR;     }
       |   DAYS    { calField = Calendar.DAY_OF_YEAR;      })
   {
      if ( amount != -1 )
      {
         cal.add( calField, amount );
      }
      else
      {
         throw new RecognitionException();
      }
   }
   ;
   catch[ NumberFormatException nfe ]
   {
      throw new RecognitionException();
   }
   catch [RecognitionException e]
   {
      throw e;
   }

date_end_of_the_MW [MolokoCalendar cal]
   : END OF?
     (   WEEKS
         {
            rollToEndOf( Calendar.DAY_OF_WEEK, cal );
         }
       | MONTHS
         {
            rollToEndOf( Calendar.DAY_OF_MONTH, cal );
         })
   ;
   catch [RecognitionException e]
   {
      throw e;
   }

date_natural [MolokoCalendar cal]
   : (TODAY | TONIGHT)
     {
     }
   | NEVER
     {
        cal.setHasDate( false );
        cal.setHasTime( false );
     }
   | TOMORROW
     {
        cal.roll( Calendar.DAY_OF_YEAR, true );
     }
   | YESTERDAY
     {
        cal.roll( Calendar.DAY_OF_YEAR, false );
     }
   ;
   catch [RecognitionException e]
   {
      throw e;
   }
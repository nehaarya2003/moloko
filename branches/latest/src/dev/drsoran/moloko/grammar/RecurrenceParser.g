parser grammar RecurrenceParser;

@option
{
   language=Java;
   tokenVocab=RecurrenceLexer;
}

@header
{
   package dev.drsoran.moloko.grammar;

   import java.text.ParseException;
   import java.text.SimpleDateFormat;
   import java.util.Calendar;
   import java.util.Comparator;
   import java.util.HashMap;
   import java.util.Iterator;
   import java.util.Locale;
   import java.util.Set;
   import java.util.TreeSet;

   import dev.drsoran.moloko.util.parsing.RtmDateTimeParsing;
   import dev.drsoran.moloko.grammar.RecurrencePatternParser;
}


@members
{
   public RecurrenceParser()
   {
      super( null );
   }

   private final static Locale LOCALE = Locale.ENGLISH;

   private final static class CmpWeekday implements Comparator< String >
   {
      private final static int weekdayToInt( String wd )
      {
         // only take the last 2 chars, the leading chars can be
         // Xst values.
         final String weekday = wd.substring( wd.length() - 2 );

         if ( weekday.equals( RecurrencePatternParser.BYDAY_MON ) )
            return 1;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_TUE ) )
            return 2;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_WED ) )
            return 3;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_THU ) )
            return 4;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_FRI ) )
            return 5;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_SAT ) )
            return 6;
         else if ( weekday.equals( RecurrencePatternParser.BYDAY_SUN ) )
            return 7;
         else
            return 1;
      }

      public int compare( String wd1, String wd2 )
      {
         return weekdayToInt( wd1 ) - weekdayToInt( wd2 );
      }
   }



   private final static class CmpOperators implements Comparator< String >
   {
      private final static int operatorToInt( String operator )
      {
         if ( operator.startsWith( RecurrencePatternParser.OP_FREQ_LIT ) )
            return 1;
         else if ( operator.startsWith( RecurrencePatternParser.OP_INTERVAL_LIT ) )
            return 2;
         else if ( operator.startsWith( RecurrencePatternParser.OP_BYDAY_LIT ) )
            return 3;
         else if ( operator.startsWith( RecurrencePatternParser.OP_BYMONTHDAY_LIT ) )
            return 3;
         else if ( operator.startsWith( RecurrencePatternParser.OP_BYMONTH_LIT ) )
            return 4;
         else if ( operator.startsWith( RecurrencePatternParser.OP_UNTIL_LIT ) )
            return 5;
         else if ( operator.startsWith( RecurrencePatternParser.OP_COUNT_LIT ) )
            return 5;
         else
            return Integer.MAX_VALUE;
      }



      public int compare( String op1, String op2 )
      {
         return operatorToInt( op1 ) - operatorToInt( op2 );
      }
   }



   private final static CmpWeekday CMP_WEEKDAY  = new CmpWeekday();

   public final static CmpOperators CMP_OPERATORS = new CmpOperators();



   private final static < E > String join( String delim, Iterable< E > values )
   {
      StringBuilder result = new StringBuilder();

      final Iterator< E > i = values.iterator();

      for ( boolean hasNext = i.hasNext(); hasNext; )
      {
         result.append( i.next() );
         hasNext = i.hasNext();

         if ( hasNext )
            result.append( delim );
      }

      return result.toString();
   }
}

// RULES

parseRecurrence returns[HashMap< String, Object > res]
   @init
   {
      res                               = new HashMap< String, Object >();
      Boolean isEvery                   = Boolean.FALSE;

      final TreeSet< String >  weekdays = new TreeSet< String >( CMP_WEEKDAY );
      final TreeSet< Integer > ints     = new TreeSet< Integer >();

      interval                          = 1;
      String freq                       = null;
      String resolution                 = null;
      String resolutionVal              = null;
   }
   @after
   {
      res.put( RecurrencePatternParser.OP_FREQ_LIT, freq );
      res.put( RecurrencePatternParser.OP_INTERVAL_LIT, new Integer( interval ) );

      if ( resolution != null && resolutionVal != null )
         res.put( resolution, resolutionVal );

      res.put( RecurrencePatternParser.IS_EVERY, isEvery );
   }
   : (EVERY { isEvery = Boolean.TRUE; } | AFTER)?
     (
        interval=parse_Number?
        (
            DAYS                                               { freq = RecurrencePatternParser.VAL_DAILY_LIT;  }
          | (
                 WEEKS
               | BIWEEKLY
                 {
                    interval = 2;
                 }
             )                                                 { freq = RecurrencePatternParser.VAL_WEEKLY_LIT; }
             (ON? THE? recurr_WD[weekdays, ""]
                   {
                      resolution    = RecurrencePatternParser.OP_BYDAY_LIT;
                      resolutionVal = join( ",", weekdays );
                   }
             )?
          | MONTHS                                             { freq = RecurrencePatternParser.VAL_MONTHLY_LIT;}
             (ON? THE? r=recurr_Monthly[weekdays, ints]
                       {
                          freq          = r.freq;
                          interval      = r.interval;
                          resolution    = r.resolution;
                          resolutionVal = r.resolutionVal;
                       }
             )?
          | YEARS                                              { freq = RecurrencePatternParser.VAL_YEARLY_LIT; }
             (ON? THE? r=recurr_Monthly[weekdays, ints]
                       {
                          freq          = r.freq;
                          interval      = r.interval;
                          resolution    = r.resolution;
                          resolutionVal = r.resolutionVal;
                       }
                       { r.hasWD }?=> (
                                        (IN | OF)?
                                         m=parse_Month
                                         {
                                            freq     = RecurrencePatternParser.VAL_YEARLY_LIT;
                                            interval = 1;
                                            res.put( RecurrencePatternParser.OP_BYMONTH_LIT, Integer.toString( m ) );
                                         }
                                      )?
       )?
   )
   | recurr_Xst[ints]
     {
        freq          = RecurrencePatternParser.VAL_MONTHLY_LIT;
        interval      = 1;
        resolution    = RecurrencePatternParser.OP_BYMONTHDAY_LIT;
        resolutionVal = join( ",", ints );
     }
   | recurr_WD[weekdays, ""]
     {
        freq          = RecurrencePatternParser.VAL_WEEKLY_LIT;
        interval      = 1;
        resolution    = RecurrencePatternParser.OP_BYDAY_LIT;
        resolutionVal = join( ",", weekdays );
     }
   | (
        firstEntry=recurr_Xst[ints] recurr_WD[weekdays, ""]
        {
           freq          = RecurrencePatternParser.VAL_WEEKLY_LIT;
           interval      = firstEntry;
           resolution    = RecurrencePatternParser.OP_BYDAY_LIT;
           resolutionVal = join( ",", weekdays );
        }
     )
   )
   (
        until=UNTIL
        {
           final String dateTimeString = until.getText()
                                              .toUpperCase()
                                              .replaceFirst( RecurrencePatternParser.OP_UNTIL_LIT +  "\\s*",
                                                             "" );

           final Calendar untilDate = RtmDateTimeParsing.parseDateTimeSpec( dateTimeString );

           if ( untilDate != null )
           {
               if ( !untilDate.isSet( Calendar.HOUR_OF_DAY ) )
               {
                  untilDate.set( Calendar.HOUR, 0 );
                  untilDate.set( Calendar.HOUR_OF_DAY, 0 );
                  untilDate.set( Calendar.MINUTE, 0 );
                  untilDate.set( Calendar.SECOND, 0 );
                  untilDate.set( Calendar.MILLISECOND, 0 );
               }

              final SimpleDateFormat sdf = new SimpleDateFormat( RecurrencePatternParser.DATE_PATTERN );
              res.put( RecurrencePatternParser.OP_UNTIL_LIT, sdf.format( untilDate.getTime() ) );
           }
        }
      | FOR count=INT
        {
           res.put( RecurrencePatternParser.OP_COUNT_LIT, Integer.parseInt( $count.text ) );
        }
   )?
   | EOF
   ;
   catch [ RecognitionException e ]
   {
      throw e;
   }
   catch [ NumberFormatException nfe ]
   {
      throw new RecognitionException();
   }

recurr_Xst [Set< Integer > res] returns [int firstEntry]
   : x=parse_Xst               { res.add( x ); firstEntry = x; }
   (((AND | COMMA) x=parse_Xst { res.add( x ); })+)?
   ;
   catch [ RecognitionException e ]
   {
      throw e;
   }

recurr_WD [Set< String > weekdays, String Xst]
   : parse_Weekday              [weekdays, Xst, true]
   (((AND | COMMA) parse_Weekday[weekdays, Xst, true])+)?
   ;
   catch [ RecognitionException e ]
   {
      throw e;
   }

recurr_Monthly [Set< String >  weekdays,
                Set< Integer > ints     ] returns [String  freq,
                                                   String  resolution,
                                                   String  resolutionVal,
                                                   int     interval,
                                                   boolean hasWD]
   @init
   {
      retval.freq     = RecurrencePatternParser.VAL_MONTHLY_LIT;
      retval.interval = 1;
   }
   : firstEntry = recurr_Xst[ints]
     {
        retval.resolution    = RecurrencePatternParser.OP_BYMONTHDAY_LIT;
        retval.resolutionVal = join( ",", ints );
     } (
          (
             LAST
             {
                firstEntry = -firstEntry;
             }
          )?
          recurr_WD[weekdays, Integer.toString( firstEntry )]
          {
             retval.hasWD         = true;
             retval.resolution    = RecurrencePatternParser.OP_BYDAY_LIT;
             retval.resolutionVal = join( ",", weekdays );
          }
       )?
   ;
   catch [ RecognitionException e ]
   {
      throw e;
   }

parse_Xst returns [int number]
   : n=INT (DOT|ST_S)?
   {
      number = Integer.parseInt( $n.text );

      if ( number < 1 )
         number = 1;
      else if ( number > 31 )
         number = 31;
   }
   | FIRST            { number = 1; }
   | (SECOND | OTHER) { number = 2; }
   | THIRD            { number = 3; }
   | FOURTH           { number = 4; }
   | FIFTH            { number = 5; }
   ;
   catch [ RecognitionException e ]
   {
      return 1;
   }
   catch [ NumberFormatException nfe ]
   {
      return 1;
   }

parse_Number returns [int number]
   : n=INT      { number = Integer.parseInt( $n.text ); }
   | NUM_ONE    { number = 1; }
   | NUM_TWO    { number = 2; }
   | NUM_THREE  { number = 3; }
   | NUM_FOUR   { number = 4; }
   | NUM_FIVE   { number = 5; }
   | NUM_SIX    { number = 6; }
   | NUM_SEVEN  { number = 7; }
   | NUM_EIGHT  { number = 8; }
   | NUM_NINE   { number = 9; }
   | NUM_TEN    { number = 10; }
   ;
   catch [ RecognitionException e ]
   {
      return 1;
   }
   catch [ NumberFormatException nfe ]
   {
      return 1;
   }

parse_Weekday [Set< String > weekdays, String Xst, boolean strict] returns [String weekday]
   : MONDAY      { weekdays.add( Xst + RecurrencePatternParser.BYDAY_MON ); }
   | TUESDAY     { weekdays.add( Xst + RecurrencePatternParser.BYDAY_TUE ); }
   | WEDNESDAY   { weekdays.add( Xst + RecurrencePatternParser.BYDAY_WED ); }
   | THURSDAY    { weekdays.add( Xst + RecurrencePatternParser.BYDAY_THU ); }
   | FRIDAY      { weekdays.add( Xst + RecurrencePatternParser.BYDAY_FRI ); }
   | SATURDAY    { weekdays.add( Xst + RecurrencePatternParser.BYDAY_SAT ); }
   | SUNDAY      { weekdays.add( Xst + RecurrencePatternParser.BYDAY_SUN ); }
   | WEEKEND     {
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_SAT );
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_SUN );
                 }
   | WEEKDAY_LIT {
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_MON );
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_TUE );
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_WED );
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_THU );
                    weekdays.add( Xst + RecurrencePatternParser.BYDAY_FRI );
                 }
   ;
   catch [ RecognitionException e ]
   {
      if ( strict )
         throw e;
      else
         return null;
   }

parse_Month returns [int number]
   : m=MONTH
   {
      try
      {
         final SimpleDateFormat sdf = new SimpleDateFormat( "MMM", LOCALE );
         sdf.parse( $m.text );

         number = sdf.getCalendar().get( Calendar.MONTH );

         if ( number == 0 )
            ++number;
      }
      catch( ParseException e )
      {
         number = 1;
      }
   }
   ;
   catch [ RecognitionException e ]
   {
      return 1;
   }

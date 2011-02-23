grammar RecurrenceTree;

@option
{
   language=Java;
   output=AST;
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

   import dev.drsoran.moloko.grammar.lang.RecurrPatternLanguage;
   import dev.drsoran.moloko.util.MolokoDateUtils;
   import dev.drsoran.moloko.util.parsing.RtmDateTimeParsing;
}

@lexer::header
{
   package dev.drsoran.moloko.grammar;
}

@members
{
   public RecurrenceTreeParser()
   {
      super( null );
   }
}

tokens
{
   QUANTIFIER;
}

// RULES

parseRecurrence
   :
   ( 
        EVERY^
      | AFTER^
   )
   (
      value=parse_Number?                                          -> ^(QUANTIFIER, value)?
      (
         DAYS
         | (WEEKS | BIWEEKLY)
            (ON? THE? recurr_WD[weekdays, ""])?
         | MONTHS
            (ON? THE? recurr_Monthly[weekdays, ints])?
         | YEARS
            (ON? THE? r=recurr_Monthly[weekdays, ints]
                       { r.hasWD }?=> ( (IN | OF)? parse_Month)?
      )?
   )
   | recurr_Xst[ints]
   | recurr_WD[weekdays, ""]
   | recurr_Xst[ints] recurr_WD[weekdays, ""]
   )
   (
        UNTIL
        {
           final String dateTimeString = until.getText()
                                              .toUpperCase()
                                              .replaceFirst( OP_UNTIL_LIT +  "\\s*",
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

              final SimpleDateFormat sdf = new SimpleDateFormat( DATE_PATTERN );
              res.put( OP_UNTIL_LIT, sdf.format( untilDate.getTime() ) );
           }
        }
      | FOR count=INT
        {
           res.put( OP_COUNT_LIT, Integer.parseInt( $count.text ) );
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
      retval.freq     = VAL_MONTHLY_LIT;
      retval.interval = 1;
   }
   : firstEntry = recurr_Xst[ints]
     {
        retval.resolution    = OP_BYMONTHDAY_LIT;
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
             retval.resolution    = OP_BYDAY_LIT;
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
   : MONDAY      { weekdays.add( Xst + BYDAY_MON ); }
   | TUESDAY     { weekdays.add( Xst + BYDAY_TUE ); }
   | WEDNESDAY   { weekdays.add( Xst + BYDAY_WED ); }
   | THURSDAY    { weekdays.add( Xst + BYDAY_THU ); }
   | FRIDAY      { weekdays.add( Xst + BYDAY_FRI ); }
   | SATURDAY    { weekdays.add( Xst + BYDAY_SAT ); }
   | SUNDAY      { weekdays.add( Xst + BYDAY_SUN ); }
   | WEEKEND     {
                    weekdays.add( Xst + BYDAY_SAT );
                    weekdays.add( Xst + BYDAY_SUN );
                 }
   | WEEKDAY_LIT {
                    weekdays.add( Xst + BYDAY_MON );
                    weekdays.add( Xst + BYDAY_TUE );
                    weekdays.add( Xst + BYDAY_WED );
                    weekdays.add( Xst + BYDAY_THU );
                    weekdays.add( Xst + BYDAY_FRI );
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


// TOKENS

EVERY         : 'every' | 'each';

AFTER         : 'after';

BIWEEKLY      : 'fortnight' | 'biweekly';

YEARS         : 'years' | 'year' | 'yrs' | 'yr';

MONTHS        : 'months' | 'month' | 'mons' | 'mon';

WEEKS         : 'weeks' | 'week' | 'wks' | 'wk';

DAYS          : 'days' | 'day';

MONTH         : 'january'   | 'jan'  | 'february' | 'feb'     | 'march' | 'mar'      | 'april' | 'apr' |
                'may'       | 'june' | 'jun'      | 'july'    | 'jul'   | 'august'   | 'aug'   |
                'september' | 'sept' | 'sep'      | 'october' | 'oct'   | 'november' | 'nov'   |
                'december'  | 'dec';

WEEKDAY_LIT   : 'weekday''s'?;

WEEKEND       : 'weekend''s'?;

MONDAY        : 'monday'    | 'mon' | 'MO';

TUESDAY       : 'tuesday'   | 'tue' | 'TU';

WEDNESDAY     : 'wednesday' | 'wed' | 'WE';

THURSDAY      : 'thursday'  | 'thu' | 'TH';

FRIDAY        : 'friday'    | 'fri' | 'FR';

SATURDAY      : 'saturday'  | 'sat' | 'SA';

SUNDAY        : 'sunday'    | 'sun' | 'SU';

FIRST         : 'first';

SECOND        : 'second';

THIRD         : 'third';

FOURTH        : 'fourth';

FIFTH         : 'fifth';

LAST          : 'last';

OTHER         : 'other';

ST_S          : 'st' | 'nd' | 'rd' | 'th';

NUM_ONE       : 'one';

NUM_TWO       : 'two';

NUM_THREE     : 'three';

NUM_FOUR      : 'four';

NUM_FIVE      : 'five';

NUM_SIX       : 'six';

NUM_SEVEN     : 'seven';

NUM_EIGHT     : 'eight';

NUM_NINE      : 'nine';

NUM_TEN       : 'ten';

AND           : 'and';

IN            : 'in';

ON            : 'on';

OF            : 'of';

THE           : 'the';

UNTIL         : 'until' STRING;

FOR           : 'for';

TIMES         : 'times';

DOT           : '.';

SEMICOLON     : ';' { $channel=HIDDEN; };

EQUALS        : '=' { $channel=HIDDEN; };

MINUS         : '-';

COMMA         : ',';

OP_BYDAY      : 'BYDAY';

OP_BYMONTH    : 'BYMONTH';

OP_BYMONTHDAY : 'BYMONTHDAY';

OP_INTERVAL   : 'INTERVAL';

OP_FREQ       : 'FREQ';

OP_UNTIL      : 'UNTIL';

OP_COUNT      : 'COUNT';

VAL_DAILY     : 'DAILY';

VAL_WEEKLY    : 'WEEKLY';

VAL_MONTHLY   : 'MONTHLY';

VAL_YEARLY    : 'YEARLY';

// yyyyMMdd'T'HHmmss
VAL_DATE      : NUMBER NUMBER NUMBER NUMBER NUMBER NUMBER NUMBER NUMBER
                'T'
                NUMBER NUMBER NUMBER NUMBER NUMBER NUMBER;
fragment
NUMBER        : '0'..'9';

INT           : MINUS? NUMBER+;

fragment
STRING        : (' '|.)+;

WS            : (  ' '
                 | '\t'
                 | '\r'
                 | '\n'
                ) { $channel=HIDDEN; };


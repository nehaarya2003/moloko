// $ANTLR 3.3 Nov 30, 2010 12:45:30 F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g 2011-02-24 08:36:35

package dev.drsoran.moloko.grammar;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;


public class RecurrenceLexer extends Lexer
{
   public static final int EOF = -1;
   
   public static final int EVERY = 4;
   
   public static final int AFTER = 5;
   
   public static final int BIWEEKLY = 6;
   
   public static final int YEARS = 7;
   
   public static final int MONTHS = 8;
   
   public static final int WEEKS = 9;
   
   public static final int DAYS = 10;
   
   public static final int MONTH = 11;
   
   public static final int WEEKDAY_LIT = 12;
   
   public static final int WEEKEND = 13;
   
   public static final int MONDAY = 14;
   
   public static final int TUESDAY = 15;
   
   public static final int WEDNESDAY = 16;
   
   public static final int THURSDAY = 17;
   
   public static final int FRIDAY = 18;
   
   public static final int SATURDAY = 19;
   
   public static final int SUNDAY = 20;
   
   public static final int FIRST = 21;
   
   public static final int SECOND = 22;
   
   public static final int THIRD = 23;
   
   public static final int FOURTH = 24;
   
   public static final int FIFTH = 25;
   
   public static final int LAST = 26;
   
   public static final int OTHER = 27;
   
   public static final int ST_S = 28;
   
   public static final int NUM_ONE = 29;
   
   public static final int NUM_TWO = 30;
   
   public static final int NUM_THREE = 31;
   
   public static final int NUM_FOUR = 32;
   
   public static final int NUM_FIVE = 33;
   
   public static final int NUM_SIX = 34;
   
   public static final int NUM_SEVEN = 35;
   
   public static final int NUM_EIGHT = 36;
   
   public static final int NUM_NINE = 37;
   
   public static final int NUM_TEN = 38;
   
   public static final int AND = 39;
   
   public static final int IN = 40;
   
   public static final int ON = 41;
   
   public static final int OF = 42;
   
   public static final int THE = 43;
   
   public static final int STRING = 44;
   
   public static final int UNTIL = 45;
   
   public static final int FOR = 46;
   
   public static final int TIMES = 47;
   
   public static final int DOT = 48;
   
   public static final int MINUS = 49;
   
   public static final int COMMA = 50;
   
   public static final int NUMBER = 51;
   
   public static final int INT = 52;
   
   public static final int WS = 53;
   
   

   // delegates
   // delegators
   
   public RecurrenceLexer()
   {
      ;
   }
   


   public RecurrenceLexer( CharStream input )
   {
      this( input, new RecognizerSharedState() );
   }
   


   public RecurrenceLexer( CharStream input, RecognizerSharedState state )
   {
      super( input, state );
      
   }
   


   @Override
   public String getGrammarFileName()
   {
      return "F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g";
   }
   


   // $ANTLR start "EVERY"
   public final void mEVERY() throws RecognitionException
   {
      try
      {
         int _type = EVERY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:15:15: ( 'every' | 'each' )
         int alt1 = 2;
         int LA1_0 = input.LA( 1 );
         
         if ( ( LA1_0 == 'e' ) )
         {
            int LA1_1 = input.LA( 2 );
            
            if ( ( LA1_1 == 'v' ) )
            {
               alt1 = 1;
            }
            else if ( ( LA1_1 == 'a' ) )
            {
               alt1 = 2;
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     1,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  1,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt1 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:15:17: 'every'
            {
               match( "every" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:15:27: 'each'
            {
               match( "each" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "EVERY"
   
   // $ANTLR start "AFTER"
   public final void mAFTER() throws RecognitionException
   {
      try
      {
         int _type = AFTER;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:17:15: ( 'after' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:17:17: 'after'
         {
            match( "after" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "AFTER"
   
   // $ANTLR start "BIWEEKLY"
   public final void mBIWEEKLY() throws RecognitionException
   {
      try
      {
         int _type = BIWEEKLY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:19:15: ( 'fortnight' |
         // 'biweekly' )
         int alt2 = 2;
         int LA2_0 = input.LA( 1 );
         
         if ( ( LA2_0 == 'f' ) )
         {
            alt2 = 1;
         }
         else if ( ( LA2_0 == 'b' ) )
         {
            alt2 = 2;
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  2,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt2 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:19:17: 'fortnight'
            {
               match( "fortnight" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:19:31: 'biweekly'
            {
               match( "biweekly" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "BIWEEKLY"
   
   // $ANTLR start "YEARS"
   public final void mYEARS() throws RecognitionException
   {
      try
      {
         int _type = YEARS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:21:15: ( 'years' | 'year' |
         // 'yrs' | 'yr' )
         int alt3 = 4;
         alt3 = dfa3.predict( input );
         switch ( alt3 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:21:17: 'years'
            {
               match( "years" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:21:27: 'year'
            {
               match( "year" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:21:36: 'yrs'
            {
               match( "yrs" );
               
            }
               break;
            case 4:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:21:44: 'yr'
            {
               match( "yr" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "YEARS"
   
   // $ANTLR start "MONTHS"
   public final void mMONTHS() throws RecognitionException
   {
      try
      {
         int _type = MONTHS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:23:15: ( 'months' | 'month' |
         // 'mons' | 'mon' )
         int alt4 = 4;
         alt4 = dfa4.predict( input );
         switch ( alt4 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:23:17: 'months'
            {
               match( "months" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:23:28: 'month'
            {
               match( "month" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:23:38: 'mons'
            {
               match( "mons" );
               
            }
               break;
            case 4:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:23:47: 'mon'
            {
               match( "mon" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "MONTHS"
   
   // $ANTLR start "WEEKS"
   public final void mWEEKS() throws RecognitionException
   {
      try
      {
         int _type = WEEKS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:25:15: ( 'weeks' | 'week' |
         // 'wks' | 'wk' )
         int alt5 = 4;
         alt5 = dfa5.predict( input );
         switch ( alt5 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:25:17: 'weeks'
            {
               match( "weeks" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:25:27: 'week'
            {
               match( "week" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:25:36: 'wks'
            {
               match( "wks" );
               
            }
               break;
            case 4:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:25:44: 'wk'
            {
               match( "wk" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "WEEKS"
   
   // $ANTLR start "DAYS"
   public final void mDAYS() throws RecognitionException
   {
      try
      {
         int _type = DAYS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:27:15: ( 'days' | 'day' )
         int alt6 = 2;
         int LA6_0 = input.LA( 1 );
         
         if ( ( LA6_0 == 'd' ) )
         {
            int LA6_1 = input.LA( 2 );
            
            if ( ( LA6_1 == 'a' ) )
            {
               int LA6_2 = input.LA( 3 );
               
               if ( ( LA6_2 == 'y' ) )
               {
                  int LA6_3 = input.LA( 4 );
                  
                  if ( ( LA6_3 == 's' ) )
                  {
                     alt6 = 1;
                  }
                  else
                  {
                     alt6 = 2;
                  }
               }
               else
               {
                  NoViableAltException nvae = new NoViableAltException( "",
                                                                        6,
                                                                        2,
                                                                        input );
                  
                  throw nvae;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     6,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  6,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt6 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:27:17: 'days'
            {
               match( "days" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:27:26: 'day'
            {
               match( "day" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "DAYS"
   
   // $ANTLR start "MONTH"
   public final void mMONTH() throws RecognitionException
   {
      try
      {
         int _type = MONTH;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:15: ( 'january' | 'jan' |
         // 'february' | 'feb' | 'march' | 'mar' | 'april' | 'apr' | 'may' | 'june' | 'jun' | 'july' | 'jul' | 'august'
         // | 'aug' | 'september' | 'sept' | 'sep' | 'october' | 'oct' | 'november' | 'nov' | 'december' | 'dec' )
         int alt7 = 24;
         alt7 = dfa7.predict( input );
         switch ( alt7 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:17: 'january'
            {
               match( "january" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:31: 'jan'
            {
               match( "jan" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:40: 'february'
            {
               match( "february" );
               
            }
               break;
            case 4:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:53: 'feb'
            {
               match( "feb" );
               
            }
               break;
            case 5:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:65: 'march'
            {
               match( "march" );
               
            }
               break;
            case 6:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:75: 'mar'
            {
               match( "mar" );
               
            }
               break;
            case 7:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:88: 'april'
            {
               match( "april" );
               
            }
               break;
            case 8:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:29:98: 'apr'
            {
               match( "apr" );
               
            }
               break;
            case 9:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:17: 'may'
            {
               match( "may" );
               
            }
               break;
            case 10:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:31: 'june'
            {
               match( "june" );
               
            }
               break;
            case 11:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:40: 'jun'
            {
               match( "jun" );
               
            }
               break;
            case 12:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:53: 'july'
            {
               match( "july" );
               
            }
               break;
            case 13:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:65: 'jul'
            {
               match( "jul" );
               
            }
               break;
            case 14:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:75: 'august'
            {
               match( "august" );
               
            }
               break;
            case 15:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:30:88: 'aug'
            {
               match( "aug" );
               
            }
               break;
            case 16:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:17: 'september'
            {
               match( "september" );
               
            }
               break;
            case 17:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:31: 'sept'
            {
               match( "sept" );
               
            }
               break;
            case 18:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:40: 'sep'
            {
               match( "sep" );
               
            }
               break;
            case 19:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:53: 'october'
            {
               match( "october" );
               
            }
               break;
            case 20:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:65: 'oct'
            {
               match( "oct" );
               
            }
               break;
            case 21:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:75: 'november'
            {
               match( "november" );
               
            }
               break;
            case 22:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:31:88: 'nov'
            {
               match( "nov" );
               
            }
               break;
            case 23:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:32:17: 'december'
            {
               match( "december" );
               
            }
               break;
            case 24:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:32:31: 'dec'
            {
               match( "dec" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "MONTH"
   
   // $ANTLR start "WEEKDAY_LIT"
   public final void mWEEKDAY_LIT() throws RecognitionException
   {
      try
      {
         int _type = WEEKDAY_LIT;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:34:15: ( 'weekday' ( 's' )? )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:34:17: 'weekday' ( 's' )?
         {
            match( "weekday" );
            
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:34:26: ( 's' )?
            int alt8 = 2;
            int LA8_0 = input.LA( 1 );
            
            if ( ( LA8_0 == 's' ) )
            {
               alt8 = 1;
            }
            switch ( alt8 )
            {
               case 1:
                  // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:34:26: 's'
               {
                  match( 's' );
                  
               }
                  break;
               
            }
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "WEEKDAY_LIT"
   
   // $ANTLR start "WEEKEND"
   public final void mWEEKEND() throws RecognitionException
   {
      try
      {
         int _type = WEEKEND;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:36:15: ( 'weekend' ( 's' )? )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:36:17: 'weekend' ( 's' )?
         {
            match( "weekend" );
            
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:36:26: ( 's' )?
            int alt9 = 2;
            int LA9_0 = input.LA( 1 );
            
            if ( ( LA9_0 == 's' ) )
            {
               alt9 = 1;
            }
            switch ( alt9 )
            {
               case 1:
                  // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:36:26: 's'
               {
                  match( 's' );
                  
               }
                  break;
               
            }
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "WEEKEND"
   
   // $ANTLR start "MONDAY"
   public final void mMONDAY() throws RecognitionException
   {
      try
      {
         int _type = MONDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:38:15: ( 'monday' | 'mon' |
         // 'mo' )
         int alt10 = 3;
         int LA10_0 = input.LA( 1 );
         
         if ( ( LA10_0 == 'm' ) )
         {
            int LA10_1 = input.LA( 2 );
            
            if ( ( LA10_1 == 'o' ) )
            {
               int LA10_2 = input.LA( 3 );
               
               if ( ( LA10_2 == 'n' ) )
               {
                  int LA10_3 = input.LA( 4 );
                  
                  if ( ( LA10_3 == 'd' ) )
                  {
                     alt10 = 1;
                  }
                  else
                  {
                     alt10 = 2;
                  }
               }
               else
               {
                  alt10 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     10,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  10,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt10 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:38:17: 'monday'
            {
               match( "monday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:38:31: 'mon'
            {
               match( "mon" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:38:39: 'mo'
            {
               match( "mo" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "MONDAY"
   
   // $ANTLR start "TUESDAY"
   public final void mTUESDAY() throws RecognitionException
   {
      try
      {
         int _type = TUESDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:40:15: ( 'tuesday' | 'tue' |
         // 'tu' )
         int alt11 = 3;
         int LA11_0 = input.LA( 1 );
         
         if ( ( LA11_0 == 't' ) )
         {
            int LA11_1 = input.LA( 2 );
            
            if ( ( LA11_1 == 'u' ) )
            {
               int LA11_2 = input.LA( 3 );
               
               if ( ( LA11_2 == 'e' ) )
               {
                  int LA11_3 = input.LA( 4 );
                  
                  if ( ( LA11_3 == 's' ) )
                  {
                     alt11 = 1;
                  }
                  else
                  {
                     alt11 = 2;
                  }
               }
               else
               {
                  alt11 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     11,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  11,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt11 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:40:17: 'tuesday'
            {
               match( "tuesday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:40:31: 'tue'
            {
               match( "tue" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:40:39: 'tu'
            {
               match( "tu" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "TUESDAY"
   
   // $ANTLR start "WEDNESDAY"
   public final void mWEDNESDAY() throws RecognitionException
   {
      try
      {
         int _type = WEDNESDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:42:15: ( 'wednesday' | 'wed'
         // | 'we' )
         int alt12 = 3;
         int LA12_0 = input.LA( 1 );
         
         if ( ( LA12_0 == 'w' ) )
         {
            int LA12_1 = input.LA( 2 );
            
            if ( ( LA12_1 == 'e' ) )
            {
               int LA12_2 = input.LA( 3 );
               
               if ( ( LA12_2 == 'd' ) )
               {
                  int LA12_3 = input.LA( 4 );
                  
                  if ( ( LA12_3 == 'n' ) )
                  {
                     alt12 = 1;
                  }
                  else
                  {
                     alt12 = 2;
                  }
               }
               else
               {
                  alt12 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     12,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  12,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt12 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:42:17: 'wednesday'
            {
               match( "wednesday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:42:31: 'wed'
            {
               match( "wed" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:42:39: 'we'
            {
               match( "we" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "WEDNESDAY"
   
   // $ANTLR start "THURSDAY"
   public final void mTHURSDAY() throws RecognitionException
   {
      try
      {
         int _type = THURSDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:44:15: ( 'thursday' | 'thu' |
         // 'th' )
         int alt13 = 3;
         int LA13_0 = input.LA( 1 );
         
         if ( ( LA13_0 == 't' ) )
         {
            int LA13_1 = input.LA( 2 );
            
            if ( ( LA13_1 == 'h' ) )
            {
               int LA13_2 = input.LA( 3 );
               
               if ( ( LA13_2 == 'u' ) )
               {
                  int LA13_3 = input.LA( 4 );
                  
                  if ( ( LA13_3 == 'r' ) )
                  {
                     alt13 = 1;
                  }
                  else
                  {
                     alt13 = 2;
                  }
               }
               else
               {
                  alt13 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     13,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  13,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt13 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:44:17: 'thursday'
            {
               match( "thursday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:44:31: 'thu'
            {
               match( "thu" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:44:39: 'th'
            {
               match( "th" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "THURSDAY"
   
   // $ANTLR start "FRIDAY"
   public final void mFRIDAY() throws RecognitionException
   {
      try
      {
         int _type = FRIDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:46:15: ( 'friday' | 'fri' |
         // 'fr' )
         int alt14 = 3;
         int LA14_0 = input.LA( 1 );
         
         if ( ( LA14_0 == 'f' ) )
         {
            int LA14_1 = input.LA( 2 );
            
            if ( ( LA14_1 == 'r' ) )
            {
               int LA14_2 = input.LA( 3 );
               
               if ( ( LA14_2 == 'i' ) )
               {
                  int LA14_3 = input.LA( 4 );
                  
                  if ( ( LA14_3 == 'd' ) )
                  {
                     alt14 = 1;
                  }
                  else
                  {
                     alt14 = 2;
                  }
               }
               else
               {
                  alt14 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     14,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  14,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt14 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:46:17: 'friday'
            {
               match( "friday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:46:31: 'fri'
            {
               match( "fri" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:46:39: 'fr'
            {
               match( "fr" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "FRIDAY"
   
   // $ANTLR start "SATURDAY"
   public final void mSATURDAY() throws RecognitionException
   {
      try
      {
         int _type = SATURDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:48:15: ( 'saturday' | 'sat' |
         // 'sa' )
         int alt15 = 3;
         int LA15_0 = input.LA( 1 );
         
         if ( ( LA15_0 == 's' ) )
         {
            int LA15_1 = input.LA( 2 );
            
            if ( ( LA15_1 == 'a' ) )
            {
               int LA15_2 = input.LA( 3 );
               
               if ( ( LA15_2 == 't' ) )
               {
                  int LA15_3 = input.LA( 4 );
                  
                  if ( ( LA15_3 == 'u' ) )
                  {
                     alt15 = 1;
                  }
                  else
                  {
                     alt15 = 2;
                  }
               }
               else
               {
                  alt15 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     15,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  15,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt15 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:48:17: 'saturday'
            {
               match( "saturday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:48:31: 'sat'
            {
               match( "sat" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:48:39: 'sa'
            {
               match( "sa" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "SATURDAY"
   
   // $ANTLR start "SUNDAY"
   public final void mSUNDAY() throws RecognitionException
   {
      try
      {
         int _type = SUNDAY;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:50:15: ( 'sunday' | 'sun' |
         // 'su' )
         int alt16 = 3;
         int LA16_0 = input.LA( 1 );
         
         if ( ( LA16_0 == 's' ) )
         {
            int LA16_1 = input.LA( 2 );
            
            if ( ( LA16_1 == 'u' ) )
            {
               int LA16_2 = input.LA( 3 );
               
               if ( ( LA16_2 == 'n' ) )
               {
                  int LA16_3 = input.LA( 4 );
                  
                  if ( ( LA16_3 == 'd' ) )
                  {
                     alt16 = 1;
                  }
                  else
                  {
                     alt16 = 2;
                  }
               }
               else
               {
                  alt16 = 3;
               }
            }
            else
            {
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     16,
                                                                     1,
                                                                     input );
               
               throw nvae;
            }
         }
         else
         {
            NoViableAltException nvae = new NoViableAltException( "",
                                                                  16,
                                                                  0,
                                                                  input );
            
            throw nvae;
         }
         switch ( alt16 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:50:17: 'sunday'
            {
               match( "sunday" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:50:31: 'sun'
            {
               match( "sun" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:50:39: 'su'
            {
               match( "su" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "SUNDAY"
   
   // $ANTLR start "FIRST"
   public final void mFIRST() throws RecognitionException
   {
      try
      {
         int _type = FIRST;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:52:15: ( 'first' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:52:17: 'first'
         {
            match( "first" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "FIRST"
   
   // $ANTLR start "SECOND"
   public final void mSECOND() throws RecognitionException
   {
      try
      {
         int _type = SECOND;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:54:15: ( 'second' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:54:17: 'second'
         {
            match( "second" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "SECOND"
   
   // $ANTLR start "THIRD"
   public final void mTHIRD() throws RecognitionException
   {
      try
      {
         int _type = THIRD;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:56:15: ( 'third' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:56:17: 'third'
         {
            match( "third" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "THIRD"
   
   // $ANTLR start "FOURTH"
   public final void mFOURTH() throws RecognitionException
   {
      try
      {
         int _type = FOURTH;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:58:15: ( 'fourth' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:58:17: 'fourth'
         {
            match( "fourth" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "FOURTH"
   
   // $ANTLR start "FIFTH"
   public final void mFIFTH() throws RecognitionException
   {
      try
      {
         int _type = FIFTH;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:60:15: ( 'fifth' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:60:17: 'fifth'
         {
            match( "fifth" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "FIFTH"
   
   // $ANTLR start "LAST"
   public final void mLAST() throws RecognitionException
   {
      try
      {
         int _type = LAST;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:62:15: ( 'last' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:62:17: 'last'
         {
            match( "last" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "LAST"
   
   // $ANTLR start "OTHER"
   public final void mOTHER() throws RecognitionException
   {
      try
      {
         int _type = OTHER;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:64:15: ( 'other' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:64:17: 'other'
         {
            match( "other" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "OTHER"
   
   // $ANTLR start "ST_S"
   public final void mST_S() throws RecognitionException
   {
      try
      {
         int _type = ST_S;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:66:15: ( 'st' | 'nd' | 'rd' |
         // 'th' )
         int alt17 = 4;
         switch ( input.LA( 1 ) )
         {
            case 's':
            {
               alt17 = 1;
            }
               break;
            case 'n':
            {
               alt17 = 2;
            }
               break;
            case 'r':
            {
               alt17 = 3;
            }
               break;
            case 't':
            {
               alt17 = 4;
            }
               break;
            default :
               NoViableAltException nvae = new NoViableAltException( "",
                                                                     17,
                                                                     0,
                                                                     input );
               
               throw nvae;
         }
         
         switch ( alt17 )
         {
            case 1:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:66:17: 'st'
            {
               match( "st" );
               
            }
               break;
            case 2:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:66:24: 'nd'
            {
               match( "nd" );
               
            }
               break;
            case 3:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:66:31: 'rd'
            {
               match( "rd" );
               
            }
               break;
            case 4:
               // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:66:38: 'th'
            {
               match( "th" );
               
            }
               break;
            
         }
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "ST_S"
   
   // $ANTLR start "NUM_ONE"
   public final void mNUM_ONE() throws RecognitionException
   {
      try
      {
         int _type = NUM_ONE;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:68:15: ( 'one' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:68:17: 'one'
         {
            match( "one" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_ONE"
   
   // $ANTLR start "NUM_TWO"
   public final void mNUM_TWO() throws RecognitionException
   {
      try
      {
         int _type = NUM_TWO;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:70:15: ( 'two' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:70:17: 'two'
         {
            match( "two" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_TWO"
   
   // $ANTLR start "NUM_THREE"
   public final void mNUM_THREE() throws RecognitionException
   {
      try
      {
         int _type = NUM_THREE;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:72:15: ( 'three' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:72:17: 'three'
         {
            match( "three" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_THREE"
   
   // $ANTLR start "NUM_FOUR"
   public final void mNUM_FOUR() throws RecognitionException
   {
      try
      {
         int _type = NUM_FOUR;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:74:15: ( 'four' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:74:17: 'four'
         {
            match( "four" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_FOUR"
   
   // $ANTLR start "NUM_FIVE"
   public final void mNUM_FIVE() throws RecognitionException
   {
      try
      {
         int _type = NUM_FIVE;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:76:15: ( 'five' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:76:17: 'five'
         {
            match( "five" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_FIVE"
   
   // $ANTLR start "NUM_SIX"
   public final void mNUM_SIX() throws RecognitionException
   {
      try
      {
         int _type = NUM_SIX;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:78:15: ( 'six' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:78:17: 'six'
         {
            match( "six" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_SIX"
   
   // $ANTLR start "NUM_SEVEN"
   public final void mNUM_SEVEN() throws RecognitionException
   {
      try
      {
         int _type = NUM_SEVEN;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:80:15: ( 'seven' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:80:17: 'seven'
         {
            match( "seven" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_SEVEN"
   
   // $ANTLR start "NUM_EIGHT"
   public final void mNUM_EIGHT() throws RecognitionException
   {
      try
      {
         int _type = NUM_EIGHT;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:82:15: ( 'eight' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:82:17: 'eight'
         {
            match( "eight" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_EIGHT"
   
   // $ANTLR start "NUM_NINE"
   public final void mNUM_NINE() throws RecognitionException
   {
      try
      {
         int _type = NUM_NINE;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:84:15: ( 'nine' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:84:17: 'nine'
         {
            match( "nine" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_NINE"
   
   // $ANTLR start "NUM_TEN"
   public final void mNUM_TEN() throws RecognitionException
   {
      try
      {
         int _type = NUM_TEN;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:86:15: ( 'ten' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:86:17: 'ten'
         {
            match( "ten" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUM_TEN"
   
   // $ANTLR start "AND"
   public final void mAND() throws RecognitionException
   {
      try
      {
         int _type = AND;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:88:15: ( 'and' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:88:17: 'and'
         {
            match( "and" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "AND"
   
   // $ANTLR start "IN"
   public final void mIN() throws RecognitionException
   {
      try
      {
         int _type = IN;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:90:15: ( 'in' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:90:17: 'in'
         {
            match( "in" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "IN"
   
   // $ANTLR start "ON"
   public final void mON() throws RecognitionException
   {
      try
      {
         int _type = ON;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:92:15: ( 'on' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:92:17: 'on'
         {
            match( "on" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "ON"
   
   // $ANTLR start "OF"
   public final void mOF() throws RecognitionException
   {
      try
      {
         int _type = OF;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:94:15: ( 'of' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:94:17: 'of'
         {
            match( "of" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "OF"
   
   // $ANTLR start "THE"
   public final void mTHE() throws RecognitionException
   {
      try
      {
         int _type = THE;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:96:15: ( 'the' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:96:17: 'the'
         {
            match( "the" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "THE"
   
   // $ANTLR start "UNTIL"
   public final void mUNTIL() throws RecognitionException
   {
      try
      {
         int _type = UNTIL;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:98:15: ( 'until' STRING )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:98:17: 'until' STRING
         {
            match( "until" );
            
            mSTRING();
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "UNTIL"
   
   // $ANTLR start "FOR"
   public final void mFOR() throws RecognitionException
   {
      try
      {
         int _type = FOR;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:100:15: ( 'for' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:100:17: 'for'
         {
            match( "for" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "FOR"
   
   // $ANTLR start "TIMES"
   public final void mTIMES() throws RecognitionException
   {
      try
      {
         int _type = TIMES;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:102:15: ( 'times' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:102:17: 'times'
         {
            match( "times" );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "TIMES"
   
   // $ANTLR start "DOT"
   public final void mDOT() throws RecognitionException
   {
      try
      {
         int _type = DOT;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:104:15: ( '.' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:104:17: '.'
         {
            match( '.' );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "DOT"
   
   // $ANTLR start "MINUS"
   public final void mMINUS() throws RecognitionException
   {
      try
      {
         int _type = MINUS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:106:15: ( '-' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:106:17: '-'
         {
            match( '-' );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "MINUS"
   
   // $ANTLR start "COMMA"
   public final void mCOMMA() throws RecognitionException
   {
      try
      {
         int _type = COMMA;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:108:15: ( ',' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:108:17: ','
         {
            match( ',' );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "COMMA"
   
   // $ANTLR start "NUMBER"
   public final void mNUMBER() throws RecognitionException
   {
      try
      {
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:111:15: ( '0' .. '9' )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:111:17: '0' .. '9'
         {
            matchRange( '0', '9' );
            
         }
         
      }
      finally
      {
      }
   }
   


   // $ANTLR end "NUMBER"
   
   // $ANTLR start "INT"
   public final void mINT() throws RecognitionException
   {
      try
      {
         int _type = INT;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:15: ( ( MINUS )? ( NUMBER
         // )+ )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:17: ( MINUS )? ( NUMBER
         // )+
         {
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:17: ( MINUS )?
            int alt18 = 2;
            int LA18_0 = input.LA( 1 );
            
            if ( ( LA18_0 == '-' ) )
            {
               alt18 = 1;
            }
            switch ( alt18 )
            {
               case 1:
                  // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:17: MINUS
               {
                  mMINUS();
                  
               }
                  break;
               
            }
            
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:24: ( NUMBER )+
            int cnt19 = 0;
            loop19: do
            {
               int alt19 = 2;
               int LA19_0 = input.LA( 1 );
               
               if ( ( ( LA19_0 >= '0' && LA19_0 <= '9' ) ) )
               {
                  alt19 = 1;
               }
               
               switch ( alt19 )
               {
                  case 1:
                     // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:113:24: NUMBER
                  {
                     mNUMBER();
                     
                  }
                     break;
                  
                  default :
                     if ( cnt19 >= 1 )
                        break loop19;
                     EarlyExitException eee = new EarlyExitException( 19, input );
                     throw eee;
               }
               cnt19++;
            }
            while ( true );
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "INT"
   
   // $ANTLR start "STRING"
   public final void mSTRING() throws RecognitionException
   {
      try
      {
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:116:15: ( ( ' ' | . )+ )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:116:17: ( ' ' | . )+
         {
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:116:17: ( ' ' | . )+
            int cnt20 = 0;
            loop20: do
            {
               int alt20 = 3;
               int LA20_0 = input.LA( 1 );
               
               if ( ( LA20_0 == ' ' ) )
               {
                  alt20 = 1;
               }
               else if ( ( ( LA20_0 >= '\u0000' && LA20_0 <= '\u001F' ) || ( LA20_0 >= '!' && LA20_0 <= '\uFFFF' ) ) )
               {
                  alt20 = 2;
               }
               
               switch ( alt20 )
               {
                  case 1:
                     // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:116:18: ' '
                  {
                     match( ' ' );
                     
                  }
                     break;
                  case 2:
                     // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:116:22: .
                  {
                     matchAny();
                     
                  }
                     break;
                  
                  default :
                     if ( cnt20 >= 1 )
                        break loop20;
                     EarlyExitException eee = new EarlyExitException( 20, input );
                     throw eee;
               }
               cnt20++;
            }
            while ( true );
            
         }
         
      }
      finally
      {
      }
   }
   


   // $ANTLR end "STRING"
   
   // $ANTLR start "WS"
   public final void mWS() throws RecognitionException
   {
      try
      {
         int _type = WS;
         int _channel = DEFAULT_TOKEN_CHANNEL;
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:118:15: ( ( ' ' | '\\t' |
         // '\\r' | '\\n' ) )
         // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:118:17: ( ' ' | '\\t' | '\\r'
         // | '\\n' )
         {
            if ( ( input.LA( 1 ) >= '\t' && input.LA( 1 ) <= '\n' )
               || input.LA( 1 ) == '\r' || input.LA( 1 ) == ' ' )
            {
               input.consume();
               
            }
            else
            {
               MismatchedSetException mse = new MismatchedSetException( null,
                                                                        input );
               recover( mse );
               throw mse;
            }
            
            _channel = HIDDEN;
            
         }
         
         state.type = _type;
         state.channel = _channel;
      }
      finally
      {
      }
   }
   


   // $ANTLR end "WS"
   
   @Override
   public void mTokens() throws RecognitionException
   {
      // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:8: ( EVERY | AFTER | BIWEEKLY
      // | YEARS | MONTHS | WEEKS | DAYS | MONTH | WEEKDAY_LIT | WEEKEND | MONDAY | TUESDAY | WEDNESDAY | THURSDAY |
      // FRIDAY | SATURDAY | SUNDAY | FIRST | SECOND | THIRD | FOURTH | FIFTH | LAST | OTHER | ST_S | NUM_ONE | NUM_TWO
      // | NUM_THREE | NUM_FOUR | NUM_FIVE | NUM_SIX | NUM_SEVEN | NUM_EIGHT | NUM_NINE | NUM_TEN | AND | IN | ON | OF |
      // THE | UNTIL | FOR | TIMES | DOT | MINUS | COMMA | INT | WS )
      int alt21 = 48;
      alt21 = dfa21.predict( input );
      switch ( alt21 )
      {
         case 1:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:10: EVERY
         {
            mEVERY();
            
         }
            break;
         case 2:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:16: AFTER
         {
            mAFTER();
            
         }
            break;
         case 3:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:22: BIWEEKLY
         {
            mBIWEEKLY();
            
         }
            break;
         case 4:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:31: YEARS
         {
            mYEARS();
            
         }
            break;
         case 5:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:37: MONTHS
         {
            mMONTHS();
            
         }
            break;
         case 6:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:44: WEEKS
         {
            mWEEKS();
            
         }
            break;
         case 7:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:50: DAYS
         {
            mDAYS();
            
         }
            break;
         case 8:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:55: MONTH
         {
            mMONTH();
            
         }
            break;
         case 9:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:61: WEEKDAY_LIT
         {
            mWEEKDAY_LIT();
            
         }
            break;
         case 10:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:73: WEEKEND
         {
            mWEEKEND();
            
         }
            break;
         case 11:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:81: MONDAY
         {
            mMONDAY();
            
         }
            break;
         case 12:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:88: TUESDAY
         {
            mTUESDAY();
            
         }
            break;
         case 13:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:96: WEDNESDAY
         {
            mWEDNESDAY();
            
         }
            break;
         case 14:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:106: THURSDAY
         {
            mTHURSDAY();
            
         }
            break;
         case 15:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:115: FRIDAY
         {
            mFRIDAY();
            
         }
            break;
         case 16:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:122: SATURDAY
         {
            mSATURDAY();
            
         }
            break;
         case 17:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:131: SUNDAY
         {
            mSUNDAY();
            
         }
            break;
         case 18:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:138: FIRST
         {
            mFIRST();
            
         }
            break;
         case 19:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:144: SECOND
         {
            mSECOND();
            
         }
            break;
         case 20:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:151: THIRD
         {
            mTHIRD();
            
         }
            break;
         case 21:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:157: FOURTH
         {
            mFOURTH();
            
         }
            break;
         case 22:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:164: FIFTH
         {
            mFIFTH();
            
         }
            break;
         case 23:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:170: LAST
         {
            mLAST();
            
         }
            break;
         case 24:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:175: OTHER
         {
            mOTHER();
            
         }
            break;
         case 25:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:181: ST_S
         {
            mST_S();
            
         }
            break;
         case 26:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:186: NUM_ONE
         {
            mNUM_ONE();
            
         }
            break;
         case 27:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:194: NUM_TWO
         {
            mNUM_TWO();
            
         }
            break;
         case 28:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:202: NUM_THREE
         {
            mNUM_THREE();
            
         }
            break;
         case 29:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:212: NUM_FOUR
         {
            mNUM_FOUR();
            
         }
            break;
         case 30:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:221: NUM_FIVE
         {
            mNUM_FIVE();
            
         }
            break;
         case 31:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:230: NUM_SIX
         {
            mNUM_SIX();
            
         }
            break;
         case 32:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:238: NUM_SEVEN
         {
            mNUM_SEVEN();
            
         }
            break;
         case 33:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:248: NUM_EIGHT
         {
            mNUM_EIGHT();
            
         }
            break;
         case 34:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:258: NUM_NINE
         {
            mNUM_NINE();
            
         }
            break;
         case 35:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:267: NUM_TEN
         {
            mNUM_TEN();
            
         }
            break;
         case 36:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:275: AND
         {
            mAND();
            
         }
            break;
         case 37:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:279: IN
         {
            mIN();
            
         }
            break;
         case 38:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:282: ON
         {
            mON();
            
         }
            break;
         case 39:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:285: OF
         {
            mOF();
            
         }
            break;
         case 40:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:288: THE
         {
            mTHE();
            
         }
            break;
         case 41:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:292: UNTIL
         {
            mUNTIL();
            
         }
            break;
         case 42:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:298: FOR
         {
            mFOR();
            
         }
            break;
         case 43:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:302: TIMES
         {
            mTIMES();
            
         }
            break;
         case 44:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:308: DOT
         {
            mDOT();
            
         }
            break;
         case 45:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:312: MINUS
         {
            mMINUS();
            
         }
            break;
         case 46:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:318: COMMA
         {
            mCOMMA();
            
         }
            break;
         case 47:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:324: INT
         {
            mINT();
            
         }
            break;
         case 48:
            // F:\\CppProjects\\Moloko\\src\\dev\\drsoran\\moloko\\grammar\\RecurrenceLexer.g:1:328: WS
         {
            mWS();
            
         }
            break;
         
      }
      
   }
   
   protected DFA3 dfa3 = new DFA3( this );
   
   protected DFA4 dfa4 = new DFA4( this );
   
   protected DFA5 dfa5 = new DFA5( this );
   
   protected DFA7 dfa7 = new DFA7( this );
   
   protected DFA21 dfa21 = new DFA21( this );
   
   static final String DFA3_eotS = "\3\uffff\1\6\3\uffff\1\11\2\uffff";
   
   static final String DFA3_eofS = "\12\uffff";
   
   static final String DFA3_minS = "\1\171\1\145\1\141\1\163\1\162\2\uffff\1\163\2\uffff";
   
   static final String DFA3_maxS = "\1\171\1\162\1\141\1\163\1\162\2\uffff\1\163\2\uffff";
   
   static final String DFA3_acceptS = "\5\uffff\1\3\1\4\1\uffff\1\1\1\2";
   
   static final String DFA3_specialS = "\12\uffff}>";
   
   static final String[] DFA3_transitionS =
   { "\1\1", "\1\2\14\uffff\1\3", "\1\4", "\1\5", "\1\7", "", "", "\1\10", "",
    "" };
   
   static final short[] DFA3_eot = DFA.unpackEncodedString( DFA3_eotS );
   
   static final short[] DFA3_eof = DFA.unpackEncodedString( DFA3_eofS );
   
   static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars( DFA3_minS );
   
   static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars( DFA3_maxS );
   
   static final short[] DFA3_accept = DFA.unpackEncodedString( DFA3_acceptS );
   
   static final short[] DFA3_special = DFA.unpackEncodedString( DFA3_specialS );
   
   static final short[][] DFA3_transition;
   
   static
   {
      int numStates = DFA3_transitionS.length;
      DFA3_transition = new short[ numStates ][];
      for ( int i = 0; i < numStates; i++ )
      {
         DFA3_transition[ i ] = DFA.unpackEncodedString( DFA3_transitionS[ i ] );
      }
   }
   
   
   class DFA3 extends DFA
   {
      
      public DFA3( BaseRecognizer recognizer )
      {
         this.recognizer = recognizer;
         this.decisionNumber = 3;
         this.eot = DFA3_eot;
         this.eof = DFA3_eof;
         this.min = DFA3_min;
         this.max = DFA3_max;
         this.accept = DFA3_accept;
         this.special = DFA3_special;
         this.transition = DFA3_transition;
      }
      


      @Override
      public String getDescription()
      {
         return "21:1: YEARS : ( 'years' | 'year' | 'yrs' | 'yr' );";
      }
   }
   
   static final String DFA4_eotS = "\3\uffff\1\6\3\uffff\1\11\2\uffff";
   
   static final String DFA4_eofS = "\12\uffff";
   
   static final String DFA4_minS = "\1\155\1\157\1\156\1\163\1\150\2\uffff\1\163\2\uffff";
   
   static final String DFA4_maxS = "\1\155\1\157\1\156\1\164\1\150\2\uffff\1\163\2\uffff";
   
   static final String DFA4_acceptS = "\5\uffff\1\3\1\4\1\uffff\1\1\1\2";
   
   static final String DFA4_specialS = "\12\uffff}>";
   
   static final String[] DFA4_transitionS =
   { "\1\1", "\1\2", "\1\3", "\1\5\1\4", "\1\7", "", "", "\1\10", "", "" };
   
   static final short[] DFA4_eot = DFA.unpackEncodedString( DFA4_eotS );
   
   static final short[] DFA4_eof = DFA.unpackEncodedString( DFA4_eofS );
   
   static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars( DFA4_minS );
   
   static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars( DFA4_maxS );
   
   static final short[] DFA4_accept = DFA.unpackEncodedString( DFA4_acceptS );
   
   static final short[] DFA4_special = DFA.unpackEncodedString( DFA4_specialS );
   
   static final short[][] DFA4_transition;
   
   static
   {
      int numStates = DFA4_transitionS.length;
      DFA4_transition = new short[ numStates ][];
      for ( int i = 0; i < numStates; i++ )
      {
         DFA4_transition[ i ] = DFA.unpackEncodedString( DFA4_transitionS[ i ] );
      }
   }
   
   
   class DFA4 extends DFA
   {
      
      public DFA4( BaseRecognizer recognizer )
      {
         this.recognizer = recognizer;
         this.decisionNumber = 4;
         this.eot = DFA4_eot;
         this.eof = DFA4_eof;
         this.min = DFA4_min;
         this.max = DFA4_max;
         this.accept = DFA4_accept;
         this.special = DFA4_special;
         this.transition = DFA4_transition;
      }
      


      @Override
      public String getDescription()
      {
         return "23:1: MONTHS : ( 'months' | 'month' | 'mons' | 'mon' );";
      }
   }
   
   static final String DFA5_eotS = "\3\uffff\1\6\3\uffff\1\11\2\uffff";
   
   static final String DFA5_eofS = "\12\uffff";
   
   static final String DFA5_minS = "\1\167\2\145\1\163\1\153\2\uffff\1\163\2\uffff";
   
   static final String DFA5_maxS = "\1\167\1\153\1\145\1\163\1\153\2\uffff\1\163\2\uffff";
   
   static final String DFA5_acceptS = "\5\uffff\1\3\1\4\1\uffff\1\1\1\2";
   
   static final String DFA5_specialS = "\12\uffff}>";
   
   static final String[] DFA5_transitionS =
   { "\1\1", "\1\2\5\uffff\1\3", "\1\4", "\1\5", "\1\7", "", "", "\1\10", "",
    "" };
   
   static final short[] DFA5_eot = DFA.unpackEncodedString( DFA5_eotS );
   
   static final short[] DFA5_eof = DFA.unpackEncodedString( DFA5_eofS );
   
   static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars( DFA5_minS );
   
   static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars( DFA5_maxS );
   
   static final short[] DFA5_accept = DFA.unpackEncodedString( DFA5_acceptS );
   
   static final short[] DFA5_special = DFA.unpackEncodedString( DFA5_specialS );
   
   static final short[][] DFA5_transition;
   
   static
   {
      int numStates = DFA5_transitionS.length;
      DFA5_transition = new short[ numStates ][];
      for ( int i = 0; i < numStates; i++ )
      {
         DFA5_transition[ i ] = DFA.unpackEncodedString( DFA5_transitionS[ i ] );
      }
   }
   
   
   class DFA5 extends DFA
   {
      
      public DFA5( BaseRecognizer recognizer )
      {
         this.recognizer = recognizer;
         this.decisionNumber = 5;
         this.eot = DFA5_eot;
         this.eof = DFA5_eof;
         this.min = DFA5_min;
         this.max = DFA5_max;
         this.accept = DFA5_accept;
         this.special = DFA5_special;
         this.transition = DFA5_transition;
      }
      


      @Override
      public String getDescription()
      {
         return "25:1: WEEKS : ( 'weeks' | 'week' | 'wks' | 'wk' );";
      }
   }
   
   static final String DFA7_eotS = "\23\uffff\1\40\1\42\1\44\1\46\1\50\1\uffff\1\52\1\54\1\56\1\60"
      + "\1\62\1\64\16\uffff\1\66\11\uffff";
   
   static final String DFA7_eofS = "\67\uffff";
   
   static final String DFA7_minS = "\2\141\1\145\1\141\1\160\1\145\1\143\1\157\1\145\1\156\1\154\1"
      + "\142\2\162\1\147\1\160\1\164\1\166\1\143\1\165\1\145\1\171\1\162"
      + "\1\143\1\uffff\1\151\1\165\1\164\1\157\2\145\16\uffff\1\145\11\uffff";
   
   static final String DFA7_maxS = "\1\163\1\165\1\145\1\141\1\165\1\145\1\143\1\157\1\145\2\156\1"
      + "\142\1\171\1\162\1\147\1\160\1\164\1\166\1\143\1\165\1\145\1\171"
      + "\1\162\1\143\1\uffff\1\151\1\165\1\164\1\157\2\145\16\uffff\1\145"
      + "\11\uffff";
   
   static final String DFA7_acceptS = "\30\uffff\1\11\6\uffff\1\1\1\2\1\12\1\13\1\14\1\15\1\3\1\4\1\5"
      + "\1\6\1\7\1\10\1\16\1\17\1\uffff\1\22\1\23\1\24\1\25\1\26\1\27\1"
      + "\30\1\20\1\21";
   
   static final String DFA7_specialS = "\67\uffff}>";
   
   static final String[] DFA7_transitionS =
   {
    "\1\4\2\uffff\1\10\1\uffff\1\2\3\uffff\1\1\2\uffff\1\3\1\7\1"
       + "\6\3\uffff\1\5", "\1\11\23\uffff\1\12", "\1\13", "\1\14",
    "\1\15\4\uffff\1\16", "\1\17", "\1\20", "\1\21", "\1\22", "\1\23",
    "\1\25\1\uffff\1\24", "\1\26", "\1\27\6\uffff\1\30", "\1\31", "\1\32",
    "\1\33", "\1\34", "\1\35", "\1\36", "\1\37", "\1\41", "\1\43", "\1\45",
    "\1\47", "", "\1\51", "\1\53", "\1\55", "\1\57", "\1\61", "\1\63", "", "",
    "", "", "", "", "", "", "", "", "", "", "", "", "\1\65", "", "", "", "",
    "", "", "", "", "" };
   
   static final short[] DFA7_eot = DFA.unpackEncodedString( DFA7_eotS );
   
   static final short[] DFA7_eof = DFA.unpackEncodedString( DFA7_eofS );
   
   static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars( DFA7_minS );
   
   static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars( DFA7_maxS );
   
   static final short[] DFA7_accept = DFA.unpackEncodedString( DFA7_acceptS );
   
   static final short[] DFA7_special = DFA.unpackEncodedString( DFA7_specialS );
   
   static final short[][] DFA7_transition;
   
   static
   {
      int numStates = DFA7_transitionS.length;
      DFA7_transition = new short[ numStates ][];
      for ( int i = 0; i < numStates; i++ )
      {
         DFA7_transition[ i ] = DFA.unpackEncodedString( DFA7_transitionS[ i ] );
      }
   }
   
   
   class DFA7 extends DFA
   {
      
      public DFA7( BaseRecognizer recognizer )
      {
         this.recognizer = recognizer;
         this.decisionNumber = 7;
         this.eot = DFA7_eot;
         this.eof = DFA7_eof;
         this.min = DFA7_min;
         this.max = DFA7_max;
         this.accept = DFA7_accept;
         this.special = DFA7_special;
         this.transition = DFA7_transition;
      }
      


      @Override
      public String getDescription()
      {
         return "29:1: MONTH : ( 'january' | 'jan' | 'february' | 'feb' | 'march' | 'mar' | 'april' | 'apr' | 'may' | 'june' | 'jun' | 'july' | 'jul' | 'august' | 'aug' | 'september' | 'sept' | 'sep' | 'october' | 'oct' | 'november' | 'nov' | 'december' | 'dec' );";
      }
   }
   
   static final String DFA21_eotS = "\23\uffff\1\57\12\uffff\1\66\1\70\7\uffff\1\74\3\uffff\1\75\4\uffff"
      + "\1\101\4\uffff\1\103\14\uffff\1\106\1\uffff\1\40\4\uffff";
   
   static final String DFA21_eofS = "\111\uffff";
   
   static final String DFA21_minS = "\1\11\1\141\1\146\1\145\2\uffff\1\141\1\145\1\141\1\uffff\1\141"
      + "\1\143\1\144\1\145\5\uffff\1\60\7\uffff\1\162\1\uffff\1\146\1\156"
      + "\1\145\2\uffff\1\143\4\uffff\1\145\3\uffff\1\145\4\uffff\1\164\1"
      + "\162\3\uffff\1\144\1\uffff\1\153\12\uffff\1\164\1\uffff\1\144\4"
      + "\uffff";
   
   static final String DFA21_maxS = "\1\171\1\166\1\165\1\162\2\uffff\1\157\1\153\1\145\1\uffff\1\165"
      + "\1\164\1\157\1\167\5\uffff\1\71\7\uffff\1\165\1\uffff\1\166\1\156"
      + "\1\145\2\uffff\1\166\4\uffff\1\145\3\uffff\1\162\4\uffff\1\164\1"
      + "\162\3\uffff\1\144\1\uffff\1\153\12\uffff\1\164\1\uffff\1\145\4"
      + "\uffff";
   
   static final String DFA21_acceptS = "\4\uffff\1\3\1\4\3\uffff\1\10\4\uffff\1\27\1\31\1\45\1\51\1\54"
      + "\1\uffff\1\56\1\57\1\60\1\1\1\41\1\2\1\44\1\uffff\1\17\3\uffff\1"
      + "\6\1\7\1\uffff\1\20\1\21\1\37\1\30\1\uffff\1\47\1\42\1\14\1\uffff"
      + "\1\33\1\43\1\53\1\55\2\uffff\1\22\1\26\1\36\1\uffff\1\13\1\uffff"
      + "\1\15\1\23\1\40\1\32\1\46\1\16\1\24\1\34\1\50\1\52\1\uffff\1\5\1"
      + "\uffff\1\25\1\35\1\11\1\12";
   
   static final String DFA21_specialS = "\111\uffff}>";
   
   static final String[] DFA21_transitionS =
   {
    "\2\26\2\uffff\1\26\22\uffff\1\26\13\uffff\1\24\1\23\1\22\1"
       + "\uffff\12\25\47\uffff\1\2\1\4\1\uffff\1\10\1\1\1\3\2\uffff\1"
       + "\20\1\11\1\uffff\1\16\1\6\1\14\1\13\2\uffff\1\17\1\12\1\15\1"
       + "\21\1\uffff\1\7\1\uffff\1\5", "\1\27\7\uffff\1\30\14\uffff\1\27",
    "\1\31\7\uffff\1\32\1\uffff\1\11\4\uffff\1\11",
    "\1\11\3\uffff\1\35\5\uffff\1\33\2\uffff\1\34", "", "",
    "\1\11\15\uffff\1\36", "\1\37\5\uffff\1\40", "\1\41\3\uffff\1\11", "",
    "\1\43\3\uffff\1\42\3\uffff\1\45\12\uffff\1\17\1\44",
    "\1\11\2\uffff\1\50\7\uffff\1\47\5\uffff\1\46",
    "\1\17\4\uffff\1\51\5\uffff\1\11",
    "\1\55\2\uffff\1\53\1\56\13\uffff\1\52\1\uffff\1\54", "", "", "", "", "",
    "\12\25", "", "", "", "", "", "", "", "\1\60\2\uffff\1\61", "",
    "\1\63\13\uffff\1\62\3\uffff\1\64", "\1\65", "\1\67", "", "",
    "\1\71\14\uffff\1\11\5\uffff\1\72", "", "", "", "", "\1\73", "", "", "",
    "\1\100\3\uffff\1\76\10\uffff\1\77", "", "", "", "", "\1\4", "\1\102", "",
    "", "", "\1\66", "", "\1\104", "", "", "", "", "", "", "", "", "", "",
    "\1\105", "", "\1\107\1\110", "", "", "", "" };
   
   static final short[] DFA21_eot = DFA.unpackEncodedString( DFA21_eotS );
   
   static final short[] DFA21_eof = DFA.unpackEncodedString( DFA21_eofS );
   
   static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars( DFA21_minS );
   
   static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars( DFA21_maxS );
   
   static final short[] DFA21_accept = DFA.unpackEncodedString( DFA21_acceptS );
   
   static final short[] DFA21_special = DFA.unpackEncodedString( DFA21_specialS );
   
   static final short[][] DFA21_transition;
   
   static
   {
      int numStates = DFA21_transitionS.length;
      DFA21_transition = new short[ numStates ][];
      for ( int i = 0; i < numStates; i++ )
      {
         DFA21_transition[ i ] = DFA.unpackEncodedString( DFA21_transitionS[ i ] );
      }
   }
   
   
   class DFA21 extends DFA
   {
      
      public DFA21( BaseRecognizer recognizer )
      {
         this.recognizer = recognizer;
         this.decisionNumber = 21;
         this.eot = DFA21_eot;
         this.eof = DFA21_eof;
         this.min = DFA21_min;
         this.max = DFA21_max;
         this.accept = DFA21_accept;
         this.special = DFA21_special;
         this.transition = DFA21_transition;
      }
      


      @Override
      public String getDescription()
      {
         return "1:1: Tokens : ( EVERY | AFTER | BIWEEKLY | YEARS | MONTHS | WEEKS | DAYS | MONTH | WEEKDAY_LIT | WEEKEND | MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY | SUNDAY | FIRST | SECOND | THIRD | FOURTH | FIFTH | LAST | OTHER | ST_S | NUM_ONE | NUM_TWO | NUM_THREE | NUM_FOUR | NUM_FIVE | NUM_SIX | NUM_SEVEN | NUM_EIGHT | NUM_NINE | NUM_TEN | AND | IN | ON | OF | THE | UNTIL | FOR | TIMES | DOT | MINUS | COMMA | INT | WS );";
      }
   }
   
}
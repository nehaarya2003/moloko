/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.drsoran.moloko.prefs;

import java.util.HashSet;
import java.util.Set;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * A {@link Preference} that displays a list of entries as a dialog.
 * <p>
 * This preference will store a set of strings into the SharedPreferences. This set will contain one or more values from
 * the {@link #setEntryValues(CharSequence[])} array.
 */
class MultiSelectListPreference extends DialogPreference
{
   private CharSequence[] mEntries;
   
   private CharSequence[] mEntryValues;
   
   private final Set< String > mValues = new HashSet< String >();
   
   private final Set< String > mNewValues = new HashSet< String >();
   
   private boolean mPreferenceChanged;
   
   
   
   public MultiSelectListPreference( Context context, AttributeSet attrs )
   {
      super( context, attrs );
   }
   
   
   
   public MultiSelectListPreference( Context context )
   {
      this( context, null );
   }
   
   
   
   /**
    * Sets the human-readable entries to be shown in the list. This will be shown in subsequent dialogs.
    * <p>
    * Each entry must have a corresponding index in {@link #setEntryValues(CharSequence[])}.
    * 
    * @param entries
    *           The entries.
    * @see #setEntryValues(CharSequence[])
    */
   public void setEntries( CharSequence[] entries )
   {
      mEntries = entries;
   }
   
   
   
   /**
    * @see #setEntries(CharSequence[])
    * @param entriesResId
    *           The entries array as a resource.
    */
   public void setEntries( int entriesResId )
   {
      setEntries( getContext().getResources().getTextArray( entriesResId ) );
   }
   
   
   
   /**
    * The list of entries to be shown in the list in subsequent dialogs.
    * 
    * @return The list as an array.
    */
   public CharSequence[] getEntries()
   {
      return mEntries;
   }
   
   
   
   /**
    * The array to find the value to save for a preference when an entry from entries is selected. If a user clicks on
    * the second item in entries, the second item in this array will be saved to the preference.
    * 
    * @param entryValues
    *           The array to be used as values to save for the preference.
    */
   public void setEntryValues( CharSequence[] entryValues )
   {
      mEntryValues = entryValues;
   }
   
   
   
   /**
    * @see #setEntryValues(CharSequence[])
    * @param entryValuesResId
    *           The entry values array as a resource.
    */
   public void setEntryValues( int entryValuesResId )
   {
      setEntryValues( getContext().getResources()
                                  .getTextArray( entryValuesResId ) );
   }
   
   
   
   /**
    * Returns the array of values to be saved for the preference.
    * 
    * @return The array of values.
    */
   public CharSequence[] getEntryValues()
   {
      return mEntryValues;
   }
   
   
   
   /**
    * Sets the value of the key. This should contain entries in {@link #getEntryValues()}.
    * 
    * @param values
    *           The values to set for the key.
    */
   public void setValues( Set< String > values )
   {
      mValues.clear();
      mValues.addAll( values );
      
      persistStringSet( values );
   }
   
   
   
   /**
    * Retrieves the current value of the key.
    */
   public Set< String > getValues()
   {
      return mValues;
   }
   
   
   
   /**
    * Returns the index of the given value (in the entry values array).
    * 
    * @param value
    *           The value whose index should be returned.
    * @return The index of the value, or -1 if not found.
    */
   public int findIndexOfValue( String value )
   {
      if ( value != null && mEntryValues != null )
      {
         for ( int i = mEntryValues.length - 1; i >= 0; i-- )
         {
            if ( mEntryValues[ i ].equals( value ) )
            {
               return i;
            }
         }
      }
      return -1;
   }
   
   
   
   @Override
   protected void onPrepareDialogBuilder( Builder builder )
   {
      super.onPrepareDialogBuilder( builder );
      
      if ( mEntries == null || mEntryValues == null )
      {
         throw new IllegalStateException( "MultiSelectListPreference requires an entries array and "
            + "an entryValues array." );
      }
      
      boolean[] checkedItems = getSelectedItems();
      builder.setMultiChoiceItems( mEntries,
                                   checkedItems,
                                   new DialogInterface.OnMultiChoiceClickListener()
                                   {
                                      @Override
                                      public void onClick( DialogInterface dialog,
                                                           int which,
                                                           boolean isChecked )
                                      {
                                         if ( isChecked )
                                         {
                                            mPreferenceChanged |= mNewValues.add( mEntryValues[ which ].toString() );
                                         }
                                         else
                                         {
                                            mPreferenceChanged |= mNewValues.remove( mEntryValues[ which ].toString() );
                                         }
                                      }
                                   } );
      mNewValues.clear();
      mNewValues.addAll( mValues );
   }
   
   
   
   public boolean[] getSelectedItems()
   {
      final CharSequence[] entries = mEntryValues;
      final int entryCount = entries.length;
      final Set< String > values = mValues;
      boolean[] result = new boolean[ entryCount ];
      
      for ( int i = 0; i < entryCount; i++ )
      {
         result[ i ] = values.contains( entries[ i ].toString() );
      }
      
      return result;
   }
   
   
   
   @Override
   protected void onDialogClosed( boolean positiveResult )
   {
      super.onDialogClosed( positiveResult );
      
      if ( positiveResult && mPreferenceChanged )
      {
         final Set< String > values = mNewValues;
         if ( callChangeListener( values ) )
         {
            setValues( values );
         }
      }
      mPreferenceChanged = false;
   }
   
   
   
   @Override
   protected Object onGetDefaultValue( TypedArray a, int index )
   {
      final Set< String > result = new HashSet< String >();
      final String defaultValues = a.getString( index );
      
      if ( defaultValues != null )
      {
         final String[] splittedValues = TextUtils.split( defaultValues, "," );
         final int valuesCount = splittedValues.length;
         for ( int i = 0; i < valuesCount; i++ )
         {
            result.add( splittedValues[ i ].toString() );
         }
      }
      
      return result;
   }
   
   
   
   @SuppressWarnings( "unchecked" )
   @Override
   protected void onSetInitialValue( boolean restoreValue, Object defaultValue )
   {
      setValues( restoreValue ? getPersistedStringSet( mValues )
                             : (Set< String >) defaultValue );
   }
   
   
   
   @Override
   protected Parcelable onSaveInstanceState()
   {
      final Parcelable superState = super.onSaveInstanceState();
      if ( isPersistent() )
      {
         // No need to save instance state
         return superState;
      }
      
      final SavedState myState = new SavedState( superState );
      myState.values = getValues();
      return myState;
   }
   
   
   
   protected void persistStringSet( Set< String > values )
   {
      persistString( createValuesString( values ) );
   }
   
   
   
   protected Set< String > getPersistedStringSet( Set< String > values )
   {
      final String persistedValuesString = getPersistedString( createValuesString( values ) );
      final String[] splittedValues = TextUtils.split( persistedValuesString,
                                                       "," );
      
      final Set< String > persistedValues = new HashSet< String >( splittedValues.length );
      for ( String listId : splittedValues )
      {
         persistedValues.add( listId );
      }
      
      return persistedValues;
   }
   
   
   
   private String createValuesString( Set< String > values )
   {
      return TextUtils.join( ",", values );
   }
   
   
   private static class SavedState extends BaseSavedState
   {
      Set< String > values;
      
      
      
      public SavedState( Parcel source )
      {
         super( source );
         values = new HashSet< String >();
         
         String[] strings = new String[ 0 ];
         source.readStringArray( strings );
         
         final int stringCount = strings.length;
         for ( int i = 0; i < stringCount; i++ )
         {
            values.add( strings[ i ] );
         }
      }
      
      
      
      public SavedState( Parcelable superState )
      {
         super( superState );
      }
      
      
      
      @Override
      public void writeToParcel( Parcel dest, int flags )
      {
         super.writeToParcel( dest, flags );
         dest.writeStringArray( values.toArray( new String[ 0 ] ) );
      }
      
      @SuppressWarnings( "unused" )
      public static final Parcelable.Creator< SavedState > CREATOR = new Parcelable.Creator< SavedState >()
      {
         @Override
         public SavedState createFromParcel( Parcel in )
         {
            return new SavedState( in );
         }
         
         
         
         @Override
         public SavedState[] newArray( int size )
         {
            return new SavedState[ size ];
         }
      };
   }
}

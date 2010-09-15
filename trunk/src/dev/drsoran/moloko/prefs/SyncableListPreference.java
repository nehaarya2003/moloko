package dev.drsoran.moloko.prefs;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import dev.drsoran.moloko.MolokoApp;
import dev.drsoran.moloko.R;


public class SyncableListPreference extends ListPreference implements
         OnClickListener, OnSharedPreferenceChangeListener,
         OnPreferenceChangeListener
{
   private CheckBox checkBox;
   
   private TextView settingSourceText;
   
   protected final String syncWithRtmKey;
   
   

   public SyncableListPreference( Context context, AttributeSet attrs )
   {
      super( context, attrs );
      
      setLayoutResource( R.layout.moloko_prefs_rtm_list_preference );
      setDialogLayoutResource( R.layout.moloko_prefs_rtm_sync_button );
      
      final TypedArray array = context.obtainStyledAttributes( attrs,
                                                               R.styleable.SyncableListPreference,
                                                               0,
                                                               0 );
      
      syncWithRtmKey = array.getString( R.styleable.SyncableListPreference_syncWithRtmKey );
      
      array.recycle();
      
      if ( syncWithRtmKey == null )
         throw new IllegalStateException( "SyncableListPreference requires a syncWithRtmKey attribute." );
   }
   


   @Override
   protected void onAttachedToHierarchy( PreferenceManager preferenceManager )
   {
      super.onAttachedToHierarchy( preferenceManager );
      
      final SharedPreferences prefs = getSharedPreferences();
      
      if ( prefs != null )
      {
         prefs.registerOnSharedPreferenceChangeListener( this );
         setOnPreferenceChangeListener( this );
      }
   }
   


   @Override
   public CharSequence getSummary()
   {
      final CharSequence summary = super.getSummary();
      final CharSequence entry = getEntry();
      
      if ( summary == null || entry == null )
      {
         return summary;
      }
      else
      {
         return String.format( summary.toString(), entry.toString() );
      }
   }
   


   @Override
   public void setSummary( CharSequence summary )
   {
      super.setSummary( summary );
      
      final CharSequence lSummary = getSummary();
      
      if ( summary == null && lSummary != null )
      {
         setSummary( null );
      }
      else if ( summary != null && !summary.equals( lSummary ) )
      {
         setSummary( summary.toString() );
      }
   }
   


   @Override
   protected void onBindView( View view )
   {
      super.onBindView( view );
      
      settingSourceText = (TextView) view.findViewById( R.id.sync_list_setting_source_text );
      
      updateUi();
   }
   


   @Override
   protected void onBindDialogView( View view )
   {
      super.onBindDialogView( view );
      
      checkBox = (CheckBox) view.findViewById( R.id.sync_check );
      
      if ( checkBox != null )
      {
         checkBox.setOnClickListener( this );
      }
   }
   


   public void onClick( View v )
   {
      final boolean isChecked = checkBox.isChecked();
      
      setSyncWithRtm( isChecked );
      
      if ( isChecked )
      {
         /*
          * Clicking on the check box simulates the negative button click, and dismisses the dialog. This is needed
          * cause we write the value from RTM and a positive result would write the current list value.
          */
         onClick( getDialog(), DialogInterface.BUTTON_NEGATIVE );
         getDialog().dismiss();
      }
   }
   


   public void onSharedPreferenceChanged( SharedPreferences sharedPreferences,
                                          String key )
   {
      if ( key.equals( getKey() ) && isSyncWithRtm() )
         setValue( sharedPreferences.getString( getKey(), getValue() ) );
   }
   


   public boolean onPreferenceChange( Preference preference, Object newValue )
   {
      updateUi();
      notifyChanged();
      return true;
   }
   


   private void updateUi()
   {
      if ( settingSourceText != null )
      {
         if ( isSyncWithRtm()
            && MolokoApp.getSettings().getRtmSettings() != null )
         {
            settingSourceText.setText( R.string.g_settings_src_rtm );
            settingSourceText.setCompoundDrawables( createLeftDrawable( R.drawable.icon_refresh_white_small ),
                                                    null,
                                                    null,
                                                    null );
         }
         else
         {
            settingSourceText.setText( R.string.g_settings_src_local );
            settingSourceText.setCompoundDrawables( createLeftDrawable( R.drawable.icon_user_white_small ),
                                                    null,
                                                    null,
                                                    null );
         }
      }
   }
   


   protected void setSyncWithRtm( boolean value )
   {
      final SharedPreferences prefs = getSharedPreferences();
      
      if ( prefs != null && callChangeListener( Boolean.valueOf( value ) ) )
      {
         prefs.edit().putBoolean( syncWithRtmKey, value ).commit();
         
         // if the user checked the box we have reset the current value
         // cause we loaded the RTM setting in the background and must
         // notify the list.
         if ( value )
         {
            setValue( prefs.getString( getKey(), getValue() ) );
         }
      }
   }
   


   protected boolean isSyncWithRtm()
   {
      final SharedPreferences prefs = getSharedPreferences();
      
      if ( prefs != null )
      {
         return prefs.getBoolean( syncWithRtmKey, true );
      }
      
      return true;
   }
   


   @Override
   protected void onPrepareDialogBuilder( Builder builder )
   {
      super.onPrepareDialogBuilder( builder );
      
      if ( checkBox != null )
         checkBox.setChecked( isSyncWithRtm() );
   }
   


   @Override
   protected void onDialogClosed( boolean positiveResult )
   {
      // in case of a positive result a list item has been
      // clicked an we switch to user setting
      if ( positiveResult )
         setSyncWithRtm( false );
      
      super.onDialogClosed( positiveResult );
   }
   


   private Drawable createLeftDrawable( int resId )
   {
      final Drawable[] drawables = settingSourceText.getCompoundDrawables();
      
      BitmapDrawable drawable = null;
      
      if ( drawables[ 0 ] != null )
      {
         drawable = new BitmapDrawable( getContext().getResources()
                                                    .openRawResource( resId ) );
         
         if ( drawable != null )
            drawable.setBounds( drawables[ 0 ].getBounds() );
      }
      
      return drawable;
   }
}

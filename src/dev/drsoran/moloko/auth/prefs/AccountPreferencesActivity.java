package dev.drsoran.moloko.auth.prefs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;

import com.mdt.rtm.ApplicationInfo;
import com.mdt.rtm.data.RtmAuth;

import dev.drsoran.moloko.R;


public class AccountPreferencesActivity extends PreferenceActivity implements
         Preference.OnPreferenceChangeListener,
         DialogInterface.OnCancelListener
{
   
   private final static String TAG = AccountPreferencesActivity.class.getSimpleName();
   
   public ProgressDialog PROGRESS_DLG = null;
   
   public AlertDialog ALERT_DLG = null;      
   
   
   private final class DlgType
   {
      public static final int PROGRESS = 0;
      
      public static final int ALERT = 1;
   }
   
   private int activeDlgId = -1;
   
   
   @Override
   protected void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      
      addPreferencesFromResource( R.xml.account_preferences_activity );
      
//      ListPreference permPref = getPermissionList();
//      
//      if ( permPref != null )
//      {
//         permPref.setOnPreferenceChangeListener( this );
//      }
//      
//      registerForContextMenu( getListView() );
//      
//      String permValue = getPermissionList().getValue();
//      
//      // if the value is null we do not have preferences yet.
//      if ( permValue == null )
//      {
//         permValue = RtmAuth.Perms.nothing.toString();
//      }
//      
      // asyncService = new AsyncRtmService( this,
      // getRtmApplicationInfo( this ),
      // RtmAuth.Perms.valueOf( permValue ) );
   }
   


   @Override
   protected void onDestroy()
   {
      super.onDestroy();
   }
   


   public static ApplicationInfo getRtmApplicationInfo( Context context )
   {
      ApplicationInfo applicationInfo = null;
      
      final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
      
      if ( prefs != null )
      {
         applicationInfo = new ApplicationInfo( prefs.getString( context.getString( R.string.key_api_key ),
                                                                 null ),
                                                prefs.getString( context.getString( R.string.key_shared_secret ),
                                                                 null ),
                                                null,
                                                prefs.getString( context.getString( R.string.key_authToken ),
                                                                 null ) );
      }
      
      return applicationInfo;
   }
   


   public static RtmAuth.Perms getRtmPermission( Context context )
   {
      RtmAuth.Perms perms = RtmAuth.Perms.nothing;
      
      final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
      
      if ( prefs != null )
      {
         perms = RtmAuth.Perms.valueOf( prefs.getString( context.getString( R.string.key_permission ),
                                                         RtmAuth.Perms.nothing.toString() ) );
      }
      
      return perms;
   }
   


   public static String getRtmAuthToken( Context context )
   {
      String token = null;
      
      final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context );
      
      if ( prefs != null )
      {
         token = prefs.getString( context.getString( R.string.key_authToken ),
                                  null );
      }
      
      return token;
   }
   


   @Override
   public boolean onContextItemSelected( MenuItem item )
   {
      switch ( item.getItemId() )
      {
         case R.id.auth_prefs_ctxmenu_perm_check_auth_token:
            activateDialog( DlgType.PROGRESS,
                            getString( R.string.phr_please_wait ),
                            getString( R.string.auth_pref_dlg_check_auth_token,
                                       RtmPermissionPreference.getPermissionEntry( getResources(),
                                                                                   getPermissionList().getValue() ) ) );
            
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
            
//            cancelOperationHandle = asyncService.auth()
//                                                .checkAuthToken( prefs.getString( getString( R.string.key_authToken ),
//                                                                                  "" ),
//                                                                 new ResultCallback< RtmAuth >()
//                                                                 {
//                                                                    public void run()
//                                                                    {
//                                                                       if ( exception == null )
//                                                                       {
//                                                                          onCheckAuthToken( result,
//                                                                                            true );
//                                                                       }
//                                                                       else if ( exception != null
//                                                                          && ( AsyncRtmService.getExceptionCode( exception ) == RtmServiceConstants.RtmErrorCodes.INVALID_AUTH_TOKEN || AsyncRtmService.getExceptionCode( exception ) == RtmServiceConstants.RtmErrorCodes.INVALID_API_KEY ) )
//                                                                       {
//                                                                          onCheckAuthToken( null,
//                                                                                            false );
//                                                                       }
//                                                                       else
//                                                                       {
//                                                                          activateDialog( DlgType.ALERT,
//                                                                                          getString( R.string.err_error ),
//                                                                                          getErrorMessageWithException( exception ) );
//                                                                       }
//                                                                    }
//                                                                 } );
            return true;
         default :
            return super.onContextItemSelected( item );
      }
   }
   


   @Override
   public void onCreateContextMenu( ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo )
   {
      if ( menuInfo instanceof AdapterView.AdapterContextMenuInfo )
      {
         final Object tag = ( (AdapterView.AdapterContextMenuInfo) menuInfo ).targetView.getTag();
         
         if ( tag instanceof String )
         {
            final String tagStr = (String) tag;
            
            // Check if the view has the right tag
            if ( tagStr.equals( RtmPermissionPreference.TAG ) )
            {
               final RtmPermissionPreference permissionPreference = getPermissionList();
               
               // Only if we have a token we can validate it
               if ( permissionPreference.hasAuthToken() )
               {
                  final MenuInflater inflater = getMenuInflater();
                  inflater.inflate( R.menu.auth_prefs_ctxmenu_perm, menu );
                  return;
               }
            }
         }
      }
      
      super.onCreateContextMenu( menu, v, menuInfo );
   }
   


   @Override
   protected Dialog onCreateDialog( int id )
   {
      Dialog dialog = null;
      
      switch ( id )
      {
         case DlgType.PROGRESS:
            dialog = PROGRESS_DLG;
            break;
         case DlgType.ALERT:
            dialog = ALERT_DLG;
            break;
         default :
            break;
      }
      
      if ( dialog != null )
      {
         activeDlgId = id;
      }
      
      return dialog;
   }
   


   public void onCancel( DialogInterface dialog )
   {
//      if ( cancelOperationHandle != null )
//      {
//         if ( cancelOperationHandle.cancel( true ) )
//         {
//            Log.d( TAG, "Operation cancelled." );
//         }
//         else
//         {
//            Log.d( TAG, "Failed to cancel operation." );
//         }
//      }
//      
//      cancelOperationHandle = null;
   }
   


   private void activateDialog( int id, final String title, final String msg )
   {
      if ( activeDlgId != -1 )
      {
         removeDialog( activeDlgId );
      }
      
      switch ( id )
      {
         case DlgType.PROGRESS:
            PROGRESS_DLG = new ProgressDialog( this );
            PROGRESS_DLG.setProgressStyle( ProgressDialog.STYLE_SPINNER );
            PROGRESS_DLG.setTitle( title );
            PROGRESS_DLG.setMessage( msg );
            PROGRESS_DLG.setCancelable( false );
            break;
         case DlgType.ALERT:
            ALERT_DLG = new AlertDialog.Builder( this ).create();
            ALERT_DLG.setTitle( title );
            ALERT_DLG.setMessage( msg );
            break;
         default :
            break;
      }
      
      showDialog( id );
   }
   


   @Override
   protected void onActivityResult( int requestCode, int resultCode, Intent data )
   {
      // switch ( requestCode )
      // {
      // case RtmWebLoginActivity.ReqType.OPEN_URL:
      // switch ( resultCode )
      // {
      // case RtmWebLoginActivity.ReturnCode.SUCCESS:
      // cancelOperationHandle = asyncService.auth()
      // .completeAuthorization( new ResultCallback< String >( data.getExtras() )
      // {
      // public void run()
      // {
      // if ( exception == null )
      // onAuthTokenReceived( result,
      // extraData.getString( getString( R.string.auth_pref_permission_key ) ) );
      // else
      // activateDialog( DlgType.ALERT,
      // getString( R.string.err_error ),
      // getErrorMessageWithException( exception ) );
      // }
      // } );
      // break;
      // default :
      // activateDialog( DlgType.ALERT,
      // getString( R.string.err_error ),
      // getString( R.string.auth_pref_err_rtmWebAccess ) );
      // break;
      // }
      // break;
      //         
      // default :
      // super.onActivityResult( requestCode, resultCode, data );
      // break;
      // }
   }
   


   public boolean onPreferenceChange( Preference preference, Object newValue )
   {
      boolean takeIt = true;
      
      // Here we drop the new value cause we have to retrieve the new
      // auth code from RTM for the permission.
      if ( newValue instanceof String )
      {
         final String newPermissionValue = (String) newValue;
         
         // If the user decided to allow nothing we simply take it. This
         // needs no check.
         if ( newPermissionValue.equals( getResources().getStringArray( R.array.rtm_permissions_values )[ 0 ] ) )
         {
            clearAuthToken();
            takeIt = true;
         }
         else
         {
            takeIt = false;
            
            // We store the requested permission level in a bundle so that
            // subsequent calls have access to it.
            final Bundle bundle = new Bundle();
            bundle.putString( getString( R.string.key_permission ),
                              newPermissionValue );
            
            final String newPermissionRequest = RtmPermissionPreference.getPermissionEntry( getResources(),
                                                                                            newPermissionValue );
            
            activateDialog( DlgType.PROGRESS,
                            getString( R.string.phr_please_wait ),
                            getString( R.string.auth_dlg_get_auth_token,
                                       newPermissionRequest ) );
            
//            cancelOperationHandle = asyncService.auth()
//                                                .beginAuthorization( RtmAuth.Perms.valueOf( newPermissionValue ),
//                                                                     new ResultCallback< String >( bundle )
//                                                                     {
//                                                                        public void run()
//                                                                        {
//                                                                           if ( exception == null )
//                                                                           {
//                                                                              onLoginUrlReceived( result,
//                                                                                                  bundle );
//                                                                           }
//                                                                           else
//                                                                           {
//                                                                              activateDialog( DlgType.ALERT,
//                                                                                              getString( R.string.err_error ),
//                                                                                              getErrorMessageWithException( exception ) );
//                                                                           }
//                                                                        }
//                                                                     } );
         }
      }
      
      return takeIt;
   }
   


   private void onLoginUrlReceived( final String loginUrl, final Bundle bundle )
   {
      Log.d( TAG, "Login-Url: " + loginUrl );
      
      if ( loginUrl != null )
      {
         // final Intent intent = new Intent( android.content.Intent.ACTION_VIEW,
         // Uri.parse( loginUrl ),
         // AccountPreferencesActivity.this,
         // RtmWebLoginActivity.class );
         // intent.putExtras( bundle );
         // startActivityForResult( intent, RtmWebLoginActivity.ReqType.OPEN_URL );
      }
      else
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_error ),
                         getErrorMessageWithObjects( getString( android.R.string.httpErrorBadUrl ) ) );
      }
   }
   


   private void onAuthTokenReceived( final String authToken,
                                     final String permissionLevel )
   {
      Log.d( TAG, "Auth-Token: " + authToken );
      
      if ( authToken == null )
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_error ),
                         getErrorMessageWithObjects( getString( R.string.auth_pref_err_invalid_auth_token ) ) );
      }
      else if ( commitNewPermissionLevel( authToken, permissionLevel ) )
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_success ),
                         getString( R.string.auth_pref_dlg_finsihed,
                                    RtmPermissionPreference.getPermissionEntry( getResources(),
                                                                                permissionLevel ) ) );
      }
   }
   


   private void onCheckAuthToken( RtmAuth result, boolean valid )
   {
      Log.d( TAG, "Auth-Token checked" );
      
      if ( !valid )
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_success ),
                         getString( R.string.auth_pref_dlg_check_auth_token_invalid ) );
         clearAuthToken();
      }
      else if ( result == null )
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_error ),
                         getString( R.string.auth_pref_err_check_auth_token_failed ) );
      }
      else
      {
         final String permsStr = result.getPerms().toString();
         
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_success ),
                         getString( R.string.auth_pref_dlg_check_auth_token_finished,
                                    RtmPermissionPreference.getPermissionEntry( getResources(),
                                                                                permsStr ),
                                    result.getUser().getUsername() ) );
         
         // Check if there is a permission difference to our settings.
         if ( !permsStr.equals( getPermissionList().getValue() ) )
         {
            commitNewPermissionLevel( result.getToken(), permsStr );
         }
      }
   }
   


   private void clearAuthToken()
   {
      final String permissionLevelNone = getResources().getStringArray( R.array.rtm_permissions_values )[ 0 ];
      
      // Set new selected permission level
      SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences( this )
                                                              .edit();
      
      // set permission to nothing
      prefsEditor.putString( getString( R.string.key_permission ),
                             permissionLevelNone );
      
      // remove auth token
      prefsEditor.remove( getString( R.string.key_authToken ) );
      
      // remove auth token date
      prefsEditor.remove( getString( R.string.key_authToken_date ) );
      
      // store everything
      if ( prefsEditor.commit() )
      {
         getPermissionList().setValue( permissionLevelNone );
      }
   }
   


   private boolean commitNewPermissionLevel( final String authToken,
                                             final String permissionLevel )
   {
//      if ( asyncService != null )
//      {
//         // try
//         // {
//         // asyncService.updateService( getRtmApplicationInfo( this ),
//         // RtmAuth.Perms.valueOf( permissionLevel ) );
//         // }
//         // catch ( RemoteException e )
//         // {
//         // activateDialog( DlgType.ALERT,
//         // getString( R.string.err_error ),
//         // getString( R.string.auth_pref_dlg_update_svc_failed,
//         // AsyncRtmService.getExceptionCause( e ) ) );
//         // return false;
//         // }
//      }
      
      // Set new selected permission level
      SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences( this )
                                                              .edit();
      
      prefsEditor.putString( getString( R.string.key_permission ),
                             permissionLevel );
      
      // set auth token
      prefsEditor.putString( getString( R.string.key_authToken ), authToken );
      
      // set auth token date
      prefsEditor.putLong( getString( R.string.key_authToken_date ),
                           System.currentTimeMillis() );
      
      final boolean ok = prefsEditor.commit();
      
      // store everything
      if ( ok )
      {
         getPermissionList().setValue( permissionLevel );
      }
      else
      {
         activateDialog( DlgType.ALERT,
                         getString( R.string.err_error ),
                         getErrorMessageWithObjects( getString( R.string.err_saving_preferences ) ) );
      }
      
      return ok;
   }
   


   private RtmPermissionPreference getPermissionList()
   {
      RtmPermissionPreference listPreference = null;
      
      Preference permPref = findPreference( getString( R.string.key_permission ) );
      
      if ( permPref instanceof ListPreference )
      {
         listPreference = (RtmPermissionPreference) permPref;
      }
      
      return listPreference;
   }
   


   private String getErrorMessageWithException( final Exception e )
   {
      return null;
//      return getString( R.string.auth_pref_dlg_error,
//                        AsyncRtmService.getExceptionCause( e ) );
      
   }
   


   private String getErrorMessageWithObjects( final Object... o )
   {
      return getString( R.string.auth_pref_dlg_error, o );
   }
}

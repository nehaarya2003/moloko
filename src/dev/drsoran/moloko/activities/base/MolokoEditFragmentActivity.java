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

package dev.drsoran.moloko.activities.base;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import dev.drsoran.moloko.ApplyChangesInfo;
import dev.drsoran.moloko.IEditFragment;
import dev.drsoran.moloko.MolokoApp;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.ValidationResult;
import dev.drsoran.moloko.content.ActionItemListApplier;
import dev.drsoran.moloko.content.ContentProviderActionItemList;
import dev.drsoran.moloko.util.Intents;
import dev.drsoran.moloko.util.UIUtils;


public abstract class MolokoEditFragmentActivity extends MolokoFragmentActivity
{
   
   @Override
   public void onAlertDialogFragmentClick( int dialogId, String tag, int which )
   {
      switch ( dialogId )
      {
         case R.id.dlg_read_only_access:
            handleReadOnlyAccessDialogClick( which );
            break;
         
         case R.id.dlg_apply_changes:
            handleApplyChangesDialogClick( tag, which );
            break;
         
         case R.id.dlg_cancel_with_changes:
            if ( !canceledByFragmentTag( which, tag ) )
               handleCancelWithChangesDialogClick( tag, which );
            break;
         
         case R.id.dlg_delete_element:
            handleDeleteElementDialogClick( tag, which );
            break;
         
         default :
            super.onAlertDialogFragmentClick( dialogId, tag, which );
            break;
      }
   }
   
   
   
   public boolean applyModifications( ApplyChangesInfo applyInfo )
   {
      boolean ok = true;
      
      if ( isReadOnlyAccess() )
      {
         ok = false;
         showOnlyReadableDatabaseAccessDialog();
      }
      else
      {
         if ( applyInfo == null || applyInfo.hasDatabaseError() )
         {
            ok = false;
            showApplyChangesInfoAsToast( applyInfo, false );
         }
         else if ( applyInfo.hasChanges() )
         {
            final ContentProviderActionItemList actionItemList = applyInfo.getActionItems();
            
            ok = new ActionItemListApplier( this, MolokoApp.getExecutor() ).applyNonThrowing( actionItemList );
            showApplyChangesInfoAsToast( applyInfo, ok );
         }
      }
      
      return ok;
   }
   
   
   
   private void showApplyChangesInfoAsToast( ApplyChangesInfo applyInfo,
                                             boolean success )
   {
      Toast.makeText( this,
                      success ? applyInfo.getApplySuccessMessage()
                             : applyInfo.getApplyFailedMessage(),
                      Toast.LENGTH_LONG ).show();
   }
   
   
   
   public boolean validateFragment( IEditFragment fragment )
   {
      final ValidationResult validationResult = fragment.validate();
      final boolean valid = validationResult.isOk();
      if ( !valid )
      {
         showValidationErrorAsToast( validationResult );
      }
      
      return valid;
   }
   
   
   
   private void showValidationErrorAsToast( ValidationResult validationResult )
   {
      Toast.makeText( this,
                      validationResult.getValidationErrorMessage(),
                      Toast.LENGTH_LONG ).show();
      
      if ( validationResult.getRequestFocusOnValidationError() != null )
      {
         validationResult.getRequestFocusOnValidationError().requestFocus();
      }
   }
   
   
   
   public boolean finishFragmentEditing( IEditFragment editFragment )
   {
      boolean ok = validateFragment( editFragment );
      
      ok = ok && applyModifications( editFragment.onFinishEditing() );
      
      return ok;
   }
   
   
   
   public boolean cancelFragmentEditing( IEditFragment editFragment )
   {
      boolean canceled = !editFragment.hasChanges();
      
      if ( !canceled )
      {
         UIUtils.showCancelWithChangesDialog( this );
      }
      
      return canceled;
   }
   
   
   
   protected void showOnlyReadableDatabaseAccessDialog()
   {
      UIUtils.showReadOnlyAccessDialog( this );
   }
   
   
   
   private void handleReadOnlyAccessDialogClick( int which )
   {
      if ( which == Dialog.BUTTON_POSITIVE )
      {
         startActivity( Intents.createOpenSystemAccountSettingsIntent() );
      }
   }
   
   
   
   protected void handleApplyChangesDialogClick( String tag, int which )
   {
   }
   
   
   
   protected void handleCancelWithChangesDialogClick( String tag, int which )
   {
      if ( which == Dialog.BUTTON_POSITIVE )
      {
         finish();
      }
   }
   
   
   
   protected void handleDeleteElementDialogClick( String tag, int which )
   {
   }
   
   
   
   @Deprecated
   private boolean canceledByFragmentTag( int which, String tag )
   {
      if ( which == Dialog.BUTTON_POSITIVE )
      {
         final Fragment frag = findAddedFragmentByTag( tag );
         if ( frag != null )
            removeFragmentByTag( tag,
                                 FragmentTransaction.TRANSIT_FRAGMENT_CLOSE );
         
         return frag != null;
      }
      
      return false;
   }
}

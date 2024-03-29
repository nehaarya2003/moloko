/* 
 *	Copyright (c) 2011 Ronny R�hricht
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

package dev.drsoran.moloko.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.ImageButton;


public class ToggleImageButton extends ImageButton implements Checkable
{
   public interface OnCheckedChangeListener
   {
      void onCheckedChanged( ToggleImageButton button, boolean checked );
   }
   
   private boolean checked = false;
   
   private OnCheckedChangeListener onCheckedChangeListener;
   
   private boolean broadcasting = false;
   
   private static final int[] CHECKED_STATE_SET =
   { android.R.attr.state_checked };
   
   

   public ToggleImageButton( Context context )
   {
      super( context );
   }
   


   public ToggleImageButton( Context context, AttributeSet attrs, int defStyle )
   {
      super( context, attrs, defStyle );
   }
   


   public ToggleImageButton( Context context, AttributeSet attrs )
   {
      super( context, attrs );
   }
   


   public boolean isChecked()
   {
      return checked;
   }
   


   public void setChecked( boolean checked )
   {
      if ( checked != this.checked )
      {
         this.checked = checked;
         refreshDrawableState();
         
         // Avoid infinite recursions if setChecked() is called from a listener
         if ( broadcasting )
         {
            return;
         }
         
         broadcasting = true;
         if ( onCheckedChangeListener != null )
         {
            onCheckedChangeListener.onCheckedChanged( this, checked );
         }
         
         broadcasting = false;
      }
   }
   


   public void toggle()
   {
      setChecked( !checked );
   }
   


   public void setOnCheckedChangeListener( OnCheckedChangeListener listener )
   {
      onCheckedChangeListener = listener;
   }
   


   @Override
   public boolean performClick()
   {
      toggle();
      return super.performClick();
   }
   


   @Override
   public boolean dispatchPopulateAccessibilityEvent( AccessibilityEvent event )
   {
      boolean populated = super.dispatchPopulateAccessibilityEvent( event );
      
      if ( !populated )
      {
         event.setChecked( checked );
      }
      
      return populated;
   }
   


   @Override
   public int[] onCreateDrawableState( int extraSpace )
   {
      final int[] drawableState = super.onCreateDrawableState( extraSpace + 1 );
      if ( isChecked() )
      {
         mergeDrawableStates( drawableState, CHECKED_STATE_SET );
      }
      return drawableState;
   }
   


   @Override
   protected boolean verifyDrawable( Drawable who )
   {
      return super.verifyDrawable( who ) || who == getBackground();
   }
   


   @Override
   protected void drawableStateChanged()
   {
      super.drawableStateChanged();
      
      if ( getBackground() != null )
      {
         final int[] myDrawableState = getDrawableState();
         
         // Set the state of the Drawable
         getBackground().setState( myDrawableState );
         
         invalidate();
      }
   }
   


   @Override
   public Parcelable onSaveInstanceState()
   {
      final Parcelable superState = super.onSaveInstanceState();
      final SavedState ss = new SavedState( superState );
      
      ss.checked = isChecked();
      return ss;
   }
   


   @Override
   public void onRestoreInstanceState( Parcelable state )
   {
      final SavedState ss = (SavedState) state;
      
      super.onRestoreInstanceState( ss.getSuperState() );
      setChecked( ss.checked );
      requestLayout();
   }
   
   
   final static class SavedState extends BaseSavedState
   {
      boolean checked;
      
      

      SavedState( Parcelable superState )
      {
         super( superState );
      }
      


      private SavedState( Parcel in )
      {
         super( in );
         checked = (Boolean) in.readValue( null );
      }
      


      @Override
      public void writeToParcel( Parcel out, int flags )
      {
         super.writeToParcel( out, flags );
         out.writeValue( checked );
      }
      


      @Override
      public String toString()
      {
         return ToggleImageButton.class.getSimpleName() + ".SavedState{"
            + Integer.toHexString( System.identityHashCode( this ) )
            + " checked=" + checked + "}";
      }
      
      public static final Parcelable.Creator< SavedState > CREATOR = new Parcelable.Creator< SavedState >()
      {
         public SavedState createFromParcel( Parcel in )
         {
            return new SavedState( in );
         }
         


         public SavedState[] newArray( int size )
         {
            return new SavedState[ size ];
         }
      };
   }
}

/* 
 *	Copyright (c) 2012 Ronny R�hricht
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

package dev.drsoran.moloko.prefs;

import android.content.Context;
import android.os.Vibrator;
import android.preference.SwitchPreference;
import android.util.AttributeSet;


class VibrateSwitchPreference extends SwitchPreference
{
   public VibrateSwitchPreference( Context context, AttributeSet attrs,
      int defStyle )
   {
      super( context, attrs, defStyle );
   }
   
   
   
   public VibrateSwitchPreference( Context context, AttributeSet attrs )
   {
      super( context, attrs );
   }
   
   
   
   public VibrateSwitchPreference( Context context )
   {
      super( context );
   }
   
   
   
   @Override
   protected void onClick()
   {
      super.onClick();
      
      if ( isChecked() )
      {
         testVibrate();
      }
   }
   
   
   
   private void testVibrate()
   {
      final Vibrator vibrator = (Vibrator) getContext().getSystemService( Context.VIBRATOR_SERVICE );
      
      if ( vibrator != null )
         vibrator.vibrate( 300 );
   }
}

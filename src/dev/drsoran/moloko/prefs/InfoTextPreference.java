/*
 * Copyright (c) 2010 Ronny R�hricht
 * 
 * This file is part of Moloko.
 * 
 * Moloko is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Moloko is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Moloko. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 * Ronny R�hricht - implementation
 */

package dev.drsoran.moloko.prefs;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import dev.drsoran.moloko.R;


public class InfoTextPreference extends Preference implements IMolokoPreference
{
   private String infoText;
   
   

   public InfoTextPreference( Context context, AttributeSet attrs )
   {
      super( context, attrs );
      
      setLayoutResource( R.layout.moloko_prefs_info_preference );
      
      final TypedArray array = context.obtainStyledAttributes( attrs,
                                                               R.styleable.InfoTextPreference,
                                                               0,
                                                               0 );
      
      infoText = array.getString( R.styleable.InfoTextPreference_infoText );
      
      final int widgetLayout = array.getResourceId( R.styleable.InfoTextPreference_infoWidget,
                                                    -1 );
      
      if ( widgetLayout != -1 )
         setWidgetLayoutResource( widgetLayout );
      
      array.recycle();
   }
   


   @Override
   protected void onBindView( View view )
   {
      super.onBindView( view );
      
      if ( infoText != null )
         ( (TextView) view.findViewById( R.id.text ) ).setText( infoText );
      
      {
         final CharSequence title = getTitle();
         
         if ( TextUtils.isEmpty( title ) )
         {
            ( (TextView) view.findViewById( android.R.id.title ) ).setVisibility( View.GONE );
            ( (TextView) view.findViewById( R.id.text ) ).setGravity( Gravity.CENTER_VERTICAL );
         }
      }
   }
   


   public void cleanUp()
   {
   }
   


   public String getInfoText()
   {
      return infoText;
   }
   


   public void setInfoText( String infoText )
   {
      this.infoText = infoText;
      notifyChanged();
   }
   


   public void setInfoText( int resId )
   {
      setInfoText( getContext().getResources().getString( resId ) );
   }
   
}

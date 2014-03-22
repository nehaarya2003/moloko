/*
 * Copyright (c) 2012 Ronny R�hricht
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

package dev.drsoran.moloko.app.tagcloud;

import java.util.Collections;

import android.os.Bundle;
import android.view.Menu;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.app.Intents;
import dev.drsoran.moloko.app.baseactivities.MolokoActivity;
import dev.drsoran.rtm.parsing.grammar.rtmsmart.RtmSmartFilterSyntax;


public class TagCloudActivity extends MolokoActivity implements
         ITagCloudFragmentListener
{
   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.tagcloud_activity );
   }
   
   
   
   @Override
   public boolean onActivityCreateOptionsMenu( Menu menu )
   {
      getMenuInflater().inflate( R.menu.sync_only, menu );
      super.onActivityCreateOptionsMenu( menu );
      
      return true;
   }
   
   
   
   @Override
   public void onOpenList( long listId )
   {
      startActivityWithHomeAction( Intents.createOpenListIntentById( listId ),
                                   getClass() );
   }
   
   
   
   @Override
   public void onOpenTag( String tag )
   {
      startActivityWithHomeAction( Intents.createOpenTagsIntent( this,
                                                                 Collections.singletonList( tag ),
                                                                 RtmSmartFilterSyntax.AND ),
                                   getClass() );
   }
   
   
   
   @Override
   public void onOpenLocation( String locationName )
   {
      startActivityWithHomeAction( Intents.createOpenLocationIntentByName( this,
                                                                           locationName ),
                                   getClass() );
   }
   
   
   
   @Override
   protected int[] getFragmentIds()
   {
      return new int[]
      { R.id.frag_tag_cloud };
   }
}
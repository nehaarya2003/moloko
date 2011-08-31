/*
 * Copyright (c) 2011 Ronny R�hricht
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

package dev.drsoran.moloko.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.adapters.HomeAdapter;
import dev.drsoran.moloko.util.AccountUtils;
import dev.drsoran.moloko.util.Intents;
import dev.drsoran.moloko.util.UIUtils;


public class HomeActivity extends MolokoFragmentActivity implements
         OnItemClickListener
{
   private static class OptionsMenu
   {
      public final static int SYNC = R.id.menu_sync;
      
      public final static int ADD_TASK = R.id.menu_quick_add_task;
      
      public final static int SEARCH = R.id.menu_search_tasks;
   }
   
   private final Handler handler = new Handler();
   
   

   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.home_activity );
      
      final GridView gridview = (GridView) findViewById( R.id.home_gridview );
      gridview.setOnItemClickListener( this );
      
      fillGrid();
   }
   


   @Override
   protected void onResume()
   {
      super.onResume();
      
      final GridView gridview = (GridView) findViewById( R.id.home_gridview );
      final HomeAdapter homeAdapter = (HomeAdapter) gridview.getAdapter();
      
      if ( homeAdapter != null )
      {
         homeAdapter.startWidgets();
         onContentChanged();
      }
   }
   


   @Override
   protected void onStop()
   {
      super.onStop();
      
      final GridView gridview = (GridView) findViewById( R.id.home_gridview );
      final HomeAdapter homeAdapter = (HomeAdapter) gridview.getAdapter();
      
      if ( homeAdapter != null )
      {
         homeAdapter.stopWidgets();
      }
   }
   


   @Override
   public boolean onCreateOptionsMenu( Menu menu )
   {
      super.onCreateOptionsMenu( menu );
      
      UIUtils.addOptionalMenuItem( this,
                                   menu,
                                   OptionsMenu.ADD_TASK,
                                   getString( R.string.app_task_add ),
                                   Menu.NONE,
                                   Menu.NONE,
                                   R.drawable.ic_menu_add_task,
                                   MenuItem.SHOW_AS_ACTION_ALWAYS,
                                   AccountUtils.isWriteableAccess( this ) );
      
      UIUtils.addSyncMenuItem( this,
                               menu,
                               OptionsMenu.SYNC,
                               Menu.NONE,
                               MenuItem.SHOW_AS_ACTION_ALWAYS );
      
      UIUtils.addOptionalMenuItem( this,
                                   menu,
                                   OptionsMenu.SEARCH,
                                   getString( R.string.search_hint ),
                                   Menu.CATEGORY_SECONDARY,
                                   Menu.NONE,
                                   R.drawable.ic_menu_search,
                                   MenuItem.SHOW_AS_ACTION_ALWAYS,
                                   true );
      return true;
   }
   


   @Override
   public boolean onOptionsItemSelected( MenuItem item )
   {
      switch ( item.getItemId() )
      {
         case android.R.id.home:
            UIUtils.newAboutMolokoDialog( this );
            return true;
            
         case OptionsMenu.ADD_TASK:
            startActivity( Intents.createAddTaskIntent( this, null ) );
            return true;
            
         default :
            return super.onOptionsItemSelected( item );
      }
   }
   


   private void fillGrid()
   {
      final GridView gridview = (GridView) findViewById( R.id.home_gridview );
      final HomeAdapter adapter = new HomeAdapter( this );
      
      gridview.setAdapter( adapter );
   }
   


   public void onItemClick( AdapterView< ? > adapterView,
                            View view,
                            int pos,
                            long id )
   {
      final HomeAdapter adapter = (HomeAdapter) ( (GridView) adapterView ).getAdapter();
      
      final Intent intent = adapter.getIntentForWidget( pos );
      
      if ( intent != null )
         startActivity( intent );
      else
      {
         final Runnable runnable = adapter.getRunnableForWidget( pos );
         
         if ( runnable != null )
            handler.post( runnable );
      }
   }
   


   @Override
   protected int[] getFragmentIds()
   {
      // TODO Auto-generated method stub
      return null;
   }
}

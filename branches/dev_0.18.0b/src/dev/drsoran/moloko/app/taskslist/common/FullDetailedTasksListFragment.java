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

package dev.drsoran.moloko.app.taskslist.common;

import java.util.Collections;
import java.util.List;

import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.app.Intents;
import dev.drsoran.moloko.app.loaders.TasksLoader;
import dev.drsoran.moloko.domain.model.RtmSmartFilter;
import dev.drsoran.moloko.domain.model.Task;
import dev.drsoran.moloko.domain.services.TaskContentOptions;
import dev.drsoran.moloko.ui.adapters.SwappableArrayAdapter;
import dev.drsoran.rtm.parsing.GrammarException;
import dev.drsoran.rtm.parsing.rtmsmart.RtmSmartFilterToken;
import dev.drsoran.rtm.parsing.rtmsmart.RtmSmartFilterTokenCollection;


public class FullDetailedTasksListFragment extends AbstractTasksListFragment
{
   public static FullDetailedTasksListFragment newInstance( Bundle configuration )
   {
      final FullDetailedTasksListFragment fragment = new FullDetailedTasksListFragment();
      
      fragment.setArguments( configuration );
      
      return fragment;
   }
   
   
   
   @Override
   public View onCreateView( LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState )
   {
      return inflater.inflate( R.layout.taskslist_fragment, container, false );
   }
   
   
   
   @Override
   public void resortTasks( int newTaskSort )
   {
      getListAdapter().sort( newTaskSort );
   }
   
   
   
   @Override
   public int getDefaultTaskSort()
   {
      return getAppContext().getSettings().getTaskSort();
   }
   
   
   
   @Override
   public Loader< List< Task >> newLoaderInstance( int id, Bundle config )
   {
      final TasksLoader loader;
      
      final RtmSmartFilter filter = (RtmSmartFilter) config.getSerializable( Intents.Extras.KEY_FILTER );
      if ( filter != null )
      {
         loader = new TasksLoader( getAppContext().asDomainContext(),
                                   filter,
                                   TaskContentOptions.WithNotes );
      }
      else
      {
         loader = new TasksLoader( getAppContext().asDomainContext(),
                                   config.getLong( Intents.Extras.KEY_LIST_ID ),
                                   TaskContentOptions.WithNotes );
      }
      
      loader.setRespectContentChanges( true );
      
      return loader;
   }
   
   
   
   @Override
   public SwappableArrayAdapter< Task > createListAdapter( RtmSmartFilter filter )
   {
      final int flags = 0;
      return new FullDetailedTasksListFragmentAdapter( getAppContext(),
                                                       getListView(),
                                                       getFilterTokens( filter ),
                                                       flags );
   }
   
   
   
   @Override
   public FullDetailedTasksListFragmentAdapter getListAdapter()
   {
      return (FullDetailedTasksListFragmentAdapter) super.getListAdapter();
   }
   
   
   
   @Override
   protected void notifiyDatasetChanged()
   {
      getListAdapter().notifyDataSetChanged();
   }
   
   
   
   private RtmSmartFilterTokenCollection getFilterTokens( RtmSmartFilter filter )
   {
      if ( filter != null )
      {
         try
         {
            return getAppContext().getParsingService()
                                  .getSmartFilterParsing()
                                  .getSmartFilterTokens( filter.getFilterString() );
         }
         catch ( GrammarException e )
         {
            Log().e( getClass(), "Unparsable smart filter", e );
         }
      }
      
      return new RtmSmartFilterTokenCollection( Collections.< RtmSmartFilterToken > emptyList() );
   }
}
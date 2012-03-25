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

package dev.drsoran.moloko.fragments;

import java.util.List;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.os.Bundle;
import android.support.v4.app.SupportActivity;
import android.support.v4.content.Loader;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.SubMenu;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ListAdapter;
import dev.drsoran.moloko.IFilter;
import dev.drsoran.moloko.IOnSettingsChangedListener;
import dev.drsoran.moloko.MolokoApp;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.adapters.SelectableTasksListFragmentAdapter;
import dev.drsoran.moloko.adapters.SelectableTasksListFragmentAdapter.ISelectionChangedListener;
import dev.drsoran.moloko.fragments.listeners.ISelectableTasksListFragmentListener;
import dev.drsoran.moloko.fragments.listeners.NullTasksListFragmentListener;
import dev.drsoran.moloko.loaders.SelectableTasksLoader;
import dev.drsoran.moloko.util.MenuCategory;
import dev.drsoran.moloko.util.Queries;
import dev.drsoran.moloko.util.UIUtils;
import dev.drsoran.provider.Rtm.Tasks;
import dev.drsoran.rtm.SelectableTask;
import dev.drsoran.rtm.Task;


public class SelectableTasksListsFragment extends
         AbstractTasksListFragment< SelectableTask > implements
         IOnSettingsChangedListener, ISelectionChangedListener
{
   private final static IntentFilter INTENT_FILTER;
   
   static
   {
      try
      {
         INTENT_FILTER = new IntentFilter( Intent.ACTION_PICK,
                                           "vnd.android.cursor.dir/vnd.rtm.task" );
         INTENT_FILTER.addCategory( Intent.CATEGORY_DEFAULT );
      }
      catch ( MalformedMimeTypeException e )
      {
         throw new RuntimeException( e );
      }
   }
   
   
   protected static class OptionsMenu extends
            AbstractTasksListFragment.OptionsMenu
   {
      public final static int SORT_SELECTION = R.id.menu_sort_selection;
      
      public final static int SELECT_ALL = R.id.menu_select_all;
      
      public final static int DESELECT_ALL = R.id.menu_deselect_all;
      
      public final static int INVERT_SELECTION = R.id.menu_invert_selection;
      
      public final static int DO_EDIT = R.id.menu_edit_selected_tasks;
      
      public final static int COMPLETE = R.id.menu_complete_selected_tasks;
      
      public final static int INCOMPLETE = R.id.menu_uncomplete_selected_tasks;
      
      public final static int POSTPONE = R.id.menu_postpone_selected_tasks;
      
      public final static int DELETE = R.id.menu_delete_selected_tasks;
   }
   
   
   private final static class CtxtMenu
   {
      public final static int TOGGLE_SELECTION = R.id.ctx_menu_toggle_selection;
   }
   
   
   public static class Config extends MinDetailedTasksListFragment.Config
   {
      private final static String SELECTION_STATE = "selection_state";
   }
   
   public final static int TASK_SORT_SELECTION = 1 << 16;
   
   private ISelectableTasksListFragmentListener listener;
   
   
   
   public static SelectableTasksListsFragment newInstance( Bundle configuration )
   {
      final SelectableTasksListsFragment fragment = new SelectableTasksListsFragment();
      
      fragment.setArguments( configuration );
      
      return fragment;
   }
   
   
   
   public SelectableTasksListsFragment()
   {
      registerAnnotatedConfiguredInstance( this,
                                           SelectableTasksListsFragment.class );
   }
   
   
   
   public static IntentFilter getIntentFilter()
   {
      return INTENT_FILTER;
   }
   
   
   
   @Override
   public Intent newDefaultIntent()
   {
      return new Intent( INTENT_FILTER.getAction( 0 ), Tasks.CONTENT_URI );
   }
   
   
   
   @Override
   public void onAttach( SupportActivity activity )
   {
      super.onAttach( activity );
      
      if ( activity instanceof ISelectableTasksListFragmentListener )
         listener = (ISelectableTasksListFragmentListener) activity;
      else
         listener = new NullTasksListFragmentListener();
   }
   
   
   
   @Override
   public void onDetach()
   {
      listener = null;
      super.onDetach();
   }
   
   
   
   @Override
   public void onActivityCreated( Bundle savedInstanceState )
   {
      super.onActivityCreated( savedInstanceState );
      registerForContextMenu( getListView() );
   }
   
   
   
   @Override
   public View onCreateView( LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState )
   {
      return inflater.inflate( R.layout.taskslist_fragment, container, false );
   }
   
   
   
   @Override
   public void onSaveInstanceState( Bundle outState )
   {
      super.onSaveInstanceState( outState );
      saveSelectedTaskIds( outState );
   }
   
   
   
   @Override
   public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
   {
      super.onCreateOptionsMenu( menu, inflater );
      
      menu.add( Menu.NONE,
                OptionsMenu.SELECT_ALL,
                MenuCategory.SECONDARY,
                R.string.select_multiple_tasks_menu_opt_select_all )
          .setIcon( R.drawable.ic_menu_select_all_tasks );
      
      menu.add( Menu.NONE,
                OptionsMenu.DESELECT_ALL,
                MenuCategory.SECONDARY,
                R.string.select_multiple_tasks_menu_opt_unselect_all )
          .setIcon( R.drawable.ic_menu_select_no_tasks );
      
      menu.add( Menu.NONE,
                OptionsMenu.INVERT_SELECTION,
                MenuCategory.SECONDARY,
                R.string.select_multiple_tasks_menu_opt_inv_selection )
          .setIcon( R.drawable.ic_menu_select_invert_tasks );
      
      final SelectableTasksListFragmentAdapter adapter = getListAdapter();
      
      if ( adapter != null )
      {
         final boolean allSelected = adapter.areAllSelected();
         final boolean someSelected = adapter.areSomeSelected();
         final List< SelectableTask > tasks = adapter.getSelectedTasks();
         final int selCnt = tasks.size();
         
         UIUtils.addOptionalMenuItem( getFragmentActivity(),
                                      menu,
                                      OptionsMenu.DO_EDIT,
                                      getString( R.string.select_multiple_tasks_menu_opt_do_edit,
                                                 selCnt ),
                                      MenuCategory.SECONDARY,
                                      Menu.NONE,
                                      R.drawable.ic_menu_edit,
                                      MenuItem.SHOW_AS_ACTION_IF_ROOM,
                                      someSelected );
         
         int selCompl = 0, selUncompl = 0;
         
         for ( SelectableTask task : tasks )
         {
            if ( task.getCompleted() != null )
               ++selCompl;
            else
               ++selUncompl;
         }
         
         // The complete task menu is only shown if all selected tasks are uncompleted
         UIUtils.addOptionalMenuItem( getFragmentActivity(),
                                      menu,
                                      OptionsMenu.COMPLETE,
                                      getString( R.string.select_multiple_tasks_menu_opt_complete,
                                                 selCnt ),
                                      MenuCategory.SECONDARY,
                                      Menu.NONE,
                                      R.drawable.ic_menu_complete,
                                      MenuItem.SHOW_AS_ACTION_IF_ROOM,
                                      someSelected && selUncompl == selCnt );
         
         // The incomplete task menu is only shown if all selected tasks are completed
         UIUtils.addOptionalMenuItem( getFragmentActivity(),
                                      menu,
                                      OptionsMenu.INCOMPLETE,
                                      getString( R.string.select_multiple_tasks_menu_opt_uncomplete,
                                                 selCnt ),
                                      MenuCategory.SECONDARY,
                                      Menu.NONE,
                                      R.drawable.ic_menu_incomplete,
                                      MenuItem.SHOW_AS_ACTION_IF_ROOM,
                                      someSelected && selCompl == selCnt );
         
         UIUtils.addOptionalMenuItem( getFragmentActivity(),
                                      menu,
                                      OptionsMenu.POSTPONE,
                                      getString( R.string.select_multiple_tasks_menu_opt_postpone,
                                                 selCnt ),
                                      MenuCategory.SECONDARY,
                                      Menu.NONE,
                                      R.drawable.ic_menu_postponed,
                                      MenuItem.SHOW_AS_ACTION_IF_ROOM,
                                      someSelected );
         
         UIUtils.addOptionalMenuItem( getFragmentActivity(),
                                      menu,
                                      OptionsMenu.DELETE,
                                      getString( R.string.select_multiple_tasks_menu_opt_delete,
                                                 selCnt ),
                                      MenuCategory.SECONDARY,
                                      Menu.NONE,
                                      R.drawable.ic_menu_trash,
                                      MenuItem.SHOW_AS_ACTION_IF_ROOM,
                                      someSelected );
         
         final MenuItem selAllItem = menu.findItem( OptionsMenu.SELECT_ALL );
         selAllItem.setEnabled( !allSelected );
         
         final MenuItem deselAllItem = menu.findItem( OptionsMenu.DESELECT_ALL );
         deselAllItem.setEnabled( someSelected );
         
         final MenuItem invSelItem = menu.findItem( OptionsMenu.INVERT_SELECTION );
         invSelItem.setEnabled( !allSelected && someSelected );
      }
   }
   
   
   
   @Override
   protected SubMenu createTasksSortSubMenu( Menu menu )
   {
      final SubMenu subMenu = super.createTasksSortSubMenu( menu );
      
      if ( subMenu != null && getListAdapter() != null )
      {
         final int selCnt = getListAdapter().getSelectedCount();
         final boolean moreThanOneSelected = selCnt > 1;
         final boolean allSelected = getListAdapter().areAllSelected();
         
         if ( !allSelected && moreThanOneSelected )
         {
            subMenu.add( OptionsMenuGroup.SORT,
                         OptionsMenu.SORT_SELECTION,
                         Menu.NONE,
                         R.string.select_multiple_tasks_sort_selection );
         }
      }
      
      return subMenu;
   }
   
   
   
   @Override
   protected void initializeTasksSortSubMenu( SubMenu subMenu,
                                              int currentTaskSort )
   {
      // INFO: These items are exclusive checkable. Setting one will reset the other.
      // The setChecked() call parameter gets ignored. Only the call matters and
      // always sets the item.
      switch ( currentTaskSort )
      {
         case TASK_SORT_SELECTION:
            subMenu.findItem( OptionsMenu.SORT_SELECTION ).setChecked( true );
            break;
         
         default :
            super.initializeTasksSortSubMenu( subMenu, currentTaskSort );
            break;
      }
   }
   
   
   
   @Override
   public boolean onOptionsItemSelected( MenuItem item )
   {
      final SelectableTasksListFragmentAdapter adapter = getListAdapter();
      
      if ( adapter != null )
      {
         switch ( item.getItemId() )
         {
            case OptionsMenu.SORT_SELECTION:
               resortTasks( TASK_SORT_SELECTION );
               item.setChecked( true );
               return true;
               
            case OptionsMenu.SELECT_ALL:
               adapter.changeSelection( SelectableTasksListFragmentAdapter.SEL_MODE_ALL );
               return true;
               
            case OptionsMenu.DESELECT_ALL:
               adapter.changeSelection( SelectableTasksListFragmentAdapter.SEL_MODE_NONE );
               return true;
               
            case OptionsMenu.INVERT_SELECTION:
               adapter.changeSelection( SelectableTasksListFragmentAdapter.SEL_MODE_INVERT );
               return true;
               
            case OptionsMenu.DO_EDIT:
               listener.onEditSelectedTasks( getListAdapter().getSelectedTasks() );
               return true;
               
            case OptionsMenu.COMPLETE:
               listener.onCompleteSelectedTasks( getListAdapter().getSelectedTasks() );
               return true;
               
            case OptionsMenu.INCOMPLETE:
               listener.onIncompleteSelectedTasks( getListAdapter().getSelectedTasks() );
               return true;
               
            case OptionsMenu.POSTPONE:
               listener.onPostponeSelectedTasks( getListAdapter().getSelectedTasks() );
               return true;
               
            case OptionsMenu.DELETE:
               listener.onDeleteSelectedTasks( getListAdapter().getSelectedTasks() );
               return true;
               
            default :
               return super.onOptionsItemSelected( item );
         }
      }
      
      return super.onOptionsItemSelected( item );
   }
   
   
   
   @Override
   public void onCreateContextMenu( ContextMenu menu,
                                    View v,
                                    ContextMenuInfo menuInfo )
   {
      final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
      final Checkable checkableView = ( (CheckBox) info.targetView.findViewById( R.id.selectmultipletasks_listitem_selected ) );
      final Task task = getTask( info.position );
      
      if ( checkableView.isChecked() )
         menu.add( Menu.NONE,
                   CtxtMenu.TOGGLE_SELECTION,
                   Menu.NONE,
                   getString( R.string.select_multiple_tasks_menu_ctx_deselect,
                              task.getName() ) );
      else
         menu.add( Menu.NONE,
                   CtxtMenu.TOGGLE_SELECTION,
                   Menu.NONE,
                   getString( R.string.select_multiple_tasks_menu_ctx_select,
                              task.getName() ) );
   }
   
   
   
   @Override
   public boolean onContextItemSelected( MenuItem item )
   {
      final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
      
      switch ( item.getItemId() )
      {
         case CtxtMenu.TOGGLE_SELECTION:
            toggle( info.position );
            return true;
         default :
            return super.onContextItemSelected( item );
      }
   }
   
   
   
   public void toggle( int pos )
   {
      getListAdapter().toggleSelection( pos );
   }
   
   
   
   @Override
   protected int getDefaultTaskSort()
   {
      return MolokoApp.getSettings( getFragmentActivity() ).getTaskSort();
   }
   
   
   
   @Override
   protected String resolveTaskSortToSqlite( int taskSort )
   {
      if ( taskSort == TASK_SORT_SELECTION )
         // Here we take the default task sort cause we re-sort the tasks later by their selection
         return Queries.resolveTaskSortToSqlite( getDefaultTaskSort() );
      else
         return super.resolveTaskSortToSqlite( taskSort );
   }
   
   
   
   @Override
   public boolean shouldResortTasks( int taskSort )
   {
      // we always want to sort cause the selection may changed
      // TODO: Enhancement: Think about a "dirty" flag to spare sorting.
      if ( taskSort == TASK_SORT_SELECTION )
         return true;
      else
         return super.shouldResortTasks( taskSort );
   }
   
   
   
   @Override
   public Loader< List< SelectableTask >> newLoaderInstance( int id,
                                                             Bundle config )
   {
      final IFilter filter = config.getParcelable( Config.FILTER );
      final String selection = filter != null ? filter.getSqlSelection() : null;
      final String order = resolveTaskSortToSqlite( config.getInt( Config.TASK_SORT_ORDER ) );
      
      final SelectableTasksLoader loader = new SelectableTasksLoader( getFragmentActivity(),
                                                                      selection,
                                                                      order );
      loader.setUpdateThrottle( DEFAULT_LOADER_THROTTLE_MS );
      
      return loader;
   }
   
   
   
   @Override
   public void onLoadFinished( Loader< List< SelectableTask >> loader,
                               List< SelectableTask > data )
   {
      super.onLoadFinished( loader, data );
      notifyListenerSelectionChanged();
   }
   
   
   
   @Override
   public ListAdapter createEmptyListAdapter()
   {
      return new SelectableTasksListFragmentAdapter( getFragmentActivity(),
                                                     R.layout.selectmultipletasks_activity_listitem );
   }
   
   
   
   @Override
   public ListAdapter createListAdapterForResult( List< SelectableTask > result,
                                                  IFilter filter )
   {
      final SelectableTasksListFragmentAdapter adapter = new SelectableTasksListFragmentAdapter( getFragmentActivity(),
                                                                                                 R.layout.selectmultipletasks_activity_listitem,
                                                                                                 result );
      adapter.setSelectionChangedListener( this );
      restoreSelectedTaskIds( getConfiguration() );
      
      if ( getTaskSort() == TASK_SORT_SELECTION )
         adapter.sortBySelection();
      
      return adapter;
   }
   
   
   
   @Override
   public SelectableTasksListFragmentAdapter getListAdapter()
   {
      return (SelectableTasksListFragmentAdapter) super.getListAdapter();
   }
   
   
   
   @Override
   public void onSelectionChanged()
   {
      invalidateOptionsMenu();
      notifyListenerSelectionChanged();
   }
   
   
   
   private void saveSelectedTaskIds( Bundle outState )
   {
      if ( getListAdapter() != null )
      {
         outState.putStringArrayList( Config.SELECTION_STATE,
                                      getListAdapter().getSelectedTaskIds() );
      }
   }
   
   
   
   private void restoreSelectedTaskIds( Bundle savedInstanceState )
   {
      if ( savedInstanceState != null && getListAdapter() != null )
      {
         final List< String > selectedTaskIds = savedInstanceState.getStringArrayList( Config.SELECTION_STATE );
         if ( selectedTaskIds != null )
         {
            getListAdapter().setSelectedTaskIds( selectedTaskIds );
            savedInstanceState.remove( Config.SELECTION_STATE );
         }
      }
   }
   
   
   
   private void notifyListenerSelectionChanged()
   {
      listener.onSelectionChanged( getListAdapter().getSelectedTasks() );
   }
}
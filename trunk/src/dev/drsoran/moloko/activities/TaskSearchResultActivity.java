package dev.drsoran.moloko.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuItem;
import dev.drsoran.moloko.R;
import dev.drsoran.moloko.search.TasksSearchRecentSuggestionsProvider;
import dev.drsoran.moloko.util.UIUtils;
import dev.drsoran.rtm.RtmSmartFilter;


public class TaskSearchResultActivity extends TasksListActivity
{
   @SuppressWarnings( "unused" )
   private final static String TAG = TaskSearchResultActivity.class.getSimpleName();
   
   private final static String QUERY_NOT_EVALUABLE = "query_not_evaluable";
   
   /**
    * This prevents the base class TasksListActivity to fill the list with all tasks during onCreate().
    */
   private boolean preventSuperFillList = true;
   
   
   protected static class TaskSearchResultOptionsMenu extends
            TasksListOptionsMenu
   {
      protected final static int START_IDX = TasksListOptionsMenu.START_IDX + 1000;
      
      public final static int NEW_SEARCH = START_IDX + 0;
      
      public final static int CLEAR_HISTORY = START_IDX + 1;
   }
   
   

   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState );
      
      if ( savedInstanceState != null )
         configuration.putAll( savedInstanceState );
      
      preventSuperFillList = false;
      
      handleIntent( getIntent() );
   }
   


   @Override
   protected void onNewIntent( Intent intent )
   {
      setIntent( intent );
      handleIntent( intent );
   }
   


   @Override
   public boolean onCreateOptionsMenu( Menu menu )
   {
      super.onCreateOptionsMenu( menu );
      
      menu.removeItem( TaskSearchResultOptionsMenu.SHOW_LISTS );
      
      menu.add( Menu.NONE,
                TaskSearchResultOptionsMenu.NEW_SEARCH,
                Menu.NONE,
                R.string.menu_opt_search_task_title )
          .setIcon( R.drawable.icon_search_black );
      
      menu.add( Menu.NONE,
                TaskSearchResultOptionsMenu.CLEAR_HISTORY,
                Menu.NONE,
                R.string.tasksearchresult_menu_opt_clear_history_title )
          .setIcon( R.drawable.icon_delete_black );
      
      return true;
   }
   


   @Override
   public boolean onOptionsItemSelected( MenuItem item )
   {
      // Handle item selection
      switch ( item.getItemId() )
      {
         case TaskSearchResultOptionsMenu.NEW_SEARCH:
            onSearchRequested();
            return true;
         case TaskSearchResultOptionsMenu.CLEAR_HISTORY:
            getRecentSuggestions().clearHistory();
            return true;
         default :
            return super.onOptionsItemSelected( item );
      }
   }
   


   @Override
   protected boolean shouldFillList()
   {
      return !preventSuperFillList;
   }
   


   @Override
   protected void fillList()
   {
      if ( configuration.containsKey( QUERY_NOT_EVALUABLE ) )
      {
         UIUtils.setTitle( this,
                           getString( R.string.tasksearchresult_titlebar_error,
                                      configuration.getString( QUERY_NOT_EVALUABLE ),
                                      R.drawable.icon_sad_face_white ) );
      }
      else
      {
         super.fillList();
      }
   }
   


   private void handleIntent( Intent intent )
   {
      if ( Intent.ACTION_SEARCH.equals( intent.getAction() ) )
      {
         configuration.clear();
         
         final String query = intent.getStringExtra( SearchManager.QUERY );
         
         // try to evaluate the query
         final String evalQuery = RtmSmartFilter.evaluate( query );
         
         if ( evalQuery != null )
         {
            getRecentSuggestions().saveRecentQuery( query, null );
            
            // we put the query string as smart filter cause
            // smart lists are nothing else than saved searches.
            //
            // Tag the filter as already evaluated.
            configuration.putString( FILTER_EVALUATED, evalQuery );
            configuration.putString( TITLE,
                                     getString( R.string.tasksearchresult_titlebar,
                                                query ) );
         }
         else
         {
            configuration.putString( QUERY_NOT_EVALUABLE, query );
         }
         
         if ( shouldFillList() )
            fillList();
      }
   }
   


   private final SearchRecentSuggestions getRecentSuggestions()
   {
      return new SearchRecentSuggestions( this,
                                          TasksSearchRecentSuggestionsProvider.AUTHORITY,
                                          TasksSearchRecentSuggestionsProvider.MODE );
   }
}

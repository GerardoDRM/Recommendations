package doge.thecraftcod.recommendations;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import doge.thecraftcod.recommendations.api.Etsy;
import doge.thecraftcod.recommendations.model.ActiveListings;


public class MainActivity extends ActionBarActivity {

    private static final String STATE_ACTIVE_LISTINGS = "StateActiveListings";

    private RecyclerView recyclerView;
    private View progressBar;
    private TextView errorView;

    private ListingAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        progressBar = findViewById(R.id.progressbar);
        errorView = (TextView) findViewById(R.id.error_view);

        // setup recyclerview
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new ListingAdapter(this);

        Log.d("List",adapter.getItemCount() + "");

        recyclerView.setAdapter(adapter);

        if (savedInstanceState == null) {
            showLoading();
            Etsy.getActiveListings(adapter);
            Log.d("NULL", "Esto es null");
        }
        else {
            if (savedInstanceState.containsKey(STATE_ACTIVE_LISTINGS)) {
                adapter.success((ActiveListings) savedInstanceState.getParcelable(STATE_ACTIVE_LISTINGS), null);
                showList();
                Log.d("INSTANCE", "Esto SHOWlist");
            }
            else {
                showLoading();
                Etsy.getActiveListings(adapter);
                Log.d("INSTANCE", "Esto es SHOWloading");
            }

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        ActiveListings activeListings = adapter.getActiveListings();

        if (activeListings != null) {
            outState.putParcelable(STATE_ACTIVE_LISTINGS, activeListings);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    public void showList() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    public void showError() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}

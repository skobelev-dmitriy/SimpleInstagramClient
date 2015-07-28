package rf.digitworld.simpleinstagramclient.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.fragments.RecyclerViewFragment;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class UsersFeedActivity extends ActionBarActivity{
    Context context;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String user_id=intent.getExtras().getString("user_id");
        String user_name=intent.getExtras().getString("user_name");
        setContentView(R.layout.activity_users_feed);
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbar.setTitle(user_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    if(savedInstanceState==null){

        getSupportFragmentManager().beginTransaction().add(R.id.container, RecyclerViewFragment.newInstance(user_id)).commit();

    }
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.home) {
           finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

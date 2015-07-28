package rf.digitworld.simpleinstagramclient.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.models.InstagramSession;
import rf.digitworld.simpleinstagramclient.models.User;
import rf.digitworld.simpleinstagramclient.models.Users;
import rf.digitworld.simpleinstagramclient.network.DataSelector;
import rf.digitworld.simpleinstagramclient.network.Network;
import rf.digitworld.simpleinstagramclient.views.adapters.UsersListAdapter;


public class MainActivity extends ActionBarActivity implements AuthDialog.OAuthDialogListener {

   private InstagramSession mSession;

    private OAuthAuthenticationListener mListener;
    private ProgressDialog mProgress;

    private String mAccessToken;
    private Context context;
    private Dialog authDialog;

    SearchView searchView;
    ListView listView;
    UsersListAdapter adapter;
    List <User> userList=new ArrayList<>();
    Toolbar toolbar;



    private static final String TAG = "myLogs";
    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token";
    private static final String API_URL = "https://api.instagram.com/v1";
    private static int WHAT_FINALIZE = 0;
    private static int WHAT_ERROR = 1;
    private static int WHAT_FETCH_INFO = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
       // toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        //toolbar.setTitle(user_name);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSession=new InstagramSession(context);
       // mSession.resetAccessToken();
        mAccessToken=mSession.getAccessToken();

        Log.d("myLogs","AccessToken="+mSession.getAccessToken());

        searchView=(SearchView)findViewById(R.id.searchView);
        listView=(ListView)findViewById(R.id.listView);

        listView.setEmptyView(searchView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId=userList.get(position).getId();
                String username=userList.get(position).getUsername();
                String user_photo=userList.get(position).getProfilePicture();
                Intent intent=new Intent(context, UsersFeedActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("user_name",username);
                intent.putExtra("user_photo",user_photo);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                Network.getApiClient().usersSearch(mAccessToken,query,new Callback<Users>() {
                    @Override
                    public void success(Users users, Response response) {


                        userList =   users.getData();
                        adapter=new UsersListAdapter(context,userList);
                        listView.setAdapter(adapter);
                       // adapter.notifyDataSetChanged();
                        Log.d("myLogs", "userList.size= "+userList.size());


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "Ошибка "+error);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });



        AuthDialog.OAuthDialogListener listener=new AuthDialog.OAuthDialogListener() {
            @Override
            public void onComplete(String accessToken) {

            }

            @Override
            public void onError(String error) {

            }

        };



        if (mSession.getAccessToken()==null){
            authDialog=new AuthDialog(MainActivity.this);

            authDialog.setCancelable(true);
            authDialog.show();
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
        if (id == R.id.action_login) {
            authDialog.show();
            return true;
        }
        if (id == R.id.action_logout) {
            mSession.resetAccessToken();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete(String accessToken) {
        mSession.storeAccessToken(accessToken);
        authDialog.dismiss();
    }

    @Override
    public void onError(String error) {
        mListener.onFail("Ошибка авторизации");
    }

    public interface OAuthAuthenticationListener {
        public abstract void onSuccess();

        public abstract void onFail(String error);
    }

}

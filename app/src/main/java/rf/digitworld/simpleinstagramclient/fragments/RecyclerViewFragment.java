package rf.digitworld.simpleinstagramclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rf.digitworld.simpleinstagramclient.EndlessScrollListener;
import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.models.InstagramSession;
import rf.digitworld.simpleinstagramclient.models.Media;
import rf.digitworld.simpleinstagramclient.models.Post;
import rf.digitworld.simpleinstagramclient.network.Network;
import rf.digitworld.simpleinstagramclient.views.DividerItemDecoration;
import rf.digitworld.simpleinstagramclient.views.adapters.MediaAdapter;

//import com.activeandroid.query.Select;


public class RecyclerViewFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user_id";
    private static final String TAG = "viewFragment";


    private RecyclerView recyclerView;
    private List <Post> posts=new ArrayList<>();
    private MediaAdapter adapter;
    private String user_id;
    private Context context;
    String max_id, next_max_id;
    ArrayList<String > pages=new ArrayList<>();
    String mAccessToken;




    public static RecyclerViewFragment newInstance(String user_id) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, user_id);

        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       context =getActivity().getApplicationContext();
        setRetainInstance(true);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            user_id = getArguments().getString(ARG_PARAM1);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        mAccessToken=new InstagramSession(getActivity().getApplicationContext()).getAccessToken();
        getUserMedia();

        initUi(v);
        return  v;
    }

    private void getUserMedia() {
        Network.getApiClient().getUsersMedia(user_id, mAccessToken, 10, new Callback<Media>() {
            @Override
            public void success(Media media, Response response) {

                posts = media.getData();
                Log.d(TAG, "posts count" + posts.size());
                next_max_id=media.getPagination().getNextMaxId();
                pages.add(next_max_id);
                bindDataToAdapter();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myLogs", "Ошибка " + error);
            }
        });
    }

    private void initUi(View view){
        Log.d(TAG,"initUi");
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);

        configureRecyclerView();
    }
    private void configureRecyclerView(){
        Log.d(TAG, "configureRecycleView");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {


            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
        recyclerView.setOnScrollListener(new EndlessScrollListener(layoutManager){
            @Override
            public void onLoadMore(int page) {
                Log.d(TAG, "Подгружаю страницу №="+(pages.size()+1)+ " -"+pages.get(pages.size()-1));
                Network.getApiClient().getNextUsersMedia(user_id, mAccessToken, 10,next_max_id, new Callback<Media>() {
                    @Override
                    public void success(Media media, Response response) {

                        posts.addAll(media.getData());
                        Log.d(TAG, "posts count"+posts.size());
                        next_max_id=media.getPagination().getNextMaxId();
                        String[] temp=media.getPagination().getNextUrl().split("=");
                        max_id=temp[1];
                        pages.add(max_id);

                        //bindDataToAdapter();
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "item count"+adapter.getItemCount());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("myLogs", "Ошибка " + error);
                    }
                });
            }
        });

        bindDataToAdapter();


    }








    private void bindDataToAdapter(){

        adapter=new MediaAdapter(context,posts);
        adapter.setOnItemClicklistener(new MediaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(context,"Like "+position,Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "bindDataToAdapter");
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}

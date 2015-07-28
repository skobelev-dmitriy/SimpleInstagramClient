package rf.digitworld.simpleinstagramclient.network;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rf.digitworld.simpleinstagramclient.models.Media;
import rf.digitworld.simpleinstagramclient.models.Pagination;
import rf.digitworld.simpleinstagramclient.models.Users;

/**
 * Created by Дмитрий on 10.07.2015.
 */
public class Network {

    public static final String AUTHURL(){
        String url="https://api.instagram.com/oauth/authorize/";
        Log.d("myLogs","AUTHURL="+url+"?client_id="+clientId+"&redirect_uri="+REDIRECTURL+"&response_type=token");
        return url+"?client_id="+clientId+"&redirect_uri="+REDIRECTURL+"&response_type=token";
    }
    private static final String TOKENURL ="https://api.instagram.com/oauth/access_token";
    //Used for getting token and User details.
    public static final String APIURL = "https://api.instagram.com/v1";

    public static String REDIRECTURL ="instagram://connect";
    private static String clientId="d239cc5914c64eb68f2846ee56cc6030";


    private static  MyApiEndpointInterface apiService;
    public static MyApiEndpointInterface getApiClient(){
        if (apiService==null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(APIURL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            apiService=restAdapter.create(MyApiEndpointInterface.class);

        }
        return apiService;
    }



    public interface MyApiEndpointInterface {

        /**
         * Search for a user by name.
         * url https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
         *
         * @param query
         * @param count Number of users to return.
         */


        @GET("/users/search")
        void usersSearch(@Query("access_token")String token,@Query("q")String query, Callback<Users> callback);

        /**
         * Get the most recent media published by a user. To get the most recent media published by
         * the owner of the access token, you can use self instead of the user-id.
         * url https://api.instagram.com/v1/users/self/feed?access_token=ACCESS-TOKEN
         *
         * @param COUNT         Count of media to return.
         * @param MAX_TIMESTAMP Return media before this UNIX timestamp.
         * @param ACCESS_TOKEN  A valid access token.
         * @param MIN_TIMESTAMP Return media after this UNIX timestamp.
         * @param MIN_ID        Return media later than this min_id.
         * @param MAX_ID        Return media earlier than this max_id
         */
        @GET("/users/{user-id}/media/recent")
        void getUsersMedia(@Path("user-id")String user_id, @Query("access_token")String token,@Query("count")int count, Callback<Media> callback ) ;

        @GET("/users/{user-id}/media/recent")
        void getNextUsersMedia(@Path("user-id")String user_id, @Query("access_token")String token,@Query("count")int count,@Query("max_id") String max_id, Callback<Media> callback );
    }

}

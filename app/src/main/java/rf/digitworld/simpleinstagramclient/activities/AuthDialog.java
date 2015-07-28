package rf.digitworld.simpleinstagramclient.activities;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import rf.digitworld.simpleinstagramclient.R;
import rf.digitworld.simpleinstagramclient.models.InstagramSession;
import rf.digitworld.simpleinstagramclient.network.Network;

/**
 * Created by Дмитрий on 10.07.2015.
 */
public class AuthDialog        extends Dialog{


    private String mUrl;
    private OAuthDialogListener mListener;
    private WebView mWebView;
    private ProgressBar progressBar;


    private static final String TAG = "myLogs";


    public AuthDialog(Context context) {
        super(context);
    }




        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
         //   Log.d("myLogs", "loadingURI=" + mUrl);
           setContentView(R.layout.dialog_login);
            mWebView=(WebView)findViewById(R.id.webView);
            progressBar=(ProgressBar)findViewById(R.id.progressBar);
            mWebView.setWebViewClient(new OAuthWebViewClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mUrl=Network.AUTHURL();
            mWebView.loadUrl(mUrl);
            Log.d("myLogs","loadingURI="+mUrl);
         /*   mListener=new OAuthDialogListener() {
                @Override
                public void onComplete(String accessToken) {
                    InstagramSession session=new InstagramSession(getOwnerActivity().getApplicationContext());
                    session.storeAccessToken(accessToken);
                    AuthDialog.this.dismiss();
                }

                @Override
                public void onError(String error) {
                    Log.d(TAG, error);
                }
            };*/
        }




    private class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(Network.REDIRECTURL)) {
                String urls[] = url.split("=");
                if (urls[0]=="access_token"){
                    Log.d(TAG, "AccessToken " + urls[1]);
                    //  mListener.onComplete(urls[1]);
                    InstagramSession session=new InstagramSession(getContext());
                    session.storeAccessToken(urls[1]);
                    AuthDialog.this.dismiss();
                }




                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d(TAG, "Page error: " + description);

            super.onReceivedError(view, errorCode, description, failingUrl);
            mListener.onError(description);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "Loading URL: " + url);

            super.onPageStarted(view, url, favicon);

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            Log.d(TAG, "onPageFinished URL: " + url);

            progressBar.setVisibility(View.INVISIBLE);
        }

    }
    public class Authenticator extends AbstractAccountAuthenticator {
        public Authenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                     String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                 String s, String s2, String[] strings, Bundle bundle)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                         Account account, Bundle bundle)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                   Account account, String s, Bundle bundle)
                throws NetworkErrorException {


            throw new UnsupportedOperationException();
        }

        @Override
        public String getAuthTokenLabel(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                        Account account, String s, Bundle bundle)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                  Account account, String[] strings)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }
    }
    public interface OAuthDialogListener {
        public abstract void onComplete(String accessToken);
        public abstract void onError(String error);
    }
}

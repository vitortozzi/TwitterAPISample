package br.com.vitortozzi.twitterapi.br.com.vitortozzi.twitterapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import br.com.vitortozzi.twitterapi.Constants;
import br.com.vitortozzi.twitterapi.R;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Don't forget to put your keys in the Constants class
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session =  Twitter.getInstance().core.getSessionManager().getActiveSession();
                // Or you could use that
                //TwitterSession session = result.data;

                Log.d("TwitterKit","@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")");
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Calls the home activity
        loginButton.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }


}

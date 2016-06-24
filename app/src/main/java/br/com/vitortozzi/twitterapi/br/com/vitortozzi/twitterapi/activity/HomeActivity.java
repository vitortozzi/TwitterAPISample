package br.com.vitortozzi.twitterapi.br.com.vitortozzi.twitterapi.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import br.com.vitortozzi.twitterapi.R;

/**
 * Created by live on 23/06/16.
 */
public class HomeActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the session data ro retrieve user's timeline
        TwitterSession session = Twitter.getInstance().core.getSessionManager().getActiveSession();
        if (session != null) {
            ((TextView) findViewById(R.id.tUsername)).setText(session.getUserName());
            final UserTimeline userTimeline = new UserTimeline.Builder()
                    .screenName(session.getUserName())
                    .build();

            final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                    .setTimeline(userTimeline)
                    .build();
            setListAdapter(adapter);
        }
    }
}

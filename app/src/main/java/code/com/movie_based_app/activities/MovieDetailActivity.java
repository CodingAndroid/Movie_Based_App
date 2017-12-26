package code.com.movie_based_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import code.com.movie_based_app.ApiConfig;
import code.com.movie_based_app.R;

public class MovieDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private TextView textView;
    private ImageView mBackImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mBackImg = (ImageView) findViewById(R.id.back);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        textView.setText(title);
        mWebView = (WebView) findViewById(R.id.movie_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.loadUrl(ApiConfig.BASE_MOBILE+id+"/mobile");

    }
}

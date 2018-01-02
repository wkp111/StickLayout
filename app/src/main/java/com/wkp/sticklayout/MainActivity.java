package com.wkp.sticklayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wkp.sticklayout_lib.widget.StickLayout;

public class MainActivity extends AppCompatActivity {

    private StickLayout mSl;
    private TextView mTv2;
    private View mTv3;
    private View mTv7;
    private View mTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSl = findViewById(R.id.sl);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv7 = findViewById(R.id.tv7);
//        mSl.setStickView(findViewById(R.id.tv2));
//        mSl.setStickView(findViewById(R.id.tv3));
        mSl.canScrollToEndViewTop(true);
    }

    public void addView(View view) {
        TextView textView = new TextView(view.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10,10,10,10);
        textView.setText("新条目");
        mSl.addView(textView,0);
    }

    public void click(View view) {
        Toast.makeText(this, "第1行", Toast.LENGTH_SHORT).show();
    }

    public void scrollTo(View view) {
        mSl.scrollToView(mTv2);
    }

    public void scrollTo3(View view) {
        mSl.scrollToView(mTv3);
    }

    public void scrollTo4(View view) {
        mSl.scrollToView(mTv4);
    }

    public void scrollTo7(View view) {
        mSl.scrollToView(mTv7);
    }
}

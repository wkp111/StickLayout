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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSl = findViewById(R.id.sl);
//        mSl.setStickView(findViewById(R.id.tv2));
//        mSl.setStickView(findViewById(R.id.tv3));
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
}

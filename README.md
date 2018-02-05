# StickLayout [ ![Download](https://api.bintray.com/packages/wkp/maven/StickLayout/images/download.svg) ](https://bintray.com/wkp/maven/StickLayout/_latestVersion)
粘性控件，其任意一个子控件都可滑动停留，无论是View，还是ViewGroup；用该控件可以轻松实现支付宝"全部应用"界面。
## 演示图
![DragGridView](https://github.com/wkp111/StickLayout/blob/master/StickLayout.gif "演示图")
![DragGridView](https://github.com/wkp111/StickLayout/blob/master/StickLayout1.gif "演示图1")
![DragGridView](https://github.com/wkp111/StickLayout/blob/master/StickLayout2.gif "演示图2")<br/>
Note：图1为设置属性wkp_canScrollToEndViewTop=true，图2没有；图3为设置滑动改变监听。
## Gradle集成
```groovy
dependencies{
      compile 'com.wkp:StickLayout:1.0.5'
      //Android Studio3.0+可用以下方式
      //implementation 'com.wkp:StickLayout:1.0.5'
}

//如不愿意等待，请加上我的maven仓库地址
maven { url "https://dl.bintray.com/wkp/maven" }
```
Note：可能存在Jcenter还在审核阶段，这时会集成失败！注意SDK版本targetSdkVersion >= 26.
## 使用详解
> 属性讲解
```xml
        <!--是否粘性停留(用于直接子控件)-->
        <attr name="wkp_stick" format="boolean"/>
        <!--是否开启滑动到最后一个控件的顶部，默认不开启(用于控件本身)，注意最后一个子控件如果为条目控件时，如ListView，请不要开启-->
        <attr name="wkp_canScrollToEndViewTop" format="boolean"/>
```
Note：每个属性都有对应的java设置代码！
> 布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:clickable="true"
        android:onClick="addView"
        android:gravity="center"
        android:padding="5dp"
        android:text="添加条目"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

        <!--app:wkp_canScrollToEndViewTop="true"-->
    <com.wkp.sticklayout_lib.widget.StickLayout
        android:id="@+id/sl"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:onClick="click"
            android:id="@+id/tv1"
            android:text="第1行"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <LinearLayout
            app:wkp_stick="true"
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="40dp">

            <TextView
                android:onClick="scrollTo"
                android:background="@android:color/holo_blue_light"
                android:text="NUM2"
                android:gravity="center"
                android:layout_weight="1"
                android:layout_width="0dp"
                android:layout_height="match_parent"/>

            <TextView
                android:onClick="scrollTo3"
                android:background="@android:color/holo_green_light"
                android:text="NUM3"
                android:gravity="center"
                android:layout_weight="1"
                android:layout_width="0dp"
                android:layout_height="match_parent"/>

            <TextView
                android:onClick="scrollTo4"
                android:background="@android:color/holo_red_light"
                android:text="NUM4"
                android:gravity="center"
                android:layout_weight="1"
                android:layout_width="0dp"
                android:layout_height="match_parent"/>

            <TextView
                android:onClick="scrollTo7"
                android:background="@android:color/holo_orange_light"
                android:text="NUM7"
                android:gravity="center"
                android:layout_weight="1"
                android:layout_width="0dp"
                android:layout_height="match_parent"/>

        </LinearLayout>

        <TextView
            android:id="@+id/tv2"
            android:text="第2行"
            android:background="@android:color/holo_blue_light"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <TextView
            android:id="@+id/tv3"
            app:wkp_stick="true"
            android:text="第3行"
            android:background="@android:color/holo_green_light"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <TextView
            android:background="@android:color/holo_red_light"
            android:id="@+id/tv4"
            android:text="第4行"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <TextView
            android:id="@+id/tv5"
            android:text="第5行"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <TextView
            android:id="@+id/tv6"
            android:text="第6行"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

        <TextView
            android:background="@android:color/holo_orange_light"
            android:id="@+id/tv7"
            android:text="第7行"
            android:gravity="center"
            android:layout_width="match_parent"
            android:layout_height="200dp"/>

    </com.wkp.sticklayout_lib.widget.StickLayout>

</LinearLayout>

```
Note：ScrollView嵌套StickLayout时事件被拦截，无效果！StickLayout嵌套如ListView的条目控件时会只显示第一行，注意解决！
> 代码示例
```java
public class MainActivity extends AppCompatActivity {

    private StickLayout mSl;
    private TextView mTv2;
    private View mTv3;
    private View mTv7;
    private View mTv4;
    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSl = findViewById(R.id.sl);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv7 = findViewById(R.id.tv7);
//        mSl.setStickView(findViewById(R.id.tv2)); //设置粘性控件
//        mSl.setStickView(findViewById(R.id.tv3));
//        mSl.canScrollToEndViewTop(true);      //设置是否开启最后控件滑动到顶部
        //设置滑动改变监听（一滑动就会有回调）
        mSl.setOnScrollChangeListener(new StickLayout.OnScrollChangeListener() {
            @Override
            public void onScrollChange(StickLayout v, View currentView, int position, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //直到当前控件改变在做事情
                if (currentPosition != position) {
                    Toast.makeText(v.getContext(), ((TextView) currentView).getText().toString(), Toast.LENGTH_SHORT).show();
                    currentPosition = position;
                }
            }
        });
    }

    public void addView(View view) {
        TextView textView = new TextView(view.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 10, 10, 10);
        textView.setText("新条目");
        mSl.addView(textView, 0);
    }

    public void scrollTo2(View view) {
        //滑动到指定子控件
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
```
Note：还有其他API请根据需要自行参考！ 
## 寄语
控件支持直接代码创建，还有更多API请观看<a href="https://github.com/wkp111/StickLayout/blob/master/sticklayout-lib/src/main/java/com/wkp/sticklayout_lib/widget/StickLayout.java">StickLayout.java</a>内的注释说明。<br/>
欢迎大家使用，感觉好用请给个Star鼓励一下，谢谢！<br/>
大家如果有更好的意见或建议以及好的灵感，请邮箱作者，谢谢！<br/>
QQ邮箱：1535514884@qq.com<br/>
163邮箱：15889686524@163.com<br/>
Gmail邮箱：wkp15889686524@gmail.com<br/>

## 版本更新
* v1.0.5<br/>
修复悬停子控件在悬停时触发onLayout方法导致的悬停子控件消失的BUG。<br/>
感谢<a href="https://github.com/Zero-Crazy">Zero-Crazy</a>的使用反馈。</br><br/>
* v1.0.4<br/>
新增滑动改变监听，主要为解决滑动过程中做一些联动操作<br/><br/>
* v1.0.3<br/>
新增滑动到指定子控件API<br/><br/>
* v1.0.1<br/>
新创建子可粘性停留控件库
## License

   Copyright 2017 wkp

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

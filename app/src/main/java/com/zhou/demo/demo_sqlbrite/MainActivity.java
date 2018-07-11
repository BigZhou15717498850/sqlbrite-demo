package com.zhou.demo.demo_sqlbrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhou.demo.demo_sqlbrite.data.Demo;
import com.zhou.demo.demo_sqlbrite.data.DemoLocalDataSource;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.UUID;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableFromIterable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSave = findViewById(R.id.save);
        Button btnQuery = findViewById(R.id.query);
        btnSave.setOnClickListener(view -> {
            //保存
            for (int i = 0; i < 20; i++) {
                String username = "代号" + i;
                String description = UUID.randomUUID().toString().substring(0,6);
                String age = i + "岁";
                String sex = i%2==1?"男":"女";


                Demo demo = new Demo(username,description,age,sex,true);
                DemoLocalDataSource.getInstance(getApplicationContext()).saveDemo(demo);

            }
        });

        btnQuery.setOnClickListener(view -> {
            DemoLocalDataSource.getInstance(getApplicationContext()).getDemos()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s-> System.out.println("当前线程" + Thread.currentThread().getName() + "  数量"+s.size()));
        });
    }
}

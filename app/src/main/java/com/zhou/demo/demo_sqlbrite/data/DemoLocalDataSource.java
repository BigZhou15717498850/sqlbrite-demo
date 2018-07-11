package com.zhou.demo.demo_sqlbrite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import com.zhou.demo.demo_sqlbrite.data.DemoPersistenceContract.DemoEntry;

import java.util.List;

/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public class DemoLocalDataSource {

    private BriteDatabase mDatabaseHelper;

    private Function<Cursor, Demo> mDemoMapperFunction;

    private static DemoLocalDataSource INSTANCE;

    public static DemoLocalDataSource getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new DemoLocalDataSource(context);
        }
        return INSTANCE;
    }

    private DemoLocalDataSource(Context context){
        SqlBrite sqlBrite = DemoDbHelper.getInstance().provideSqlBrite();
        mDatabaseHelper = DemoDbHelper.getInstance().provideDatabase(sqlBrite, context);

        mDemoMapperFunction = this::getDemo;
    }

    private Demo getDemo(Cursor cursor) {
        String username =
                cursor.getString(cursor.getColumnIndexOrThrow(DemoEntry.COLUMN_NAME_USERNAME));
        String description=
                cursor.getString(cursor.getColumnIndexOrThrow(DemoEntry.COLUMN_NAME_DESCRIPTION));
        String age=
                cursor.getString(cursor.getColumnIndexOrThrow(DemoEntry.COLUMN_NAME_AGE));
        String sex=
                cursor.getString(cursor.getColumnIndexOrThrow(DemoEntry.COLUMN_NAME_SEX));
        boolean enable=
                cursor.getInt(cursor.getColumnIndexOrThrow(DemoEntry.COLUMN_NAME_ENABLE))==1;
        return new Demo(username, description, age, sex, enable);
    }


    public Flowable<List<Demo>> getDemos(){
        String[] projections = {
                DemoEntry.COLUMN_NAME_USERNAME,
                DemoEntry.COLUMN_NAME_DESCRIPTION,
                DemoEntry.COLUMN_NAME_AGE,
                DemoEntry.COLUMN_NAME_SEX,
                DemoEntry.COLUMN_NAME_ENABLE
        };

        String sql = String.format("SELECT %s FROM %s", TextUtils.join(",",projections),DemoEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(DemoEntry.TABLE_NAME,sql)
                .mapToList(mDemoMapperFunction)
                .toFlowable(BackpressureStrategy.BUFFER);
    }


    public void saveDemo(Demo demo){
        ContentValues values = new ContentValues();
        values.put(DemoEntry.COLUMN_NAME_USERNAME,demo.getUsername());
        values.put(DemoEntry.COLUMN_NAME_DESCRIPTION,demo.getDescription());
        values.put(DemoEntry.COLUMN_NAME_AGE,demo.getAge());
        values.put(DemoEntry.COLUMN_NAME_SEX,demo.getSex());
        values.put(DemoEntry.COLUMN_NAME_ENABLE,demo.isEnable());
        mDatabaseHelper.insert(DemoEntry.TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE,values);
    }

}

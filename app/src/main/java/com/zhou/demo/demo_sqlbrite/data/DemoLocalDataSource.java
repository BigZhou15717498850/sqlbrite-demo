package com.zhou.demo.demo_sqlbrite.data;

import android.content.Context;
import android.database.Cursor;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import com.zhou.demo.demo_sqlbrite.data.DemoPersistenceContract.DemoEntry;
/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public class DemoLocalDataSource {

    private BriteDatabase mDatabaseHelper;

    private Function<Cursor, Demo> mDemoMapperFunction;

    public DemoLocalDataSource(Context context){
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        DemoDbHelper dbHelper = new DemoDbHelper(context);
        sqlBrite.wrapDatabaseHelper(dbHelper, Schedulers.io());
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

}

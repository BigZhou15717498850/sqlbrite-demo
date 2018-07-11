package com.zhou.demo.demo_sqlbrite.data;


import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.content.Context;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import io.reactivex.schedulers.Schedulers;

/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public class DemoDbHelper{
    private static DemoDbHelper mInstance;

    public static final String DATABASE_NAME ="demo.db";

    public static DemoDbHelper getInstance() {
        if(mInstance==null){
            return new DemoDbHelper();
        }
        return mInstance;
    }


    public SqlBrite provideSqlBrite(){
        return new SqlBrite.Builder().logger(new SqlBrite.Logger() {
            @Override
            public void log(String message) {

            }
        }).build();
    }

    public BriteDatabase provideDatabase(SqlBrite sqlBrite, Context context){
        Configuration configuration = Configuration.builder(context)
                .name(DATABASE_NAME)
                .callback(new DemoDbCallback())
                .build();
        Factory factory = new FrameworkSQLiteOpenHelperFactory();
        SupportSQLiteOpenHelper openHelper = factory.create(configuration);
        return sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

}

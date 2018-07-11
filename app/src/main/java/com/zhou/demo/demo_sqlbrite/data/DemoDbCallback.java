package com.zhou.demo.demo_sqlbrite.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

import com.squareup.sqlbrite3.SqlBrite;

/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public class DemoDbCallback extends SupportSQLiteOpenHelper.Callback {

    public static final int DATABASE_VERSION =1;

    //类型
    private static final String TYPE_TEXT=" TEXT";
    private static final String TYPE_BOOLEAN=" INTEGER";
    private static final String COMMA_SEP=",";

    private static final String SQL_CREATE_DEMO_DB =
            "CREATE TABLE "+DemoPersistenceContract.DemoEntry.TABLE_NAME + "("
                    + DemoPersistenceContract.DemoEntry.COLUMN_NAME_USERNAME + TYPE_TEXT + "PRIMARY KEY,"
                    + DemoPersistenceContract.DemoEntry.COLUMN_NAME_DESCRIPTION + TYPE_TEXT +COMMA_SEP
                    + DemoPersistenceContract.DemoEntry.COLUMN_NAME_AGE + TYPE_TEXT + COMMA_SEP
                    + DemoPersistenceContract.DemoEntry.COLUMN_NAME_SEX + TYPE_TEXT + COMMA_SEP
                    + DemoPersistenceContract.DemoEntry.COLUMN_NAME_ENABLE + TYPE_BOOLEAN + ")";
    /**
     * Creates a new Callback to get database lifecycle events.
     *
     */
    public DemoDbCallback() {
        super(DATABASE_VERSION);
    }

    @Override
    public void onCreate(SupportSQLiteDatabase db) {
            db.execSQL(SQL_CREATE_DEMO_DB);
    }

    @Override
    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.zhou.demo.demo_sqlbrite.data;

import android.provider.BaseColumns;

/**
 * 作者 ly309313
 * 日期 2018/7/11
 * 描述
 */

public final class DemoPersistenceContract {

    private DemoPersistenceContract() {
    }

    public static abstract class DemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_ENABLE = "enable";
    }
}

package cn.edu.bupt.sdmda.dbdemo;

import android.provider.BaseColumns;

/**
 * Created by liuyang on 11/30/16.
 */
public final class MemoContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "memo.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = " ,";

    private MemoContract() {
    }

    public static class MemoTable implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_MODTIME = "modtime";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + MemoTable.TABLE_NAME + " (" +
                MemoTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                MemoTable.COLUMN_NAME_MODTIME + TEXT_TYPE + COMMA_SEP +
                MemoTable.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                MemoTable.COLUMN_NAME_CONTENT + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + MemoTable.TABLE_NAME;
    }
}

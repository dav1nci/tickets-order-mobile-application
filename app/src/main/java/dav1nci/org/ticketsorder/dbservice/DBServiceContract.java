package dav1nci.org.ticketsorder.dbservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by dav1nci on 01.12.15.
 */
public class DBServiceContract extends SQLiteOpenHelper
{
    public static abstract class FeedExample implements BaseColumns
    {
        public static final String TABLE_NAME = "Test";
        public static final String COLUMN_NAME_TEST_ID = "test_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        private static final String TEXT_TYPE = " TEXT";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + FeedExample.TABLE_NAME + " (" +
            FeedExample._ID + " INTEGER PRIMARY KEY," +
            FeedExample.COLUMN_NAME_TEST_ID + TEXT_TYPE + COMMA_SEP +
            FeedExample.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
            FeedExample.COLUMN_NAME_SUBTITLE + TEXT_TYPE + ")";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + FeedExample.TABLE_NAME;

    public DBServiceContract(Context context)
    {
        super(context, "Test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }




}

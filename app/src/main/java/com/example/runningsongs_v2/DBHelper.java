package com.example.runningsongs_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "RunnerTrackerDB.db";
    public static final String TABLE_RUNNERTRACKER = "RunnerTracker2";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_SONG_STAMPS = "song_stamps";

    public DBHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
//        String CREATE_RUNNERTRACKER_TABLE = "CREATE TABLE " +
//                TABLE_RUNNERTRACKER + "("
//                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DISTANCE
//                + " INTEGER," + COLUMN_DATE + " TEXT,"+
//                COLUMN_TIME + " TEXT"+ ")";
        String CREATE_RUNNERTRACKER_TABLE = "CREATE TABLE " +
                TABLE_RUNNERTRACKER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DISTANCE
                + " INTEGER," + COLUMN_DATE + " TEXT,"+
                COLUMN_TIME + " TEXT,"+ COLUMN_SONG_STAMPS + " TEXT"+ ")";
        db.execSQL(CREATE_RUNNERTRACKER_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUNNERTRACKER);
            onCreate(db);
        }

    }
    public void addRunnerTracker(RunnerTracker runnerTracker) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_DISTANCE, runnerTracker.getRunnerTrackerDistance());
        values.put(COLUMN_DATE, runnerTracker.getRunnerTrackerDate());
        values.put(COLUMN_TIME, runnerTracker.getRunnerTrackerTime());

        int idx = 0;
        String f = "";
        for(SongStamp s: runnerTracker.getSongStamps()) {
            // Example 1:title-artist:12.9219-92.009;2:title-artist:12.9219-92.009;
            f += (String.valueOf(idx) + ":" + s.song.title + "###" + s.song.artist + ":" + String.valueOf(s.latLng.latitude) + "###" + String.valueOf(s.latLng.longitude) + ";");
            idx += 1;
        }
        values.put(COLUMN_SONG_STAMPS, f);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RUNNERTRACKER, null, values);

        db.close();
    }
}

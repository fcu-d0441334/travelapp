package com.kaiappgo.go;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {
    public SQLiteDatabase db=null; // 資料庫類別
    private final static String	DATABASE_NAME= "db1.db";// 資料庫名稱
    private final static String	TABLE_NAME="table01"; // 資料表名稱
    private final static String	_ID	= "_id"; // 資料表欄位/
    private final static String	NAME = "name";
    private final static String	ADDR1 = "addr1";
    private final static String	ADDR2 = "addr2";
    private final static String	CONTEXT = "context";
    /* 建立資料表的欄位 */
    private final static String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY," + NAME + " TEXT,"+ ADDR1 + " INTERGER,"+ ADDR2 + " INTERGER," + CONTEXT + " TEXT)";

    private Context mCtx = null;
    public MyDB(Context ctx){
        this.mCtx = ctx;      // 傳入 建立物件的 ＭainActivity
    }

    public void open() throws SQLException { // 開啟已經存在的資料庫
        db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try	{
            db.execSQL(CREATE_TABLE);// 建立資料表
        }catch (Exception e) {
        }
    }

    public void close() {  // 關閉資料庫
        db.close();
    }

    public Cursor getAll() { // 查詢所有資料
        return db.query(TABLE_NAME,
                new String[] {_ID, NAME, ADDR1,ADDR2,  CONTEXT},
                null, null, null, null, null,null);
    }

    public Cursor get(long rowId) throws SQLException { // 查詢指定 ID 的資料
        Cursor mCursor = db.query(TABLE_NAME,
                new String[] {_ID, NAME, ADDR1, ADDR2, CONTEXT},
                _ID +"=" + rowId, null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public long append(String name,double addr1,double addr2, String context) { // 新增一筆資料
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(ADDR1, addr1);
        args.put(ADDR2, addr2);
        args.put(CONTEXT, context);
        return db.insert(TABLE_NAME, null, args);
    }

}

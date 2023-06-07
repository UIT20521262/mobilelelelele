package edu.poly.lab03_2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    Context con;
    public DB(Context con){
        super(con, "SinhVien", null, 1);
        this.con=con;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
        public int thucthisql(String sql) {
            try{
                getReadableDatabase().execSQL(sql);
                return 1;
            }catch (SQLException e) {
                return 0;
            }
        }
        public Cursor laydulieu(String sql) {
            Cursor cursor = getReadableDatabase().rawQuery(sql,null);
            return cursor;
        }


}

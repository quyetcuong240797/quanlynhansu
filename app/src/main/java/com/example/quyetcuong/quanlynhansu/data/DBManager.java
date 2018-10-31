package com.example.quyetcuong.quanlynhansu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.quyetcuong.quanlynhansu.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employee_database";
    private static final String TABLE_NAME = "employee_database";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String CHUC_VU = "chuc_vu";


    private Context context;


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                NAME + " TEXT, " +
                ADDRESS + " TEXT, " +
                PHONE_NUMBER + " TEXT," +
                EMAIL + " TEXT," +
                CHUC_VU + " TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Tạo Thành Công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
    }


    //Add new a student
    public void addStudent(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, nhanVien.getName());
        values.put(PHONE_NUMBER, nhanVien.getPhone_number());
        values.put(EMAIL, nhanVien.getEmail());
        values.put(CHUC_VU, nhanVien.getChuc_vu());

        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    //Select a student by ID
    public NhanVien getNhanVienById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,
                        NAME, ADDRESS, PHONE_NUMBER, EMAIL, CHUC_VU}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NhanVien nhanVien = new NhanVien(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        cursor.close();
        db.close();
        return nhanVien;
    }

    //lay toan bo danh sach nhan vien

    public List<NhanVien> getAllStudent() {
        List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(cursor.getString(0));
                nhanVien.setName(cursor.getString(1));
                nhanVien.setAddress(cursor.getString(2));
                nhanVien.setPhone_number(cursor.getString(4));
                nhanVien.setEmail(cursor.getString(5));
                nhanVien.setChuc_vu(cursor.getString(6));

                listNhanVien.add(nhanVien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNhanVien;
    }


}

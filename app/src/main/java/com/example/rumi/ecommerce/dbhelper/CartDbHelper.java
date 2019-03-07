package com.example.rumi.ecommerce.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rumi.ecommerce.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDbHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "db_carts";
    private static final String COL_ID = "ID";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";
    private Context mContext;

    public CartDbHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_NAME + " TEXT, " + PRICE + " DECIMAL(10, 5), " + QUANTITY + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Cart> getAll() {
        List<Cart> carts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String product_name = cursor.getString(1);
            Double price = cursor.getDouble(2);
            int quantity = cursor.getInt(3);
            Cart note = new Cart(id, product_name, price, quantity);
            carts.add(note);
        }

        cursor.close();

        return carts;
    }

    public boolean addData(String product_name, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, product_name);
        contentValues.put(PRICE, price);
        contentValues.put(QUANTITY, quantity);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public int getCartCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int result = cursor.getCount();

        cursor.close();

        return result;
    }

    public double getCartTotal() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(price) as total FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        double total = 0;
        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("total"));// get final total
        }

        cursor.close();

        return total;
    }
}

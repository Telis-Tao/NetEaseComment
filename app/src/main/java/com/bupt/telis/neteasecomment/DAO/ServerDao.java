package com.bupt.telis.neteasecomment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Telis on 2015/6/9.
 */
public class ServerDao {
    private DBHelper dbHelper;

    public ServerDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean add(String table, ContentValues values) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            long id = db.insert(table, null, values);
            flag = (id != -1 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return flag;
    }

    public List<Map<String, String>> queryMulti(String table, String[] columns, String
            whereClause, String[] selectionArgs, String groupBy, String having, String orderBy,
                                                String limit) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        SQLiteDatabase db = null;
        Cursor cursor;
        try {
            db = dbHelper.getWritableDatabase();
            cursor = db.query(table, columns, whereClause, selectionArgs, groupBy, having,
                    orderBy, limit);
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    map.put(columnName, columnValue);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return list;
    }

    public List<Map<String, String>> queryMulti(String sql) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        SQLiteDatabase db = null;
        Cursor cursor;
        try {
            db = dbHelper.getWritableDatabase();
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    map.put(columnName, columnValue);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return list;
    }

    public Map<String, String> querySingle(String table, String[] columns, String flag, String
            groupBy, String having, String orderBy, String limit) {
        Map<String, String> map = new HashMap<String, String>();
        SQLiteDatabase db = null;
        Cursor cursor;
        try {
            db = dbHelper.getWritableDatabase();
            cursor = db.query(table, columns, "flag like ?", new String[]{flag}, groupBy, having,
                    orderBy, limit);
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    map.put(columnName, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return map;
    }

    public Map<String, String> querySingle(String sql) {
        return null;
    }

    public String queryValue(String table, String[] columns, String key, String value) {
        String result = null;
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query(table, columns, key + " like ?", new String[]{value}, null,
                    null, null, null);
            cursor.moveToNext();
            String columnName = cursor.getColumnName(0);
            result = cursor.getString(cursor.getColumnIndex(columnName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return result;
    }

    public String queryValue(String sql) {
        //未实现
        return null;
    }
}

package cn.edu.bupt.sdmda.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by liuyang on 11/29/16.
 */
public class MemoAdapter extends BaseAdapter{

    // The viewholder
    public static class ViewHolder {
        TextView vTitle, vModTime;
    }

    private Context context;
    private int resId;
    private LayoutInflater inflater;
    private List<Map<String, Object>> data;
    private MemoSQLHelper sqlHelper;

    public MemoAdapter(Context ctx, MemoSQLHelper s, int layout) {
        context = ctx;
        resId = layout;
        inflater = LayoutInflater.from(context);
        sqlHelper = s;
        data = readSQL();
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            convertView = inflater.inflate(resId, parent, false);
            vh  = new ViewHolder();
            vh.vTitle = convertView.findViewById(R.id.memotitle);
            vh.vModTime =  convertView.findViewById(R.id.modtime);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.vTitle.setText((String)data.get(position).get(MemoContract.MemoTable.COLUMN_NAME_TITLE));
        // Get timestamp from data and format it
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        vh.vModTime.setText(df.format(new Timestamp(
                Long.parseLong((String)data.get(position).get(MemoContract.MemoTable.COLUMN_NAME_MODTIME)))));
        return convertView;
    }

    public void freshData() {
        data = readSQL();
    }

    private List<Map<String, Object>> readSQL() {
        List<Map<String, Object>> ret = new ArrayList<>();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        // the column we need
        String[] projection = {
                MemoContract.MemoTable._ID,
                MemoContract.MemoTable.COLUMN_NAME_MODTIME,
                MemoContract.MemoTable.COLUMN_NAME_TITLE,
                MemoContract.MemoTable.COLUMN_NAME_CONTENT
        };
        // how to order the result
        String sortOrder = MemoContract.MemoTable.COLUMN_NAME_MODTIME + " DESC";
        // query and get cursor
        Cursor c = db.query(
                MemoContract.MemoTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        // iterate all data and add them to "data"
        while(c.moveToNext()) {
            Map<String, Object> tempData = new HashMap<>();
            tempData.put(MemoContract.MemoTable.COLUMN_NAME_TITLE,
                    c.getString(c.getColumnIndex(MemoContract.MemoTable.COLUMN_NAME_TITLE)));
            tempData.put(MemoContract.MemoTable.COLUMN_NAME_CONTENT,
                    c.getString(c.getColumnIndex(MemoContract.MemoTable.COLUMN_NAME_CONTENT)));
            tempData.put(MemoContract.MemoTable.COLUMN_NAME_MODTIME,
                    c.getString(c.getColumnIndex(MemoContract.MemoTable.COLUMN_NAME_MODTIME)));
            tempData.put(MemoContract.MemoTable._ID,
                    c.getInt(c.getColumnIndex(MemoContract.MemoTable._ID)));
            ret.add(tempData);
        }
        c.close();
        db.close();
        return ret;
    }

    public void addMemo(String t, String c, long modtime) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        // construct the key-value data to insert into the database
        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoTable.COLUMN_NAME_TITLE, t);
        values.put(MemoContract.MemoTable.COLUMN_NAME_CONTENT, c);
        values.put(MemoContract.MemoTable.COLUMN_NAME_MODTIME, modtime);
        // INSERT INTO MemoContract.MemoTable.TABLE_NAME (values.KEYS) VALUES (values.VALUES)
        db.insert(MemoContract.MemoTable.TABLE_NAME, null, values);
        db.close();
    }

    public void updateMemo(int id, String t, String c, long modtime) {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        // construct the key-value data to update the database
        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoTable.COLUMN_NAME_TITLE, t);
        values.put(MemoContract.MemoTable.COLUMN_NAME_CONTENT, c);
        values.put(MemoContract.MemoTable.COLUMN_NAME_MODTIME, modtime);
        String whereClause = MemoContract.MemoTable._ID + " = ?";
        String[] whereArgs = new String[] {id+""};
        // UPDATE MemoContract.MemoTable.TABLE_NAME SET values.KEYS=values.VALUES WHERE whereClause=whereArgs
        db.update(MemoContract.MemoTable.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }


    public void deleteData(int position) {
        // get the id of data first
        int id = (int)data.get(position).get(MemoContract.MemoTable._ID);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        // use id to find that row
        String selections = MemoContract.MemoTable._ID + " = ?";
        // NOTE: convert id from int to string
        String[] selectionArgs = {id+""};
        // DELETE FROM MemoContract.MemoTable.TABLE_NAME WHERE selections=selectionArgs
        db.delete(MemoContract.MemoTable.TABLE_NAME, selections, selectionArgs);
        // remove the data in the list
        data.remove(position);
        db.close();
    }


}

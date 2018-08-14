package com.example.secha.a0725_sub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FamousRestaurant extends MenuActivity {

    private MyDBHelper mDBHelper;
    private EditText editRail,editRes,editStation;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resdate);

        mDBHelper=new MyDBHelper(this);

        editRail=(EditText)findViewById(R.id.editRail);
        editRes=(EditText)findViewById(R.id.editRes);
        editStation=(EditText)findViewById(R.id.editStation);

        textResult=(TextView)findViewById(R.id.textResult);

    }
    public void mOnclick(View v){
        SQLiteDatabase db;
        ContentValues values;
        String[] projection={"_id","rail","famous","station"};
        Cursor cur;

        switch(v.getId()){

            case R.id.btnInsert://삽입클릭
                if(editRail.getText().length()>0 && editRes.getText().length()>0 && editStation.getText().length()>0)
                {
                    db=mDBHelper.getWritableDatabase();
                    values=new ContentValues();

                    values.put("rail",editRail.getText().toString());
                    values.put("famous",editRes.getText().toString());
                    values.put("station",editStation.getText().toString());

                    db.insert("restaurant",null,values);
                    mDBHelper.close();
                }
                break;

            case R.id.btnAll://전체검색
                db=mDBHelper.getReadableDatabase();
                cur=db.query("restaurant",projection,null,null,null,null,null);
                if(cur !=null){
                    showResult(cur);
                    cur.close();
                }
                mDBHelper.close();
                break;

            case R.id.btnName://이름검색
                if(editRail.getText().length()>0){
                    db=mDBHelper.getReadableDatabase();
                    String rail=editRail.getText().toString();
                    cur=db.query("restaurant",projection,"rail=?",new String[]{rail},null,null,null);
                    if(cur != null) {
                        showResult(cur);
                        cur.close();
                    }
                    mDBHelper.close();
                }
                break;
        }
    }

    private void showResult(Cursor cur) {
        textResult.setText("");
        int rail_col=cur.getColumnIndex("rail");
        int famous_col=cur.getColumnIndex("famous");
        int station_col=cur.getColumnIndex("station");

        while(cur.moveToNext()) {
            String rail =cur.getString(rail_col);
            String famous =cur.getString(famous_col);
            String station =cur.getString(station_col);

            textResult.append(rail+","+famous+","+station+"\n");
        }
    }

}



class MyDBHelper extends SQLiteOpenHelper{
    public MyDBHelper(Context context){
        super(context,"mytest.db",null,1);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE restaurant(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"rail TEXT,famous TEXT,station TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);

    }

}

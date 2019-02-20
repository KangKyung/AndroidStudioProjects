package com.example.rudgn.history;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BrowserHistory extends Activity {

    Button btn_history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_history);



        btn_history = (Button)findViewById(R.id.btn_history);
        btn_history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String[] proj = new String[] { Browser.EXTRA_HEADERS, Browser.BookmarkColumns.URL };
                String sel = Browser.BookmarkColumns.BOOKMARK + " = 0"; // 0 = history, 1 = bookmark
                Cursor mCur = getContentResolver().query(Browser.BOOKMARKS_URI, proj, sel, null, null);
                mCur.moveToFirst();
                String title = "";
                String url = "";
                if (mCur.getCount()> 0 && mCur !=null) {
                    boolean cont = true;
                    while (mCur.isAfterLast() == false && cont) {
                        title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
                        url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));
                        // Do something with title and url



                        mCur.moveToNext();
                    }
                }




            }
        });


//this one for toast
        Cursor mCur =this.managedQuery(Browser.BOOKMARKS_URI,
                Browser.HISTORY_PROJECTION, null, null, null);
        mCur.moveToFirst();
        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            while (mCur.isAfterLast() == false) {
                Toast.makeText(
                        BrowserHistory.this,
                        mCur.getString(Browser.HISTORY_PROJECTION_TITLE_INDEX),
                        Toast.LENGTH_LONG).show();
                Toast.makeText(
                        BrowserHistory.this,
                        mCur.getString(Browser.HISTORY_PROJECTION_URL_INDEX),
                        Toast.LENGTH_LONG).show();
                mCur.moveToNext();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
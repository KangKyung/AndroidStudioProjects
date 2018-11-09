package com.example.rudgn.a180921;

import java.io.Serializable;

/**
 * Created by rudgn on 2018-09-21.
 */

public class SimpleData implements Serializable {
    private static final long serialVersionUID = 1000000L;
    private int mIntData = 0;
    private String mStrData = "Superdroid";

    public int getIntData() {
        return mIntData();
    }

    public String getStringData() {
        return mStrData;
    }

    public void setIntData(int intData) {
        mIntData = intData;
    }

    public void setStringData( String StrData) {
        mStrData = strData;
    }
}




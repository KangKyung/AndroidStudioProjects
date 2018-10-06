package com.example.rudgn.aactivity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rudgn on 2018-10-06.
 */

public class SampleData implements Parcelable {
    private int mIntData = 0;
    private String mStrData = "Superdroid";

    public int getIntData(){
        return mIntData;
    }

    public String getStrData() {
        return mStrData;
    }

    public void setIntData(int IntData) {
        this.mIntData = IntData;
    }

    public void setStrData(String StrData) {
        this.mStrData = StrData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //  송신 측 사용함수
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIntData);
        dest.writeString(mStrData);
    }

    public static final Parcelable.Creator<SampleData> CREATOR = new Creator<SampleData>() {
        //  수신 측 사용함수
        @Override
        public SampleData createFromParcel(Parcel src) {
            SampleData sampleData = new SampleData();

            sampleData.setIntData(src.readInt());
            sampleData.setStrData(src.readString());

            return sampleData;
        }

        @Override
        public SampleData[] newArray(int size) {
            return null;
        }
    };
}

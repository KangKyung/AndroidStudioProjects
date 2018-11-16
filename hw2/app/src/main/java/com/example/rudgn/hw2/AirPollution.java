package com.example.rudgn.hw2;

/**
 * Created by rudgn on 2018-11-16.
 */

//  각 값들을 저장하는 클래스
public class AirPollution {
    String date;
    String location;
    int NO2;
    int O3;
    int CO;
    int SO2;
    int PM10;
    int PM25;

    public void setDate(String dt){ this.date = dt;}
    public void setLocation(String lc){ this.location = lc;}
    public void setNO2(int no2){ this.NO2 = no2;}
    public void setO3(int o3){ this.O3 = o3;}
    public void setCO(int co){ this.CO = co;}
    public void setSO2(int so2){ this.SO2 = so2;}
    public void setPM10(int pm10){ this.PM10 = pm10;}
    public void setPM25(int pm25){ this.PM25 = pm25;}

    public String getDate(){ return date;}
    public String getLocation() { return location;}
    public int getNO2() { return NO2;}
    public int getO3() { return O3; }
    public int getCO() { return CO; }
    public int getSO2(){ return SO2;}
    public int getPM10(){ return PM10;}
    public int getPM25(){ return PM25;}
}

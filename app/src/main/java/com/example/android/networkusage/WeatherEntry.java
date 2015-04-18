package com.example.android.networkusage;

import java.util.Arrays;

/**
 * Created by u1 on 2015/4/18.
 */
public class WeatherEntry {

    private  String locationName;
    private  String[] startTime;
    private  String[] endTime;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStartTime(int i) {
        return startTime[i];
    }

    public void setStartTime(String startTime,int i) {
        this.startTime[i] = startTime;
    }

    public String getEndTime(int i) {
        return endTime[i];
    }

    public void setEndTime(String endTime, int i) {
        this.endTime[i] = endTime;
    }

    public String getParameterWxName(int i) {
        return parameterWxName[i];
    }

    public void setParameterWxName(String parameterWxName,int i) {
        this.parameterWxName[i] = parameterWxName;
    }

    public int getParameterWxValue(int i) {
        return parameterWxValue[i];
    }

    public void setParameterWxValue(int parameterWxValue, int i) {
        this.parameterWxValue[i] = parameterWxValue;
    }

    public int getParameterMaxTName(int i) {
        return parameterMaxTName[i];
    }

    public void setParameterMaxTName(int parameterMaxTName,int i) {
        this.parameterMaxTName[i] = parameterMaxTName;
    }

    public int getParameterMinTName(int i) {
        return parameterMinTName[i];
    }

    public void setParameterMinTName(int parameterMinTName,int i) {
        this.parameterMinTName[i] = parameterMinTName;
    }

    private  String[] parameterWxName;
    private  int [] parameterWxValue;
    private  int[] parameterMaxTName;
    private  int[] parameterMinTName;

    public WeatherEntry(){

        startTime=new String[14];
        endTime=new String[14];
        parameterWxName=new String[14];
        parameterWxValue=new int[14];
        parameterMaxTName=new int[14];
        parameterMinTName=new int[14];


    }

//    @Override
//    public String toString() {
//        return "WeatherEntry{" +
//                "locationName='" + locationName + '\'' +
//                ", startTime=" + Arrays.toString(startTime) +
//                '}';
//    }
}
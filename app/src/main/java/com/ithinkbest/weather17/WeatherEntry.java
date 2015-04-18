package com.ithinkbest.weather17;

import java.util.Arrays;

/**
 * Created by u1 on 2015/4/18.
 */
public class WeatherEntry {

    private String locationName;
    private String[] startTime;
    private String[] endTime;


    private String[] parameterWxName;
    private int[] parameterWxValue;
    private int[] parameterMaxTName;
    private int[] parameterMinTName;

    public WeatherEntry() {

        startTime = new String[15];
        endTime = new String[15];
        parameterWxName = new String[15];
        parameterWxValue = new int[15];
        parameterMaxTName = new int[15];
        parameterMinTName = new int[15];


    }
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStartTime(int i) {
        return startTime[i];
    }

    public void setStartTime(String startTime, int i) {
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

    public void setParameterWxName(String parameterWxName, int i) {
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

    public void setParameterMaxTName(int parameterMaxTName, int i) {
        this.parameterMaxTName[i] = parameterMaxTName;
    }

    public int getParameterMinTName(int i) {
        return parameterMinTName[i];
    }

    public void setParameterMinTName(int parameterMinTName, int i) {
        this.parameterMinTName[i] = parameterMinTName;
    }

    String getDateStr(String str) {
        return str.substring(5, 7) + "/" + str.substring(8, 10) + " " + str.substring(11, 16);
    }

    public String getCurrent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            sb.append(getDateStr(startTime[i])).append(" ~ ").append(getDateStr(endTime[i])).
                    append("<br>");
            sb.append(parameterWxName[i]).append(" ");
            sb.append("最低" + parameterMinTName[i]).append(" ");
            sb.append("最高" + parameterMaxTName[i]).append("度<br><br>");


        }
        return sb.toString();
    }

    public String getDetail() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            sb.append(getDateStr(startTime[i])).append(" ~ ").append(getDateStr(endTime[i])).
                    append("<br>");
            sb.append(parameterWxName[i]).append(" ");
            sb.append("最低" + parameterMinTName[i]).append(" ");
            sb.append("最高" + parameterMaxTName[i]).append("度<br><br>");


        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "WeatherEntry{" +
                "locationName='" + locationName + '\'' +
                ", startTime=" + Arrays.toString(startTime) +
                ", endTime=" + Arrays.toString(endTime) +
                ", parameterWxName=" + Arrays.toString(parameterWxName) +
                ", parameterWxValue=" + Arrays.toString(parameterWxValue) +
                ", parameterMaxTName=" + Arrays.toString(parameterMaxTName) +
                ", parameterMinTName=" + Arrays.toString(parameterMinTName) +
                '}';
    }
}
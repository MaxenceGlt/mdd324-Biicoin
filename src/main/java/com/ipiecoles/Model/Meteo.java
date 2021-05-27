package com.ipiecoles.Model;

import java.sql.Timestamp;

public class Meteo {
    private String coucher;
    private Integer humidite;
    private String icon;
    private String lever;
    private Double temp;
    private String temps;



    public String getCoucher() {
        return coucher;
    }

    public void setCoucher(String coucher) {
        this.coucher = coucher;
    }

    public Integer getHumidite() {
        return humidite;
    }

    public void setHumidite(Integer humidite) {
        this.humidite = humidite;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }
}

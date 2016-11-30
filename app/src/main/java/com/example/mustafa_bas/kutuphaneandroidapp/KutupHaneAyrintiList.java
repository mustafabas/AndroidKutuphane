package com.example.mustafa_bas.kutuphaneandroidapp;

import java.util.List;

/**
 * Created by Mustafa-Bas on 5/1/2015.
 */
public class KutupHaneAyrintiList {
    private String command;
    private List<KutuphaneAyrintiHelper> kutuphaneList;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<KutuphaneAyrintiHelper> getKutuphaneList() {
        return kutuphaneList;
    }

    public void setKutuphaneList(List<KutuphaneAyrintiHelper> kutuphaneList) {
        this.kutuphaneList = kutuphaneList;
    }
}

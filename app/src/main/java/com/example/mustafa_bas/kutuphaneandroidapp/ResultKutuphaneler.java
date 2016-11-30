package com.example.mustafa_bas.kutuphaneandroidapp;

import java.util.List;

/**
 * Created by Mustafa-Bas on 4/30/2015.
 */
public class ResultKutuphaneler {
    private String command;
    private List<kutuphaneler> kutuphaneList;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<kutuphaneler> getKutuphaneList() {
        return kutuphaneList;
    }

    public void setKutuphaneList(List<kutuphaneler> kutuphaneList) {
        this.kutuphaneList = kutuphaneList;
    }
}

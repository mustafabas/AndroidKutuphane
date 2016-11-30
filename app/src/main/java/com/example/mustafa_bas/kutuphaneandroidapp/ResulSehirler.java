package com.example.mustafa_bas.kutuphaneandroidapp;
import java.util.List;
/**
 * Created by Mustafa-Bas on 4/29/2015.
 */
public class ResulSehirler {
    private String command;
    private List<Sehirler> sehirList;


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Sehirler> getSehirList() {
        return sehirList;
    }

    public void setSehirList(List<Sehirler> sehirlerList) {
        this.sehirList = sehirlerList;
    }
}

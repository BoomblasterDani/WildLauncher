package com.example.wildlauncher;

public class Application { //Blueprint for app info
    public Application(int id, String name, String path, String scriptPath) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.scriptPath = scriptPath;
        //this.running = running;
    }

    public String name;
    public int id;
    public String path;
    public String scriptPath;
    public boolean running;


}

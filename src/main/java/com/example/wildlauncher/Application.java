package com.example.wildlauncher;

public class Application { //Blueprint for app infos
    public Application(String name, String path, String scriptPath, boolean running) {
        this.name = name;
        this.path = path;
        this.scriptPath = scriptPath;
        this.running = running;
    }

    public String name;
    public int id;
    public String path;
    public String scriptPath;
    public boolean running;


}

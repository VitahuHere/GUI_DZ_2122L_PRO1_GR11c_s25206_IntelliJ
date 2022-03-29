package main;


import app.App;
import java.util.Timer;


public class Main {
    public static void main(String[] args){
        Task task = new Task();
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);

    }
}

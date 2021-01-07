package pl.fox.neuralsnake;

import java.awt.*;

public class Info {

    private int minutes = 0;
    private int seconds = 0;

    public void update(){
        if(seconds == 60){
            minutes++;
            seconds = 0;
        }
    }

    public void render(Graphics2D g){
        g.setColor(Color.GREEN);
        g.drawString(minutes + " : " + seconds, 100, 100);
    }


    public int getSeconds() {
        return seconds;
    }

    public void addSeconds() {
        seconds++;
    }
}

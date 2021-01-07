package pl.fox.neuralsnake;

import pl.fox.neuralsnake.display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;

    private final String title;
    private final int width;
    private final int height;

    private boolean isRunning = false;

    private Thread gameThread;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init(){
        display = new Display(title, width, height);
    }

    @Override
    public void run() {
        init();

        while(isRunning){

        }
    }

    private void update(){

    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0, 0, width, height);

        bs.show();
        g2d.dispose();
    }

    public synchronized void start(){
        if(!isRunning){
            isRunning = true;
            gameThread = new Thread(this);
            gameThread.setName(title);
            gameThread.start();
        }
    }

    public synchronized void stop(){
        if(isRunning){
            try{
                gameThread.join();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}

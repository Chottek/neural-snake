package pl.fox.neuralsnake;

import pl.fox.neuralsnake.display.Display;

public class Game implements Runnable{

    private Display display;

    private final String title;
    private final int width;
    private final int height;

    private boolean isRunning = false;

    private Thread gameThread;

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

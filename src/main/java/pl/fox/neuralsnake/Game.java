package pl.fox.neuralsnake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.fox.neuralsnake.display.Display;
import pl.fox.neuralsnake.input.KeyInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Game.class);

    private Display display;
    private KeyInput keyInput;

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

        keyInput = new KeyInput();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyInput);
        display.getCanvas().addKeyListener(keyInput);

        LOG.info("Initialized {}", display.getClass().getSimpleName());
    }

    @Override
    public void run() {
        init();
        final int FPS = 20;
        double timePerTick = 1000000000 / FPS;
        double delta = 0;
        long now = 0;
        long lastTime = System.nanoTime();
        long timer = 0;

        while(isRunning){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                update();
                render();
                delta--;
            }

            if(timer >= 1000000000){
                timer = 0;
            }
        }
        stop();
    }

    private void update(){
        LOG.info("{}", keyInput.getKeyCode());
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(0, 0, width, height);

        //TODO: Render Snake and field here

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

package pl.fox.neuralsnake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public static boolean[] keys;
    public static boolean left, right, up, down;

    public KeyInput(){
        keys = new boolean[256];
    }

    public void update(){
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)){
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!(e.getKeyCode() < 0 || e.getKeyCode() > keys.length)){
            keys[e.getKeyCode()] = false;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

}

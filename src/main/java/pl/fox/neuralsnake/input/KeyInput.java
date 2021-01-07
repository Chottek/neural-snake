package pl.fox.neuralsnake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public static boolean[] keys;
    private boolean left, right, up, down;
    private boolean space, pause;

    public KeyInput(){
        keys = new boolean[256];
    }

    public void update(){
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];

        space = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_ESCAPE];
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


    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isSpace() {
        return space;
    }

    public boolean isPause() {
        return pause;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

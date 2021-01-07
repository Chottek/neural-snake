package pl.fox.neuralsnake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private int keyCode = 0;

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = 0;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int getKeyCode() {
        return keyCode;
    }
}

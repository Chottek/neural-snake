package pl.fox.neuralsnake.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int width;
    private final int height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setBackground(Color.BLACK);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public Canvas getCanvas(){ return canvas; }

    public JFrame getFrame(){ return frame; }



}

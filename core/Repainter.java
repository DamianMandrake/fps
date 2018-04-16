package core;

import java.awt.*;
/**
 * A thread that handles all repaint operations.
 * **/
public class Repainter extends Thread{
    private Frame frame;
    private BulletHandler bulletHandler;
    private Engine engine;
    public Repainter(Frame theFrame,BulletHandler bulletHandler, Engine eng){
        this.frame = theFrame;
        this.start();
        this.engine = eng;
        this.bulletHandler = bulletHandler;
    }


    public void run(){
        while(true){
            try {
                Thread.sleep(250);
                //this.frame.repaint();
                Graphics g = this.frame.getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0,0,1000,1000);
                g.setColor(Color.BLACK);
                this.engine.paint(g);
                this.bulletHandler.paintThemAll(g);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }

    }
}

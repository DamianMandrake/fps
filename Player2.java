import core.AbstractGun;
import core.AbstractPlayer;

import java.awt.Graphics;
import java.awt.Color;

/**
 * This class exists only because of the different control scheme... Could just set a
 * subclass of AbstractControls...
 * **/
public class Player2 extends AbstractPlayer {
    private final static int WIDTH = 70;
    private int maxX = 40;
    private final static int HEIGHT = 100;
    private int maxY= 40;

    public Player2(int x, int y, String name, AbstractGun gun){
        super(x,y,name,gun);
        super.setAbstractControls(new Controls());
        super.setHitBox(new Player2.Hitbox());
    }

    @Override
    public void paintPlayer(Graphics g) {
        super.paintPlayer(g);
        System.out.println("painting 2");
        g.setColor(Color.BLACK);
        g.drawRect(this.x, this.y, WIDTH, HEIGHT);

    }


    /**
     * Overriding since maxX and maxY must also be updated
     * **/
    @Override
    public void setX(int x) {
        super.setX(x);
        this.maxX = x + WIDTH;
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        this.maxY = y+HEIGHT;
    }

    class Controls extends AbstractControls{
        @Override
        public int upKey() {
            return 'i';
        }

        @Override
        public int downKey() {
            return 'k';
        }

        @Override
        public int leftKey() {
            return 'j';
        }

        @Override
        public int rightKey() {
            return 'l';
        }

        @Override
        public int shootKey() {
            return '\n';
        }

        @Override
        public int leftClick() {
            return -1;
        }

        @Override
        public int rightClick() {
            return -1;
        }
    }

    class Hitbox extends AbstractHitBox {
        @Override
        public int getMinX() {
            return Player2.this.x;
        }

        @Override
        public int getMinY() {
            return Player2.this.y;
        }

    }
}

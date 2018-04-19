import core.AbstractGun;
import core.AbstractPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Player extends AbstractPlayer {
    private final static int WIDTH = 70;
    private int maxX = 40;
    private final static int HEIGHT = 100;
    private int maxY= 40;

    public Player(int x, int y, String name, AbstractGun gun){
        super(x,y,name,gun);

        super.setAbstractControls(new Controls());
        super.setHitBox(new Hitbox());
    }

    @Override
    public void paintPlayer(Graphics g) {
        super.paintPlayer(g);
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
            return 'w';
        }

        @Override
        public int downKey() {
            return 's';
        }

        @Override
        public int leftKey() {
            return 'a';
        }

        @Override
        public int rightKey() {
            return 'd';
        }

        @Override
        public int shootKey() {
            return ' ';
        }

        @Override
        public int leftClick() {
            return MouseEvent.BUTTON1;
        }

        @Override
        public int rightClick() {
            return MouseEvent.BUTTON3;
        }
    }

    class Hitbox extends AbstractHitBox{
        @Override
        public int getMinX() {
            return Player.this.x;
        }

        @Override
        public int getMinY() {
            return Player.this.y;
        }

    }

}

import core.AbstractGun;

import java.awt.*;
/**
 * A concrete implementation of a gun.
 * The gun also has x and y and will be painted after an offset units after the player's x,y
 * **/
public class M4 extends AbstractGun {

    private final static String name = "m4";
    private static final GunCharacteristics gunChars = getChars();
    private final static int WIDTH = 30;
    private boolean isReverse;

    private int bulletxDelta,bulletyDelta ;
    public M4(int x,int y, boolean isReverse){
        super(x,y,name,gunChars);
        this.isReverse = isReverse;
        if(this.isReverse) {
            bulletxDelta = -40;
            bulletyDelta = -10;
        }else
        {
            bulletxDelta = 40;
            bulletyDelta = 10;
        }

    }

    private static GunCharacteristics getChars(){
        return new GunCharacteristics(18,6);
    }

    @Override
    public void paintGun(Graphics g) {
        g.drawArc(this.x, this.y, WIDTH,WIDTH,0,360);
    }

    @Override
    public Bullet shoot() {

        return new M4Bullet(this.x+ this.bulletxDelta,this.y +this.bulletyDelta, this.isReverse);
    }



    class M4Bullet extends Bullet{
        int oldx,oldy;
        public M4Bullet(int x,int y,boolean isReverse){
            super(x,y);
            this.oldx =this.oldy = -1;
            if(isReverse) {
                this.xoffset = -20;
                this.yoffset = 0;
            }

        }
        @Override
        public void paintBullet(Graphics g) {
/*
            g.setColor(Color.WHITE);
            g.fillArc(this.oldx, this.oldy, M4.WIDTH-20,M4.WIDTH-20,0,360);
            g.setColor(Color.BLACK);*/
            g.fillArc(this.x, this.y, M4.WIDTH-20,M4.WIDTH-20,0,360);

            this.oldx = this.x;
            this.oldy = this.y;
            this.incrementXAndY();
        }
        @Override
        public int damagePerBullet(){
            return 5;
        }
    }
}

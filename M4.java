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

    public M4(int x,int y){
        super(x,y,name,gunChars);
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
        return new M4Bullet(this.x+20,this.y+20);
    }



    class M4Bullet extends Bullet{
        public M4Bullet(int x,int y){
            super(x,y);
        }
        @Override
        public void paintBullet(Graphics g) {
            g.fillArc(this.x, this.y, M4.WIDTH-20,M4.WIDTH-20,0,360);
            this.x += 20;

        }
        @Override
        public int damagePerBullet(){
            return 20;
        }
    }
}

package core;

import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class BulletHandler {
    private LinkedList<AbstractGun.Bullet> bullets;

    public BulletHandler(){
        this.bullets = new LinkedList<>();
    }

    /** Paints all the bullets that have to be painted. Also removes ones that are outside
     * **/
    public void paintThemAll(Graphics g){
        try {
            Iterator<AbstractGun.Bullet> iterator = this.bullets.iterator();
            while (iterator.hasNext()) {
                AbstractGun.Bullet current = iterator.next();
                current.paintBullet(g);
                if (this.isOutOfBounds(current)) {
                    iterator.remove();
                }

            }
        }catch (ConcurrentModificationException cme){
            cme.printStackTrace();
        }
    }

    public void addBullet(AbstractGun.Bullet recentBullet){
        this.bullets.add(recentBullet);
    }

    private boolean isOutOfBounds(AbstractGun.Bullet bullet){
        return bullet.x > Engine.MAX_X || bullet.y > Engine.MAX_Y;
    }
    /**
     * Called while painting a player.
     * **/
    public boolean didHitPlayer(AbstractPlayer player){
        Iterator<AbstractGun.Bullet> iterator=this.bullets.iterator();
        AbstractGun.Bullet currentBullet ;
        boolean result = false;
        while (iterator.hasNext() && (result=!(iterator.next().didHitPlayer(player.getHitBox()))));

        return !result;
    }

    public interface BulletHitCallback{
        public void hit();
    }
}

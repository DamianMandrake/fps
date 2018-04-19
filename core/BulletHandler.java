package core;

import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class BulletHandler {
    private LinkedList<AbstractGun.Bullet> bullets;

    private LinkedList<BulletHitCallback> callbacks;
    public BulletHandler(){
        this.bullets = new LinkedList<>();
        this.callbacks = new LinkedList<>();
    }


    public void addCallback(BulletHitCallback ca){
        this.callbacks.add(ca);
    }

    public void remove(BulletHitCallback callback){
        this.callbacks.remove(callback);
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
        return bullet.x > Engine.MAX_X || bullet.y > Engine.MAX_Y || bullet.x < 0 || bullet .y <0;
    }
    /**
     * Called while painting a player.
     * **/
    public void didHitPlayer(AbstractPlayer player){
        if(this.bullets.size() == 0)
            return ;
        Iterator<AbstractGun.Bullet> iterator=this.bullets.iterator();

        while (iterator.hasNext() ){
            AbstractGun.Bullet current = iterator.next();
            if(current.didHitPlayer(player.getHitBox()))
                for(BulletHitCallback b:this.callbacks)
                    b.hit(player, current.damagePerBullet());
        }


    }

    public interface BulletHitCallback{
        public void hit(AbstractPlayer hit, int damagePerBullet);
    }
}

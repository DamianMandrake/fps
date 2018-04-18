package core;

import java.awt.Graphics;

public abstract class AbstractGun {
    private String name;
    protected int x;
    protected int y;
    private GunCharacteristics gunCharacteristics;
    private int ownerId;
    public AbstractGun(int x,int y,String name,GunCharacteristics gunChars,int ownerId){
        this.x = x;
        this.y = y;
        this.name = name;
        this.gunCharacteristics = gunChars;
        this.ownerId = ownerId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GunCharacteristics getGunCharacteristics() {
        return gunCharacteristics;
    }

    public void setGunCharacteristics(GunCharacteristics gunCharacteristics) {
        this.gunCharacteristics = gunCharacteristics;
    }

    protected static class GunCharacteristics{
        double damagePerBullet;
        int maxBulletCapacity;
        int angleOfFire;//for calc
        int remainingBullets;//for calc
        int bulletsInClip;//for calculations
        int maxBulletsInClip;
        public GunCharacteristics( int maxBulletCapacity
        ,int maxBulletsInClip){
            this.maxBulletCapacity = maxBulletCapacity;
            this.angleOfFire = angleOfFire;
            this.remainingBullets = 0;
            this.bulletsInClip = maxBulletsInClip;
            this.maxBulletsInClip = maxBulletsInClip;
        }

    }
    protected abstract class Bullet{
        protected int x;
        protected int y;
        protected int xoffset =20;
        protected int yoffset = 0;
        public Bullet(int x,int y){
            this.x=x;
            this.y=y;
        }
        public void setX(int x){this.x = x;}
        public void setY(int y){this.y = y;}
        public boolean didHitPlayer(AbstractPlayer.AbstractHitBox hitBox){
            return this.x >= hitBox.getMinX() && this.x <= hitBox.getMaxX() && this.y >= hitBox.getMinY() && this.y <= hitBox.getMaxY();
        }
        /**
         * Callback triggered whenever a bullet is fired and hits a Player.
         * **/
        public abstract int damagePerBullet();
        /**
         * Callback triggered when a gun is painted
         * **/
        public abstract void paintBullet(Graphics g);

        /**
         * TODO CHANGE THIS TO
         * **/
        protected void incrementXAndY(int xoffset,int yoffset){
            this.x += xoffset;
            this.y += yoffset;

        }

        @Override
        public String toString(){
            return "{id:"+AbstractGun.this.ownerId+", bx:"+this.x+", by:"+this.y+"}";
        }


    }



    /**
     * Callback fired whenever a gun is to be painted
     * **/
    public abstract void paintGun(Graphics g);
    /**
     * Callback fired whenever a key press occurs. NOTE: IT IS THE RESPONSIBILITY OF THE CHILD CLASS TO DECREMENT gunChars.bulletsInClip
     * **/
    public abstract Bullet shoot();
    /**
     * Called whenever the reload button is pressed
     * **/
    public void reload(){
        this.gunCharacteristics.bulletsInClip = this.gunCharacteristics.maxBulletsInClip;
    }
}

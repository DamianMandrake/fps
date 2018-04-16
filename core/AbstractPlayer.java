package core;

import java.awt.Graphics;

public abstract class AbstractPlayer extends AbstractPlayerModel {
    private final static int DEFAULT_HP=100;
    private final static int DEFAULT_Y_DELTA = 10;
    private final static int DEFAULT_X_DELTA = 10;


    private static AbstractControls abstractControls;

    private AbstractHitBox hitBox;
    private AbstractGun gun;


    public AbstractPlayer(int x, int y, String name, AbstractGun gun){
        super(x,y,name,0,DEFAULT_HP);
        this.gun = gun;

    }




    public AbstractGun getGun() {
        return gun;
    }

    public void setGun(AbstractGun gun) {
        this.gun = gun;
    }


    public void goUp(){
        this.y = this.y >0 ? this.y - DEFAULT_Y_DELTA: this.y;
        this.gun.y = this.gun.y >0 ? this.gun.y - DEFAULT_Y_DELTA: this.gun.y;
    }
    
    public void goDown(){
        this.y = this.y < Engine.MAX_Y ? this.y + DEFAULT_Y_DELTA:this.y;
        this.gun.y = this.gun.y < Engine.MAX_Y ? this.gun.y + DEFAULT_Y_DELTA:this.gun.y;

    }
    
    public void goRight(){
        this.x = this.x < Engine.MAX_X ? this.x + DEFAULT_X_DELTA:this.x;
        this.gun.x = this.gun.x < Engine.MAX_X ? this.gun.x + DEFAULT_X_DELTA:this.gun.x;


    }
    
    public void goLeft(){
        this.x = this.x > 0 ? this.x - DEFAULT_X_DELTA : this.x;
        this.gun.x = this.gun.x > 0 ? this.gun.x - DEFAULT_X_DELTA : this.gun.x;
    }

    public boolean isDead(){
        return this.healthPoints <= 0;
    }


    public long getId(){
        return this.id;
    }

    public static void setAbstractControls(AbstractControls AbstractControls){
        AbstractPlayer.abstractControls = AbstractControls;
    }

    public static AbstractControls getControls(){
        return AbstractPlayer.abstractControls;
    }

    public void setHitBox(AbstractHitBox hitBox){
        this.hitBox = hitBox;
    }

    public AbstractHitBox getHitBox(){
        return this.hitBox;
    }
    /** 
     * So that Controls are customisable.
     * **/
    protected abstract class AbstractControls {
        public abstract int upKey();
        public abstract int downKey();
        public abstract int leftKey();
        public abstract int rightKey();
        public abstract int shootKey();
        
        public abstract int leftClick();
        public abstract int rightClick();
    }


    protected abstract class AbstractHitBox{
        public abstract int getMinX();
        public abstract int getMinY();
        public abstract int getMaxX();
        public abstract int getMaxY();
    }

    /**
     * Callback fired when the player is to be painted on the screen. A Call to this method must be given by the base class.
     * **/
    public void paintPlayer(Graphics g){
        this.gun.paintGun(g);
    }


}

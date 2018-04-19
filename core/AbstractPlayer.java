package core;

import java.awt.*;

public abstract class AbstractPlayer extends AbstractPlayerModel {
    private final static int DEFAULT_HP=100;
    private final static int DEFAULT_Y_DELTA = 10;
    private final static int DEFAULT_X_DELTA = 10;


    private AbstractControls abstractControls;

    private AbstractHitBox hitBox;
    private AbstractGun gun;

    private int oldx,oldy;
    public AbstractPlayer(int x, int y, String name, AbstractGun gun){
        super(x,y,name,0,DEFAULT_HP);
        this.gun = gun;
        this.oldx = x;
        this.oldy = y;

    }




    public AbstractGun getGun() {
        return gun;
    }

    public void setGun(AbstractGun gun) {
        this.gun = gun;
    }


    public void goUp(){
        this.initOldValues();
        this.y = this.y >0 ? this.y - DEFAULT_Y_DELTA: this.y;
        this.gun.y = this.gun.y >0 ? this.gun.y - DEFAULT_Y_DELTA: this.gun.y;
    }
    
    public void goDown(){
        this.initOldValues();
        this.y = this.y < Engine.MAX_Y ? this.y + DEFAULT_Y_DELTA:this.y;
        this.gun.y = this.gun.y < Engine.MAX_Y ? this.gun.y + DEFAULT_Y_DELTA:this.gun.y;

    }
    
    public void goRight(){
        this.initOldValues();
        this.x = this.x < Engine.MAX_X ? this.x + DEFAULT_X_DELTA:this.x;
        this.gun.x = this.gun.x < Engine.MAX_X ? this.gun.x + DEFAULT_X_DELTA:this.gun.x;


    }
    
    public void goLeft(){
        this.initOldValues();
        this.x = this.x > 0 ? this.x - DEFAULT_X_DELTA : this.x;
        this.gun.x = this.gun.x > 0 ? this.gun.x - DEFAULT_X_DELTA : this.gun.x;
    }

    public boolean isDead(){
        return this.healthPoints <= 0;
    }


    public long getId(){
        return this.id;
    }

    public void setAbstractControls(AbstractControls AbstractControls){
        this.abstractControls = AbstractControls;
    }

    public AbstractControls getControls(){
        return this.abstractControls;
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
        protected int WIDTH = 80;
        protected int HEIGHT = 100;
        public abstract int getMinX();
        public abstract int getMinY();
        public int getMaxX(){
            return AbstractPlayer.this.x + WIDTH;
        }
        public int getMaxY(){
            return AbstractPlayer.this.x + HEIGHT;
        }
        public int getWIDTH(){return this.WIDTH;}
        public int getHEIGHT(){return this.HEIGHT;}
    }

    /**
     * An alternative to repaint. Fill the old place with the bgcolor.
     * **/
    public void paintOldPlayer(Graphics g){
        System.out.println("oldx "+oldx+" oldy "+oldy+" x "+x+" y "+y );
        g.setColor(Color.WHITE);
        g.fillRect(this.oldx, this.oldy, this.hitBox.getWIDTH() +20 , this.hitBox.getHEIGHT() +20);
        g.setColor(Color.BLACK);
    }

    /**
     * Callback fired when the player is to be painted on the screen. A Call to this method must be given by the base class.
     * **/
    @Override
    public void paintPlayer(Graphics g){
        this.gun.paintGun(g);
        g.drawString(" hp "+this.getHealthPoints(),this.x -10,this.y-10);
        g.drawString(this.getName(),this.x-10,this.y-30);
    }

    @Override
    public String toString(){
        return "{id:"+this.id+","+" x:"+this.x+",y:"+this.y+"}";

    }


    private void initOldValues(){
        this.oldx =x;
        this.oldy =y;
    }




}

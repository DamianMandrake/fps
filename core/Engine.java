package core;

import sun.util.resources.cldr.so.CurrencyNames_so;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the entire game. Spawns players , Gives them guns , paints them
 * **/
public class Engine implements BulletHandler.BulletHitCallback {

    public final static int MAX_X = 600;
    public final static int MAX_Y= 600;
    private AbstractPlayer currentPlayer,otherPlayer;
    private BulletHandler bulletHandler;

    private CustomKeyListener keyListener;
    private MouseListener mouseListener;
    private GameOverListener gameOverListener;
    /**
     * Hashmap of Players on the LAN.
     * **/
    private HashMap<Long,AbstractPlayer> players;
    public Engine(AbstractPlayer currentPlayer, AbstractPlayer otherPlayer, BulletHandler bulletHandler, GameOverListener game){
        this.players = new HashMap<>();
        this.currentPlayer = currentPlayer;
        this.bulletHandler = bulletHandler;
        this.otherPlayer = otherPlayer;
        this.keyListener = new CustomKeyListener();
        this.mouseListener = new MouseListener();
        this.gameOverListener = game;
        this.bulletHandler.addCallback(this);

    }

    public CustomKeyListener getKeyListener(){return this.keyListener;}
    public MouseListener getMouseListener(){return this.mouseListener;}


    public void addPlayer(AbstractPlayer player){
        this.players.put(player.getId(),player);
    }

    public AbstractPlayer getPlayerAt(int playerId){
        return this.players.get(playerId);
    }

    public void paint(Graphics g){
        //this.currentPlayer.paintOldPlayer(g);
        this.currentPlayer.paintPlayer(g);
        this.otherPlayer.paintPlayer(g);
        this.bulletHandler.didHitPlayer(this.currentPlayer);

        this.bulletHandler.didHitPlayer(this.otherPlayer);

        for (Long playerId: this.players.keySet()) {
            //this.players.get(playerId).paintOldPlayer(g);
            this.players.get(playerId).paintPlayer(g);
        }

        if(this.currentPlayer.healthPoints <=0 )
            this.gameOverListener.gameover(this.otherPlayer,this.currentPlayer);
        if (this.otherPlayer.healthPoints <=0)
            this.gameOverListener.gameover(this.currentPlayer, this.otherPlayer);
        System.out.println("health "+this.currentPlayer.healthPoints+" other health "+this.otherPlayer.healthPoints);
    }



    class CustomKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int currentKey = e.getKeyChar();
            AbstractPlayer.AbstractControls controls = currentPlayer.getControls(), secondary = otherPlayer.getControls();

            if(controls != null) {
                    if (currentKey == controls.upKey()) {
                        currentPlayer.goUp();
                    }

                    else if (currentKey == controls.downKey())
                        currentPlayer.goDown();
                    else if (currentKey == controls.leftKey())
                        currentPlayer.goLeft();
                    else if (currentKey == controls.rightKey())
                        currentPlayer.goRight();
                    else if (currentKey == controls.shootKey())
                        this.handleGunShot(currentPlayer);


            } if (secondary!=null){
                if (currentKey == secondary.upKey())
                    otherPlayer.goUp();
                else if (currentKey == secondary.downKey())
                    otherPlayer.goDown();
                else if (currentKey == secondary.leftKey())
                    otherPlayer.goLeft();

                else if (currentKey == secondary.rightKey())
                    otherPlayer.goRight();
                else if (currentKey == secondary.shootKey())
                    this.handleGunShot(otherPlayer);

            }
            else
                System.out.println("Controls havent been set");
        }




        private void handleGunShot(AbstractPlayer player){
            bulletHandler.addBullet(player.getGun().shoot());

        }

    }

    class MouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent me) {
            int current = me.getButton();
            AbstractPlayer.AbstractControls controls = currentPlayer.getControls();
            if (current == controls.rightClick()) {
                currentPlayer.getGun().reload();

            } else if (current == controls.leftClick()) {
                bulletHandler.addBullet(currentPlayer.getGun().shoot());

            }
        }
    }


    public interface GameOverListener{
        public void gameover(AbstractPlayer winner, AbstractPlayer loser);
    }



    @Override
    public void hit(AbstractPlayer victim, int damagePerBullet){
        victim.setHealthPoints(victim.getHealthPoints()- damagePerBullet);
    }

}

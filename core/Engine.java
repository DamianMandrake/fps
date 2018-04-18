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
public class Engine {

    public final static int MAX_X = 1000;
    public final static int MAX_Y= 1000;
    private AbstractPlayer currentPlayer;
    private BulletHandler bulletHandler;

    private CustomKeyListener keyListener;
    private MouseListener mouseListener;
    /**
     * Hashmap of Players on the LAN.
     * **/
    private HashMap<Long,AbstractPlayer> players;
    public Engine(AbstractPlayer currentPlayer, BulletHandler bulletHandler){
        this.players = new HashMap<>();
        this.currentPlayer = currentPlayer;
        this.bulletHandler = bulletHandler;
        this.keyListener = new CustomKeyListener();
        this.mouseListener = new MouseListener();

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
        for (Long playerId: this.players.keySet()) {
            //this.players.get(playerId).paintOldPlayer(g);
            this.players.get(playerId).paintPlayer(g);
        }
    }



    class CustomKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int currentKey = e.getKeyChar();
            AbstractPlayer.AbstractControls controls = AbstractPlayer.getControls();
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
                        this.handleGunShot();

            }
            else
                System.out.println("Controls havent been set");
        }




        private void handleGunShot(){
            bulletHandler.addBullet(currentPlayer.getGun().shoot());

        }

    }

    class MouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent me) {
            int current = me.getButton();
            AbstractPlayer.AbstractControls controls = AbstractPlayer.getControls();
            if (current == controls.rightClick()) {
                currentPlayer.getGun().reload();

            } else if (current == controls.leftClick()) {
                bulletHandler.addBullet(currentPlayer.getGun().shoot());

            }
        }
    }


}

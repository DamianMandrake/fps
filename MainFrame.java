import core.AbstractPlayer;
import core.BulletHandler;
import core.Engine;
import core.Repainter;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Engine.GameOverListener {
    private Engine engine;
    private Player currentPlayer;
    private Player2 otherPlayer;
    private M4 currentPlayersGun, otherPlayersGun;
    private Repainter repainter;
    private BulletHandler bulletHandler;

    public MainFrame(){
        this.currentPlayersGun = new M4(150,100, false);
        this.currentPlayer = new Player(100,100,"random",this.currentPlayersGun);
        this.bulletHandler = new BulletHandler();
        this.currentPlayer.getGun().setBullet(this.currentPlayersGun.shoot());
        this.otherPlayersGun = new M4(500,100,true);
        this.otherPlayer = new Player2(500,100,"newbie",this.otherPlayersGun);
        this.otherPlayer.getGun().setBullet(this.otherPlayersGun.shoot());

        this.engine = new Engine(this.currentPlayer, this.otherPlayer,this.bulletHandler,this);


        this.repainter = new Repainter(this,this.bulletHandler, this.engine);

        this.setSize(600,600);
        this.addKeyListener(engine.getKeyListener());
        this.addMouseListener(engine.getMouseListener());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }


    public void tp(){
        Graphics g= getGraphics();
        this.engine.paint(g);
        this.bulletHandler.paintThemAll(g);
    }

    @Override
    public void paint(Graphics g){
        this.engine.paint(g);

        this.bulletHandler.paintThemAll(g);
    }

    @Override
    public void repaint(){
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,600,600);
        g.setColor(Color.BLACK);
        this.paint(g);
    }

    public static void main(String ap[]){
        MainFrame mf=new MainFrame();
    }

    @Override
    public void gameover(AbstractPlayer winner,AbstractPlayer loser){
        //todo open dialog
        JOptionPane.showMessageDialog(this,winner.getName()+" has won");
        this.otherPlayer.setHealthPoints(100);
        this.currentPlayer.setHealthPoints(100);
        this.repaint();

    }
}

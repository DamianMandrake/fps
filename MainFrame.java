import core.BulletHandler;
import core.Engine;
import core.Repainter;

import java.awt.*;

public class MainFrame extends Frame {
    private Engine engine;
    private Player currentPlayer;
    private M4 currentPlayersGun;
    private Repainter repainter;
    private BulletHandler bulletHandler;
    public MainFrame(){
        this.currentPlayersGun = new M4(100,100);
        this.currentPlayer = new Player(100,100,"random",this.currentPlayersGun);
        this.bulletHandler = new BulletHandler();

        this.engine = new Engine(this.currentPlayer,this.bulletHandler);


        this.repainter = new Repainter(this,this.bulletHandler, this.engine);

        this.setSize(600,600);
        this.addKeyListener(engine.getKeyListener());
        this.addMouseListener(engine.getMouseListener());
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
        super.repaint();
    }

    public static void main(String ap[]){
        MainFrame mf=new MainFrame();
    }
}

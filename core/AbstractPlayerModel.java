package core;

import java.awt.*;

/**
 * A model class for holding lan players
 *
 * **/
public class AbstractPlayerModel {

    protected String name;
    protected int x;
    protected int y;
    /** Will have to attach an Observer on hp.**/
    protected int healthPoints;
    protected int score;
    protected long id;// a unique identifier attached to each player


    public AbstractPlayerModel(int x,int y, String name, int score, int healthPoints){
        this.x = x;
        this.y = y;
        this.name =name;
        this.score =score;
        this.healthPoints = healthPoints;
        this.id = this.name.hashCode() + System.currentTimeMillis();
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

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void paintPlayer(Graphics g){
        g.drawRect(this.x, this.y, 80,100);
    }

}

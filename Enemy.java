import javax.swing.*;
import com.sun.prism.BasicStroke;
import java.awt.*;

public class Enemy{

    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;  //velocidade da bolinha

    private double type;
    private double rank;

    private Color color1;

    private boolean ready; //se o inimigo esta na tela
    //private boolean death;

    public Enemy(int type,int rank){
        this.type = type;
        this.rank = rank;

        //deafault enemy
        if(type ==1){
            color1 = Color.green;
            if(rank == 1){
                speed = 1; //speed pequeno (começo)
                r = 7;
            }
        }

        x = Math.random() * Tela.width/2 + Tela.width / 4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;

    }

    public double getx(){ return x; }
    public double gety(){ return y; }
    public double getr(){ return r; }

    public void hit(){


    }

    public void update(){
        x += dx;
        y += dy;

        if(!ready){
            if(x>r && x< Tela.width - r && y>r && y<Tela.height - r){
                ready = true;
            } //se esta dentro da tela do jogo
        }

        if(x<r && dx<0) dx = -dx;
        if(y<r && dy<0) dy = -dy;
        if(x > Tela.width - r && dx > 0 ) dx = -dx;
        if(y > Tela.height - r && dy > 0) dy = -dy;

    }

    public void draw(Graphics2D g){

        g.setColor(color1);
        g.fillOval((int) (x-r), (int) (y-r), 2 * r, 2 * r);

        //g.setStroke(new BasicStroke(1.5f));
        g.setColor(color1.darker());
        g.drawOval((int) (x-r), (int) (y-r), 2 * r, 2 * r);
        //g.setStroke(new BasicStroke(1.0f));

    }
}

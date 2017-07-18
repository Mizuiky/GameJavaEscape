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

    public Enemy(int rank){
        this.rank = rank;

        //deafault enemy
        if(rank ==1){
            color1 = Color.green;
            speed = 6; //speed pequeno (começo)
            r = 7;
            
        }
        else if(rank == 2){
            color1 = Color.blue;
            speed = 7; //speed pequeno (começo)
            r = 8;
        }
         else if(rank == 3){
            color1 = Color.yellow;
            speed = 9; //speed pequeno (começo)
            r = 8;
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

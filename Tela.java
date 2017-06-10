import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;


public class Tela extends JPanel implements Runnable{

    public static int width = 500;
    public static int height = 500;

    private Thread thread;
    private boolean rodando; //irá dizer se o jogo esta rodando

    private BufferedImage img;
    private Graphics2D g;

    public Tela(){
        super();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
      
    }

    public void addNotify(){  //faz com que o JPanel seja visivel
        super.addNotify();
        if(thread == null){
            thread = new thread(this);
            thread.start();
        }
    }

    public void run(){
        rodando = true;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) img.getGraphics();
        while(rodando){
            gameUpdate();
            gameRender();
            gameDraw();
        }

    }

    private void gameUpdate(){  //atualização do player dos inimigos etc


    }

    private void gameRender(){
        g.setColor(Color.yellow);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);
        g.drawString("TEST STRING",100,100);

    }
    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(img,0,0,null);
        g2.dispose();
    }


}
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
<<<<<<< HEAD
import java.awt.event.*;
=======
import java.util.ArrayList;
>>>>>>> a7b8b3500a716a3a80d43a83705987dbc3c3ae6a


public class Tela extends JPanel implements Runnable, KeyListener{

    public static int width = 500;
    public static int height = 500;

    private Thread thread;
    private boolean rodando; //irá dizer se o jogo esta rodando

    private BufferedImage img;
    private Graphics2D g;
    
    private Player player;

    public static ArrayList<Enemy> enemies;

    public Tela(){
        super();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
      
    }

    public void addNotify(){  //faz com que o JPanel seja visivel
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run(){
        rodando = true;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) img.getGraphics();
<<<<<<< HEAD
        
        player = new Player();
        
=======

        enemies = new ArrayList<Enemy>();
        for(int i=0;i<=3;i++){
            enemies.add(new Enemy(1,1));
        }

>>>>>>> a7b8b3500a716a3a80d43a83705987dbc3c3ae6a
        while(rodando){
            gameUpdate();
            gameRender();
            gameDraw();
        }

    }

	
	
    private void gameUpdate(){  //atualização do player dos inimigos etc
<<<<<<< HEAD
		player.update();
=======

        //update enemies
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).update();
        }
>>>>>>> a7b8b3500a716a3a80d43a83705987dbc3c3ae6a

    }

    private void gameRender(){    
        g.setColor(Color.yellow);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);
<<<<<<< HEAD
       // g.drawString("TEST STRING",100,100);
        player.draw(g);
=======
        //g.drawString("TEST STRING",100,100);

        //enemies
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).draw(g);
        }

>>>>>>> a7b8b3500a716a3a80d43a83705987dbc3c3ae6a
    }
    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(img,0,0,null);
        g2.dispose();
    }

	public void keyTyped (KeyEvent key) {}
	public void keyPressed (KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(true);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setRight(true);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setUp(true);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setDown(true);
			}			
			
		}
	public void keyReleased (KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(false);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setRight(false);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setUp(false);
			}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setDown(false);
			}			
		}
	
}

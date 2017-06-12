import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;

import java.awt.event.*;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Tela extends JPanel implements Runnable, KeyListener{
	private Image fundo;
    public static int width = 1066;
    public static int height = 600;

    private Thread thread;
    private boolean rodando; //irá dizer se o jogo esta rodando
	private boolean fim;

    private BufferedImage img;
    private Graphics2D g;
    
    private Player player;

    public static ArrayList<Enemy> enemies;


    public Tela(){
        super();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        ImageIcon referencia = new ImageIcon("universo.png");
		fundo = referencia.getImage();
      
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
		
        player = new Player();
        enemies = new ArrayList<Enemy>();
		for(int i = 0;i < 6;i++){
			enemies.add(new Enemy(1,1));
		}

        while(rodando){
            gameUpdate();
            gameRender();
            gameDraw();
        }

		g.setColor(new Color(153, 50, 204));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.setFont(new Font("Century Gothic", Font.BOLD, 46));
		String s = "G A M E   O V E R";
		int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, width / 2, height / 2);
		gameDraw();
	
    }	
	
    private void gameUpdate(){  //atualização do player dos inimigos etc

		//criando inimigos

		player.update();

        //update enemies
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).update();
        }
		
		if(player.isDead()) {
			rodando = false;
		}
		//colisao
		
		    int px = player.getx();
			int py = player.gety();
			int pr = player.getr();
		for(int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				double ex = e.getx();
				double ey = e.gety();
				double er = e.getr();
				
				double dx = px - ex;
				double dy = py - ey;
				double dist = Math.sqrt(dx * dx + dy * dy);
				
				if(dist < pr + er) {
					player.Life();
					
				}//fecha if
		
			}//fecha for
		}
	

    private void gameRender(){  //irá desenhar, setar cores, background etc no jogo  
        
		//desenha a tela de fundo
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);
        g.drawImage(fundo, 0, 0, null);//imagem estatica
       
	   //desenha player
	    player.draw(g);

        //desenha enemies
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).draw(g);
        }


    }
    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(img,0,0,null);
        g2.dispose();
    }

	private void createNewEnemies(){
		enemies.clear();
		Enemy e;

				for(int i = 0; i < 7;i++){
					enemies.add(new Enemy(1,1));

				}
	}

	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if(keyCode == KeyEvent.VK_UP) {
			player.setUp(true);
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			player.setDown(true);
		}
		
	}
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if(keyCode == KeyEvent.VK_UP) {
			player.setUp(false);
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			player.setDown(false);
		}
		
		
		}
	
}

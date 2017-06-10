import java.awt.*;
import java.awt.Graphics2D;

public class Player {
	
	private int x,y,r;
	private int dx,dy;
	private int speed;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	
	
		public Player (){
			x = Tela.WIDTH/2;
			y = Tela.HEIGHT/2;
			r=5; //radius
			dx=0;
			dy=0;
			speed=5;
		
	
			
			}//fecha player
	
	public void setLeft(boolean b) { left=b;}
	public void setRight(boolean b) { right=b;}
	public void setUp(boolean b) { up=b;}
	public void setDown(boolean b) { down=b;}
	
		public void update(){
			
			if(left){
				dx=-speed;
				}
			if(right){
				dx=speed;
				}	
			if(up){
				dy=-speed;
				}	
			if (down){
				dy=speed;
				}
				
			x+=dx;
			y+=dy;
			
			//checar medidas
			if(x<r) x=r;
			if(y<r) y=r;
			if (x>Tela.WIDTH-r) x = Tela.WIDTH-r;
			if (y>Tela.HEIGHT-r) y = Tela.HEIGHT-r;
			
			dx=0;
			dy=0;		
			}//fecha updated
	
		
		public void draw (Graphics2D g){
			
			g.setColor(Color.blue);
			g.fillOval(x-r, y-r, 2*r, 2*r);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.blue.darker());
			g.drawOval(x-r, y-r, 2*r, 2*r);
			g.setStroke(new BasicStroke(1));
			}
	}

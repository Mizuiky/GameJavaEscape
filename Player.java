import java.awt.*;

public class Player {
	
	private int x;
	private int y;
	private int r;
	
	private int dx;
	private int dy;
	private int speed;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private Color color1;
	private Color color2;
	
	public int jogador=0;
	private int vida;
	
	public Player() {
		
		x = Tela.width / 2;
		y = Tela.height / 2;
		r = 8;
		
		dx = 0;
		dy = 0;
		speed = 2;
		vida=1;
		
		color1 = Color.RED;
		
		
		
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	public int getr() { return r; }
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	
	public void Life() {
		vida--;
	}
	public boolean isDead() { return vida <= 0; }
	
	public void update() {
		
		if(left) {
			dx = -speed;
		}
		if(right) {
			dx = speed;
		}
		if(up) {
			dy = -speed;
		}
		if(down) {
			dy = speed;
		}
		
		x += dx;
		y += dy;
		
		if(x < r) x = r;
		if(y < r) y = r;
		if(x > Tela.width - r) x = Tela.width - r;
		if(y > Tela.height - r) y = Tela.height - r;
		
		dx = 0;
		dy = 0;
		
		
	}
	
	public void draw(Graphics2D g) {
		
	
			g.setColor(color1);
			g.fillOval(x - r, y - r, 2 * r, 2 * r);
			g.setStroke(new BasicStroke(3));
			g.setColor(color1.darker());
			g.drawOval(x - r, y - r, 2 * r, 2 * r);
			g.setStroke(new BasicStroke(1));
		}
		
		
	}
	


















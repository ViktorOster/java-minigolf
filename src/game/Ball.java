package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ball extends GameObject{
	private GameObject player;
	private Handler handler;

	private float health = 100;
	private float hitCount;
	
	
	private BufferedImage ballImage;
	
	private BufferedImage ball1;
	private BufferedImage ball2;
	private BufferedImage ball3;
	
	private int releasedDir;
	private int deadCount;
	
	Random r = new Random();

	private int animFast;
	private int animSlow;

	private int randInt;
	private float dir;
	
	public Ball(float x, float y, ID id, Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
		//this.dir = d;
		dead = false;
	
		BufferedImageLoader loader = new BufferedImageLoader();

		randInt = r.nextInt(3);
		
		hit = false;
		
		//velX = -3;
		//velY = -3;
		
		ballImage = loader.loadImage("/ball.png");
			
		ball1 = loader.loadImage("/ball1.png");
		ball2 = loader.loadImage("/ball2.png");
		ball3 = loader.loadImage("/ball3.png");
		
		//velX = 6;

	}

	public void tick() {
		
		
		velX *= 0.994;
		velY *= 0.994;

		animFast++;
		animSlow++;
		
		if(animFast >5) animFast = 0;
		if(animSlow >11) animSlow = 0;
		
		if(hit){
			hitCount++;
			if(hitCount == 2){
				health -= 100;
			}
			
		}
		
		x += velX;
		y += velY;
		
		velX = Game.clamp(velX, -8, 8);
		velY = Game.clamp(velY, -8, 8);
	
		
		//if(y >= Game.HEIGHT*2+100) handler.removeObject(this);
		
		
		collision();
		
	} 
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			
			
			if(tempObject.getId() == ID.Block){
				
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					velX = velX * -1;
					
				} 
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()+ (35);
					//velY = 0;
					velY = velY * -1;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					velX = velX * -1;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
					velY = velY * -1;
				}
				
			}
			if(tempObject.getId() == ID.Putter){
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX()+ (10);
					velX = velX * -1;
					
				}
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY()+ (10);
					velY = velY * -1;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX()-(26);
					velX = velX * -1;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					y = tempObject.getY()- (26);
					velY = velY * -1;
				}	
			}	
		}		
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-6, (int)y-6, ballImage.getWidth()+10, ballImage.getHeight()+10);
		
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x-2, (int)y+5, 4, 6);
		
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+6, (int)y-2, 4, ballImage.getHeight()/2);
		
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+14, (int)y+5, 4, 6);
		
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)x+6, (int)y+10, 4, ballImage.getHeight()/2);
		
	}
	
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		
		if(velX >1 || velY > 1 || velX <-1 || velY <-1){
			if(animFast >=0 && animFast <=1) g.drawImage(ball1, (int)x, (int)y, null);
			if(animFast >=2 && animFast <=3) g.drawImage(ball2, (int)x, (int)y, null);
			if(animFast >=4 && animFast <=5) g.drawImage(ball3, (int)x, (int)y, null);
		}
		else if (velX >0.07 || velY > 0.07 || velX <-0.07 || velY <-0.07){
			if(animSlow >=0 && animSlow <=3) g.drawImage(ball1, (int)x, (int)y, null);
			if(animSlow >=4 && animSlow <=7) g.drawImage(ball2, (int)x, (int)y, null);
			if(animSlow >=8 && animSlow <=11) g.drawImage(ball3, (int)x, (int)y, null);
		}
		else g.drawImage(ball1, (int)x, (int)y, null);
		//if(animCount >=7 && animCount <=12) g2d.drawImage(walkDown2, (int)x, (int)y, null);
		//
		//g2d.draw(getBounds());
		/*
		 
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsBottom());
		*/
		
	}

	@Override
	public Polygon getBoundsPoly() {
		// TODO Auto-generated method stub
		return null;
	}


}

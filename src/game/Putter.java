package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Putter extends GameObject{
	private GameObject player;
	private Handler handler;

	private BufferedImage putterImage;
	
	private BufferedImage putL;
	private BufferedImage putUpL;
	private BufferedImage putUp;
	private BufferedImage putUpR;
	private BufferedImage putR;
	private BufferedImage putDownR;
	private BufferedImage putDown;
	private BufferedImage putDownL;
	
	private int velTimer;
	private float xPos1;
	private float xPos2;
	private float yPos1;
	private float yPos2;
	private GameObject ball;
	
	private boolean colliding = false;
	private int collideTime = 0;
	
	Random r = new Random();

	
	public Putter(float x, float y, ID id, Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
		//this.dir = d;
		dead = false;
	
		BufferedImageLoader loader = new BufferedImageLoader();

		
		putterImage = loader.loadImage("/putter.png");
		/*
		putL = loader.loadImage("/putL.png");
		putUpL = loader.loadImage("/putUpL.png");
		putUp = loader.loadImage("/putUp.png");
		putUpR = loader.loadImage("/putUpR.png");
		putR = loader.loadImage("/putR.png");
		putDownR = loader.loadImage("/putDownR.png");
		putDown = loader.loadImage("/putDown.png");
		putDownL = loader.loadImage("/putDownL.png");
		*/
					
		//velX = 6;

		
	}

	public void tick() {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ball){
				ball = tempObject;
			}
		}
		
		
		if(colliding) collideTime++;
		else collideTime = 0;
		if(collideTime == 1) {
			HUD.moves--;
			AudioPlayer.getSound("golfHit").play(1, 23);
		}
		
		if(velTimer == 0) {
			xPos1 = x;
			yPos1 = y;
		}
		velTimer++;
		
		if(velTimer > 3) {
			xPos2 = x;
			yPos2 = y;
			//xPos2 = x;
			
			velX = (xPos2-xPos1) / 2;
			velY = (yPos2-yPos1) / 2;
			
			//System.out.println(velX + " " + velY);
			
			velTimer = 0;
		}
		
		//if(y >= Game.HEIGHT*2+100) handler.removeObject(this);
		
		collision();
		
	} 
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ball){
				
				if(getBounds().intersects(tempObject.getBounds())){
					tempObject.setVelX(velX);
					tempObject.setVelY(velY);
					colliding = true;
					
				} else colliding = false;
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()+ (35);
					//velX = 0;
					
				} 
				if(getBoundsTop().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()+ (35);
					//velY = 0;
				}	
				if(getBoundsRight().intersects(tempObject.getBounds())){
					
					x = tempObject.getX()-(19);
					//velX = 0;
					
				}	
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					
					y = tempObject.getY()- (19);
					//velY = 0;
				}	
				
			}	
		}		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x-8, (int)y-8, putterImage.getWidth(), putterImage.getHeight());
		
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x-8, (int)y-3, 4, 6);
		
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x-2, (int)y-8, 4, putterImage.getHeight()/2);
		
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+4, (int)y-3, 4, 6);
		
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)x-2, (int)y, 4, putterImage.getHeight()/2);
		
	}

	
	
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);

		
		if(!dead){	
			/*
			if(ball != null){
				if(y> ball.getY()+16) {
					if(x< ball.getX()) {
						g.drawImage(putDownL, (int)x-8, (int)y-8, null);
					} else if(x> ball.getX()+16){
						g.drawImage(putDownR, (int)x-8, (int)y-8, null);
					} else g.drawImage(putUp, (int)x-8, (int)y-8, null);
					System.out.println("under ball");
				}
				if(y< ball.getY()) {
					if(x< ball.getX()) {
						g.drawImage(putUpL, (int)x-8, (int)y-8, null);
					} else if(x> ball.getX()+16){
						g.drawImage(putUpR, (int)x-8, (int)y-8, null);
					} else g.drawImage(putDown, (int)x-8, (int)y-8, null);
					System.out.println("above ball");
				}
				if(x> ball.getX()+16 && y< ball.getY()+16 && y> ball.getY()) {
					g.drawImage(putL, (int)x-8, (int)y-8, null);
					System.out.println("right of ball");
				}
				if(x< ball.getX() && y< ball.getY()+16 && y> ball.getY()) {
					g.drawImage(putR, (int)x-8, (int)y-8, null);
					System.out.println("left of ball");
				}
			}
			*/
			
			g.drawImage(putterImage, (int)x-8, (int)y-8, null);
			//if(animCount >=0 && animCount <=6) g2d.drawImage(walkDown1, (int)x, (int)y, null);
			//if(animCount >=7 && animCount <=12) g2d.drawImage(walkDown2, (int)x, (int)y, null);
			
		} else{
		
			
			
			
		}
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

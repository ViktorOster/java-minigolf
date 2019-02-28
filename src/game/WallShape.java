package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class WallShape extends GameObject{
	
	private Handler handler;
	
	private int sizeX;
	private int sizeY;
	private GameObject player;
	private Ball ball;
	
	private BufferedImage block;

	private String type;
	int [] xPoints = new int[3];
	int [] yPoints = new int[3];
	
	public WallShape(float x, float y, float w, float h, ID id, String typ, Handler handler) { //1= L, 2 = down, 3 = R, 4 = up, 5 = L/DOWN, 6 = R/DOWN 7= UP/L, 8= UP/R
		super(x, y, id);
		this.handler = handler;
		this.width = w;
		this.height = h;
		this.type = typ;
		
		this.type = typ;
		
		if(type.equals("upL")){
			xPoints[0] = (int)x;
			xPoints[1] = (int)x;
			xPoints[2] = (int)x+32;
			yPoints[0] = (int)y+32;
			yPoints[1] = (int)y;
			yPoints[2] = (int)y;
		}
		else if(type.equals("upR")){
			xPoints[0] = (int)x;
			xPoints[1] = (int)x+32;
			xPoints[2] = (int)x+32;
			yPoints[0] = (int)y;
			yPoints[1] = (int)y;
			yPoints[2] = (int)y+32;
		}
		else if(type.equals("downL")){
			xPoints[0] = (int)x;
			xPoints[1] = (int)x;
			xPoints[2] = (int)x+32;
			yPoints[0] = (int)y;
			yPoints[1] = (int)y+32;
			yPoints[2] = (int)y+32;
		}
		else if(type.equals("downR")){
			xPoints[0] = (int)x;
			xPoints[1] = (int)x+32;
			xPoints[2] = (int)x+32;
			yPoints[0] = (int)y+32;
			yPoints[1] = (int)y+32;
			yPoints[2] = (int)y;
		}

	}

	public void tick() {
		
		
		collision();
		
		
	}
	private void collision(){

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ball){
					
					if(type.equals("upL")){
						if(getBoundsPoly().intersects(tempObject.getBoundsLeft())){
							
							//x = tempObject.getX()+ (35);
							//velX = 0;
							tempObject.velY = tempObject.velX * -1;
							
						} 
						if(getBoundsPoly().intersects(tempObject.getBoundsTop())){
							
							//y = tempObject.getY()+ (35);
							//velY = 0;
							
							
							tempObject.velX = tempObject.velY *-1;
							tempObject.velY = 0;
							
						}	
						if(getBoundsPoly().intersects(tempObject.getBoundsRight())){
							
							//x = tempObject.getX()-(19);
							//velX = 0;
							tempObject.velY = tempObject.velX * -1;
							
						}	
						if(getBoundsPoly().intersects(tempObject.getBoundsBottom())){
							
							//y = tempObject.getY()- (19);
							//velY = 0;
							tempObject.velX = tempObject.velY * -1;
						}
					}
				
					
				
					
				if(getBoundsPoly().intersects(tempObject.getBounds())){
					//tempObject.setVelX(velX);
					//tempObject.setVelY(velY);
					if(!AudioPlayer.getSound("hitWall").playing())AudioPlayer.getSound("hitWall").play(1, 23);
					
				} 
				
				
				
			}	
		}		
	}
	
	@Override
	public void drawShadow(Graphics g){
	
		
	}
	
	public Polygon getBoundsPoly() {

		return new Polygon(xPoints, yPoints, 3);		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);

		//g.setColor(Color.green);

		//g.drawImage(block, (int)x, (int)y, null);

		g2d.draw(getBoundsPoly());
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	

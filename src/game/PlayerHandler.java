package game;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import game.Game.STATE;

public class PlayerHandler extends MouseAdapter implements MouseListener {
	private Game game;
	private Handler handler;
	private int mx;
	private int my;
	private GameObject putter;
	
	
	private BufferedImage putterImage;

	public PlayerHandler(Game game, Handler handler){
		
		this.game = game;
		this.handler = handler;
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
	
		putterImage = loader.loadImage("/putter.png");
		putter = new Putter((int)mx, (int)my, ID.Putter, handler);
		handler.addObject(putter);


	}
	
	public void mousePressed(MouseEvent e){
		//handler.addObject(new Ball(mx, my, ID.Ball, 4, handler));
		
		mx = e.getX();
		my = e.getY();
	
	}
	@Override
	public void mouseMoved(MouseEvent e){
		mx = e.getX();
		my = e.getY();
		
		
	}
	
	
	public void mouseReleased(MouseEvent e){
		//System.out.println("released");
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick(){
		putter.setX(mx);
		putter.setY(my);
	}
	
	public void render(Graphics g){
		
		
		//g.drawImage(putterImage, (int)mx-(putterImage.getWidth()/2), (int)my-(putterImage.getHeight()/2), null);
		
		
	}

	
}

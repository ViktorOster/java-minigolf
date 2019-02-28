package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HUD {
	
	private BufferedImage HUD;
	public void loadHudImages(){
		BufferedImageLoader loader = new BufferedImageLoader();
		
		//HUD = loader.loadImage("/HUD.png");
		
		
	}
	public static ArrayList<Integer> leaderBoard = new ArrayList<Integer>();
	
	public static float TIME = 60;
	
	private float greenValue = 255;
	static int timerLvl1 = 60;
	
	static float score = 0;
	static float points = 0;
	private float level = 1;
	static int moves = 10;
	
	public void tick(){
		if(TIME >0){
			TIME = (timerLvl1 - (int)score/60);
		} 
		
		//if(Player.immmortal) HEALTH = 100;
		TIME = Game.clamp(TIME, 50, 60);
		//greenValue = Game.clamp(greenValue,  0 , 255);		
		//greenValue = HEALTH*2;
		
		score ++;
	}
	public static void clearScore(){
		score = 0;
		
	}
		
	public void render(Graphics g){
		//g.drawImage(HUD, 0, 0, null);
		Font fnt = new Font("monotype", 1 , 20);
		g.setFont(fnt);
		
		g.setColor(Color.white);
		//g.drawString("" + (int)points, 400, 35);
		g.drawString("moves left: " + moves, 150, 25);
		g.drawString("time left: " + (int)TIME, 350, 25);
		
		Font fnt2 = new Font("monotype", 1 , 14);
		g.setFont(fnt2);
		//g.drawString("Hall of \n Fame: ", 650, 25);
		
		//for(int i : leaderBoard){
		//	g.drawString(leaderBoard.indexOf(i) + ": " + leaderBoard.get(i), 650, 40+i*30);
		//}
		
	}
	
	public void score(float score){
		this.score = score;
	}
	public float getScore(){
		return score;
	}
	public float getLevel(){
		return level;
	}
	public void setLevel(float level){
		this.level = level;
	}

}

package game;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import game.Game.STATE;

public class Handler {

	private int deadTimer = 0;
	static boolean finishedLvl = false;
	private GameObject ball;
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Ball){
				ball = tempObject;
				
				
			}	
		}	
		
		while(finishedLvl) {
			String input = JOptionPane.showInputDialog("You finished the level in " + (60 - HUD.TIME) + " seconds and " 
		+ (10 - HUD.moves) + " move(s). \n "
					+ "Play again? (Y/N)");
			if(input.equals("Y")){
				HUD.leaderBoard.add(10 - HUD.moves);
				clearEverything();
				Game.gameState = STATE.Game;
			} else System.exit(0);
		}
		
		if(HUD.TIME <= 0){
			
			while(HUD.TIME <= 0){
				String input = JOptionPane.showInputDialog("Time's up, play again? (Y/N)");
				if(input.equals("Y")){
					removeObject(ball);
					clearEverything();
					Game.gameState = STATE.Game;
				} else System.exit(0);
				
			}
			
		} 
		if(HUD.moves <= 0){
			
			while(HUD.moves <= 0){
				String input = JOptionPane.showInputDialog("No moves left, play again? (Y/N)");
				if(input.equals("Y")){
					removeObject(ball);
					clearEverything();
					Game.gameState = STATE.Game;
				} else System.exit(0);
				
			}
			
		} 
		
		for(int i = 0; i < object.size(); i++){
			
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}			
	}
	
	public void render(Graphics g){

		for(int i = 0; i < object.size(); i++){
			
			
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}	
		
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public void clearEnemies(){
		object.clear();
	}
	
	public void clearEverything(){
		//object.clear();
		//Menu menu = new Menu(game, handler);
		
		HUD.TIME = 60;
		HUD.points = 0;
		HUD.moves = 10;
		Spawn.clearSpawn();
		HUD.clearScore();
		finishedLvl = false;
		Game.gameState = STATE.Game;
		
		addObject(new Ball(100, 100, ID.Ball, this));
		//handler.addObject(new Player(70, 150, ID.Player, handler));
		
		//AudioPlayer.getMusic("music").stop();
		//AudioPlayer.getMusic("game_over").loop();
	}
	
}

package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load(){
		try {
			
			//soundMap.put("shoot", new Sound("res/smgShot.ogg"));
			soundMap.put("score", new Sound("res/shootingRangeHit.ogg"));
			soundMap.put("golfCup", new Sound("res/golfCup.ogg"));
			soundMap.put("golfHit", new Sound("res/golfHit.ogg"));
			soundMap.put("hitWall", new Sound("res/hitWall.ogg"));
			//musicMap.put("music", new Music("res/AliensKilledStage1.ogg"));
			//musicMap.put("game_over", new Music("res/game_over.ogg"));
			//musicMap.put("menu_music", new Music("res/haunted theme.ogg"));
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
	
}

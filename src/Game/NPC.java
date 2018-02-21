package Game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NPC {
	int xpos;
	int ypos;
	int ID;
	String name;
	int direction=2;
	int vision = 8;
	boolean defeated = false;
	boolean talking = false;
	ArrayList<String> preBattleSpeech = new ArrayList<String>();
	ArrayList<String> postBattleSpeech = new ArrayList<String>();
	ArrayList<Point> path = new ArrayList<Point>();

	final int NORTH = 0;
	final int EAST = 1;
	final int SOUTH = 2;
	final int WEST = 3;

	int AI;
	final int FRIENDLY = 0;
	final int FRIENDLY_PATROL = 1;
	final int AGGRESSIVE = 2;
	final int AGGRESSIVE_PATROL = 3;
	
	BufferedImage [][]artwork;
	
	public NPC(int x, int y, int id, int ai){
		xpos = x;
		ypos = y;
		ID = id;
		AI = ai;
		if(id==0){
			this.name="Priest";
			artwork = GamePanel.priest;
		}
	}
	public void update(){
		if(canSeePlayer()&&(AI==AGGRESSIVE||AI==AGGRESSIVE_PATROL)&&!defeated){
			if(talking==false&&defeated==false){
				talking = true;
			}
		}
	}
	public void followPath(){

	}
	public boolean canSeePlayer(){
		if(direction==NORTH){
			for(int i = 0; i<AppletUI.client.players.size();i++){
				if(Math.abs(this.ypos-AppletUI.client.players.get(i).ypos)>=vision){
					return true;
				}
			}
		}
		else if(direction==SOUTH){
			for(int i = 0; i<AppletUI.client.players.size();i++){
				if(Math.abs(this.ypos-AppletUI.client.players.get(i).ypos)>=vision){
					return true;
				}
			}
		}
		else if(direction==EAST){
			for(int i = 0; i<AppletUI.client.players.size();i++){
				if(Math.abs(this.xpos-AppletUI.client.players.get(i).xpos)>=vision){
					return true;
				}
			}
		}
		else if(direction==WEST){
			for(int i = 0; i<AppletUI.client.players.size();i++){
				if(Math.abs(this.xpos-AppletUI.client.players.get(i).xpos)>=vision){
					return true;
				}
			}
		}
		return false;
	}
	public void Draw(Graphics g){
		if(GamePanel.spiritWorldOn==false){
			g.drawImage(artwork[direction][0],944,514,32,32,null);
		}
		else{
			g.drawImage(artwork[direction][0],944,514,32,32,null);
		}
	}
}


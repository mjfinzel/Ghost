package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.io.IOException;

public class Button {
	int xpos;
	int ypos;
	int ID=-1;
	int buttonWidth = 190;
	int buttonHeight=40;
	String name;
	public Button(int x, int y, int id){
		xpos = x;
		ypos = y;
		ID = id;
		if(id==0){
			name = "Attack";
		}
		else if(id == 1){
			name = "Run";
		}
		else if(id == 2){
			name = "Item";
		}
		else if(id == 3){
			name = "Possess";
		}
		else if(id == 4){
			name = "Singleplayer";
		}
		else if(id == 5){
			name = "Multiplayer";
		}

	}
	public boolean mouseOnButton(){
		if(MouseInfo.getPointerInfo().getLocation().x>=xpos&&MouseInfo.getPointerInfo().getLocation().x<=xpos+buttonWidth){
			if(MouseInfo.getPointerInfo().getLocation().y>=ypos&&MouseInfo.getPointerInfo().getLocation().y<=ypos+buttonHeight){
				return true;
			}
		}
		return false;
	}
	public void pushButton(){
		if(GamePanel.messages.size()==0){//no messages
			if(this.ID==0&&GamePanel.player.battling){//attack
				GamePanel.player.forms.get(0).attacks.get(0).use(GamePanel.player.forms.get(0), GamePanel.player.currentTarget);
			}
			else if(this.ID==1&&GamePanel.player.battling){//run
				if(GamePanel.player.forms.size()==0){
					GamePanel.player.battling=false;
					GamePanel.player.currentTarget=null;
					GamePanel.messages.add("You escaped!");
				}
				else if(GamePanel.randomNumber(1, 2)==1){
					GamePanel.player.battling=false;
					GamePanel.player.currentTarget=null;
					GamePanel.messages.add("You escaped!");
				}
				else if(GamePanel.player.forms.get(GamePanel.player.currentForm).level>GamePanel.player.currentTarget.level){
					GamePanel.player.battling=false;
					GamePanel.player.currentTarget=null;
					GamePanel.messages.add("You escaped!");
				}
				else{
					GamePanel.messages.add("Failed to escape!");
				}
			}
			else if(this.ID==2){

			}
			else if(this.ID==3&&GamePanel.player.battling){//possess

				GamePanel.player.possessCurrentTarget();

			}
			else if(this.ID==4){//singleplayer
				AppletUI.GAMEMODE=AppletUI.SINGLEPLAYER;
				AppletUI.GAMESTATE=AppletUI.NEWGAME;
				try {
					AppletUI.client = new Client();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				GamePanel.buttons.clear();
			}
			else if(this.ID==5){//multiplayer
				AppletUI.GAMEMODE=AppletUI.MULTIPLAYER;
				AppletUI.GAMESTATE=AppletUI.setIP;
				GamePanel.buttons.clear();
			}
		}
	}
	public void Draw(Graphics g){
		if(GamePanel.messages.size()==0){//no messages
			if(mouseOnButton()){
				g.drawImage(GamePanel.apri[0][1],xpos,ypos,buttonWidth,buttonHeight,null);
			}
			else{
				g.drawImage(GamePanel.apri[0][0],xpos,ypos,buttonWidth,buttonHeight,null);
			}
			Font font = new Font("Iwona Heavy",Font.BOLD,18);
			g.setFont(font);
			g.setColor(Color.WHITE);
			FontMetrics metrics = g.getFontMetrics();
			int x = xpos+(buttonWidth/2)-(metrics.stringWidth(name)/2);
			g.drawString(name, x, ypos+25);
		}
	}
}

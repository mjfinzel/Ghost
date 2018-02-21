package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	protected Socket socket;
	int currentPlayer = 0;
	PrintWriter out = null;
	BufferedReader in = null;
	public ServerThread(Socket clientSocket) {
		this.socket = clientSocket;
	}
	public void run(){
		
		try {
			out = new PrintWriter(socket.getOutputStream(), true); 
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		while(true)  {

			String command;
			try {
				command = in.readLine();


				if(command!=null){
					if(command.contains("checkNorth")){
						String temp = command.substring(12, command.length());
						setCurrentPlayer(temp);
						out.println(canMoveNorth());
					}
					if(command.contains("checkSouth")){
						String temp = command.substring(12, command.length());
						setCurrentPlayer(temp);
						out.println(canMoveSouth());
					}
					if(command.contains("checkEast")){
						String temp = command.substring(12, command.length());
						setCurrentPlayer(temp);
						out.println(canMoveEast());
					}
					if(command.contains("checkWest")){
						String temp = command.substring(12, command.length());
						setCurrentPlayer(temp);
						out.println(canMoveWest());
					}
					if(command.contains("moveUp")){
						String temp = command.substring(7, command.length());
						setCurrentPlayer(temp);
						moveNorth();
						//	out.println("setXPos: "+players.get(currentPlayer).xpos);
						//out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						for(int i = 0; i< Server.threads.size();i++){
							Server.threads.get(i).out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						}
						System.out.println("server moved the player up");
					}
					if(command.contains("moveDown")){
						String temp = command.substring(9, command.length());
						setCurrentPlayer(temp);
						moveSouth();
						//	out.println("setXPos: "+players.get(currentPlayer).xpos);
						//out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						for(int i = 0; i< Server.threads.size();i++){
							Server.threads.get(i).out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						}
						System.out.println("server moved the player down");
					}
					if(command.contains("moveLeft")){
						String temp = command.substring(9, command.length());
						setCurrentPlayer(temp);
						moveWest();
						//	out.println("setXPos: "+players.get(currentPlayer).xpos);
						//out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						for(int i = 0; i< Server.threads.size();i++){
							Server.threads.get(i).out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						}
						System.out.println("server moved the player left");
					}
					if(command.contains("moveRight")){
						String temp = command.substring(10, command.length());
						setCurrentPlayer(temp);
						moveEast();
						//	out.println("setXPos: "+players.get(currentPlayer).xpos);
						//out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						for(int i = 0; i< Server.threads.size();i++){
							Server.threads.get(i).out.println("setPos("+temp+"):" +Server.players.get(currentPlayer).xpos+","+Server.players.get(currentPlayer).ypos);
						}
						System.out.println("server moved the player right");
					}
					if(command.contains("addPlayer: ")){
						String temp = command.substring(11, command.length());
						Server.addPlayer(temp);
						out.println("added player");
						//System.out.println("added a player!");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	public void moveNorth(){
		//determine what tile the player is currently on and what tile is above them
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile northernTile = null;
		if(Server.players.get(currentPlayer).ypos-1>=0){
			northernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos-1];
		}
		if(northernTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[0]==1&&northernTile.enterCollisionType[0]==1||GamePanel.editMode){
				//walkAnim = new Animation(GamePanel.playersprites, walkAnimFrames, walkingSpeed, 32, 3, 944,514, false, 0, 0);
				//if(GamePanel.editMode)
				Server.players.get(currentPlayer).ypos-=1;
				Server.players.get(currentPlayer).direction = 3;

			}
			else if(current.exitCollisionType[0]==1&&northernTile.enterCollisionType[0]==2){
				//ypos-=1;
				northernTile.door.enterDoor();
				Server.players.get(currentPlayer).xpos=northernTile.door.roomBPos.x;
				Server.players.get(currentPlayer).ypos=northernTile.door.roomBPos.y;
			}
		}
	}
	public void moveSouth(){
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile southernTile = null;
		if(Server.players.get(currentPlayer).ypos+1<GamePanel.rooms.get(GamePanel.currentRoom).height){
			southernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos+1];
		}
		if(southernTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[2]==1&&southernTile.enterCollisionType[2]==1||GamePanel.editMode){
				//players.get(currentPlayer).walkAnim = new Animation(GamePanel.playersprites, walkAnimFrames, walkingSpeed, 32, 0, 944,514, false, 0, 0);
				//if(GamePanel.editMode)
				Server.players.get(currentPlayer).ypos+=1;
				Server.players.get(currentPlayer).direction = 0;
			}
			else if(current.exitCollisionType[0]==1&&southernTile.enterCollisionType[2]==2){
				System.out.println("Entering door");
				//ypos-=1;
				southernTile.door.enterDoor();
				Server.players.get(currentPlayer).xpos=southernTile.door.roomBPos.x;
				Server.players.get(currentPlayer).ypos=southernTile.door.roomBPos.y;
			}
		}
		if(current.exitCollisionType[2]==2){
			System.out.println("Entering door");
			//ypos-=1;
			current.door.enterDoor();
			Server.players.get(currentPlayer).xpos=current.door.roomBPos.x;
			Server.players.get(currentPlayer).ypos=current.door.roomBPos.y;
		}
	}
	public void moveEast(){
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile EasternTile = null;
		if(Server.players.get(currentPlayer).xpos+1<GamePanel.rooms.get(GamePanel.currentRoom).width){
			EasternTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos+1][Server.players.get(currentPlayer).ypos];
		}
		if(EasternTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[1]==1&&EasternTile.enterCollisionType[1]==1||GamePanel.editMode){
				//walkAnim = new Animation(GamePanel.playersprites, walkAnimFrames, walkingSpeed, 32, 1, 944,514, false, 0, 0);

				Server.players.get(currentPlayer).xpos+=1;
				Server.players.get(currentPlayer).direction = 1;

			}
		}
	}
	public void moveWest(){
		//determine what tile the player is currently on and what tile is above them
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile westernTile = null;
		if(Server.players.get(currentPlayer).xpos-1>=0){
			westernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos-1][Server.players.get(currentPlayer).ypos];
		}
		if(westernTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[3]==1&&westernTile.enterCollisionType[3]==1||GamePanel.editMode){
				//walkAnim = new Animation(GamePanel.playersprites, walkAnimFrames, walkingSpeed, 32, 2, 944, 514, false, 0, 0);

				Server.players.get(currentPlayer).xpos-=1;
				Server.players.get(currentPlayer).direction = 2;

			}
		}
	}
	public boolean canMoveNorth(){
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile northernTile = null;
		if(Server.players.get(currentPlayer).ypos-1>=0){
			northernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos-1];
		}
		if(northernTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[0]==1&&(northernTile.enterCollisionType[0]==1||northernTile.enterCollisionType[0]==2)||GamePanel.editMode){
				return true;
			}
		}
		return false;
	}
	public boolean canMoveSouth(){
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile southernTile = null;
		if(Server.players.get(currentPlayer).ypos+1<GamePanel.rooms.get(GamePanel.currentRoom).height){
			southernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos+1];
		}
		if(southernTile!=null&&!AppletUI.client.players.get(0).battling){
			if((current.exitCollisionType[2]==1||current.exitCollisionType[2]==2)&&(southernTile.enterCollisionType[2]==1||southernTile.enterCollisionType[2]==1)||GamePanel.editMode){
				return true;
			}
		}
		if(current.exitCollisionType[2]==2||southernTile.enterCollisionType[2]==2){
			return true;
		}
		return false;
	}
	public boolean canMoveEast(){
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile EasternTile = null;
		if(Server.players.get(currentPlayer).xpos+1<GamePanel.rooms.get(GamePanel.currentRoom).width){
			EasternTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos+1][Server.players.get(currentPlayer).ypos];
		}
		if(EasternTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[1]==1&&EasternTile.enterCollisionType[1]==1||GamePanel.editMode){
				return true;

			}
		}
		return false;
	}
	public boolean canMoveWest(){
		//determine what tile the player is currently on and what tile is above them
		Tile current = GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos][Server.players.get(currentPlayer).ypos];
		Tile westernTile = null;
		if(Server.players.get(currentPlayer).xpos-1>=0){
			westernTile= GamePanel.rooms.get(GamePanel.currentRoom).tiles[Server.players.get(currentPlayer).xpos-1][Server.players.get(currentPlayer).ypos];
		}
		if(westernTile!=null&&!AppletUI.client.players.get(0).battling){
			if(current.exitCollisionType[3]==1&&westernTile.enterCollisionType[3]==1||GamePanel.editMode){
				return true;

			}
		}
		return false;
	}
	public Player getPlayer(String name){
		for(int i = 0;i<Server.players.size();i++){
			if(Server.players.get(i).name.equals(name)){
				return Server.players.get(i);
			}
		}
		Server.players.add(new Player(50,50,name));
		return Server.players.get(Server.players.size()-1);
	}
	public void setCurrentPlayer(String name){
		for(int i = 0; i<Server.players.size();i++){
			if(Server.players.get(i).name.equals(name)){
				currentPlayer=i;
			}
		}
	}
}

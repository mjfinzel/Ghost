package Game;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Controller extends JPanel implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
	private JPanel gamePanel;
	public static Point cursorPos = new Point(0,0);
	public static Point currentTilePos = new Point(0,0);
	private static boolean[] keyboardState = new boolean[525];
	public static int updateSpeed = 125;
	static long lastUpdateTime = System.currentTimeMillis();

	public Controller(){
		this.setDoubleBuffered(true);

	}
	public static boolean keyboardKeyState(int key)
	{
		return keyboardState[key];
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(GamePanel.confirmationBoxes.size()>0){
			GamePanel.confirmationBoxes.get(GamePanel.confirmationBoxes.size()-1).pressYes();
		}
		if(GamePanel.confirmationBoxes.size()>0){
			GamePanel.confirmationBoxes.get(GamePanel.confirmationBoxes.size()-1).pressNo();
		}
		if(GamePanel.messages.size()>0){
			String msg = GamePanel.messages.get(GamePanel.messages.size()-1);
			GamePanel.messages.remove(GamePanel.messages.size()-1);
			if(msg.equals("Level up!")){
				GamePanel.showLevelUpText=true;
			}
			if(!msg.equals("Level up!")&&GamePanel.showLevelUpText){
				GamePanel.showLevelUpText=false;
			}
		}
		else{
			for(int i = 0; i<GamePanel.buttons.size();i++){
				if(GamePanel.buttons.get(i).mouseOnButton()){
					GamePanel.buttons.get(i).pushButton();
					break;
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
		if(AppletUI.GAMESTATE==AppletUI.PLAYING){
			if(GamePanel.editMode){
				if (keyboardKeyState(KeyEvent.VK_UP)){
					GamePanel.player.moveNorth();
					if(cursorPos.y>0){
						//cursorPos.y--;
						//GamePanel.player.direction = 3;

					}
				}
				if (keyboardKeyState(KeyEvent.VK_DOWN)){
					GamePanel.player.moveSouth();
					if(cursorPos.y<GamePanel.rooms.get(GamePanel.currentRoom).height-1){
						//cursorPos.y++;
						//GamePanel.player.direction = 0;

					}
				}
				if (keyboardKeyState(KeyEvent.VK_LEFT)){
					GamePanel.player.moveWest();
					if(cursorPos.x>0){
						//cursorPos.x--;
						//GamePanel.player.direction = 2;

					}
				}
				if (keyboardKeyState(KeyEvent.VK_RIGHT)){
					GamePanel.player.moveEast();
					if(cursorPos.x<GamePanel.rooms.get(GamePanel.currentRoom).width-1){
						//cursorPos.x++;
						//GamePanel.player.direction = 1;

					}
				}
				if (keyboardKeyState(KeyEvent.VK_W)){
					if(currentTilePos.y>0){
						currentTilePos.y--;
						GamePanel.tooltip.moveCursorUp();
					}
				}
				if (keyboardKeyState(KeyEvent.VK_S)){
					if(currentTilePos.y<44){
						currentTilePos.y++;
						GamePanel.tooltip.moveCursorDown();
					}
				}

				if (keyboardKeyState(KeyEvent.VK_A)){
					if(currentTilePos.x>0){
						currentTilePos.x--;
						GamePanel.tooltip.moveCursorLeft();
					}
				}
				if (keyboardKeyState(KeyEvent.VK_D)){
					if(currentTilePos.x<10){
						currentTilePos.x++;
						GamePanel.tooltip.moveCursorRight();
					}
				}
			}
			if (keyboardKeyState(KeyEvent.VK_G)){
				if(GamePanel.editMode==true){
					GamePanel.editMode=false;
					//GamePanel.player.xpos=cursorPos.x;
					//GamePanel.player.ypos=cursorPos.y;
				}
				else{
					GamePanel.editMode=true;
					//cursorPos.x=GamePanel.player.xpos;
					//cursorPos.y=GamePanel.player.ypos;
				}
			}

			if (e.getKeyCode()==KeyEvent.VK_F){
				GamePanel.player.showForms();
			}
			if (e.getKeyCode()==KeyEvent.VK_1){
				GamePanel.confirmationBoxes.add(new ConfirmationBox(800,600,0));
			}
			if (e.getKeyCode()==KeyEvent.VK_2){
				loadLevel(GamePanel.rooms.get(GamePanel.currentRoom).name);
			}
			if (keyboardKeyState(KeyEvent.VK_3)){
				if(GamePanel.spiritWorldOn){
					GamePanel.spiritWorldOn=false;
					for(int i = 0; i<GamePanel.clouds.size();i++){
						GamePanel.clouds.get(i).growthRate=GamePanel.randomNumber(-2, -4);
					}
				}
				else{
					GamePanel.spiritWorldOn=true;
					for(int i = 0; i<20; i++){
						GamePanel.clouds.add(new FogCloud(GamePanel.randomNumber(0, 2420),GamePanel.randomNumber(0, 1080)));
					}
				}
			}
			if (keyboardKeyState(KeyEvent.VK_4)){
				if(GamePanel.tooltip.drawTiles){
					GamePanel.tooltip.drawTiles=false;
				}
				else{
					GamePanel.tooltip.drawTiles=true;
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_P){
				if(GamePanel.player.forms.get(0).talents.visible==false){
					GamePanel.player.forms.get(0).talents.visible=true;
				}
				else{
					GamePanel.player.forms.get(0).talents.visible=false;
				}
			}
			if (keyboardKeyState(KeyEvent.VK_E)){
				if(GamePanel.tooltip.drawTiles){
					GamePanel.rooms.get(GamePanel.currentRoom).tiles[GamePanel.player.xpos][GamePanel.player.ypos]= new Tile(currentTilePos.x,currentTilePos.y,GamePanel.player.xpos*32,GamePanel.player.ypos*32);
				}
				else{
					int id = 0;
					GamePanel.rooms.get(GamePanel.currentRoom).npcs.add(new NPC(GamePanel.player.xpos,GamePanel.player.ypos,id,0));
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
				if(GamePanel.player.currentTarget!=null)
					GamePanel.player.currentTarget.currentHealth=0;
				System.out.println("pressed escape");
			}
			GamePanel.cursor.xpos=944;
			GamePanel.cursor.ypos=524;
			//		GamePanel.player.xpos = GamePanel.cursor.xpos;
			//		GamePanel.player.ypos = GamePanel.cursor.ypos;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keyboardState[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (keyboardKeyState(KeyEvent.VK_6)){
			ColorGene randWildGene = new ColorGene();
			randWildGene.setColor(new Color(255,195,0));
			ColorGene result = randWildGene.generateOffspringColor(GamePanel.player.currentTarget.colorGene);
			GamePanel.player.currentTarget.colorGene=result;
			GamePanel.player.currentTarget.specialColor=result.getColor();
			GamePanel.player.currentTarget.Sprites=Images.cut("/Creatures/ForestGrub.png", 100, 100);
			GamePanel.player.currentTarget.colorSprite(GamePanel.player.currentTarget.Sprites);
		}
		if (e.getKeyCode()==KeyEvent.VK_C){
			if(GamePanel.messages.size()>0){
				GamePanel.messages.remove(GamePanel.messages.size()-1);
			}
		}
		if(AppletUI.GAMESTATE==AppletUI.NEWGAME||AppletUI.GAMESTATE==AppletUI.setIP){
			if(!keyboardKeyState(KeyEvent.VK_BACK_SPACE)&&!keyboardKeyState(KeyEvent.VK_ENTER)){
				if(AppletUI.GAMESTATE==AppletUI.NEWGAME){
					GamePanel.name = GamePanel.name+e.getKeyChar();
				}
				else{
					GamePanel.ip = GamePanel.ip+e.getKeyChar();
				}
			}
			else if(keyboardKeyState(KeyEvent.VK_ENTER)){
				if(AppletUI.GAMESTATE==AppletUI.NEWGAME){
					//add the new player to the client
					//AppletUI.client.addPlayer(GamePanel.name);
					AppletUI.GAMESTATE=AppletUI.PLAYING;
				}
				else if(AppletUI.GAMESTATE==AppletUI.setIP){
					if(GamePanel.ip=="LOCALHOST"){
						AppletUI.serverThread.start();
					}
					try {
						AppletUI.client = new Client();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					AppletUI.GAMESTATE=AppletUI.NEWGAME;

				}

			}
			else{
				if(AppletUI.GAMESTATE==AppletUI.NEWGAME){
					if(GamePanel.name.length()>0)
						GamePanel.name = GamePanel.name.substring(0, GamePanel.name.length()-1);
				}
				else if(AppletUI.GAMESTATE==AppletUI.setIP){
					if(GamePanel.ip.length()>0)
						GamePanel.ip = GamePanel.ip.substring(0, GamePanel.ip.length()-1);
				}
			}
		}
	}
	public void setGamePanel(JPanel panelRef) {
		gamePanel = panelRef;
		gamePanel.addKeyListener(this);
		gamePanel.addMouseListener(this);
		gamePanel.addMouseMotionListener(this);
		gamePanel.addMouseWheelListener(this);
	}
	public static void saveLevel(String name){
		String folderName = System.getenv("APPDATA")+File.separator+"Ghost_Saves";
		Path saveDirectory = Paths.get(folderName);
		//create an empty folder to store save files in if none exist
		if(Files.notExists(saveDirectory)){
			String path = System.getenv("APPDATA")+File.separator+"Ghost_Saves";
			//(use relative path for Unix systems)
			File dir = new File(path);
			//(works for both Windows and Linux)
			dir.mkdirs();
		}



		String path = System.getenv("APPDATA")+File.separator+"Ghost_Saves"+File.separator+name;
		//(use relative path for Unix systems)
		File dir = new File(path);
		//(works for both Windows and Linux)
		dir.mkdirs(); 

		//create a text file inside the folder to store the level's map
		File lvl = new File((dir.getAbsolutePath())+"//"+name+"_map.txt");

		Writer writer =null;
		try{
			//save the map data for the level
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lvl), "utf-8"));
			for(int i = 0; i<GamePanel.rooms.get(GamePanel.currentRoom).width;i++){
				for(int j = 0;j<GamePanel.rooms.get(GamePanel.currentRoom).height;j++){
					writer.write("("+GamePanel.rooms.get(GamePanel.currentRoom).tiles[i][j].xpos+","+GamePanel.rooms.get(GamePanel.currentRoom).tiles[i][j].ypos+","+GamePanel.rooms.get(GamePanel.currentRoom).tiles[i][j].tileX+","+GamePanel.rooms.get(GamePanel.currentRoom).tiles[i][j].tileY+")");
				}
				writer.write("\n");
			}
			try{writer.close();}catch(Exception ex){}
			//confirm to the player that the level was saved
			System.out.println("Saved level as: "+name+" to filepath: "+lvl.getAbsolutePath());
		}
		catch(Exception ex){
			System.out.println("Failed to write to (or create) game map file: "+ name);
			ex.printStackTrace();
		}
		finally{
			try{writer.close();}catch(Exception ex){}
		}

	}
	public static void checkKeyPositions(){

		if(!GamePanel.editMode){
			if (keyboardKeyState(KeyEvent.VK_B)){
				if(GamePanel.currentBattle==null){
					
				}
			}
			if (keyboardKeyState(KeyEvent.VK_UP)){
				if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
					//AppletUI.client.canMoveUp();
				}
				else{
					GamePanel.player.moveNorth();
				}
				if(cursorPos.y>0){
					//cursorPos.y--;
					//GamePanel.player.direction = 3;

				}
			}
			if (keyboardKeyState(KeyEvent.VK_DOWN)){
				if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
					//AppletUI.client.canMoveDown();
				}
				else{
					GamePanel.player.moveSouth();
				}
				if(cursorPos.y<GamePanel.rooms.get(GamePanel.currentRoom).height-1){
					//cursorPos.y++;
					//GamePanel.player.direction = 0;

				}
			}
			if (keyboardKeyState(KeyEvent.VK_LEFT)){
				if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
					//AppletUI.client.canMoveLeft();
				}
				else{
					GamePanel.player.moveWest();
				}
				if(cursorPos.x>0){
					//cursorPos.x--;
					//GamePanel.player.direction = 2;

				}
			}
			if (keyboardKeyState(KeyEvent.VK_RIGHT)){
				if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
					//AppletUI.client.canMoveRight();
				}
				else{
					GamePanel.player.moveEast();
				}
				if(cursorPos.x<GamePanel.rooms.get(GamePanel.currentRoom).width-1){
					//cursorPos.x++;
					//GamePanel.player.direction = 1;

				}
			}
		}
	}
	public static void loadLevel(String name){
		String map_path = System.getenv("APPDATA")+File.separator+"Ghost_Saves"+File.separator+name+File.separator+name+"_map.txt";

		Path mapPath = Paths.get(map_path);

		InputStream map = null;

		//if the file paths don't exist
		if(Files.notExists(mapPath)){
			System.out.println("files didn't exist, creating files");
			map = Controller.class.getResourceAsStream(("/"+name+"/"+name+"_map.txt")); 

		}
		else{
			//    InputStream in = new FileInputStream(theFile);  
			try {
				map = new FileInputStream((System.getenv("APPDATA")+"/Ghost_Saves/"+name+"/"+name+"_map.txt"));
				//unitsData = new FileInputStream((System.getenv("APPDATA")+"/BattleShores_Saves/"+name+"/"+name+"_units.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//unitsData = Controller.class.getResourceAsStream(("/"+name+"/"+name+"_units.txt")); 
		}
		int currentChar;
		ArrayList<String> lines = new ArrayList<String>();
		String line1 = "";

		BufferedReader reader = null;
		try{
			//load the level's tile arrangement
			reader = new BufferedReader(new InputStreamReader(map, "UTF-8"));
			//reader = new BufferedReader(new FileReader(file1));
			for(int i = 0; i<GamePanel.rooms.get(GamePanel.currentRoom).width;i++){
				String line = reader.readLine();
				for(int j = 0;j<GamePanel.rooms.get(GamePanel.currentRoom).height;j++){
					int start = 0;
					int end = -1;
					ArrayList<String> temp = new ArrayList<String>();
					//System.out.println(line);
					char[] lineChars = line.toCharArray();
					//loop through every character in the line
					for(int k = 0; k<lineChars.length;k++){
						if(lineChars[k]==')'){
							end++;

						}
						if(end==j){
							//find the start of this tile's data
							for(int p = k; p>0;p--){
								if(lineChars[p]=='('){
									start = p+1;
									break;
								}
							}
							//loop through this line and store each value to temp

							String tileData = line.substring(start, k+1);//should look something like: "(1,3,12,15)"
							//System.out.println(tileData);
							char[] tileChars = tileData.toCharArray();
							//System.out.println(tileData);
							for(int l = 0; l<tileChars.length;l++){
								//get the value this is currently on
								//find the start of this value
								if(tileChars[l]==','||tileChars[l]==')'){//if this character is a comma
									int strt = -1;
									//find the comma or ( before this character was found
									for(int y = l;y>=0&&(tileChars[y]!=','||y==l)&&tileChars[y]!='(';y--){
										strt=y;
									}

									temp.add(tileData.substring(strt,l));
								}
							}
							//System.out.println(temp.toString());

							//System.out.println("temp= "+temp+" start= "+start+" k= "+k);
							GamePanel.rooms.get(GamePanel.currentRoom).tiles[i][j]=new Tile(Integer.valueOf(temp.get(2)),Integer.valueOf(temp.get(3)),Integer.valueOf(temp.get(0)),Integer.valueOf(temp.get(1)));
							break;
						}
					}

				}

			}
			try{reader.close();}catch(Exception ex){}

		}
		catch(Exception ex){
			System.out.println("Failed to write to (or create) game map file: "+ name);
			ex.printStackTrace();
		}
		finally{
			try{reader.close();}catch(Exception ex){}
		}
	}
	public void updateAll(){
		if (gamePanel != null)
			gamePanel.getParent().repaint();
	}


}

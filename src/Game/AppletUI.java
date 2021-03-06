package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class AppletUI extends JFrame{
	private static final long serialVersionUID = -6215774992938009947L;
	public static int windowWidth=960;
	public static int windowHeight=540;
	public static final long milisecInNanosec = 1000000L;
	public static final long secInNanosec = 1000000000L;
	public static int currentFPS = 0;
	public static int fpsSum = 0;
	public static long lastFPSupdate = System.nanoTime();
	public long lastDrawTime = System.currentTimeMillis();
	private int GAME_FPS = 60;
	private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
	Controller ctrl;
	public static Server server;
	public static Client client;

	public static int GAMESTATE = 3;
	public static final int NEWGAME = 0;
	public static final int PLAYING = 1;
	public static final int setIP = 2;
	public static final int CHOOSEGAMEMODE = 3;
	
	public static int GAMEMODE = 1;
	public static final int SINGLEPLAYER = 1;
	public static final int MULTIPLAYER = 2;
	
	public static Thread serverThread;

	public static void main(String[] args) throws IOException{
		//System.out.println("starting server");
		serverThread = new Thread() {			
			public void run(){
				try {
					server = new Server();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		//serverThread.start();
		//System.out.println("started server");
		//System.out.println("starting client");
		//client = new Client();
		//System.out.println("started client");

		AppletUI f = new AppletUI ();
		f.setSize(windowWidth,windowHeight);
		f.setVisible(true);
		//server = new Server();
		//client1 = new Client();
		//client2 = new Client();

	}

	public AppletUI() {

		//setSize(windowWidth,windowHeight);
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		JPanel drawPanel = new GamePanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel.setBackground(Color.BLACK);
		ctrl = new Controller();
		this.addKeyListener(ctrl);
		ctrl.setGamePanel(drawPanel);
		this.setFocusable(true);
		pane.add(drawPanel);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);  
		this.setUndecorated(true);  
		//We start game in new thread.
		Thread gameThread = new Thread() {			
			public void run(){
				gameLoop();
			}
		};
		gameThread.start();
	}
	public void startServer(){

	}
	public void gameLoop(){
		long beginTime, timeTaken, timeLeft;
		while(true){
			windowWidth = this.getWidth();
			windowHeight = this.getHeight();
			GamePanel.battlePos = new Point((this.getWidth()/2)-250,(this.getHeight()/2)-200);
			//System.out.println("looping");
			beginTime = System.nanoTime();
			repaint();
			Controller.checkKeyPositions();
			if(client!=null&&GAMEMODE==MULTIPLAYER){
//				try {
//					while(client.fromServer.ready()){
//						String command = "";
//						command = client.fromServer.readLine();
//						//System.out.println("4");
//						//System.out.println("listening");
//
//						if(command.equals("added player")){
//							client.players.add(new Player(0,0,GamePanel.name));
//							AppletUI.GAMESTATE=AppletUI.PLAYING;
//							System.out.println("loaded player from server");
//						}
//						if(command.contains("setPos")){
//							int commaPos = 0;
//							int nameStart = 0;
//							int nameEnd = 0;
//							for(int i = 0; i<command.length();i++){
//								if(command.toCharArray()[i]==','){
//									commaPos = i;
//								}
//								if(command.toCharArray()[i]=='('){
//									nameStart = i+1;
//								}
//								if(command.toCharArray()[i]==')'){
//									nameEnd = i;
//								}
//							}
//							String name = command.substring(nameStart,nameEnd);
//							String tempX = command.substring(nameEnd+2, commaPos);
//							client.getPlayer(name).xpos=Integer.valueOf(tempX);
//							String tempY = command.substring(commaPos+1, command.length());
//							client.getPlayer(name).ypos=Integer.valueOf(tempY);
//							client.getPlayer(name).direction=0;
//						}
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
			// If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
			if (timeLeft < 10){ 
				timeLeft = 10; //set a minimum
			}
			try {
				//Provides the necessary delay and also yields control so that other thread can do work.
				Thread.sleep(timeLeft);
			} catch (InterruptedException ex) { }
			fpsSum++;
			if(lastFPSupdate+secInNanosec<=System.nanoTime()){
				currentFPS = fpsSum;
				fpsSum = 0;
				lastFPSupdate = System.nanoTime();
			}

		}
	}
}

package Game;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {

	//public static ObjectOutputStream outputToClient;
	//public static ObjectInputStream inputFromClient;
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<ServerThread> threads = new ArrayList<ServerThread>();
	public Server() throws IOException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		serverSocket = new ServerSocket(7000);

		while (true) {
			// Listen for a new connection request
			try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
			ServerThread thread = new ServerThread(socket);
			thread.start();
			threads.add(thread);
			//threads.get(threads.size()-1).start();
			System.out.println("started thread");
		}
	}
	public static void addPlayer(String name){
		players.add(new Player(50, 50, name));
		for(int i = 0; i<threads.size();i++){
			threads.get(i).out.println("");
		}
		System.out.println("added player: "+name+" to server!");
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
}

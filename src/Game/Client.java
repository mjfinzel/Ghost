package Game;

/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {
	public static ArrayList<Player> players = new ArrayList<Player>();
	//String ipAddress = "LOCALHOST";
	Socket socket;// = new Socket(GamePanel.ip, 7000);
	PrintWriter toServer;//= new PrintWriter(socket.getOutputStream(), true);
	BufferedReader fromServer;// = new BufferedReader(new InputStreamReader(socket.getInputStream()));


	String serverResponse = "";
	public Thread inputThread ;

	public Client() throws IOException{
		if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
			socket = new Socket(GamePanel.ip, 7000);
			toServer = new PrintWriter(socket.getOutputStream(), true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
	}
//	public void canMoveUp(){
//		if(AppletUI.client.players.get(0).walkAnim==null){
//			toServer.println("checkNorth "+GamePanel.name);
//			AppletUI.client.players.get(0).direction = 3;
//			try {
//				String msg = fromServer.readLine();
//				System.out.println("recieved: "+msg+" from server");
//				if(msg.equals("true")){
//					AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	public void moveUp(){
//		//tell the server that the client is requesting to move up
//		//AppletUI.client.players.get(0).direction = 3;
//		//if(AppletUI.client.players.get(0).walkAnim==null){
//		//if(!	GamePanel.editMode)
//		//	AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//
//		toServer.println("moveUp "+GamePanel.name);
//
//		System.out.println("client requested to move up");
//		try {
//			String msg = fromServer.readLine();
//			System.out.println("recieved: "+msg+" from server");
//			int commaPos = 0;
//			int nameStart = 0;
//			int nameEnd = 0;
//			for(int i = 0; i<msg.length();i++){
//				if(msg.toCharArray()[i]==','){
//					commaPos = i;
//				}
//				if(msg.toCharArray()[i]=='('){
//					nameStart = i+1;
//				}
//				if(msg.toCharArray()[i]==')'){
//					nameEnd = i;
//				}
//			}
//			String name = msg.substring(nameStart,nameEnd);
//			if(name.equals(GamePanel.name)){
//				String tempX = msg.substring(nameEnd+2, commaPos);
//				getPlayer(name).xpos=Integer.valueOf(tempX);
//				String tempY = msg.substring(commaPos+1, msg.length());
//				getPlayer(name).ypos=Integer.valueOf(tempY);
//				getPlayer(name).direction=3;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//}
//	}
//	public void canMoveDown(){
//		if(AppletUI.client.players.get(0).walkAnim==null){
//			toServer.println("checkSouth "+GamePanel.name);
//			AppletUI.client.players.get(0).direction = 0;
//			try {
//				String msg = fromServer.readLine();
//				System.out.println("recieved: "+msg+" from server");
//				if(msg.equals("true")){
//					AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	public void moveDown(){
//		//AppletUI.client.players.get(0).direction = 0;
//		//if(AppletUI.client.players.get(0).walkAnim==null){
//		//if(!GamePanel.editMode)
//		//AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//
//		//tell the server that the client is requesting to move up
//		toServer.println("moveDown "+GamePanel.name);
//		System.out.println("client requested to move down");
//		try {
//			String msg = fromServer.readLine();
//			System.out.println("recieved: "+msg+" from server");
//			int commaPos = 0;
//			int nameStart = 0;
//			int nameEnd = 0;
//			for(int i = 0; i<msg.length();i++){
//				if(msg.toCharArray()[i]==','){
//					commaPos = i;
//				}
//				if(msg.toCharArray()[i]=='('){
//					nameStart = i+1;
//				}
//				if(msg.toCharArray()[i]==')'){
//					nameEnd = i;
//				}
//			}
//			String name = msg.substring(nameStart,nameEnd);
//			if(name.equals(GamePanel.name)){
//				String tempX = msg.substring(nameEnd+2, commaPos);
//				getPlayer(name).xpos=Integer.valueOf(tempX);
//				String tempY = msg.substring(commaPos+1, msg.length());
//				getPlayer(name).ypos=Integer.valueOf(tempY);
//				getPlayer(name).direction=0;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//}
//	}
//	public void canMoveLeft(){
//		if(AppletUI.client.players.get(0).walkAnim==null){
//			toServer.println("checkWest "+GamePanel.name);
//			AppletUI.client.players.get(0).direction = 2;
//			try {
//				String msg = fromServer.readLine();
//				System.out.println("recieved: "+msg+" from server");
//				if(msg.equals("true")){
//					AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	public void moveLeft(){
//		//AppletUI.client.players.get(0).direction = 2;
//		//if(AppletUI.client.players.get(0).walkAnim==null){
//		//if(!GamePanel.editMode)
//		//AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//
//		//tell the server that the client is requesting to move up
//		toServer.println("moveLeft "+GamePanel.name);
//		System.out.println("client requested to move left");
//		try {
//			String msg = fromServer.readLine();
//			System.out.println("recieved: "+msg+" from server");
//
//			int commaPos = 0;
//			int nameStart = 0;
//			int nameEnd = 0;
//			for(int i = 0; i<msg.length();i++){
//				if(msg.toCharArray()[i]==','){
//					commaPos = i;
//				}
//				if(msg.toCharArray()[i]=='('){
//					nameStart = i+1;
//				}
//				if(msg.toCharArray()[i]==')'){
//					nameEnd = i;
//				}
//			}
//			String name = msg.substring(nameStart,nameEnd);
//			System.out.println("name: "+name);
//			if(name.equals(GamePanel.name)){
//				String tempX = msg.substring(nameEnd+2, commaPos);
//				getPlayer(name).xpos=Integer.valueOf(tempX);
//				String tempY = msg.substring(commaPos+1, msg.length());
//				getPlayer(name).ypos=Integer.valueOf(tempY);
//				getPlayer(name).direction=2;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//}
//	}
//	public void canMoveRight(){
//		if(AppletUI.client.players.get(0).walkAnim==null){
//			toServer.println("checkEast "+GamePanel.name);
//			AppletUI.client.players.get(0).direction = 1;
//			try {
//				String msg = fromServer.readLine();
//				System.out.println("recieved: "+msg+" from server");
//				if(msg.equals("true")){
//					AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	public void moveRight(){
//		//AppletUI.client.players.get(0).direction = 1;
//		//if(AppletUI.client.players.get(0).walkAnim==null){
//		//if(!GamePanel.editMode)
//		//AppletUI.client.players.get(0).walkAnim = new Animation(GamePanel.playersprites, AppletUI.client.players.get(0).walkAnimFrames, AppletUI.client.players.get(0).walkingSpeed, 32, AppletUI.client.players.get(0).direction, 944,514, false, 0, 0);
//
//		//tell the server that the client is requesting to move up
//
//		toServer.println("moveRight "+GamePanel.name);
//		System.out.println("client requested to move right");
//		try {
//			String msg = fromServer.readLine();
//			System.out.println("recieved: "+msg+" from server");
//			int commaPos = 0;
//			int nameStart = 0;
//			int nameEnd = 0;
//			for(int i = 0; i<msg.length();i++){
//				if(msg.toCharArray()[i]==','){
//					commaPos = i;
//				}
//				if(msg.toCharArray()[i]=='('){
//					nameStart = i+1;
//				}
//				if(msg.toCharArray()[i]==')'){
//					nameEnd = i;
//				}
//			}
//			String name = msg.substring(nameStart,nameEnd);
//			if(name.equals(GamePanel.name)){
//				String tempX = msg.substring(nameEnd+2, commaPos);
//				getPlayer(name).xpos=Integer.valueOf(tempX);
//				String tempY = msg.substring(commaPos+1, msg.length());
//				getPlayer(name).ypos=Integer.valueOf(tempY);
//				getPlayer(name).direction=1;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//}
//	}
	public void addPlayer(String name){
		if(AppletUI.GAMEMODE==AppletUI.MULTIPLAYER){
			System.out.println("call to addPlayer()");
			toServer.println("addPlayer: "+name);
			String command = null;
			try {
				command = fromServer.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(command.equals("added player")){
				players.add(new Player(50,50,GamePanel.name));
				System.out.println("added player: "+name+" to client!");
				AppletUI.GAMESTATE=AppletUI.PLAYING;
			}
		}
		else if(AppletUI.GAMEMODE==AppletUI.SINGLEPLAYER){
			players.add(new Player(50,50,GamePanel.name));
			AppletUI.GAMESTATE=AppletUI.PLAYING;
		}
	}
//	public Player getPlayer(String name){
//		for(int i = 0;i<players.size();i++){
//			if(players.get(i).name.equals(name)){
//				return players.get(i);
//			}
//		}
//		players.add(new Player(50,50,name));
//		return players.get(players.size()-1);
//	}



}

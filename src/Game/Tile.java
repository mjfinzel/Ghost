package Game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tile {
	int xpos;
	int ypos;
	int tileX;
	int tileY;
	int tileID;
	float brightness = .1f;
	int []enterCollisionType = new int[4];//NESW - 0=north, 1=east, 2=south, 3=west | 1 is true, 0 is false, 2 is door
	int []exitCollisionType = new int[4];//NESW - 0=north, 1=east, 2=south, 3=west | 1 is true, 0 is false
	Grass grass;
	Door door;
	public Tile(int tilex, int tiley, int x, int y){
		xpos=x;
		ypos=y;
		tileX=tilex;
		tileY=tiley;
		//by default a tile has no entering or exiting collisions
		for(int i = 0; i<4;i++){
			enterCollisionType[i]=1;
			exitCollisionType[i]=1;
		}


		if(tileX==6&&tileY==1){
			grass = new Grass(xpos,ypos);
		}
		if(tileX==6&&tileY==2){
			grass = new Grass(xpos,ypos);
			grass.grassType = 3;
		}
		if(tileX==7&&tileY==6){//bottom left corner of tree
			GamePanel.rooms.get(GamePanel.currentRoom).tiles[(xpos+32)/32][ypos/32] = new Tile(8,6,xpos+32,ypos);
			grass = new Grass(xpos,ypos);
			grass.grassType=grass.TREE;
			enterCollisionType[0]=0;
			enterCollisionType[1]=0;
			enterCollisionType[2]=0;
			enterCollisionType[3]=0;
			exitCollisionType[0]=0;
			exitCollisionType[1]=0;
			exitCollisionType[2]=0;
			exitCollisionType[3]=0;
		}
		if(tileX==8&&tileY==6){//bottom right corner of tree
			enterCollisionType[0]=0;
			enterCollisionType[1]=0;
			enterCollisionType[2]=0;
			enterCollisionType[3]=0;
			exitCollisionType[0]=0;
			exitCollisionType[1]=0;
			exitCollisionType[2]=0;
			exitCollisionType[3]=0;
		}
//		if(tileX==7&&tileY==7){
//			GamePanel.rooms.get(GamePanel.currentRoom).tiles[(xpos+32)/32][ypos/32] = new Tile(8,7,xpos+32,ypos);
//		}
//		if(tileX==7&&tileY==8){
//			GamePanel.rooms.get(GamePanel.currentRoom).tiles[(xpos+32)/32][ypos/32] = new Tile(8,8,xpos+32,ypos);
//		}

		//the top left corner of a cliff
		if((tileX==9||tileX==10)&&tileY==0){
			enterCollisionType[0]=1;
			enterCollisionType[1]=0;
			enterCollisionType[2]=0;
			enterCollisionType[3]=1;
			exitCollisionType[0]=0;
			exitCollisionType[1]=1;
			exitCollisionType[2]=1;
			exitCollisionType[3]=0;
		}
		//cliff facing west where the top part is east and the bottom of the cliff is west
		else if((tileX==9||tileX==10)&&tileY==1){
			enterCollisionType[0]=1;
			enterCollisionType[1]=0;
			enterCollisionType[2]=1;
			enterCollisionType[3]=1;
			exitCollisionType[0]=1;
			exitCollisionType[1]=1;
			exitCollisionType[2]=1;
			exitCollisionType[3]=0;
		}
		//bottom left corner of a cliff
		else if((tileX==9||tileX==10)&&tileY==2){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		//horrizontal cliff tile representing the top edge of a cliff
		else if((tileX==9||tileX==10)&&tileY==3){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=1;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=1;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//horrizontal cliff tile representing the bottom edge of a cliff
		else if((tileX==9||tileX==10)&&tileY==5){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		//inner corner of a cliff, cliff on south and west, elevated on north and east
		else if((tileX==9||tileX==10)&&tileY==6){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=1;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=1;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=0;//west
		}
		//inner north western corner of a cliff, represents the top edge inverted corner like:  _|
		else if((tileX==9||tileX==10)&&tileY==7){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=1;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=1;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//top right corner of a cliff
		else if((tileX==9||tileX==10)&&tileY==8){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//middle right side of a cliff
		else if((tileX==9||tileX==10)&&tileY==9){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//bottom right corner of a cliff
		else if((tileX==9||tileX==10)&&tileY==10){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		//inverted bottom right corner of a cliff
		else if((tileX==9||tileX==10)&&tileY==11){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//inverted top right corner of a cliff
		else if((tileX==9||tileX==10)&&tileY==12){
			enterCollisionType[0]=1;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=1;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=1;//east
			exitCollisionType[2]=1;//south
			exitCollisionType[3]=1;//west
		}
		//lone rock
		else if((tileX==9||tileX==10)&&tileY==9){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		//horizontal brick wall
		else if(tileX==1&&tileY==0){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		//vertical brick wall
		else if(tileX==1&&tileY==1){
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		else if(tileX>=0&&tilex<=4&&tileY==14){//rear interior wall of house
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		else if(tileX>=3&&tilex<=4&&tileY==15){//stove
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		else if(tileX==5&&tileY>=15&&tileY<=16){//bed
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
		}
		else if(tileX>=0&&tileX<=7&&tileY>=9&&tileY<=13){//cabin entrance
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
			if(tileX==4&&tileY==13){//door
				enterCollisionType[0]=2;
				//if this tile is placed it should prompt the user to enter a room name for it to lead to
				door = new Door(GamePanel.currentRoom,1);
				door.roomBPos.x=10;
				door.roomBPos.y=5;
			}
		}
		else if(tileX==1&&tileY==3){//cabin exit
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=1;//east
			enterCollisionType[2]=1;//south
			enterCollisionType[3]=1;//west
			exitCollisionType[0]=1;//north
			exitCollisionType[1]=1;//east
			exitCollisionType[2]=2;//south
			exitCollisionType[3]=1;//west

			//if this tile is placed it should prompt the user to enter a room name for it to lead to
			door = new Door(GamePanel.currentRoom,GamePanel.currentRoom-1);
			door.roomBPos.x=55;
			door.roomBPos.y=30;

		}
		else if(tileX==10&&tileY==14){//cave exit
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=2;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west

			//if this tile is placed it should prompt the user to enter a room name for it to lead to
			door = new Door(GamePanel.currentRoom,0);
			door.roomBPos.x=87;
			door.roomBPos.y=23;
			if(xpos==3*32&&ypos==23*32){
				door = new Door(GamePanel.currentRoom,3);
				door.roomBPos.x=140;
				door.roomBPos.y=18;
			}
		}
		else if(tileX==9&&tileY==14){//cave entrance
			enterCollisionType[0]=0;//north
			enterCollisionType[1]=0;//east
			enterCollisionType[2]=0;//south
			enterCollisionType[3]=0;//west
			exitCollisionType[0]=0;//north
			exitCollisionType[1]=0;//east
			exitCollisionType[2]=0;//south
			exitCollisionType[3]=0;//west
			if(tileX==9&&tileY==14){//door
				enterCollisionType[0]=2;
				//if this tile is placed it should prompt the user to enter a room name for it to lead to
				door = new Door(GamePanel.currentRoom,2);
				door.roomBPos.x=47;
				door.roomBPos.y=28;
			}
		}

	}
	public void Draw(Graphics g){
		int xChange = 0;
		int yChange = 0;
		if(GamePanel.player.walkAnim!=null){
			if(GamePanel.player.direction==1){
				xChange = GamePanel.player.walkAnim.framesDrawn*2;
			}
			else if(GamePanel.player.direction==2){
				xChange = -GamePanel.player.walkAnim.framesDrawn*2;
			}

			if(GamePanel.player.direction==0){
				yChange = GamePanel.player.walkAnim.framesDrawn*2;
			}
			else if(GamePanel.player.direction==3){
				yChange = -GamePanel.player.walkAnim.framesDrawn*2;
			}
		}
		int x = 944+xpos-(xChange+GamePanel.player.xpos*32);
		int y = 524+ypos-(yChange+GamePanel.player.ypos*32);
		if(x>=-32&&x<=1920&&y>=-32&&y<=1080){
			if(GamePanel.spiritWorldOn==false){
				g.drawImage(GamePanel.tiles[tileX][tileY],x,y,32,32,null);
			}
			else{
				g.drawImage(GamePanel.spiritTiles[tileX][tileY],x,y,32,32,null);
			}
			if(grass!=null){
				grass.drawRear(g);
			}
		}

	}
}

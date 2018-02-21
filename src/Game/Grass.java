package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Grass {
	int xpos;
	int Xpos;
	int ypos;
	int oldX;
	int encounterRate = 10;
	final int GRASS = 1;
	final int TREE = 2;
	final int CAVE = 3;
	int grassType = GRASS;
	int numShakes = 0;
	int shakeTime = GamePanel.randomNumber(500, 2000);
	int color = 0; 
	BufferedImage[][]artwork = GamePanel.grassTextures;
	Boolean occupied = false;
	long lastShakeTime = System.currentTimeMillis();
	Animation leafAnim;// = new Animation(GamePanel.leaves,10,GamePanel.randomNumber(200, 250),40,0,(xpos),(ypos)-4,true,0,0);
	public Grass(int x, int y){
		xpos = x;
		Xpos = x;
		oldX = xpos;
		ypos = y;
		if(grassType==GRASS){
			if(GamePanel.randomNumber(1, 15)==1){
				occupied=true;

			}
			if(color==0){
				leafAnim = new Animation(GamePanel.leaves,10,GamePanel.randomNumber(150, 200),40,0,(xpos)-4,(ypos)-4,false,0,0);
			}
			else{
				leafAnim = new Animation(GamePanel.goldLeaves,10,GamePanel.randomNumber(150, 200),40,0,(xpos)-4,(ypos)-4,false,0,0);
			}
		}
	}
	public void enterGrass(){
		if(grassType==GRASS||grassType==CAVE){
			if(grassType==GRASS){
				encounterRate = 10;
			}
			else if (grassType==CAVE){
				encounterRate = 10;
			}
			if(occupied||GamePanel.randomNumber(1, encounterRate)==1){
				//System.out.println("entered occupied grass!");
				//determine monster coloring
				int roll = GamePanel.randomNumber(1, 2000);
				if(roll==2000){// 1/2000
					color = 2;
				}
				else if(roll <=5){// 1/400
					color = 1;
				}
				else{
					color = 0;
				}
				boolean done = false;
				while(!done){
					//determine what monster
					int whatMonster = GamePanel.randomNumber(1, 1000);
					if(whatMonster<=5){//super rare (0.5% chance)

					}
					else if(whatMonster>5&&whatMonster<=15){//very rare(1% chance)

					}
					else if(whatMonster>15&&whatMonster<=65){//rare (5% chance)
						if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Teyl Forest"&&false){
							GamePanel.player.enterBattle(4,color,true);//armonkey
							GamePanel.player.enterBattle(5,color,true);//gnuDragon
							done = true;
						}
						else if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Spider Canyon"){
							GamePanel.player.enterBattle(7,color,true);//sunny the bird
							done = true;
						}
					}
					else if(whatMonster>65&&whatMonster<=265){//uncommon (20% chance)
						if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Teyl Forest"&&false){
							GamePanel.player.enterBattle(3,color,true);//firefly
							done = true;
						}
					}
					else{//common (73.5% chance)
						if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Teyl Forest"){
							GamePanel.player.enterBattle(2,color,true);//forest grub
							done = true;
						}
						else if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Spider Canyon"){
							GamePanel.player.enterBattle(6,color,true);//pig
							done = true;
						}
						else if(GamePanel.rooms.get(GamePanel.currentRoom).name=="Forbidden Cave"){
							GamePanel.player.enterBattle(8,color,true);//rat
							done = true;
						}
					}
				}
				//make this grass no longer occupied
				occupied  = false;
			}
		}
	}
	public void drawFront(Graphics g){
		if(grassType!=CAVE){
			int xChange = 0;
			int yChange = 0;

			if(GamePanel.player.walkAnim!=null){
				int temporary=0;
				if(GamePanel.player.walkAnim.framesDrawn>0){
					temporary = 1;
				}
				if(GamePanel.player.direction==1){
					xChange = (GamePanel.player.walkAnim.framesDrawn-temporary)*2;
				}
				else if(GamePanel.player.direction==2){
					xChange = -(GamePanel.player.walkAnim.framesDrawn-temporary)*2;
				}

				if(GamePanel.player.direction==0){
					yChange = (GamePanel.player.walkAnim.framesDrawn-temporary)*2;
				}
				else if(GamePanel.player.direction==3){
					yChange = -(GamePanel.player.walkAnim.framesDrawn-temporary)*2;
				}
			}
			int x = 944+xpos-(xChange+GamePanel.player.xpos*32);
			int y = 524+ypos-(yChange+GamePanel.player.ypos*32);
			if(x>=-64&&x<=1920&&y>=-32&&y<=1144){
				if(grassType==GRASS){
					if(GamePanel.spiritWorldOn==false){
						if(grassType==GRASS){
							g.drawImage(artwork[0][0],x,y-32,32,64,null);
						}

					}
					else{
						if(grassType==GRASS){
							g.drawImage(GamePanel.spiritGrassTextures[0][0],x,y-32,32,64,null);
						}
					}
				}
				else if(grassType==TREE){
					if(GamePanel.spiritWorldOn==false){
						g.drawImage(GamePanel.tiles[7][5],x,y-32,32,32,null);
						g.drawImage(GamePanel.tiles[7][4],x,y-64,32,32,null);
						g.drawImage(GamePanel.tiles[8][5],x+32,y-32,32,32,null);
						g.drawImage(GamePanel.tiles[8][4],x+32,y-64,32,32,null);
					}
					else{
						g.drawImage(GamePanel.spiritTiles[7][5],x,y-32,32,32,null);
						g.drawImage(GamePanel.spiritTiles[7][4],x,y-64,32,32,null);
						g.drawImage(GamePanel.spiritTiles[8][5],x+32,y-32,32,32,null);
						g.drawImage(GamePanel.spiritTiles[8][4],x+32,y-64,32,32,null);
					}

				}
			}
			if(this.occupied){
				if(lastShakeTime+shakeTime<=System.currentTimeMillis()){
					numShakes++;
					shakeTime = GamePanel.randomNumber(500, 1000);
					if(Xpos>oldX){
						Xpos--;
					}
					else{
						Xpos++;
					}
					lastShakeTime = System.currentTimeMillis();
				}
				if(GamePanel.spiritWorldOn){
					leafAnim.spriteSheet=GamePanel.spiritLeaves[0];
				}
				else{
					leafAnim.spriteSheet=GamePanel.leaves[0];
				}

				int xChange2 = 0;
				int yChange2 = 0;
				if(GamePanel.player.walkAnim!=null){
					if(GamePanel.player.direction==1){
						xChange2 = GamePanel.player.walkAnim.framesDrawn*2;
					}
					else if(GamePanel.player.direction==2){
						xChange2 = -GamePanel.player.walkAnim.framesDrawn*2;
					}

					if(GamePanel.player.direction==0){
						yChange2 = GamePanel.player.walkAnim.framesDrawn*2;
					}
					else if(GamePanel.player.direction==3){
						yChange2 = -GamePanel.player.walkAnim.framesDrawn*2;
					}
				}
				leafAnim.xpos = 944+xpos-(xChange2+GamePanel.player.xpos*32);
				leafAnim.ypos = 524+ypos-(yChange2+GamePanel.player.ypos*32);

				leafAnim.Draw(g);
				if(numShakes>5){
					ArrayList<Grass> nearbyGrass = new ArrayList<Grass>();
					//System.out.println("start");
					//pick a random grass tile next to this
					for(int i = (ypos/32)-1;i<=(ypos/32)+1;i++){
						for(int j = (xpos/32)-1;j<=(xpos/32)+1;j++){
							//System.out.println("i "+i+", j: "+j);
							if(!(i==(ypos/32)-1&&j==(xpos/32)-1)&&!(i==(ypos/32)+1&&j==(xpos/32)+1)&&!(i==(ypos/32)-1&&j==(xpos/32)+1)&&!(i==(ypos/32)+1&&j==(xpos/32)-1)){
								if(i<GamePanel.rooms.get(GamePanel.currentRoom).height&&j<GamePanel.rooms.get(GamePanel.currentRoom).width&&i>=0&&j>=0){
									if(GamePanel.rooms.get(GamePanel.currentRoom).tiles[j][i].grass!=null&&GamePanel.rooms.get(GamePanel.currentRoom).tiles[j][i].grass.occupied==false&&GamePanel.rooms.get(GamePanel.currentRoom).tiles[j][i].grass.grassType==GRASS){

										nearbyGrass.add(GamePanel.rooms.get(GamePanel.currentRoom).tiles[j][i].grass);
									}
								}
							}
						}				
					}
					if(nearbyGrass.size()>0){
						int temp = GamePanel.randomNumber(0, nearbyGrass.size()-1);
						nearbyGrass.get(temp).occupied=true;
						//nearbyGrass.get(temp).color=this.color;

						int x2 = nearbyGrass.get(temp).xpos;
						int y2 = nearbyGrass.get(temp).ypos;
						//if(nearbyGrass.get(temp).color==0){
						nearbyGrass.get(temp).leafAnim = new Animation(GamePanel.leaves,10,GamePanel.randomNumber(150, 200),40,0,(x2)-4,(y2)-4,false,0,0);
						//}
						//else{
						//	nearbyGrass.get(temp).leafAnim = new Animation(GamePanel.goldLeaves,10,GamePanel.randomNumber(150, 200),40,0,(x)-4,(y)-4,true,0,0);
						//}
						//this.color=0;
						//nearbyGrass.get(GamePanel.randomNumber(0, nearbyGrass.size()-1)).shakeTime = GamePanel.randomNumber(500, 2000);
						this.occupied=false;
						numShakes = 0;
					}
				}
			}
			else{
				Xpos=oldX;
			}
		}

	}
	public void drawRear(Graphics g){
		if(grassType!=GRASS){
			occupied = false;
		}
		if(grassType!=CAVE){
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

			//g.drawImage(artwork[1][0],Xpos,ypos-48,32,64,null);
			if(grassType == GRASS){
				if(GamePanel.spiritWorldOn==false){
					g.drawImage(artwork[1][0],x,y-32,32,64,null);
				}
				else{
					g.drawImage(GamePanel.spiritGrassTextures[1][0],x,y-32,32,64,null);
				}
			}
		}
	}
}
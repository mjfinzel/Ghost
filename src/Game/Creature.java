package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Creature {
	int xpos;
	int ypos;
	int creatureID;
	int attack;
	int defense;
	int accuracy;
	int health;
	double currentHealth;
	int crit;
	int avoid;
	int laziness;
	int speed;
	int level;
	int willPower;
	int energy = 100;
	int currentEnergy;
	int currentXP = 0;
	int experienceToLevel;
	int type;
	int color = 0;

	double attackGainPerLevel;
	double defenseGainPerLevel;
	double accuracyGainPerLevel;
	double healthGainPerLevel;
	double critGainPerLevel;
	double avoidGainPerLevel;
	double speedGainPerLevel;
	double lazinessGainPerLevel;

	final int fire = 0;
	final int water = 1;
	final int anima = 2;
	final int shadow = 3;
	final int earth = 4;
	final int light = 5;
	final int time = 6;
	boolean isWild = true;
	ArrayList<Attack> attacks = new ArrayList<Attack>();
	Button formSelect;
	PassiveGrid talents = new PassiveGrid();
	BufferedImage [][]Sprites;
	String name = "";
	Color specialColor;
	ColorGene colorGene = new ColorGene();
	public Creature(int lvl, int ID, int clr, boolean wild){
		isWild = wild;
		
		color = clr;
		creatureID = ID;
		level = lvl;
		if(creatureID==1){
			attackGainPerLevel=15;
			defenseGainPerLevel=10;
			accuracyGainPerLevel=15;
			healthGainPerLevel=30;
			critGainPerLevel=15;
			avoidGainPerLevel=15;
			speedGainPerLevel=15;
			lazinessGainPerLevel=15;

			this.name="Forest Sprite";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+20;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=100;
		}
		else if(creatureID==2){
			attackGainPerLevel=12;
			defenseGainPerLevel=10;
			accuracyGainPerLevel=14;
			healthGainPerLevel=15;
			critGainPerLevel=13;
			avoidGainPerLevel=12;
			speedGainPerLevel=11;
			lazinessGainPerLevel=25;

			this.name="Forest Grub";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+15;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=1;
			this.Sprites= Images.cut("/Creatures/ForestGrub.png", 100, 100);
			colorGene.setColor(new Color(255,195,0));
			specialColor = colorGene.getColor();
			attacks.add(new Attack("Bite"));
		}
		else if(creatureID==3){
			attackGainPerLevel=15;
			defenseGainPerLevel=20;
			accuracyGainPerLevel=15;
			healthGainPerLevel=25;
			critGainPerLevel=12;
			avoidGainPerLevel=12;
			speedGainPerLevel=13;
			lazinessGainPerLevel=10;

			this.name="Giant Firefly";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+30;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=3;
			this.Sprites=Images.cut("/Creatures/FireFly.png", 100, 100);
			attacks.add(new Attack("Bite"));
		}
		else if(creatureID==4){
			attackGainPerLevel=20;
			defenseGainPerLevel=12;
			accuracyGainPerLevel=12;
			healthGainPerLevel=12;
			critGainPerLevel=15;
			avoidGainPerLevel=15;
			speedGainPerLevel=15;
			lazinessGainPerLevel=4;

			this.name= "Armonkey";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+22;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=6;
			this.Sprites=GamePanel.monkey;
			attacks.add(new Attack("Punch"));
		}
		else if(creatureID==5){
			attackGainPerLevel=17;
			defenseGainPerLevel=13;
			accuracyGainPerLevel=10;
			healthGainPerLevel=20;
			critGainPerLevel=12;
			avoidGainPerLevel=5;
			speedGainPerLevel=15;
			lazinessGainPerLevel=15;

			this.name= "GnuDragon";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+24;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=20;
			this.Sprites=GamePanel.dragon;
			attacks.add(new Attack("Bite"));
		}
		else if(creatureID==6){
			attackGainPerLevel=18;
			defenseGainPerLevel=15;
			accuracyGainPerLevel=2;
			healthGainPerLevel=30;
			critGainPerLevel=12;
			avoidGainPerLevel=15;
			speedGainPerLevel=15;
			lazinessGainPerLevel=20;

			this.name= "Piggy";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+4;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=20;
			this.Sprites=GamePanel.pig;
			attacks.add(new Attack("Charge"));
		}
		else if(creatureID==7){
			attackGainPerLevel=18;
			defenseGainPerLevel=13;
			accuracyGainPerLevel=30;
			healthGainPerLevel=15;
			critGainPerLevel=25;
			avoidGainPerLevel=15;
			speedGainPerLevel=15;
			lazinessGainPerLevel=5;

			this.name= "Birderp";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+14;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=20;
			this.Sprites=GamePanel.bird;
			attacks.add(new Attack("Peck"));
		}
		else if(creatureID==8){
			attackGainPerLevel=18;
			defenseGainPerLevel=13;
			accuracyGainPerLevel=30;
			healthGainPerLevel=13;
			critGainPerLevel=25;
			avoidGainPerLevel=15;
			speedGainPerLevel=25;
			lazinessGainPerLevel=5;

			this.name= "Rat";
			this.attack=(int)(level*attackGainPerLevel);
			this.defense=(int)(level*defenseGainPerLevel);
			this.accuracy=(int)(level*accuracyGainPerLevel);
			this.health=(int)(level*healthGainPerLevel)+14;
			this.currentHealth=health;
			this.crit=(int)(level*critGainPerLevel);
			this.avoid=(int)(level*avoidGainPerLevel);
			this.speed=(int)(level*speedGainPerLevel);
			this.laziness=(int)(level*lazinessGainPerLevel);
			this.willPower=6;
			this.Sprites=Images.cut("/Creatures/Rat.png", 100, 100);

			attacks.add(new Attack("Bite"));
		}
		if(isWild){
			colorSprite(this.Sprites);
		}
		experienceToLevel = (attack+defense+accuracy+health+crit+avoid+speed+laziness)/8;
	}
	public void colorSprite(BufferedImage[][] sprite){
		//System.out.println("setting sprite color to: "+specialColor.getRed()+","+specialColor.getGreen()+","+specialColor.getBlue());
		for(int i = 0; i<2;i++){
			for(int j = 0; j<1;j++){
				//check for black and white pixels
				for(int x = 0; x<100; x++){
					for(int y = 0; y<100; y++){
						int red = new Color(sprite[i][j].getRGB(x, y)).getRed();
						int green = new Color(sprite[i][j].getRGB(x, y)).getGreen();
						int blue = new Color(sprite[i][j].getRGB(x, y)).getBlue();
						if(red==blue&&red==green&&blue==green&&new Color(sprite[i][j].getRGB(x, y)).getAlpha()==255&&red!=0){
							int newRed = specialColor.getRed()-(255-red);
							if(newRed<0){
								newRed=0;
							}
							int newGreen = specialColor.getGreen()-(255-green);
							if(newGreen<0){
								newGreen=0;
							}
							int newBlue = specialColor.getBlue()-(255-blue);
							if(newBlue<0){
								newBlue=0;
							}
							sprite[i][j].setRGB(x,y, new Color(newRed,newGreen,newBlue).getRGB());
						}
					}
				}
			}
		}
	}
	public void restoreHP(int amt){
		currentHealth+=amt;
		if(currentHealth>health){
			currentHealth = health;
		}
	}
	public void takeDmg(Creature attacker, Attack atk, int amt, int dmgType){
		int dmgTaken = ((int)(amt*effectiveness(dmgType,type)))-defense;
		int rng = (int)((double)dmgTaken/15.0);
		if(rng==0){
			rng = 1;
		}
		dmgTaken = dmgTaken+GamePanel.randomNumber(-rng, rng);
		if(dmgTaken<0){
			dmgTaken = GamePanel.randomNumber(0, 1);
		}
		currentHealth-=dmgTaken;
		if(currentHealth<=0){
			attacker.gainXP(this);
			for(int i = 0; i<AppletUI.client.players.size();i++){
				AppletUI.client.players.get(i).battling=false;
				AppletUI.client.players.get(i).currentTarget=null;
			}
		}
		if(dmgTaken>0){
			GamePanel.messages.add(atk.name+" dealt "+dmgTaken+" damage to "+name+"!");
		}
		else{
			GamePanel.messages.add(attacker.name+"'s "+atk.name+" did no damage.");
		}
	}
	public void gainXP(Creature opponent){
		//gain experience equal to the opponents average stats
		int xpGain = opponent.attack+opponent.defense+opponent.accuracy+opponent.avoid+opponent.laziness+opponent.speed+opponent.health+opponent.crit;
		this.currentXP+=xpGain;
		if(this.currentXP>=this.experienceToLevel){
			if(level<100){
				levelUp();
				experienceToLevel = laziness*5;
			}
			else{
				currentXP=experienceToLevel;
			}
		}
	}
	public void levelUp(){


		int oldAttack = attack;
		attack=(int)(level*(attackGainPerLevel));
		//GamePanel.messages.add("Attack: "+oldAttack+" -> "+attack);

		int oldDefense = defense;
		defense = (int)(level*(defenseGainPerLevel));
		//GamePanel.messages.add("Defense: "+oldDefense+" -> "+defense);

		int oldAccuracy = accuracy;
		accuracy = (int)(level*(accuracyGainPerLevel));
		//GamePanel.messages.add("Accuracy: "+oldAccuracy+" -> "+accuracy);

		int oldHealth = health;
		health=(int)(level*(healthGainPerLevel));
		//GamePanel.messages.add("Health: "+oldHealth+" -> "+health);

		int oldCrit = crit;
		crit = (int)(level*(critGainPerLevel));
		//GamePanel.messages.add("Crit: "+oldCrit+" -> "+crit);

		int oldAvoid = avoid;
		avoid = (int)(level*(avoidGainPerLevel));
		//GamePanel.messages.add("Avoid: "+oldAvoid+" -> "+avoid);

		int oldSpeed = speed;
		speed = (int)(level*(speedGainPerLevel));
		//GamePanel.messages.add("Speed: "+oldSpeed+" -> "+speed);

		int oldLaziness = laziness;
		laziness = (int)(laziness*(lazinessGainPerLevel));

		GamePanel.messages.add("Level up!");
	}
	public double effectiveness(int atkType, int targetType){
		//fire hit fire
		if(atkType == 0&&targetType == 0)
			return 1.0;
		//fire hit water
		if(atkType == 0&&targetType == 1)
			return .5;
		//fire hit anima
		if(atkType == 0&&targetType == 2)
			return 1.5;
		//fire hit shadow
		if(atkType == 0&&targetType == 3)
			return 1.0;
		//fire hit earth
		if(atkType == 0&&targetType == 4)
			return 1.0;
		//fire hit light
		if(atkType == 0&&targetType == 5)
			return 1.0;
		//fire hit time
		if(atkType == 0&&targetType == 6)
			return 1.0;
		//water hit fire
		if(atkType == 1&&targetType == 0)
			return 1.0;
		//water hit water
		if(atkType == 1&&targetType == 1)
			return 1.0;
		//water hit anima
		if(atkType == 1&&targetType == 2)
			return 1.0;
		//water hit shadow
		if(atkType == 1&&targetType == 3)
			return 1.0;
		//water hit earth
		if(atkType == 1&&targetType == 4)
			return 1.0;
		//water hit light
		if(atkType == 1&&targetType == 5)
			return 1.0;
		//water hit time
		if(atkType == 1&&targetType == 6)
			return 1.0;
		//anima hit fire
		if(atkType == 2&&targetType == 0)
			return 1.0;
		//anima hit water
		if(atkType == 2&&targetType == 1)
			return 1.0;
		//anima hit anima
		if(atkType == 2&&targetType == 2)
			return 1.0;
		//anima hit shadow
		if(atkType == 2&&targetType == 3)
			return 1.0;
		//anima hit earth
		if(atkType == 2&&targetType == 4)
			return 1.0;
		//anima hit light
		if(atkType == 2&&targetType == 5)
			return 1.0;
		//anima hit time
		if(atkType == 2&&targetType == 6)
			return 1.0;
		//shadow hit fire
		if(atkType == 3&&targetType == 0)
			return 1.0;
		//shadow hit water
		if(atkType == 3&&targetType == 1)
			return 1.0;
		//shadow hit anima
		if(atkType == 3&&targetType == 2)
			return 1.0;
		//shadow hit shadow
		if(atkType == 3&&targetType == 3)
			return 1.0;
		//shadow hit earth
		if(atkType == 3&&targetType == 4)
			return 1.0;
		//shadow hit light
		if(atkType == 3&&targetType == 5)
			return 1.0;
		//shadow hit time
		if(atkType == 3&&targetType == 6)
			return 1.0;
		//earth hit fire
		if(atkType == 4&&targetType == 0)
			return 1.0;
		//earth hit water
		if(atkType == 4&&targetType == 1)
			return 1.0;
		//earth hit anima
		if(atkType == 4&&targetType == 2)
			return 1.0;
		//earth hit shadow
		if(atkType == 4&&targetType == 3)
			return 1.0;
		//earth hit earth
		if(atkType == 4&&targetType == 4)
			return 1.0;
		//earth hit light
		if(atkType == 4&&targetType == 5)
			return 1.0;
		//earth hit time
		if(atkType == 4&&targetType == 6)
			return 1.0;
		//light hit fire
		if(atkType == 5&&targetType == 0)
			return 1.0;
		//light hit water
		if(atkType == 5&&targetType == 1)
			return 1.0;
		//light hit anima
		if(atkType == 5&&targetType == 2)
			return 1.0;
		//light hit shadow
		if(atkType == 5&&targetType == 3)
			return 1.0;
		//light hit earth
		if(atkType == 5&&targetType == 4)
			return 1.0;
		//light hit light
		if(atkType == 5&&targetType == 5)
			return 1.0;
		//light hit time
		if(atkType == 5&&targetType == 6)
			return 1.0;
		//time hit fire
		if(atkType == 6&&targetType == 0)
			return 1.0;
		//time hit water
		if(atkType == 6&&targetType == 1)
			return 1.0;
		//time hit anima
		if(atkType == 6&&targetType == 2)
			return 1.0;
		//time hit shadow
		if(atkType == 6&&targetType == 3)
			return 1.0;
		//time hit earth
		if(atkType == 6&&targetType == 4)
			return 1.0;
		//time hit light
		if(atkType == 6&&targetType == 5)
			return 1.0;
		//time hit time
		if(atkType == 6&&targetType == 6)
			return 1.0;
		//no conditions satisfied
		return 1.0;
	}
}

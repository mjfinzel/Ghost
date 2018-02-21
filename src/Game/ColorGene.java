package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ColorGene {
	Color color;
	int [] red = new int[255];//11 will always be passed and has an effect, 10 passed some of the time, 00 never passed and has no effect
	int [] green = new int[255];
	int [] blue = new int[255];
	int mutationRate = 200;
	public ColorGene(){

	}
	public void setColor(Color clr){
		color=clr;
		for(int i = 0; i<255;i++){
			if(i<color.getRed()){
				red[i]=11;
			}
			else{
				red[i]=00;
			}
			if(i<color.getGreen()){
				green[i]=11;
			}
			else{
				green[i]=00;
			}
			if(i<color.getBlue()){
				blue[i]=11;
			}
			else{
				blue[i]=00;
			}
			//determine whether a mutation has occurred or not
			if(GamePanel.randomNumber(1, mutationRate)==1){//if the roll is 1 then a mutation has occured
				int roll = GamePanel.randomNumber(1, 3);
				//determine which color has mutated
				if(roll==1){
					roll = GamePanel.randomNumber(1, 3);
					if(roll==1){//33% chance to be recessive or dominant
						red[i]=10;//recessive
					}
					else if(roll==2){
						red[i]=11;//dominant
					}
					else{//off
						red[i]=00;
					}
				}
				else if(roll==2){
					roll = GamePanel.randomNumber(1, 3);
					if(roll==1){//33% chance to be recessive or dominant
						green[i]=10;//recessive
					}
					else if(roll==2){
						green[i]=11;//dominant
					}
					else{//off
						green[i]=00;
					}
				}
				else if(roll==3){
					roll = GamePanel.randomNumber(1, 3);
					if(roll==1){//33% chance to be recessive or dominant
						blue[i]=10;//recessive
					}
					else if(roll==2){
						blue[i]=11;//dominant
					}
					else{//off
						blue[i]=00;
					}
				}
			}
		}
	}
	public Color getColor(){

		int tempRed=0;
		int tempGreen=0;
		int tempBlue=0;
		for(int i = 0; i<255;i++){
			if(red[i]==11||red[i]==10){
				tempRed++;
			}
			if(green[i]==11||green[i]==10){
				tempGreen++;
			}
			if(blue[i]==11||blue[i]==10){
				tempBlue++;
			}
		}		
		System.out.println("new color was: "+tempRed+","+tempGreen+","+tempBlue);
		return (new Color(tempRed,tempGreen,tempBlue));
		
	}
	public int crossGenes(int gene1, int gene2){
		int roll = GamePanel.randomNumber(1, 4);
		int result = 00;
		//both genes are inactive
		if(gene1==00&&gene2==00){
			result = 00;
		}
		//both genes are dominant
		if(gene1==11&&gene2==11){
			result = 11;
		}
		//one gene is recessive and one is dominant
		if(gene1==11&&gene2==10){
			if(GamePanel.randomNumber(1, 2)==1){
				result = 11;
			}
			else{
				result = 10;
			}
		}
		//one gene is dominant and the other is inactive
		if((gene1==11&&gene2==00)||(gene1==00&&gene2==11)){
			result = 10;
		}
		//one gene is recessive and the other is inactive
		if(gene1==10&&gene2==00){
			if(GamePanel.randomNumber(1, 2)==1){
				result = 10;
			}
			else{
				result = 00;
			}
		}
		//both genes are recessive
		if(gene1==10&&gene2==10){
			if(roll==1){
				result = 11;
			}
			else if(roll==2){
				result = 00;
			}
			else{
				result = 10;
			}
		}
		return result;
	}
	public ColorGene generateOffspringColor(ColorGene partnersColor){
		ColorGene offspringColor = new ColorGene();
		for(int i = 0; i<255;i++){
			offspringColor.red[i]=crossGenes(red[i],partnersColor.red[i]);
			offspringColor.green[i]=crossGenes(green[i],partnersColor.green[i]);
			offspringColor.blue[i]=crossGenes(blue[i],partnersColor.blue[i]);
		}
		return offspringColor;
	}
	public void DrawAtPos(int x, int y,Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(2));
	    int scale = 3;
		for(int i = 0; i<255*scale;i+=scale){
			int redHeight = 0;
			//red component
			g.setColor(Color.red);
			if(red[i/scale]==11){
				g.drawLine(x+i, y, x+i, y+20);
			}
			else if(red[i/scale]==10){
				g.drawLine(x+i, y, x+i, y+10);
				g.setColor(Color.white);
				g.drawLine(x+i, y+10, x+i, y+20);
			}
			else if(red[i/scale]==00){
				g.setColor(Color.white);
				g.drawLine(x+i, y, x+i, y+20);
			}
			//green component
			g.setColor(Color.green);
			if(green[i/scale]==11){
				g.drawLine(x+i, y+23, x+i, y+43);
			}
			else if(green[i/scale]==10){
				g.drawLine(x+i, y+23, x+i, y+33);
				g.setColor(Color.white);
				g.drawLine(x+i, y+30, x+i, y+43);
			}
			else if(green[i/scale]==00){
				g.setColor(Color.white);
				g.drawLine(x+i, y+23, x+i, y+43);
			}
			//blue component
			g.setColor(Color.blue);
			if(blue[i/scale]==11){
				g.drawLine(x+i, y+46, x+i, y+66);
			}
			else if(blue[i/scale]==10){
				g.drawLine(x+i, y+46, x+i, y+56);
				g.setColor(Color.white);
				g.drawLine(x+i, y+56, x+i, y+66);
			}
			else if(blue[i/scale]==00){
				g.setColor(Color.white);
				g.drawLine(x+i, y+46, x+i, y+66);
			}
			
		}
	}
}

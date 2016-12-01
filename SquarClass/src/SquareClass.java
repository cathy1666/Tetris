import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
//SquareClass inherit JComponent as the playing space component
public class SquareClass extends JComponent
{
	private int x, y,//x, y is preset block situation
				dir, Class;
	private int height, weight;
	private Image[] images;
	private Image imBG,imBG2;
	private int score;
	//mArray is the background of playing space
	//1,2 is present square
	//3 is stop square(obstacle)
	private char [][]mArray;
	//define each direction of seven kind square
	//each one is a 5*5 block
	private char square[][][][]=
	{
	{//[]
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,2,1,0},
	{0,0,1,1,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,1,2,0},
	{0,0,1,1,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,1,1,0},
	{0,0,2,1,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,1,1,0},
	{0,0,1,2,0},
	{0,0,0,0,0}
	}
	},
	{//T
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,1,2,1,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,1,2,0,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,1,2,1,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,0,2,1,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	}
	},
	{//L
	{
	{0,0,1,0,0},
	{0,0,1,0,0},
	{0,0,2,1,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{1,1,2,0,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,1,2,0,0},
	{0,0,1,0,0},
	{0,0,1,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,2,1,1},
	{0,0,1,0,0},
	{0,0,0,0,0}
	}
	},
	{//I
	{
	{0,0,1,0,0},
	{0,0,1,0,0},
	{0,0,2,0,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{1,1,2,1,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,0,2,0,0},
	{0,0,1,0,0},
	{0,0,1,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,1,2,1,1},
	{0,0,0,0,0},
	{0,0,0,0,0}
	}
	},
	{//Z
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,1,2,0,0},
	{0,0,1,1,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,1,0},
	{0,0,2,1,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,1,1,0,0},
	{0,0,2,1,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,1,2,0,0},
	{0,1,0,0,0},
	{0,0,0,0,0}
	}
	},
	{//Lt
	{
	{0,0,1,0,0},
	{0,0,1,0,0},
	{0,1,2,0,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{1,1,2,0,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,2,1,0},
	{0,0,1,0,0},
	{0,0,1,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,0,2,1,1},
	{0,0,0,0,0},
	{0,0,0,0,0}}
	},
	{//Zt
	{
	{0,0,0,0,0},
	{0,0,0,0,0},
	{0,0,2,1,0},
	{0,1,1,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,0,0},
	{0,0,2,1,0},
	{0,0,0,1,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,0,1,1,0},
	{0,1,2,0,0},
	{0,0,0,0,0},
	{0,0,0,0,0}
	},
	{
	{0,0,0,0,0},
	{0,1,0,0,0},
	{0,1,2,0,0},
	{0,0,1,0,0},
	{0,0,0,0,0}
	}
	}
	};
	//initial
	SquareClass()
	{
		height = 25;
		weight = 10;
		x = 0;
		y = 0;
		dir = 0;
		Class = 0;	
		score = 0;		
		mArray = new char [height][weight];
		//set size of the playing space component(SquareClass)
		setSize(350,600);
		//get square icon
		images = new Image[8];
        for(int i = 0; i < images.length; i++)
            images[i] = (new ImageIcon(SquareClass.class.getResource((i+1) + ".jpg"))).getImage();
        //get background icon
        imBG = (new ImageIcon(SquareClass.class.getResource("background.jpg"))).getImage();
        imBG2 = (new ImageIcon(SquareClass.class.getResource("background2.jpg"))).getImage();
	}
	//when game restart, call this function
	public void reset() 
	{
		x = 0;
		y = 0;
		int rn = (int)(Math.random()*100); 
		dir = rn % 4;
		Class = rn % 7;
		score = 0;
		//clear the playing space
		for(int i = 0; i < height; i++)
			for(int j = 0; j < weight; j++)
				mArray[i][j] = 0;		
	}
	//create a new square on the top of playing space 
	public void newSquare()
	{		
		y = 0;
		int rn = (int)(Math.random()*100); 
		x = rn%5;
		dir = rn % 4;
		Class = rn % 7;
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				if((i+y) >= 0 && (i+y) < height && (j+x) >= 0 && (j+x) < weight)
					if(mArray[y+i][x+j] != 0)
							mArray[i+y][j+x] = square[Class][dir][i][j];
	}
	//test can the present square move to the appointed situation (XStep, YStep)?
	public boolean squareStop(int XStep, int YStep)
	{
		int i, j;
		for(i = 0 ; i < 5 ; i++){
			for(j = 0 ; j < 5 ; j++){
				if(square[Class][dir][i][j] != 0)
				{
					if((y + i + YStep) < 0 || (y + i + YStep) >= height)
						return true;					
					if((x + j + XStep) < 0 || (x + j + XStep) >= weight)
						return true;
					if(mArray[y + i + YStep][x + j + XStep] == 3)
						return true;
				}
			}
		}
	    return false;	    
	}
	//if squareStop()doesn't have argument, it is used to test can the present spin?
	public boolean squareStop()
	{
		int i, j, nextdir;
		nextdir = (dir+1)%4 ;
		for(i = 0 ; i < 5 ; i++)
			for(j = 0 ; j < 5 ; j++)
				if(square[Class][nextdir][i][j] != 0)
				{
					if((y + i) < 0 || (y + i) >= height)
						return true;
					if((x + j) < 0 || (x + j) >= weight)
						return true;
					if(mArray[y + i][x + j] == 3)
						return true;
				}
	    return false;	    
	}
	//set the situation of moved present square
	public void setSquare(char choise)
	{
		boolean stop = false;
		switch(choise)
		{
			case 'x'://move right
				if(!(stop = squareStop(1, 0))) //call squareStop() function to test
					x++;
				break;
			case 'z'://move left
				if(!(stop = squareStop(-1, 0))) //call squareStop() function to test
					x--;
				break;
			case 's'://spin
				if(!(stop = squareStop()))//call squareStop() function to test
				{ 
					dir = (dir+1)%4;		
				}
				break;
		}
		if(!stop)
		{
			//clear previous square
			for(int i = 0; i < height; i++)
				for(int j = 0; j < weight; j++)
					if(mArray[i][j] != 3)
						mArray[i][j] = 0;
			//write present square to the background mAarry
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					if((i+y) >=0 && (i+y) < height && (j+x) >= 0 && (j+x) < weight)
						if(mArray[y+i][x+j] == 0)
							mArray[i+y][j+x] = square[Class][dir][i][j];
		}
	}
	//if setSquare()doesn't have argument, 
	//it is used to set the situation of moved present square when move down
	public void setSquare()
	{
		if(!squareStop(0, 1))
		{
			y++;
			//clear previous square
			for(int i = 0; i < height; i++)
				for(int j = 0; j < weight; j++)
					if(mArray[i][j] != 3)
						mArray[i][j] = 0;
			//write present square to the background mAarry
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					if((i+y) >= 0 && (i+y) < height && (j+x) >= 0 && (j+x) < weight)
						if(mArray[y+i][x+j] == 0)
							mArray[y + i][x + j] = square[Class][dir][i][j];
		}
		else//if square stop 
		{
			//write present square to background as obstacle
			setBackGround();
			//create a new square
			newSquare();			
		}
	}	
	//test if it is game over
	//when there are any obstacle at first row (top) of playing space, game over
	public boolean IsGameover()
	{
		for(int i = 0; i < weight; i++)
		{
			if(mArray[5][i] == 3)
			{
				//create a message dialog and show score
				JOptionPane.showMessageDialog(null,
            			"GAME OVER!!\nYor score is " + getScore());
				return true;
			}
		}
		return false;
	}
	//write the stop square to background as obstacle(mArray = 3)
	private void setBackGround()
	{
		int i,j;
		for(i = 0; i < 5; i++)
			for(j = 0; j < 5; j++)
				if(square[Class][dir][i][j] > 0)
					if((i+y) >= 0 && (i+y) < height && (j+x) >= 0 && (j+x) < weight)
						mArray[i+y][j+x] = 3;
	}
	//when stop square fill a row, remove it and get score
	public void removeRow()
	{
		boolean remove;
		for(int i = height-1; i > 0; i--)
		{
			remove = true;
			for(int j = 0; j < weight; j++)
			{				
				if(mArray[i][j] != 3)
				{
					remove = false;
					break;					
				}
			}
			if(remove)
			{
				score++;
				for(int j = i; j > 0; j--)//從消去的那一列開始，每列都下移一格
					mArray[j] = mArray[j-1];
				for(int j = 0; j < weight; j++)//新的第一列設為0
					mArray[0][j] = 0;
				dispaly();
			}				
		}
	}
	//get present score
	public int getScore()
	{
		return score;
	}
	public void dispaly()
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < weight; j++)
			{
				if(mArray[i][j] == 0)
				{
					System.out.print("*");
				}
				else if(mArray[i][j] == 1)
				{
					System.out.print("A");
				}
				else if(mArray[i][j] == 2)
				{
					System.out.print("A");
				}
				else if(mArray[i][j] == 3)
				{
					System.out.print("X");
				}				
			}
			System.out.println("");
		}
	}
	//draw present block
	private void drawBlock(Graphics g, Image image, int x, int y) 
	{
        g.drawImage(image, x*25, y*25, 25, 25, this);
    }
	//draw the background
	public void paint(Graphics g) 
	{
        // clean previous screen
        g.setColor(Color.white);
        g.fillRect(0, 0,250, 500);
        if(score>=1)
        g.drawImage(imBG, 0, 0,250, 500, this);
        else
        g.drawImage(imBG2, 0, 0,250, 500, this);
        // draw stack of pieces
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) 
            {
            	//every kind of square has different color 
            	if(mArray[i+5][j] == 1 )
            		drawBlock(g, images[Class], j, i);
            	else if(mArray[i+5][j] == 2)
            		drawBlock(g, images[Class], j, i);
            	//when square stop(became obstacle) it will become the same color
            	else if(mArray[i+5][j]==3)
            		drawBlock(g, images[7], j, i);
            }
        }
    }
}

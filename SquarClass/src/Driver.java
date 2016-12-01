import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.applet.Applet;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
@SuppressWarnings("serial")
public class Driver extends JFrame 
{	
	private SquareClass squareClass;
	private JLabel scoreLabel;
	private JTextField keyin;
	private JButton newGameBtn, pauseBtn;
	
    private boolean isGameover = false;	
	private boolean isRestart = true;
    private boolean isPause;
    
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 600;
	
	private Thread gameThread;

	public Driver()
	{
		//super("Tetrix");// Set the title bar text.
		squareClass = new SquareClass();

		// setup UI Component
        setupUIComponent();  
        
		// setup event listener
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    keyin.addKeyListener(new UserInputListener());
	    newGameBtn.addActionListener(new newGameBtnListener());        
        pauseBtn.addActionListener(new pauseBtnListener());          
	}
	public void setupUIComponent() 
	{		
		// set size of the content pane.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// set panels' situation by program
		setLayout(null);
        
		// set score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setSize(120, 80);
        scorePanel.setLocation(2, 50);
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setBorder(BorderFactory.createTitledBorder(""));
        scorePanel.add(new JLabel("¡@Score¡@"));
        scorePanel.add(scoreLabel = new JLabel(squareClass.getScore()+""));
        add(scorePanel);// Add to the content pane.
        
        // set button panel
        JPanel btnPanel = new JPanel();
        btnPanel.setSize(120, 80);
        btnPanel.setLocation(2, 150);  
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBorder(BorderFactory.createTitledBorder(""));
        newGameBtn = new JButton("New Game");
        newGameBtn.setSize(100, 30);
        pauseBtn = new JButton("Pause");
        pauseBtn.setSize(100, 30);        
        btnPanel.add(newGameBtn);
        btnPanel.add(pauseBtn);        
        add(btnPanel);// Add to the content pane.
        
        // set square component panel
        JPanel squareComponentPanel = new JPanel();
        squareComponentPanel.setLayout(null); 
        squareComponentPanel.setBorder(BorderFactory.createTitledBorder(""));
        squareComponentPanel.setSize(250 , 500);
        squareComponentPanel.setLocation(130, 30); 
        squareComponentPanel.add(squareClass);  
        //squareClass.setLocation(10, 10);              
        add(squareComponentPanel);// Add to the content pane.
        
        keyin = new JTextField(); 
        keyin.setEditable(false);// hide the keyin TextField
        keyin.setLocation(130, 10);
        add(keyin);// Add to the content pane.
        
        setVisible(true);// Display the window.
    }
	// Key Listener
	private class UserInputListener implements KeyListener
	{
		public void keyPressed(KeyEvent e) 
		{
			if(isRestart || squareClass.IsGameover() || isPause)
				return;		                    
		    switch(e.getKeyCode())
		    {
               	case KeyEvent.VK_RIGHT:// press ¡u¡÷¡v
                      	squareClass.setSquare('x');
                        break;
                case KeyEvent.VK_LEFT:// press ¡u¡ö¡v
                	    squareClass.setSquare('z');                               
                        break;
                case KeyEvent.VK_UP:// press ¡u¡ô¡v
                	    squareClass.setSquare('s');
                        break;
                case KeyEvent.VK_DOWN:// press ¡u¡õ¡v
                	    squareClass.setSquare();
                        break;
		    }
		    // every time after user control, it will repaint 
		    squareClass.repaint();
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}
	//act when "Pause" button press
	private class pauseBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
            isPause = true;
            JOptionPane.showMessageDialog(null,"Game Pause");
            keyin.requestFocus();                
            isPause = false;                
            gameThread.interrupt();
        }
	}
	//act when "New Game" button press
	private class newGameBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
            isRestart = true;
            keyin.requestFocus();
            
            // wait for 1 seconds to end the previous game
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex) {
                ex.printStackTrace();
            }
            
            gameThread = new Thread(new Runnable() {
                public void run() {
                    while(!squareClass.IsGameover() && !isRestart) 
                    {
                        if(!isPause) 
                        {
                            try 
                            {                            	
                            	squareClass.setSquare();//keep moving down
                            	squareClass.dispaly();	                            	
                            	squareClass.removeRow(); 
                            	scoreLabel.setText(squareClass.getScore()+"");
                            	
                    			isGameover = squareClass.IsGameover();                                                            
                                Thread.sleep(500);//set delay time
                            }
                            catch(InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            squareClass.repaint(); // After every move, it will repaint 
                        }
                        else // when "Pause" Button clicked
                        {
                            try{ 
                                synchronized(this){wait();}
                            }
                            catch(InterruptedException ex) {}
                        }/*
                        if(isGameover)
                        {
                        	JOptionPane.showMessageDialog(null,
                        			"GAME OVER!!\nYor score is " + squareClass.getScore());
                        }*/
                    }
                }
            });                        
            scoreLabel.setText(squareClass.getScore()+"");
            squareClass.reset();
            
            isRestart = false;
            gameThread.start();
        }
	}
	public static void main(String[] args) throws IOException
	{
		new Driver();
	}
}

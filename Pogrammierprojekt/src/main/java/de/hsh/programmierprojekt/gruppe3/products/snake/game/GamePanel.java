package de.hsh.programmierprojekt.gruppe3.products.snake.game;
import de.hsh.programmierprojekt.gruppe3.products.snake.util.Sound;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



import java.util.Random;


public class GamePanel extends JPanel implements ActionListener{

    
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; // wie groß sind die Elemente
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75; // Geschwendigkeit des Spiels
    final int x[] = new int [GAME_UNITS];
    final int y[] = new int [GAME_UNITS];
    int bodyParts = 6;
    int Apple1;
    int AppleX;
    int AppleY;
    char direction = 'R';
    boolean running = false; 
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    
    public void startGame() {        
            newApple();
            running = true;
            timer = new Timer(DELAY,this);
            timer.start();        
            Sound.playSound2();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        
    }
    
    public void draw(Graphics g) {
        if(running) {
            g.setColor(Color.black);
            // quadrate im Background
            for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            
            
            g.setColor(Color.red);
            g.fillOval(AppleX, AppleY, UNIT_SIZE, UNIT_SIZE); //wie groß sind die Äpfel
            
            for(int i=0; i< bodyParts; i++) {
                if(i==0) {
                    g.setColor(Color.black);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45,180,0));
                    
                    // 
                    if(Apple1 > 10) {
                        g.setColor(Color.black);;  
                    }
                    
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);;
                }
            }
            
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+Apple1, (SCREEN_WIDTH - 
                    metrics.stringWidth("Score: "+Apple1))/2,
                    g.getFont().getSize());
            
        }
        else {
            gameOver(g);
        }
    }
    
    public void newApple() {
        AppleX = random.nextInt((SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        AppleY = random.nextInt((SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        
    }
    
    public void move() {
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
        case 'U' :
            y[0] = y[0] - UNIT_SIZE;
            break;
        case 'D' :
            y[0] = y[0] + UNIT_SIZE;
            break;
        case 'L' :
            x[0] = x[0] - UNIT_SIZE;
            break;
        case 'R' :
            x[0] = x[0] + UNIT_SIZE;
            break;
        }
    }
    
    public void pcr() { // check Apple 1
        if((x[0] == AppleX) && (y[0] == AppleY)) {
            bodyParts++;
            Apple1++;
            newApple();
            Sound.playSound1();
        }
        
    }
    
    public void checkCollisions() {
        //checks if head collides with body
        for(int i = bodyParts; i>0; i--) {
            if((x[0] == x[i])&& (y[0] ==y[i])) {
                running = false ;
            }
        }
        //check if head touches left border
        if(x[0] < 0) {
            running = false;
        }
      //check if head touches right border
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
      //check if head touches top border 
        if(y[0] < 0) {
            running = false;
        }
      //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT) {
            running = false;  
        }
        if(!running) {
            timer.stop();
        }
    }
    
    public void gameOver(Graphics g) {
        
   
        // Game over menu
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH -
                metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        int choose = JOptionPane.showConfirmDialog(null, "Press Yes to Replay");
        
        if(choose == 0) {
        // Replay the game
        new GameFrame();            
        } else if (choose == 1) {
        JOptionPane.showMessageDialog(null, "Bye Bye ;)");
    }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(running) {
            move();
            pcr();
            checkCollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(direction != 'R') {
                    direction = 'L';
                }
                break;   
                
            case KeyEvent.VK_RIGHT:
                if(direction != 'L') {
                    direction = 'R';
                }
                break;  
                
            case KeyEvent.VK_UP:
                if(direction != 'D') {
                    direction = 'U';
                }
                break;   
                
            case KeyEvent.VK_DOWN:
                if(direction != 'U') {
                    direction = 'D';
                }
                break;  
            }
        }
    }
}

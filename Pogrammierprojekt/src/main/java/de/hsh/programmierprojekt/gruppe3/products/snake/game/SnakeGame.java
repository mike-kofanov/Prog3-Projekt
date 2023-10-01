package de.hsh.programmierprojekt.gruppe3.products.snake.game;
import javax.swing.JOptionPane;

public class SnakeGame{
    
    public static void main(String[] args) {

        int choose = JOptionPane.showConfirmDialog(null, "Press 'Yes' to Continue");
        
        if(choose == 0) {
            // Start the game
            new GameFrame();            
        } else if (choose == 1) {
            JOptionPane.showMessageDialog(null, "Bye Bye ;)");
        }
    }

}
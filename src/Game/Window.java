package Game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    MainMenuPanel mainMenu;
    GamePanel gamePanel;
    Image windowIcon; 

    public Window() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
    	
    	gamePanel = new GamePanel();
    	mainMenu = new MainMenuPanel();
        windowIcon = new ImageIcon("emblempic.png").getImage();
       
    	
        
        
        
        
        
        
        this.setIconImage(windowIcon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(gamePanel);
        
        
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
        
        	
        	
        
        
        
        //gamePanel();
        
        
        
    }

    

}

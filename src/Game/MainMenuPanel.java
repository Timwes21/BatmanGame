package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel implements KeyListener {

    JLabel mainMenu = new JLabel("Main Menu");
    JLabel pressAnywhere = new JLabel("Press Anywhere to continue");
    int WIDTH = 1000;
    int HEIGHT = 500;
    Image BG = new ImageIcon("altpic.png").getImage();
    Font customFont;
    public boolean visible = true;
    
    
    MainMenuPanel() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
    	File audioFile = new File("batman-theme-intense-part-made-with-Voicemod.mp3");
    	GamePanel gamePanel = new GamePanel();
    	
    	
    	JButton start = new JButton("Start");
    	start.setContentAreaFilled(false);
    	
    	start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action when button is clicked
                System.out.println("Clear button clicked!");
                setVisible(false);
            }
        });
    	
    	
    	

        setFocusable(true);
    	addKeyListener(this);
    	
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        
        
        
        this.add(start);


    }

    
    public Font loadFont(String fontName, float fontSize) {
        try (InputStream is = getClass().getResourceAsStream(fontName)) {
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int)fontSize); // Fallback font
        }
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	Font batFont = loadFont("GothamNightsBold-Wrp4.ttf", 24);
    	Font batFont2 = loadFont("Andes.ttf", 60);
    	
    	g.drawImage(BG, 0, 0, WIDTH, HEIGHT, this);
    	
    	g.setFont(batFont2);
    	g.setColor(Color.white);
    	g.drawString("The Batman", (WIDTH/2)-120, HEIGHT/2);
    	
    	g.setFont(batFont);
    	g.drawString("Press Anywhere to Continue", WIDTH/2-100, HEIGHT/2+50);
    }

	
	@SuppressWarnings("deprecation")
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}


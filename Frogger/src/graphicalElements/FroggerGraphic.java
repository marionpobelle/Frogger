package graphicalElements;

import javax.swing.*;

import gameCommons.IFrog;
import util.Direction;
import jaco.mp3.player.MP3Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.io.File;

@SuppressWarnings("serial")
public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 16;
	@SuppressWarnings("unused")
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private MP3Player player;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();
		
		//Change l'icone de la fenetre de jeu
		Image icon = new ImageIcon("src/frogger/icon.png").getImage();
		
		//Met en place la musique 
		this.player = new MP3Player();
		this.player.setRepeat(true);
		this.player.addToPlayList(new File("src/frogger/dejavu8bit.mp3"));
		this.player.play();
		
		//Défini la couleur du fond du jeu
		setBackground(new Color(0,0,0,255));
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		//Crée la fenêtre de jeu
		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
	}
	
	//Affiche les éléments
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			//g.setColor(e.color);
			//g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
			g.drawImage(e.image, pixelByCase*e.absc, pixelByCase*(height-1-e.ord),this);
		}
	}

	
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

    /**
     * Enlève tous les éléments actuellement affichés
     */
	public void clear() {
		this.elementsToDisplay.clear();
	}

	/**
	 * Ajoute l'élément aux éléments à afficher
	 * @param e
	 */
	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	 /**
     * Lie la grenouille à l'environneemnt graphique
     * @param frog
     */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
	public void endGameScreen(String s, String s2, String s3) {
		frame.remove(this);
		
		//Met en place la musique de fin
		this.player.removeAll();
		this.player.addToPlayList(new File("src/frogger/sadtrombone.mp3"));
		this.player.setRepeat(false);
		this.player.skipForward();
		this.player.play();
		
		//Nous avons changé les affichages pour que le score le temps et le texte s'envoie joliment
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 50));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		label.setForeground(new Color(54,212,86,255));
		JLabel label2 = new JLabel(s2);
		label2.setFont(new Font("TimesRoman", 1, 35));
		label2.setSize(this.getSize());
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setVerticalAlignment(SwingConstants.BOTTOM);
		label2.setForeground(new Color(153,222,118,255));
		JLabel label3 = new JLabel(s3);
		label3.setFont(new Font("TimesRoman", 1, 35));
		label3.setSize(this.getSize());
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setVerticalAlignment(SwingConstants.TOP);
		label3.setForeground(new Color(118,222,146,255));
		
		
		frame.getContentPane().add(label);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label3);
		frame.repaint();

	}

	@Override
	/**
	 * Renvoie le nombre de pixel par case
	 * @return
	 */
	public int getPixelsByCase() {
		return this.pixelByCase;
	}

}

package environment;

//import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	//private final Color colorLtR = Color.RED;
	//private final Color colorRtL = Color.WHITE;
	private Image image;
	
	/**
	 * Contructeur - initialise une voiture
	 * @param game2
	 * 					le jeu dans lequel seont les voitures
	 * @param position
	 * 					la position de la voiture
	 * @param direction
	 * 					si vrai - de gauche à droite, de droite à gauche si faux
	 */
	public Car(Game game2, Case position, boolean direction){
		this.game = game2;
		this.length = game.randomGen.nextInt(3)+1;
		this.leftToRight = direction;
		this.leftPosition = position;
		//Assigne la bonne image en fonction de la taille et de la direction de la voiture
		if(this.length == 3){
			if(leftToRight){
				this.image = new ImageIcon("src/frogger/truck1Right.png").getImage();
			}else {
				this.image = new ImageIcon("src/frogger/truck1Left.png").getImage();
			}
			this.image = this.image.getScaledInstance(game.getGraphic().getPixelsByCase()*3,game.getGraphic().getPixelsByCase(),Image.SCALE_REPLICATE);
		} else if (this.length == 2){
			if(leftToRight){
				this.image = new ImageIcon("src/frogger/car1Right.png").getImage();
			}else {
				this.image = new ImageIcon("src/frogger/car1Left.png").getImage();
			}
			this.image = this.image.getScaledInstance(game.getGraphic().getPixelsByCase()*2,game.getGraphic().getPixelsByCase(),Image.SCALE_REPLICATE);
		} else if (this.length == 1){
			if(leftToRight){
				this.image = new ImageIcon("src/frogger/car2Right.png").getImage();
			}else {
				this.image = new ImageIcon("src/frogger/car2Left.png").getImage();
			}
			this.image = this.image.getScaledInstance(game.getGraphic().getPixelsByCase(),game.getGraphic().getPixelsByCase(),Image.SCALE_REPLICATE);
		}

	}
	/**
	 * Renvoie l'image de la voiture
	 * @return
	 */
	public Image getImage(){
		return this.image;
	}
	
	/**
	 * Renvoie un tableau contenant toutes les cases couvertes par une voiture
	 * @return
	 */
	public ArrayList<Case> getCases(){
		ArrayList<Case> tab = new ArrayList<Case>();
		for(int i = 0; i<this.length; i++){
			Case a = new Case(this.leftPosition.absc+i , this.leftPosition.ord);
			tab.add(a);
		}
		return tab;
	}
	
	/**
	 * Déplace les voitures et les affiches si vrai, les affiches juste si faux
	 * @param bool		
	 * 				un booléen indicant si on déplace ou non les voitures
	 */
	public void move(boolean bool) {
		if(bool){
			Case k;
			if(this.leftToRight){
				k = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			} else {
				k = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			}
			this.leftPosition = k;
		}
		this.addToGraphics();
	}

	
	/**
	 * Indique si une voiture couvre une case donnée ou non
	 * @param pos
	 * 				Les coordonées de la case en question
	 * @return
	 */
	public boolean coverCase(Case pos) {
		for(int i=0; i<this.length; i++){
			if(this.leftPosition.absc + i == pos.absc){
				return true;
			}
		}
		return false;
	}

	/* Indique si la voiture se trouve ou non dans l'écran
	 */
	public boolean inScreen() {
		if(this.leftPosition.absc + this.length < 0 || this.leftPosition.absc > this.game.width){
			return false;
		}
		return true;
	}
	

	/**
	 * Change l'ordonnée d'une voiture
	 * @param n
	 * 			un entier correspondant au décalage d'ordonnée désiré
	 */
	public void changeCarOrd(int n){
		Case c = new Case(this.leftPosition.absc, this.leftPosition.ord + n);
		this.leftPosition = c;
	}
	



	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	//Nous avons modifié cette fonctions pour afficher nos images au lieu des couleurs
	private void addToGraphics() {
		//Pour la couleur simple
		/*for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
			.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}*/
		game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, this.image));
	}


}

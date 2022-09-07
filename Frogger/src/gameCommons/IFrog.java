package gameCommons;

import java.awt.Image;

import util.Direction;

public interface IFrog {
	


	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * Déplace la grenouille dans la direction donnée et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	/**
	 * Renvoie l'image de la grenouille
	 * @return
	 */
	public Image getImage();
	
	/**
	 * Change le statut de la grenouille pour indiquer que le jeu est fini
	 */
	public void getDead();
	
	/**
	 * Indique si la grenouille a fini ou si elle se joue encore
	 * @return
	 */
	public boolean getStatus();
	
	/**
	 * Change l'image de la grenouille
	 * @param i
	 * 			entier entre 1 et 4 (inclus) qui détermine l'image de la grenouille
	 */
	public void changeImage(int i);
	

}

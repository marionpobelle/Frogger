package frog;

import java.awt.Image;

import util.Direction;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;

public class InfFrog extends Frog implements IFrog {

	private int frogUpLim;
	private int frogDownLim;
	@SuppressWarnings("unused")
	private boolean isUpdated;

	/**
	 * Initialise la grenouille
	 * @param game
	 * 				le jeu dans lequel se trouve la grenouille
	 */
	public InfFrog(Game game) {
		super(game);
		this.frogDownLim = 3;
		this.frogUpLim = 6;
		this.isUpdated = false;

	}
	
	/**
	 * Renvoie la limite basse de l'écran
	 * @return
	 */
	public int getDownLim(){
		return this.frogDownLim;
	}
	
	/**
	 * Renvoie la limite haute de l'écran
	 * @return
	 */
	public int getUpLim(){
		return this.frogUpLim;
	}

	/*
	 * (non-Javadoc)
	 * @see frog.Frog#changeImage(int)
	 */
	public void changeImage(int i) {
		super.changeImage(i);
	}

	/*
	 * (non-Javadoc)
	 * @see frog.Frog#getStatus()
	 */
	public boolean getStatus(){
		return super.getStatus();
	}

	/*
	 * (non-Javadoc)
	 * @see frog.Frog#getDead()
	 */
	public void getDead(){
		super.getDead();
	}

	/*
	 * (non-Javadoc)
	 * @see frog.Frog#getImage()
	 */
	public Image getImage(){
		return super.getImage();
	}

	/**
	 * Indique si la grenouille se déplace vers le haut et dépasse ou non la limite haute
	 * @return
	 */
	public boolean goingUp(){
		return this.pos.ord>this.frogUpLim;
	}
	
	/**
	 * Indique si la grenouille se déplace vers le bas et dépasse ou non la limite basse
	 * @return
	 */
	public boolean goingDown(){
		return this.pos.ord<this.frogDownLim;
	}

	/**
	 * Change l'ordonée de la grenouille
	 * @param o
	 * 			la nouvelle ordonée
	 */
	public void setOrd(int o){
		this.pos = new Case(this.pos.absc, o);
	}

	/**
	 * Indique que la grenouille est arrivée à la limite haute
	 */
	public void doUpdate(){
		this.isUpdated = true;
	}

	//@Override
	/*
	 * (non-Javadoc)
	 * @see frog.Frog#getPosition()
	 */
	public Case getPosition() {
		return super.getPosition();
	}

	//@Override
	/*
	 * (non-Javadoc)
	 * @see frog.Frog#getDirection()
	 */
	public Direction getDirection() {
		return super.getDirection();
	}

	/*
	 * (non-Javadoc)
	 * @see frog.Frog#move(util.Direction)
	 */
	public void move(Direction key) {
		switch(key) {
		case up:
			changeImage(1);
			this.pos = new Case(this.pos.absc, this.pos.ord+1);
			this.game.updateScore(true);
			break;
		case down:
			if(this.pos.ord != 0){
				changeImage(3);
				this.pos = new Case(this.pos.absc, this.pos.ord-1);
			}
			this.game.updateScore(false);
			break;
		case right:
			if(this.pos.absc != this.game.width){
				changeImage(2);
				this.pos = new Case(this.pos.absc+1, this.pos.ord);
			}
			break;
		case left:
			if(this.pos.absc != 0){
				changeImage(4);
				this.pos = new Case(this.pos.absc-1, this.pos.ord);
			}
			break;
		default :
			break;
		}
		this.direct = key;
		this.game.testLose();
	}

}

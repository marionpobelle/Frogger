package frog;

import java.awt.Image;

import javax.swing.ImageIcon;

import util.Direction;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {
	
	protected Game game;
	protected Direction direct;
	private Image image;
	protected Case pos;
	private boolean dead;
	
	private Image upIm = new ImageIcon("src/frogger/frogUp.png").getImage();
	private Image downIm = new ImageIcon("src/frogger/frogDown.png").getImage();
	private Image leftIm = new ImageIcon("src/frogger/frogLeft.png").getImage();
	private Image rightIm = new ImageIcon("src/frogger/frogRight.png").getImage();
	
	
	/**
	 * Constructeur - initialise la grenouille
	 * @param game
	 * 				le jeu dans lequel se trouve la grenouille
	 */
	public Frog(Game game) {
		this.game = game;
		this.pos = new Case(game.width/2, 0);
		this.direct = Direction.up;
		this.dead = false;
		upIm = upIm.getScaledInstance(game.getGraphic().getPixelsByCase(), game.getGraphic().getPixelsByCase(), Image.SCALE_REPLICATE);
		downIm = downIm.getScaledInstance(game.getGraphic().getPixelsByCase(), game.getGraphic().getPixelsByCase(), Image.SCALE_REPLICATE);
		leftIm = leftIm.getScaledInstance(game.getGraphic().getPixelsByCase(), game.getGraphic().getPixelsByCase(), Image.SCALE_REPLICATE);
		rightIm = rightIm.getScaledInstance(game.getGraphic().getPixelsByCase(), game.getGraphic().getPixelsByCase(), Image.SCALE_REPLICATE);
		changeImage(1);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#changeImage(int)
	 */
	public void changeImage(int i) {
		switch (i){
		case 1:
			image = upIm;
			break;
		case 2:
			image = leftIm;
			break;
		case 3:
			image = downIm;
			break;
		case 4:
			image = rightIm;
			break;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#getStatus()
	 */
	public boolean getStatus(){
		return this.dead;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#getDead()
	 */
	public void getDead(){
		this.dead = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#getImage()
	 */
	public Image getImage(){
		return this.image;
	}

	//@Override
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#getPosition()
	 */
	public Case getPosition() {
		return this.pos;
	}
	
	//@Override
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#getDirection()
	 */
	public Direction getDirection() {
		return this.direct;
	}

	//@Override
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IFrog#move(util.Direction)
	 */
	public void move(Direction key) {
		switch(key) {
			case up:
				if(this.pos.ord < this.game.height-1){
					changeImage(1);
					this.pos = new Case(this.pos.absc, this.pos.ord+1);
				}
				break;
			case down:
				if(this.pos.ord > 0){
					changeImage(3);
					this.pos = new Case(this.pos.absc, this.pos.ord-1);
				}
				break;
			case right:
				if(this.pos.absc < this.game.width-1){
					changeImage(2);
					this.pos = new Case(this.pos.absc+1, this.pos.ord);
				}
				break;
			case left:
				if(this.pos.absc > 0){
					changeImage(4);
					this.pos = new Case(this.pos.absc-1, this.pos.ord);
				}
				break;
			default :
				break;
		}
		this.direct = key;
	}


}

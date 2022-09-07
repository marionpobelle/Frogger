package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	protected Game game;
	protected ArrayList<Lane> lanes;

	/**
	 * Constructeur - initialise l'environement
	 * @param game
	 * 				le jeu dans lequel on a cet environement
	 */
	public Environment(Game game){
		this.game = game;
		this.lanes = new ArrayList<Lane>();
		//On crée une lane à densité 0 comme ligne de départ
		Lane dep = new Lane(game, 0, 0);
		this.lanes.add(dep);
		//On crée les autres lanes
		for(int i = 1; i < this.game.height-1; i ++) {
			double dens = this.game.defaultDensity;
			Lane l = new Lane(game, i, dens);
			this.lanes.add(l);
		}
		//On crée une ligne d'arrivée sans voitres
		Lane arr = new Lane(game, this.game.height-1, 0);
		this.lanes.add(arr);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IEnvironment#isSafe(gameCommons.Case)
	 */
	public boolean isSafe(Case c) {
		return this.lanes.get(c.ord).isSafe(c);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameCommons.IEnvironment#getLanes()
	 */
	public ArrayList<Lane> getLanes(){
		return this.lanes;
	}

	/*
	 * (non-Javadoc)
	 * @see gameCommons.IEnvironment#isWinningPosition(gameCommons.Case)
	 */
	public boolean isWinningPosition(Case c) {
		if((this.game.getFrog().getPosition().ord == this.game.height-1) == true){
			this.game.getFrog().getDead();
			return true;
		} 
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see gameCommons.IEnvironment#update()
	 */
	public void update() {
		for(int i = 1; i < this.game.height-1; i ++) {
			this.lanes.get(i).update();
		}
	}
		

}
package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import frog.InfFrog;

public class InfEnvironment extends Environment implements IEnvironment{
	
	
	/**
	 * Constructeur - initialise l'environement infini
	 * @param game
	 */
	public InfEnvironment(Game game) {
		super(game);
		this.game = game;
		this.lanes = new ArrayList<Lane>();
		Lane dep = new Lane(game, 0, 0);
		this.lanes.add(dep);
		for(int i = 1; i < this.game.height; i ++) {
			double dens = this.game.defaultDensity;
			Lane l = new Lane(game, i, dens);
			this.lanes.add(l);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see environment.Environment#isSafe(gameCommons.Case)
	 */
	public boolean isSafe(Case posFrog) {
		for(Lane l : this.lanes){
			for(Car c : l.getCars()){
				for(Case ca : c.getCases()){
					if(ca.absc == posFrog.absc && ca.ord == posFrog.ord){
						return false;
					}
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see environment.Environment#getLanes()
	 */
	public ArrayList<Lane> getLanes(){
		return super.getLanes();
	}
	
	/*
	 * (non-Javadoc)
	 * @see environment.Environment#update()
	 */
	public void update() {
		if(((InfFrog) this.game.getFrog()).goingUp()){
			
			((InfFrog) this.game.getFrog()).setOrd(this.game.getFrog().getPosition().ord -1);
			((InfFrog) this.game.getFrog()).doUpdate();
			
			for(int l = 0; l<this.lanes.size(); l++){
				this.lanes.get(l).changeOrd(-1);
			}
			
			this.lanes.add(new Lane(this.game, this.game.height, this.game.defaultDensity));
		}
		if(((InfFrog) this.game.getFrog()).goingDown()){
			if(this.lanes.get(((InfFrog) this.game.getFrog()).getDownLim()).getOrd()<((InfFrog) this.game.getFrog()).getDownLim()){
				for(int l = 0; l<this.lanes.size(); l++){
					this.lanes.get(l).changeOrd(1);
				}
				((InfFrog) this.game.getFrog()).setOrd(this.game.getFrog().getPosition().ord +1);
			}
		}
		for(int l = 0; l<this.lanes.size(); l++){
			this.lanes.get(l).update();
		}
	}
}

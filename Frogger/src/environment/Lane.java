package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars;
	private int timer;
	private boolean leftToRight;
	private double density;

	/**
	 * Constructeur - initialise une lane
	 * @param game2
	 * 				le jeu dans lequel se trouve la lane
	 * @param ord
	 * 				l'ordonée de la lane
	 * @param densite
	 * 				la densité de voiture sur cette lane
	 */
	public Lane(Game game2, int ord, double densite){
		this.timer = 0;
		this.game = game2;
		this.ord = ord; 
		this.speed = this.game.randomGen.nextInt(this.game.minSpeedInTimerLoops) +1 ;
		this.density = densite;
		this.leftToRight = this.game.randomGen.nextBoolean();
		this.cars = new ArrayList<Car>();
		this.mayAddCar();
		for(int i = 1; i<game.width; i++){
			this.mayAddCar();
			this.moveCars(true);
		}
	}
	
	/**
	 * Renvoie un tableau contenant toutes les voitures de la lane
	 * @return
	 */
	public ArrayList<Car> getCars(){
		return this.cars;
	}

	/**
	 * Met à jour les voitures et les déplaces selon leur vitesse
	 */
	public void update() {
		this.timer++;
        if (this.timer <= this.speed) {
            this.moveCars(false);
        } else {
            this.moveCars(true);
            this.mayAddCar();
            this.timer = 0;
        }
	}
	
	/**
	 * Determine si une case est sécurisée ou non
	 * @param pos
	 * 				la case en question
	 * @return
	 */
	public boolean isSafe(Case pos){
		for(int i = 0; i<this.cars.size(); i++){
			if(this.cars.get(i).coverCase(pos)){
			return false;
			}
		}
		return true;
	}
	
	/**
	 * Renvoie l'ordonnée d'une lane
	 * @return
	 */
	public int getOrd(){
		return this.ord;
	}
	
	/**
	 * Déplace les voitures si le booléen est vrai, et retire les anciennes voitures
	 * @param bool
	 * 				un booléen déterminant si on déplace ou non les voitures
	 */
	private void moveCars(boolean bool){
		for(Car car : this.cars){
			car.move(bool);
		}
		this.removeOldCar();
	}
	
	/**
	 * Supprime les voitures qui sont hors de l'écran
	 */
	public void removeOldCar(){
		for(int i = 0; i<this.cars.size(); i++){
			if(!this.cars.get(i).inScreen()){
				this.cars.remove(this.cars.get(i));
			}
		}
	}
	
	/**
	 * Change l'ordonnée de toutes les voitures d'une lane
	 * @param n
	 * 			le décalage d'ordonnée souhaité
	 */
	public void changeOrd(int n){
		this.ord = this.ord + n;
		for (Car car :cars) {
			car.changeCarOrd(n);
		}
	}
	
	
	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}
	
	/**
	 * 
	 * @return
	 */
	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}

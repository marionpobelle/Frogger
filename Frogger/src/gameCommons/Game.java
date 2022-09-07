package gameCommons;

//import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public int score;
	public boolean infini;
	public int highscore;
	public long startTime;
	public long endTime;
	
	// Lien aux objets utilisés
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant déplacement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.score = 0;
		this.highscore = 0;
		this.startTime = System.nanoTime();
		this.endTime = 0;
		//A CHANGER SELON LE MODE
		this.infini = true;
	}
	
	public void updateScore(boolean b){
		if (b) this.score++;
		if (!b) this.score--;
		if(this.score > this.highscore) this.highscore = this.score;
	}

	/**
	 * Lie l'objet frog à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(!this.frog.getStatus())
		if(this.environment.isSafe(this.frog.getPosition())==false){
			this.endTime = System.nanoTime();
			if(!this.infini){
				this.graphic.endGameScreen("C'est perdu ! ", " ", " " ); 
			} else {
				int time = (int) ((this.endTime - this.startTime)/1000000000); 
				this.graphic.endGameScreen("C'est perdu !   ", "Score : " + this.highscore, "Temps : " + time + "s");
			}
			this.frog.getDead();
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée
	 */
	public boolean testWin() {
		if(!this.frog.getStatus()){
			if(this.environment.isWinningPosition(this.frog.getPosition())){
				this.endTime = System.nanoTime();
				int time = (int) ((this.endTime - this.startTime)/1000000000); 
				if(!this.infini){
					this.graphic.endGameScreen("C'est gagn� ! ", " ", "Temps : " + time + "s");
				} else {
					this.graphic.endGameScreen("C'est gagn� !", "Score : " + this.highscore, "Temps : " + time + "s");
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), this.frog.getImage()));
		testLose();
		testWin();
	}
	
	public IFrog getFrog(){
		return frog;
	}

}

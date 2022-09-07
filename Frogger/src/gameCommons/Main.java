package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import frog.Frog;
import environment.InfEnvironment;
import frog.InfFrog;
//import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 50;
		int height = 40;
		int tempo = 90;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.05;
		
		//Création de l'interface graphique
		final IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		final Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		
		//Initialisation du monde selon le mode de jeu choisi
		if(!game.infini){
			//Création et liason de la grenouille
			IFrog frog = new Frog(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Création et liaison de l’environnement
			IEnvironment env = new Environment(game);
			game.setEnvironment(env);
		} else {
			//Création et liason de la grenouille
			IFrog frog = new InfFrog(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Création et liaison de l’environnement
			IEnvironment env = new InfEnvironment(game);
			game.setEnvironment(env);
		}
		
				
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}

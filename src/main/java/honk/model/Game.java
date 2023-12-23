package honk.model;

import java.util.ArrayList;

public class Game {
	private final Tamagotchi tama;
	private final Maison house = new Maison();
	private final Chronometer chronometer = new Chronometer(1);
	
	/**
	 * Game gère tout ce qui concerne le jeu.
	 * Elle contiendra la maison dans laquelle le joueur évolue, et le Tamagotchi.
	 * @param typeTama String : nom du Tamagotchi choisi par le joueur.
	 * @throws Exception Au cas où typeTama n'est pas bon.
	 */
	public Game(String typeTama) throws Exception {
		switch (typeTama) {
			case "chat":
				tama = new Chat();
				break;
			case "chien":
				tama = new Chien();
				break;
			case "lapin":
				tama = new Lapin();
				break;
			case "robot":
				tama = new Robot();
				break;
			default:
				throw new Exception("Pas un Tamagotchi valide.");
		}
		/*
		gv = new gameView();
		
		gv.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goHaut();
			}
		});
		
		gv.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goBas();
			}
		});
		
		gv.left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goGauche();
			}
		});
		
		gv.right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goDroite();
			}
		});
		
		gv.mangerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.manger();
				chronometer.addTimeSkip(chronometer.toMillis(1));
			}
		});
		
		gv.dormirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.dormir();
				chronometer.addTimeSkip(chronometer.toMillis(8));
			}
		});
		
		gv.jouerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.jouer();
				chronometer.addTimeSkip(chronometer.toMillis(2));
			}
		});
		
		gv.sePromenerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.promenade();
				chronometer.addTimeSkip(chronometer.toMillis(3));
			}
		});
		
		gv.seLaverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.toilette();
				chronometer.addTimeSkip(chronometer.toMillis(0.75));
			}
		});*/
	}
	
	public void gamifyThis() throws InterruptedException {
		ArrayList<String> actionsPossibles;
		
		tama.printListeActions();
		
		while (!tama.isDead()) {
			Thread.sleep(100);
			tama.applyStatsTime(chronometer);
			/*
			gv.statsTama.setText(tama.toString());
			gv.chrono.setText(chronometer + "\nPièce actuelle : " + house.getPiece().getPiece());
			
			actionsPossibles = tama.getActionByPiece(house.getPiece().getPiece());
			gv.mangerButton.setVisible(false);
			gv.dormirButton.setVisible(false);
			gv.jouerButton.setVisible(false);
			gv.sePromenerButton.setVisible(false);
			gv.seLaverButton.setVisible(false);
			if (actionsPossibles != null) {
				for (String piece : actionsPossibles) {
					switch (piece) {
						case "manger":
							gv.mangerButton.setVisible(true);
							break;
						case "dormir":
							gv.dormirButton.setVisible(true);
							break;
						case "jouer":
							gv.jouerButton.setVisible(true);
							break;
						case "promenade":
							gv.sePromenerButton.setVisible(true);
							break;
						case "toilette":
							gv.seLaverButton.setVisible(true);
							break;
					}
				}
			}*/
		}
		/*
		gv.mangerButton.setVisible(false);
		gv.dormirButton.setVisible(false);
		gv.jouerButton.setVisible(false);
		gv.sePromenerButton.setVisible(false);
		gv.seLaverButton.setVisible(false);
		gv.GameOver.setVisible(true);*/
	}
}

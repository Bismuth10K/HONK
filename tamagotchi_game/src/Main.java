import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
	Maison house;
	Tamagotchi tama;
	Chronometer chronometer;
	private JPanel mainPanel;
	private JTextArea statsTama;
	private JTextArea chrono;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JPanel directionPanel;
	private JButton up;
	private JButton down;
	private JButton left;
	private JButton right;
	private JPanel actionPanel;
	private JButton mangerButton;
	private JButton dormirButton;
	private JButton jouerButton;
	private JButton sePromenerButton;
	private JButton seLaverButton;
	private JTextArea GameOver;
	
	public Main(Tamagotchi tama, Chronometer chronometer) {
		setContentPane(mainPanel);
		setTitle("Démo jouable");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.tama = tama;
		this.chronometer = chronometer;
		
		GameOver.setVisible(false);
		
		up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goHaut();
			}
		});
		
		down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goBas();
			}
		});
		
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goGauche();
			}
		});
		
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				house.goDroite();
			}
		});
		
		mangerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.manger();
				chronometer.addTimeSkip(chronometer.toMillis(1));
			}
		});
		
		dormirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.dormir();
				chronometer.addTimeSkip(chronometer.toMillis(8));
			}
		});
		
		jouerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.jouer();
				chronometer.addTimeSkip(chronometer.toMillis(2));
			}
		});
		
		sePromenerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.promenade();
				chronometer.addTimeSkip(chronometer.toMillis(3));
			}
		});
		
		seLaverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				tama.toilette();
				chronometer.addTimeSkip(chronometer.toMillis(0.75));
			}
		});
	}
	
	public static void main(String[] args) throws Exception {
		Chat cat = new Chat();
		Chronometer chronometer = new Chronometer(1000);
		Main main = new Main(cat, chronometer);
		ArrayList<String> actionsPossibles;
		
		
		cat.printListeActions();
		
		main.house = new Maison();
		while (!main.tama.isDead()) {
			Thread.sleep(200);
			cat.applyStatsTime(chronometer);
			main.setStatsTama();
			main.chrono.setText(chronometer + "\nPièce actuelle : " + main.house.getPiece().getPiece());
			
			actionsPossibles = cat.getActionByPiece(main.house.getPiece().getPiece());
			main.mangerButton.setVisible(false);
			main.dormirButton.setVisible(false);
			main.jouerButton.setVisible(false);
			main.sePromenerButton.setVisible(false);
			main.seLaverButton.setVisible(false);
			if (actionsPossibles != null) {
				for (String piece : actionsPossibles) {
					switch (piece) {
						case "manger":
							main.mangerButton.setVisible(true);
							break;
						case "dormir":
							main.dormirButton.setVisible(true);
							break;
						case "jouer":
							main.jouerButton.setVisible(true);
							break;
						case "promenade":
							main.sePromenerButton.setVisible(true);
							break;
						case "toilette":
							main.seLaverButton.setVisible(true);
							break;
					}
				}
			}
		}
		main.mangerButton.setVisible(false);
		main.dormirButton.setVisible(false);
		main.jouerButton.setVisible(false);
		main.sePromenerButton.setVisible(false);
		main.seLaverButton.setVisible(false);
		main.GameOver.setVisible(true);
	}
	
	public void setStatsTama() {
		statsTama.setText(tama.toString());
	}
}

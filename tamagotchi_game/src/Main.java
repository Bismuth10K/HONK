import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
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
	private JButton manger;
	private JPanel actionPanel;
	private JButton mangerButton;
	private JButton dormirButton;
	Maison house;
	Tamagotchi tama;
	
	public Main() {
		setContentPane(mainPanel);
		setTitle("Démo jouable");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		
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
	}
	
	public void setStatsTama(String tama) {
		statsTama.setText(tama);
	}
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		ArrayList<String> actionsPossibles;
		
		Chronometer chronometer = new Chronometer(1);
		
		Chat cat = new Chat();
		cat.printListeActions();
		
		main.house = new Maison();
		while (true) {
			Thread.sleep(50);
			cat.applyStatsTime(chronometer);
			main.setStatsTama(cat.toString());
			main.chrono.setText(chronometer.toString() + "\nPièce actuelle : " + main.house.getPiece().getPiece());
			
			actionsPossibles = cat.getActionByPiece(main.house.getPiece().getPiece());
			if(actionsPossibles != null) {
				for (String piece : actionsPossibles) {
				
				}
			}
		}
	}
}

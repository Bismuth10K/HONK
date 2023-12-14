import java.awt.*;
import java.util.Random;
import java.lang.Thread;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Launcher extends JFrame {
	public Launcher() {
		super("Tamagotchi test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		JPanel contentPane = (JPanel)this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JButton("Push"));
		contentPane.add(new JButton("CLIQUEZ !"));
	}
	
	public static void main(String[] args) throws Exception {
		// Apply a look'n feel
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Launcher launcher = new Launcher();
		launcher.setVisible(true);
		Chronometer chronometer = new Chronometer(15000);
		System.out.println(chronometer);
		
		Chat cat = new Chat();
		cat.printListeActions();
		Robot rob = new Robot();
		rob.printListeActions();
		
		Maison house = new Maison();
		System.out.println(house.getPiece().getPiece());
		Random random = new Random();
		for (int i = 0; i <= 1000; i++) {
			Thread.sleep(1000);
			System.out.println(chronometer);
			cat.applyStatsTime(chronometer);
			System.out.println(cat);
			
			for (String piece: cat.getActionByPiece(house.getPiece().getPiece())) {
			
			}
		}
		System.out.println(chronometer);
	}
}
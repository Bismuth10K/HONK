import javax.swing.*;

public class gameView extends JFrame {
	public JTextArea chrono;
	public JButton mangerButton;
	public JButton dormirButton;
	public JButton jouerButton;
	public JButton sePromenerButton;
	public JButton seLaverButton;
	public JTextArea GameOver;
	public JTextArea statsTama;
	public JButton up;
	public JButton down;
	public JButton left;
	public JButton right;
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JPanel directionPanel;
	private JPanel actionPanel;
	
	public gameView() {
		setContentPane(mainPanel);
		setTitle("Démo jouable");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		
		GameOver.setVisible(false);
	}
}

package TetrisCopy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

/*
Java Tetris game clone
Author: Jan Bodnar
Website: http://zetcode.com
 */
public class Tetris extends JFrame  {
	private static Font boldfont = new Font("Arial", Font.BOLD, 17);
	private static Font plaintFont = new Font("Arial", Font.PLAIN, 17);
	private static Font gameOverFont = new Font("SansSerif", Font.BOLD, 30);
//	private static Font gameOverFont = new Font("Arial", Font.BOLD, 30);
	private JLabel tatusbar;
	public JTextField tf,tf2;
	public  JLabel lines;
	JPanel rightPanel;
	JPanel missonPan, scorePan;
	JPanel p1,p2,p3,p4,p5;
	JButton startBtn;

	public Tetris() {
		super("OPP Project : MISSION TETRIS");
		//setFocusable(true);
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add("North", new JLabel(" "));
		add("South", new JLabel(" "));
		add("West", new JLabel("        "));
		

		rightPanel = new JPanel(new BorderLayout());

		scorePan = new JPanel(new GridLayout(12, 1, 0, 0));
		scorePan.add(new JLabel("                                           "));
		p1 = new JPanel();
		JLabel score = new JLabel("Score: ", JLabel.LEFT);
		score.setFont(boldfont);
		p1.add(score, BorderLayout.WEST);
		p1.add(new JLabel("            "), BorderLayout.CENTER);
		scorePan.add(p1);
		
		p2 = new JPanel();
		tf = new JTextField(7);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		tf.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf.setFont(plaintFont);
		tf.setText("0");
		tf.setForeground(Color.white);
		tf.setBackground(Color.black);
		p2.add(tf);
		scorePan.add(p2);

		p3 = new JPanel();
		lines = new JLabel("Lines: ", JLabel.LEFT);
		lines.setFont(boldfont);
		p3.add(lines, BorderLayout.WEST);
		p3.add(new JLabel("            "), BorderLayout.CENTER);
		scorePan.add(p3);
		
		p4 = new JPanel();
		tf2 = new JTextField(7);
		tf2.setHorizontalAlignment(JTextField.RIGHT);
		tf2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf2.setFont(plaintFont);
		tf2.setText("0");
		tf2.setForeground(Color.white);
		tf2.setBackground(Color.black);
		p4.add(tf2);
		scorePan.add(p4);

		p5 = new JPanel();
		startBtn = new JButton("Start");
		p5.add(startBtn);
		scorePan.add(p5);

		tatusbar = new JLabel(" 0");

		rightPanel.add(scorePan);
		rightPanel.add("South", tatusbar);
		add("East", rightPanel);

		var board = new Board(this);
		add("Center", board);
		board.start();

		setSize(380, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
	}

	JTextField getStatusBar() {

		return tf;
	}

	public static void main(String[] args) {

		//EventQueue.invokeLater(() -> {
			var game = new Tetris();
			game.setVisible(true);
		//});
	}
}

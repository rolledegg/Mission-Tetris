package Tetris;

import java.awt.*;
import java.awt.geom.*;
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
	private static Font boldFont = new Font("Arial", Font.BOLD, 20);
	private static Font boldSmallFont = new Font("Arial", Font.BOLD, 16);
	private static Font plaintFont = new Font("Arial", Font.PLAIN, 19);
	private static Font gameOverFont = new Font("SansSerif", Font.BOLD, 30);
//	private static Font gameOverFont = new Font("Arial", Font.BOLD, 30);
	private JLabel missionSuccess;
	public JTextField scoreTexField,lineTextField;
	public JPanel missionPan;
	public JLabel timeLabel, linesLabel;
	
	public Tetris() {
		super("OPP Project : MISSION TETRIS");
		//setFocusable(true);
		initUI();
	}

	private void initUI() {
		//Container c = getContentPane();
		setLayout(null);

		missionSuccess = new JLabel("Success 1 ", JLabel.LEFT);
		missionSuccess.setFont(boldFont);
		missionSuccess.setBounds(290,220,120,30);
		add(missionSuccess);
		
		JLabel score = new JLabel("Score: ", JLabel.LEFT);
		score.setFont(boldFont);
		score.setBounds(290,260,80,30);
		add(score);
		
		scoreTexField = new JTextField(7);
		scoreTexField.setHorizontalAlignment(JTextField.RIGHT);
		scoreTexField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scoreTexField.setFont(plaintFont);
		scoreTexField.setText("0");
		scoreTexField.setForeground(Color.white);
		scoreTexField.setBackground(Color.black);
		scoreTexField.setBounds(290,295,145,22);
		add(scoreTexField);

		JLabel lines = new JLabel("Lines: ", JLabel.LEFT);
		lines.setFont(boldFont);
		lines.setBounds(290,335,80,30);
		add(lines);
		
		lineTextField = new JTextField(7);
		lineTextField.setHorizontalAlignment(JTextField.RIGHT);
		lineTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lineTextField.setFont(plaintFont);
		lineTextField.setText("0");
		lineTextField.setForeground(Color.white);
		lineTextField.setBackground(Color.black);
		lineTextField.setBounds(290,370,145,22);
		add(lineTextField);
		
		var board = new Board(this);
		board.setBounds(30,70,230,460);
		add(board);
		board.start();

		//Thread mission = new Thread(new Mission(5,5));
		//mission.setBounds(70,220,100,100);
		//add(mission);

		missionPan= new JPanel();
		missionPan.setLayout(null);
		missionPan.setBounds(290,70,145,145);
		missionPan.setBackground(Color.LIGHT_GRAY);
		
		JLabel label1 = new JLabel("MISSION ", JLabel.LEFT);
		label1.setFont(boldFont);
		label1.setBounds(30,10,80,30);
		label1.setForeground(Color.red);
		missionPan.add(label1);
		
		JLabel label2 = new JLabel("Time ", JLabel.LEFT);
		label2.setFont(boldSmallFont);
		label2.setBounds(10,40,80,30);
		missionPan.add(label2);
		
		timeLabel = new JLabel("", JLabel.LEFT);
		timeLabel.setFont(boldSmallFont);
		timeLabel.setBounds(70,40,70,30);
		timeLabel.setForeground(Color.gray);
		missionPan.add(timeLabel);
		
		add(missionPan);
		
		setSize(480, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(Color.black);
		//g2.setStroke(new BasicStroke(2));
		//g2.fillRect(290, 70,145,145);
	}

	JTextField getStatusBar() {

		return lineTextField;
	}

	public static void main(String[] args) {

		//EventQueue.invokeLater(() -> {
			var game = new Tetris();
			game.setVisible(true);
		//});
	}
}

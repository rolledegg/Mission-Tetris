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
	private static Font missionFont = new Font("Arial", Font.BOLD, 24);
//	private static Font gameOverFont = new Font("Arial", Font.BOLD, 30);
	private JLabel missionSuccess;
	public JTextField scoreTextField,lineTextField;
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

		missionSuccess = new JLabel("Success 0 ", JLabel.LEFT);
		missionSuccess.setFont(boldFont);
		missionSuccess.setBounds(290,220,120,30);
		add(missionSuccess);
		
		JLabel score = new JLabel("Score: ", JLabel.LEFT);
		score.setFont(boldFont);
		score.setBounds(290,260,80,30);
		add(score);
		
		scoreTextField = new JTextField(7);
		scoreTextField.setHorizontalAlignment(JTextField.RIGHT);
		scoreTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scoreTextField.setFont(plaintFont);
		scoreTextField.setText("0");
		scoreTextField.setForeground(Color.white);
		scoreTextField.setBackground(Color.black);
		scoreTextField.setBounds(290,295,145,22);
		add(scoreTextField);

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

		missionPan= new JPanel();
		missionPan.setLayout(null);
		missionPan.setBounds(290,70,145,140);
		missionPan.setBackground(Color.LIGHT_GRAY);
		
		JLabel label1 = new JLabel("MISSION ", JLabel.LEFT);
		label1.setFont(missionFont);
		label1.setBounds(20,20,140,30);
		label1.setForeground(Color.red);
		missionPan.add(label1);
		
		JLabel label2 = new JLabel("Time ", JLabel.LEFT);
		label2.setFont(boldSmallFont);
		label2.setBounds(25,55,80,30);
		missionPan.add(label2);
		
		timeLabel = new JLabel("30", JLabel.CENTER);
		timeLabel.setFont(boldSmallFont);
		timeLabel.setBounds(67,56,70,30);
		timeLabel.setForeground(Color.gray.darker());
		missionPan.add(timeLabel);
		
		JLabel label3 = new JLabel("Lines ", JLabel.LEFT);
		label3.setFont(boldSmallFont);
		label3.setBounds(25,90,80,30);
		missionPan.add(label3);
		
		linesLabel = new JLabel("3 / 3", JLabel.CENTER);
		linesLabel.setFont(boldSmallFont);
		linesLabel.setBounds(67,90,70,30);
		linesLabel.setForeground(Color.gray.darker());
		missionPan.add(linesLabel);
		
		add(missionPan);
		
		var board = new Board(this);
		board.setBounds(30,70,230,460);
		add(board);
		board.start();
		
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

	JTextField getLineTextField() {
		return lineTextField;
	}
	
	JTextField getScoreTextField() {
		return scoreTextField;
	}
	
	JLabel getSuccessCount() {
		return missionSuccess;
	}
	
	JLabel getTimeLabel() {
		return timeLabel;
	}
	
	JLabel getLineLabel() {
		return linesLabel;
	}

	public static void main(String[] args) {

		//EventQueue.invokeLater(() -> {
			var game = new Tetris();
			game.setVisible(true);
		//});
	}
}

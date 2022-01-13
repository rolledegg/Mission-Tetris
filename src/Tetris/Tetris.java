package Tetris;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Tetris extends JFrame  {
	private static Font boldFont = new Font("Arial", Font.BOLD, 20);
	private static Font boldSmallFont = new Font("Arial", Font.BOLD, 16);
	private static Font boldMoreSmallFont = new Font("Arial", Font.BOLD, 12);
	private static Font plainFont = new Font("Arial", Font.PLAIN, 19);
	private static Font missionFont = new Font("Arial", Font.BOLD, 24);
	private JLabel missionSuccess;
	private JTextField scoreTextField,lineTextField;
	private JPanel missionPan; 
	private JLabel timeLabel, linesLabel;
	
	public Tetris() {
		super("OPP Project : MISSION TETRIS");
		initUI();
	}

	private void initUI() {
		Container c = getContentPane();
		c.setLayout(null);
		//set backgroundUI
		c.setBackground(new Color(40,40,80));
		
		ImageIcon back = new ImageIcon("block.png");
		JLabel background = new JLabel(back);
		background.setBounds(0,0,526, 623);
		add(background);
		
		ImageIcon title = new ImageIcon("title.png");
		JLabel titlel = new JLabel(title);
		titlel.setBounds(10,10,390, 372);
		add(titlel);
		
		//set Jlabel and JtextField in AbsoluteLayout
		missionSuccess = new JLabel("Success 0 ", JLabel.LEFT);
		missionSuccess.setFont(boldFont);
		missionSuccess.setForeground(Color.LIGHT_GRAY);
		missionSuccess.setBounds(322,246,120,30);
		add(missionSuccess);
		
		JLabel score = new JLabel("Score: ", JLabel.LEFT);
		score.setFont(boldFont);
		score.setForeground(Color.LIGHT_GRAY);
		score.setBounds(322,290,80,30);
		add(score);
		
		scoreTextField = new JTextField(7);
		scoreTextField.setHorizontalAlignment(JTextField.RIGHT);
		scoreTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scoreTextField.setFont(plainFont);
		scoreTextField.setText("0");
		scoreTextField.setEditable(false);
		scoreTextField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		scoreTextField.setForeground(Color.white);
		scoreTextField.setBackground(Color.black);
		scoreTextField.setBounds(322,325,143,26);
		add(scoreTextField);

		JLabel lines = new JLabel("Lines: ", JLabel.LEFT);
		lines.setFont(boldFont);
		lines.setBounds(322,365,80,30);
		lines.setForeground(Color.LIGHT_GRAY);
		add(lines);
		
		lineTextField = new JTextField(7);
		lineTextField.setHorizontalAlignment(JTextField.RIGHT);
		lineTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lineTextField.setFont(plainFont);
		lineTextField.setText("0");
		lineTextField.setEditable(false);
		lineTextField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		lineTextField.setForeground(Color.white);
		lineTextField.setBackground(Color.black);
		lineTextField.setBounds(322,400,143,26);
		add(lineTextField);
		
		JLabel l1 = new JLabel("ก็ / กๆ  :    Move", JLabel.RIGHT);
		l1.setFont(boldMoreSmallFont);
		l1.setForeground(Color.lightGray);
		l1.setBounds(365,455,100,30);
		add(l1);
		
		JLabel l2 = new JLabel("ก่  :  Rotate", JLabel.RIGHT);
		l2.setFont(boldMoreSmallFont);
		l2.setForeground(Color.lightGray);
		l2.setBounds(385,475,80,30);
		add(l2);
		
		JLabel l3 = new JLabel(" ก้  :   Down", JLabel.RIGHT);
		l3.setFont(boldMoreSmallFont);
		l3.setForeground(Color.lightGray);
		l3.setBounds(385,495,80,30);
		add(l3);
		
		JLabel l4 = new JLabel(" P  :  Pause", JLabel.RIGHT);
		l4.setFont(boldMoreSmallFont);
		l4.setForeground(Color.lightGray);
		l4.setBounds(385,515,80,30);
		add(l4);


		//mission panel UI
		missionPan= new JPanel();
		missionPan.setLayout(null);
		missionPan.setBounds(320,103,145,140);
		missionPan.setBackground(new Color(255,255,255));
		missionPan.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY));
		
		JLabel label1 = new JLabel("MISSION ", JLabel.LEFT);
		label1.setFont(missionFont);
		label1.setBounds(20,20,140,30);
		label1.setForeground(Color.red.darker());
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
		
		//tetris board
		var board = new Board(this);
		board.setBounds(45,101,230,482);
		board.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY));
		add(board);
		board.start();
		
		setSize(526, 650);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}
	
}

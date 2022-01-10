package Tetris;

import java.awt.*;
import javax.swing.*;

public class TetrisMain {
	static JFrame frame;
	static JPanel NorthPanel,CenterPanel, EastPanel;
	static JLabel t1,e,t2,r,i,s;

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("OOP Project : MISSION TETRIS");
		frame.setLayout(new BorderLayout());
		
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		EastPanel = new JPanel();
		
		t1 = new JLabel("T");
		t1.setBackground(Color.red);
		NorthPanel.add(t1);
		e = new JLabel("E");
		e.setBackground(Color.orange);
		NorthPanel.add(e);
		t2 = new JLabel("T");
		t2.setBackground(Color.yellow);
		NorthPanel.add(t2);
		
		CenterPanel.add("North",NorthPanel);
		frame.add("Center",CenterPanel);
		frame.add("East",EastPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}

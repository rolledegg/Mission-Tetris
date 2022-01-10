package Tetris;

import java.awt.Font;

import javax.swing.*;

public class Mission  implements Runnable {
	private static Font plaintFont = new Font("Arial", Font.PLAIN, 16);
	int time, lines;
	Tetris Parent;

	public Mission(Tetris parent,int time, int lines) {
		this.time = time;
		this.lines = lines;
		this.Parent = parent;
	}


	public void run() {
		Parent.timeLabel.setText(String.valueOf(time));
		int x= time;
		System.out.println("running"+x);
		for(int i = 0; i<x; i++) {
			System.out.println("running-"+time+","+x);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time--;
			Parent.timeLabel.setText(String.valueOf(time));
		}

	}

}

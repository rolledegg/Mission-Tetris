package Tetris;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Mission implements Runnable {
	private static Font plaintFont = new Font("Arial", Font.PLAIN, 16);
	int time, lines;
	Tetris parent;
	Board board;
	JLabel timeLabel, lineLabel;

	public Mission(Tetris parent, Board board, int time, int lines) {
		this.time = time;
		this.lines = lines;
		this.parent = parent;
		this.board = board;
		this.timeLabel = parent.getTimeLabel();
		this.lineLabel = parent.getLineLabel();
	}

	public void run() {
		timeLabel.setText(String.valueOf(time) + "s");
		lineLabel.setText("0 / " + String.valueOf(lines));

		int initTime = time;
		int initLines = board.getNumLinesRemoved();
		
		for (int i = 0; i < initTime; i++) {
			System.out.println("timer " + time + "/" + initTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time--;
			int gab = board.getNumLinesRemoved()-initLines;
			
			timeLabel.setText(String.valueOf(time) + "s");
			if(time<6) {
				timeLabel.setForeground(Color.red);
			}else {
				timeLabel.setForeground(Color.gray.darker());
			}
			lineLabel.setText(gab + " / " + String.valueOf(lines));
			
			// Mission Success
			if (lines <= gab) {
				board.setMissionSucces(true);
				break;
			}
			
			// Mission Fail
			if (time == 0) {
				board.setGameOver(true);
			}
			
			//Gameover before timeout
			if(board.getGameOver()) {
				break;
			}
		}
		System.out.println("break");

	}

}

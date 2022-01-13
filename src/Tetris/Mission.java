package Tetris;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Mission implements Runnable {
	private static Font plaintFont = new Font("Arial", Font.PLAIN, 16);
	private int missionTime, missionLines;
	private Tetris parent;
	private Board board;
	private JLabel timeLabel, lineLabel;

	public Mission(Tetris parent, Board board, int time, int lines) {
		this.missionTime = time;
		this.missionLines = lines;
		this.parent = parent;
		this.board = board;
		this.timeLabel = parent.getTimeLabel();
		this.lineLabel = parent.getLineLabel();
	}

	public void run() {
		//set mission
		timeLabel.setText(String.valueOf(missionTime) + "s");
		lineLabel.setText("0 / " + String.valueOf(missionLines));

		int initTime = missionTime;
		int initLines = board.getNumLinesRemoved();
		
		for (int i = 0; i < initTime; i++) {
			//timer
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			missionTime--;
			
			//text color turn red when 5sec left
			timeLabel.setText(String.valueOf(missionTime) + "s");
			if(missionTime<6) {
				timeLabel.setForeground(Color.red);
			}else {
				timeLabel.setForeground(Color.gray.darker());
			}
			
			int gab = board.getNumLinesRemoved()-initLines;
			lineLabel.setText(gab + " / " + String.valueOf(missionLines));
			
			// Mission Success
			if (missionLines <= gab) {
				board.setMissionSucces(true);
				break;
			}
			
			// Mission Fail
			if (missionTime == 0) {
				board.setGameOver(true);
			}
			
			//Gameover before timeout
			if(board.getGameOver()) {
				break;
			}
		}
	}

}

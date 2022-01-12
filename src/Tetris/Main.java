package Tetris;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			var game = new Tetris();
			game.setVisible(true);
		});
	}

}

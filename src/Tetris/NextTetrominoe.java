package Tetris;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.*;

import Tetris.Shape.Tetrominoe;

public class NextTetrominoe extends JPanel {
	Board board;
	Shape shape;
	
	NextTetrominoe(Board board, Shape shape){
		this.board = board;
		this.shape = shape;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		drawTTetrominoe(g);
	}
	
	private void drawTTetrominoe (Graphics g) {
		int squareHeigth = (int)getSize().getHeight()/5;
		int squareWidth =squareHeigth;
		if (shape.getShape() != Tetrominoe.NoShape) {
	
			for (int i = 0; i < 4; i++) {
				//(50,50 ½ÃÀÛÁ¡)
				int x = 2+shape.x(i);
				int y = 2+shape.y(i);

				board.drawSquare(g, x * squareWidth, 0 + (5- y - 1) * squareHeigth,
						shape.getShape());

			}
		}
	}
}

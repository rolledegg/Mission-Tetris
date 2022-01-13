package Tetris;

import javax.swing.*;

import Tetris.Shape.Tetrominoe;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JPanel {

	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 21;
	private final int PERIOD_INTERVAL = 300;

	private DropTimer timer;
	private boolean isFallingFinished = false;
	private boolean isGameOver = false;
	private boolean isPaused = false;
	private boolean ableRotate = true;
	private boolean isMissionSucces = false;
	private boolean isStuck = false;
	private int missionSuccesCount = 0;
	private int numLinesRemoved = 0;
	private int curX = 0;
	private int curY = 0;
	private int score = 0;
	private Shape curPiece;

	private Tetris parent;
	private JTextField lineTextField, scoreTextField;
	private JLabel successCountLabel;

	private Tetrominoe[] board;

	private static Font gameOverFont = new Font("SansSerif", Font.BOLD, 33);

	public Board(Tetris parent) {
		this.parent = parent;

		initBoard(parent);
	}


	private void initBoard(Tetris parent) {
		this.requestFocus();
		setFocusable(true);
		setBackground(Color.black);
		lineTextField = parent.getLineTextField();
		scoreTextField = parent.getScoreTextField();
		successCountLabel = parent.getSuccessCount();

		addKeyListener(new TAdapter());
	}

	public int getNumLinesRemoved() {
		return numLinesRemoved;
	}

	public boolean getGameOver() {
		return isGameOver;
	}

	public Shape getCurPiece() {
		return curPiece;
	}

	public void setGameOver(boolean bool) {
		isGameOver = bool;
	}

	public void setMissionSucces(boolean bool) {
		isMissionSucces = bool;
	}
	
	
	private int squareWidth() {

		return (int) getSize().getWidth() / BOARD_WIDTH;
	}

	private int squareHeight() {

		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}

	private Tetrominoe shapeAt(int x, int y) {

		return board[(y * BOARD_WIDTH) + x];
	}

	void start() {
		curPiece = new Shape();
		board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];

		clearBoard();
		newPiece();

		timer = new DropTimer(this, PERIOD_INTERVAL);
		timer.start();

		Thread mission = new Thread(new Mission(parent, this, 30, 1));
		mission.start();
	}

	private void pause() {
		isPaused = !isPaused;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);

		if (isGameOver == true) {
			drawGameOver(g);
		}

	}

	private void doDrawing(Graphics g) {
		// draw past Tetrominoe
		var size = getSize();
		int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

		for (int i = 0; i < BOARD_HEIGHT; i++) {

			for (int j = 0; j < BOARD_WIDTH; j++) {

				Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);

				if (shape != Tetrominoe.NoShape) {

					drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
				}
			}
		}

		// draw falling Tetrominoe
		if (curPiece.getShape() != Tetrominoe.NoShape) {
			// Randomly rotate StuckShape 1~4 time once
			if (curPiece.getShape().ordinal() > 7 && isStuck) {
				for (int i = 0; i < Math.abs(new Random().nextInt()) % 4 + 1; i++) {
					System.out.println("roatete"+i);
					curPiece = curPiece.rotateRight();
				}
				isStuck = false;
			}

			for (int i = 0; i < 4; i++) {

				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);

				drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
						curPiece.getShape());

			}
		}
	}
	
	//draw one square
	public void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(250, 0, 0), new Color(255, 130, 36), new Color(255, 228, 0),
				new Color(0, 165, 0), new Color(0, 216, 255), new Color(0, 84, 255), new Color(149, 54, 255),
				new Color(100, 100, 100), new Color(100, 100, 100), new Color(100, 100, 100), new Color(100, 100, 100),
				new Color(100, 100, 100), new Color(100, 100, 100), new Color(100, 100, 100) };

		Color color = colors[shape.ordinal()];

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(Color.black);
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}

	private void drawGameOver(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(new Color(0, 100, 100, 180));
		g.fillRect(0, getHeight() / 2 - 35, getWidth(), 70);

		g.setColor(new Color(255, 0, 0));
		g.setFont(gameOverFont);
		g.drawString("GAME OVER", 15, (getHeight() / 2) + 10);

		g.fillRect(0, getHeight() / 2 - 42, getWidth(), 7);
		g.fillRect(0, getHeight() / 2 + 35, getWidth(), 7);

	}

	private void dropDown() {

		int newY = curY;

		while (newY > 0) {

			if (!tryMove(curPiece, curX, newY - 1)) {

				break;
			}

			newY--;
		}

		pieceDropped();
	}

	private void oneLineDown() {

		if (!tryMove(curPiece, curX, curY - 1)) {

			pieceDropped();
		}
	}

	private void clearBoard() {

		for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {

			board[i] = Tetrominoe.NoShape;
		}
	}

	private void pieceDropped() {

		for (int i = 0; i < 4; i++) {

			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
		}

		removeFullLines();

		if (!isFallingFinished) {

			newPiece();
		}
	}

	private void newPiece() {

		curPiece.setRandomShape();
		int x = curPiece.pieceShape.ordinal();
		if (x > 7) {
			ableRotate = false;
			isStuck = true;
		} else {
			ableRotate = true;
		}

		curX = BOARD_WIDTH / 2;
		curY = BOARD_HEIGHT - 1 + curPiece.minY();

		// Game Over
		if (!tryMove(curPiece, curX, curY)) {

			curPiece.setShape(Tetrominoe.NoShape);
			timer.setStop(true);
			isGameOver = true;
			repaint();

		}
	}

	private boolean tryMove(Shape newPiece, int newX, int newY) {

		for (int i = 0; i < 4; i++) {

			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);

			if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

				return false;
			}

			if (shapeAt(x, y) != Tetrominoe.NoShape) {

				return false;
			}
		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;

		repaint();

		return true;
	}

	private void removeFullLines() {

		int numFullLines = 0;

		for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

			boolean lineIsFull = true;

			for (int j = 0; j < BOARD_WIDTH; j++) {

				if (shapeAt(j, i) == Tetrominoe.NoShape) {

					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {

				numFullLines++;

				for (int k = i; k < BOARD_HEIGHT - 1; k++) {
					for (int j = 0; j < BOARD_WIDTH; j++) {
						board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
					}
				}
			}
		}

		if (numFullLines > 0) {

			numLinesRemoved += numFullLines;

			lineTextField.setText(String.valueOf(numLinesRemoved));
			isFallingFinished = true;
			curPiece.setShape(Tetrominoe.NoShape);
		}

	}

	public class DropTimer extends Thread {
		Board board;
		int time;
		boolean stop=false;
		
		public DropTimer(Board board, int time) {
			this.board=board;
			this.time = time;
			
		}
		
		public void setStop(boolean bool) {
			stop = bool;
		}

		@Override
		public void run() {
			while(!stop) {
				new GameCycle();
				
				//control the speed of a Tetrominoe falling
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class GameCycle  {
		public GameCycle() {
			doGameCycle();
		}
	}

	private void doGameCycle() {

		update();
		checkMissionSuccess();
		setScore();
		repaint();
	}

	private void update() {
		if (isPaused || isGameOver) {

			return;
		}
		if (isFallingFinished) {

			isFallingFinished = false;
			newPiece();
		} else {

			oneLineDown();
		}
	}
	
	private void checkMissionSuccess() {
		int randomTime, randomLines = 0;
		if (isGameOver == false) {
			if (isMissionSucces == true) {
				missionSuccesCount++;
				parent.getSuccessCount().setText("Success " + String.valueOf(missionSuccesCount));
				
				randomTime = 10 * (Math.abs(new Random().nextInt()) % 6 + 2); // 20~70
				if (randomTime <= 30)
					randomLines = 1;
				else if (randomTime > 30 && randomTime <= 50)
					randomLines = 2;
				else if (randomTime > 50 && randomTime <= 70)
					randomLines = 3;
				
				Thread mission = new Thread(new Mission(parent, this, randomTime, randomLines));
				mission.start();
				isMissionSucces = false;
			}
		}
	}

	private void setScore() {
		score = (100 * numLinesRemoved) + (300 * missionSuccesCount);
		parent.getScoreTextField().setText(String.valueOf(score));
	}
	
	class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (curPiece.getShape() == Tetrominoe.NoShape) {
				return;
			}

			int keycode = e.getKeyCode();

			switch (keycode) {

			case KeyEvent.VK_P:
				pause();
				break;
			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyEvent.VK_DOWN:
				oneLineDown();
				break;
			case KeyEvent.VK_UP:
				if (ableRotate == true) {
					tryMove(curPiece.rotateRight(), curX, curY);
					break;
				} else {
					tryMove(curPiece, curX, curY);
					break;
				}
			case KeyEvent.VK_SPACE:
				dropDown();
				break;
			}
		}
	}
}

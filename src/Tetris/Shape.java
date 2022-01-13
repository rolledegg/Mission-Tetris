package Tetris;

import java.util.Random;

public class Shape {

	protected enum Tetrominoe {
		NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape,
		ZGShape, SGShape, LineGShape, TGShape, SquareGShape, LGShape, MirroredGLShape
	}

	public Tetrominoe pieceShape;
	private int coords[][];
	private int[][][] coordsTable;
	

	public Shape() {

		initShape();
	}

	private void initShape() {

		coords = new int[4][2];

		coordsTable = new int[][][] { { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },//NoShape
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, // SShape
				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, // ZShape
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // MirroredLShape
				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // TShape
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, // SquareShape
				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, // LineShape 
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, // LShape
				// repeat again for stuck Tetrominoe (unable to rotate)
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, 
				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, 
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, 
				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, 
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, 
				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, 
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };

		setShape(Tetrominoe.NoShape);
	}

	protected void setShape(Tetrominoe shape) {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; ++j) {
				coords[i][j] = coordsTable[shape.ordinal()][i][j];
			}
		}

		pieceShape = shape;
	}

	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	public Tetrominoe getShape() {
		return pieceShape;
	}

	public void setRandomShape() {
		int x = Math.abs(new Random().nextInt()) % 8 + 1;

		Tetrominoe[] values = Tetrominoe.values();
		if (x == 8) {
			int y = Math.abs(new Random().nextInt()) % 7 ;
			setShape(values[x+y]);
		} else {
			setShape(values[x]);
		}
	}

	public int minX() {

		int m = coords[0][0];

		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}

		return m;
	}

	public int minY() {

		int m = coords[0][1];

		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][1]);
		}

		return m;
	}

	public Shape rotateLeft() {

		if (pieceShape == Tetrominoe.SquareShape) {

			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, y(i));
			result.setY(i, -x(i));
		}

		return result;
	}

	public Shape rotateRight() {

		if (pieceShape == Tetrominoe.SquareShape) {

			return this;
		}

		var result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {

			result.setX(i, -y(i));
			result.setY(i, x(i));
		}

		return result;
	}
}

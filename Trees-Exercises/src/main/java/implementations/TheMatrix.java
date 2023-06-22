package implementations;

import java.util.ArrayDeque;

public class TheMatrix {
	private char[][] matrix;
	private char fillChar;
	private char toBeReplaced;
	private int startRow;
	private int startCol;

	public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
		this.matrix = matrix;
		this.fillChar = fillChar;
		this.startRow = startRow;
		this.startCol = startCol;
		this.toBeReplaced = this.matrix[this.startRow][this.startCol];
	}

	public void solve() {
		// fillMatrix(this.startRow, this.startCol);
		ArrayDeque<int[]> coordinates = new ArrayDeque<>();

		coordinates.offer(new int[] { startRow, startCol });

		while (!coordinates.isEmpty()) {
			int[] position = coordinates.poll();

			int row = position[0];
			int col = position[1];

			this.matrix[row][col] = this.fillChar;

			if (isValidChar(row + 1, col)) {
				coordinates.offer(new int[] { row + 1, col });

			}
			if (isValidChar(row, col + 1)) {
				coordinates.offer(new int[] { row, col + 1 });

			}
			if (isValidChar(row - 1, col)) {
				coordinates.offer(new int[] { row - 1, col });

			}
			if (isValidChar(row, col - 1)) {
				coordinates.offer(new int[] { row, col - 1 });

			}

		}
	}

	private void fillMatrix(int row, int col) {
		if (!isValidChar(row, col)) {
			return;
		}

		this.matrix[row][col] = this.fillChar;

		this.fillMatrix(row + 1, col);
		this.fillMatrix(row, col + 1);
		this.fillMatrix(row - 1, col);
		this.fillMatrix(row, col - 1);

	}

	public String toOutputString() {
		StringBuilder result = new StringBuilder();

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				result.append(matrix[row][col]);
			}
			result.append(System.lineSeparator());
		}

		return result.toString().trim();
	}

	private boolean isValidChar(int row, int col) {
		if (row < matrix.length && row >= 0 && col >= 0 && col < matrix[0].length && matrix[row][col] == toBeReplaced) {
			return true;
		}
		return false;
	}

}

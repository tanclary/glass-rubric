package engine;

import java.util.Arrays;

public class NotationToMatrixConverter {

	public int rows;
	public int columns;
	public NotationToMatrixConverter(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public double[][] getMatrix(String notation) {
		// Three paths: 
		// E_3[1, 2] is an example of adding row 2 to row 1, 3 times
		// E_2[1] is an example of multiplying row 1 by 2
		// E[1,2] is an example of swapping rows 1 and 2
		OperationType type = getOperationType(notation);
		String trimmed = trimNotation(notation);
		return switch (type) {
			case SCALAR -> convertScalarNotation(trimmed);
			case SUM -> convertSumNotation(trimmed);
			case SWAP -> convertSwapNotation(trimmed);
			default -> throw new RuntimeException("This definitely should not have happened..");
		};
	}
	
	private String trimNotation(String notation) {
		return notation.substring(1);
	}

	private OperationType getOperationType(String notation) {
		if (notation.contains("_")) {
			if (notation.contains(",")) {
				return OperationType.SUM;
			}
			return OperationType.SCALAR;
		}
		return OperationType.SWAP;
	}
	
	private double[][] convertScalarNotation(String notation) {
		String cleanNotation = notation.substring(1, notation.length() - 1);
		double[] values = parseCleanNotation(cleanNotation);
		return generateMatrix(values[0], (int) values[1], (int) values[1]);
	}
	
	private double[][] convertSwapNotation(String notation) {
		return null;
	}

	private double[][] convertSumNotation(String notation) {
		return null;
	}	
	
	private double[] parseCleanNotation(String notation) {
		return Arrays.stream(notation.split(",")).mapToDouble(Double::parseDouble).toArray();
	}
	
	private double[][] generateMatrix(double scalar, int sourceRow, int targetRow) {
		return null;
	}
}

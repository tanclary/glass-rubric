package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotationToMatrixConverter {

	public int rows;
	public int columns;
	public double[][] identityMatrix;
	public NotationToMatrixConverter(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.identityMatrix = getIdentityMatrix();
	}
	
	public double[][] getIdentityMatrix() {
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			double[] current = new double[columns];
			for (int j = 0; j < columns; j++) {
				if (i == j) {
					current[j] = (double) 1;
				} else {
					current[j] = (double) 0;
				}
			}
			result[i] = current;
		}
		return result;
	}
	
	public double[][] getMatrix(String notation) {
		if (notation.contains(" ")) {
			return getMatrix(notation.split(" "));
		}
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
	
	public double[][] getMatrix(String[] notations) {
		double[][][] matrices = new double[notations.length][][];
		// Collect matrices in a disgusting 3d array
		for (int i = 0; i < notations.length; i++) {
			matrices[i] = getMatrix(notations[i]);
		}
		final ElementaryMatrixMultiplier multiplier = new ElementaryMatrixMultiplier();
		double[][] answer = matrices[matrices.length - 1];
		for (int i = matrices.length - 2; i >= 0; i--) {
			answer = multiplier.multiply(answer, matrices[i]);
		}
		return answer;
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
		String cleanNotation = notation
			.substring(1, notation.length() - 1)
			.replaceAll("\\[", ",");
		double[] values = parseCleanNotation(cleanNotation);
		return scalarTransform(values[0] - 1, (int) values[1] - 1, (int) values[1] - 1);
	}
	
	private double[][] convertSumNotation(String notation) {
		String cleanNotation = notation
			.substring(1, notation.length() - 1)
			.replaceAll("\\[", ",");
		double[] values = parseCleanNotation(cleanNotation);
		return scalarTransform(values[0], (int) values[1] - 1, (int) values[2] - 1);
	}		
	
	private double[][] convertSwapNotation(String notation) {
		String cleanNotation = notation.substring(1, notation.length() - 1);
		double[] values = parseCleanNotation(cleanNotation);
		return swapTransform((int) values[0] - 1, (int) values[1] - 1);
	}
	
	private double[] parseCleanNotation(String notation) {
		return Arrays.stream(notation.split(",")).mapToDouble(Double::parseDouble).toArray();
	}
	
	private double[][] scalarTransform(double scalar, int sourceIdx, int targetIdx) {
		// TODO: fix it or it's over
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			if (i == sourceIdx) {
				for (int j = 0; j < columns; j++) {
					result[i][j] = identityMatrix[sourceIdx][j] + identityMatrix[targetIdx][j] * scalar;
				}
			} else {
				for (int j = 0; j < columns; j++) {
					result[i][j] = identityMatrix[i][j];
				}
			}
		}
		return result;
	}
	
	private double[][] swapTransform(int row1, int row2) {
		// TODO: make this better
		double[][] result = new double[rows][columns];
		double[] r1 = identityMatrix[row1];
		double[] r2 = identityMatrix[row2];
		for (int i = 0; i < rows; i++) {
			if (i == row1) {
				result[i] = r2;
			} else if (i == row2) {
				result[i] = r1;
			} else {
				result[i] = identityMatrix[i];
			}
		}
		return result;
	}
}

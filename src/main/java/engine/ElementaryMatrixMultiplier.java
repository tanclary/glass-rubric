package engine;

import java.util.Arrays;

public class ElementaryMatrixMultiplier {
	public ElementaryMatrixMultiplier() {}
	
	public double[][] multiply(double[][] matrix1, double[][] matrix2) {
		double[][] result = new double[matrix1.length][matrix2[0].length];
		
		for(int i =0;i<matrix1.length;i++){
			for(int j=0;j<matrix2[0].length;j++){
				for(int k=0;k<matrix2.length;k++){
					result[i][j] += matrix1[i][k]*matrix2[k][j];
				}
			}      
		}
		
		return result;
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}	
}

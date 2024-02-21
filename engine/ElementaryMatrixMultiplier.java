package engine;

import java.util.Arrays;

public class ElementaryMatrixMultiplier {
	public static void main(String[] args) {
			int[][] m1 = {{1,2,3}, {1,2,3}, {1,2,3}};
			int[][] m2 = {{1,0,0}, {0,1,0}, {0,0,1}};
			int[][] res = multiply(m1, m2);
			printMatrix(res);
	}
	
	public ElementaryMatrixMultiplier() {}
	
	public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
		int[][] result = new int[matrix1.length][matrix2[0].length];
		
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

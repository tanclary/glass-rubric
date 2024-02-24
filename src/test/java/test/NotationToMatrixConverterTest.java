package test;

import engine.NotationToMatrixConverter;
import org.junit.Assert;
import org.junit.Test;

public class NotationToMatrixConverterTest {
	@Test
	public void testRowsAndColumnsAreCorrectlySet() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		Assert.assertEquals(3, notationToMatrixConverter.columns);
		Assert.assertEquals(3, notationToMatrixConverter.rows);
	}
	
	@Test
	public void testGetIdentityMatrix() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		double[][] expected = {{1,0,0}, {0,1,0}, {0,0,1}};
		double[][] identityMatrix = notationToMatrixConverter.identityMatrix;
		Assert.assertArrayEquals(expected, identityMatrix);
	}
	
	@Test
	public void testSwapNotation() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E[1,3]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{0,0,1}, {0,1,0}, {1,0,0}};
		Assert.assertArrayEquals(expected, result);
	}
	
	
	@Test
	public void testSwapNotationWithSameRow() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E[3,3]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{1,0,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);
	}	
	
	
	@Test
	public void testScalarNotation() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_3[1]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{3,0,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);
	}
	
	@Test
	public void testScalarNotationWithZero() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_0[1]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{0,0,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);
	}	
	
	@Test
	public void testScalarNotationWithFraction() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_0.5[1]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{0.5,0,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);
	}		
	
	@Test
	public void testScalarNotationWithNegative() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_-1[1]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{-1,0,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);
	}			
	
	@Test
	public void testSumNotation() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_1[1,2]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{1,1,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);	
	}
	
	@Test
	public void testSumNotationWithFractionalScalar() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_0.5[1,2]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{1,0.5,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);	
	}	
	
	
	@Test
	public void testSumNotationWithNegativeScalar() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E_-1[1,2]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{1,-1,0}, {0,1,0}, {0,0,1}};
		Assert.assertArrayEquals(expected, result);	
	}		
	
	@Test
	public void testBasicNotationSequence() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E[1,3] E_2[1]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{0,0,2}, {0,1,0}, {1,0,0}};
		Assert.assertArrayEquals(expected, result);
	}
	
	@Test
	public void testNotationSequenceWithAllTransforms() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		String notation = "E[1,3] E_2[1] E_-3[1,3] E_4[2] E[3,2]";
		double [][] result = notationToMatrixConverter.getMatrix(notation);
		double [][] expected = {{-3,0,2}, {1,0,0}, {0,4,0}};
		Assert.assertArrayEquals(expected, result);
	}	
}

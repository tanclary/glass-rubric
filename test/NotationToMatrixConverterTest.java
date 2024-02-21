package test;

import engine.NotationToMatrixConverter;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Notation;

public class NotationToMatrixConverterTest {
	@Test
	public void testRowsAndColumnsAreCorrectlySet() {
		NotationToMatrixConverter notationToMatrixConverter = new NotationToMatrixConverter(3, 3);
		Assert.assertEquals(3, notationToMatrixConverter.columns);
		Assert.assertEquals(3, notationToMatrixConverter.rows);
	}
}

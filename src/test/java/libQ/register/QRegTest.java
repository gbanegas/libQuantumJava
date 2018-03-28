package libQ.register;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.OperationNotPermittedException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;

public class QRegTest {

	QReg reg;
	QReg reg2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		reg = new QReg(new BigInteger("3"), 4);
		reg2 = new QReg(new BigInteger("3"), 4);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQReg() {
		QReg tempReg = new QReg(BigInteger.ZERO, 4);
		assertEquals(4, tempReg.getWidth());
		assertEquals(1, tempReg.getSize());
		assertEquals(1, tempReg.getAmplitude().size());
		assertEquals(1, tempReg.getState().size());
	}

	@Test
	public void testToString() {
		String toVerify = "(1.0 | 0011>)";
		String gotFromClass = reg.toString();
		assertEquals(1, gotFromClass.compareTo(toVerify));
	}

	@Test
	public void testMeasureQBitAtPosition() {
		BigInteger measuredBitOne = reg.measureQBitAtPosition(0);
		BigInteger measuredBitZero = reg2.measureQBitAtPosition(4);
		// System.out.println(reg);

		assertEquals(BigInteger.ONE, measuredBitOne);
		assertEquals(BigInteger.ZERO, measuredBitZero);
		assertEquals(3, reg.getWidth());
		assertEquals(3, reg2.getWidth());
	}

	@Test
	public void testMeasure() {
		// System.out.println(reg);
		BigInteger measured = reg.measure();
		assertEquals(new BigInteger("3"), measured);
		// fail("Not yet implemented");
	}

	@Test
	public void testGetWidth() {
		assertEquals(4, reg.getWidth());
		assertEquals(4, reg2.getWidth());
	}

	@Test
	public void testGetSize() throws UnexpectedException, OperationNotPermittedException {
		assertEquals(1, reg.getSize());
		IGate hadamardGate = GateFactory.getInstance().getGate(EGateTypes.E_HadamardGate);
		hadamardGate.apply(reg, 0);
		assertEquals(2, reg.getSize());
	}

	@Test
	public void testSetSize() {
		assertEquals(1, reg.getSize());
		reg.setSize(reg.getSize() + 1);
		assertEquals(2, reg.getSize());
	}

	@Test
	public void testGetHashw() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHashw() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmplitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAmplitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHash() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHash() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStateAtPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAmplituteAtPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHashValueAtPosition() {
		fail("Not yet implemented");
	}

}

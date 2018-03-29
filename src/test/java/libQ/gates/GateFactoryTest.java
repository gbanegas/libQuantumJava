package libQ.gates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GateFactoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		GateFactory instance = null;
		assertNull(instance);
		instance = GateFactory.getInstance();
		assertNotNull(instance);

	}

	@Test
	public void testGetGate() {
		IGate gate = null;
		assertNull(gate);
		gate = GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		assertNotNull(gate);

	}

	@Test
	public void testGetNrHadamardGate() {
		assertEquals(0, GateFactory.getInstance().getNrHadamardGate());
		GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		assertEquals(3, GateFactory.getInstance().getNrHadamardGate());
	}

	@Test
	public void testGetNrCNotGate() {
		assertEquals(1, GateFactory.getInstance().getNrCNotGate());
		GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		assertEquals(4, GateFactory.getInstance().getNrCNotGate());
	}

	@Test
	public void testGetNrToffoli() {
		assertEquals(0, GateFactory.getInstance().getNrToffoli());
		GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		assertEquals(3, GateFactory.getInstance().getNrToffoli());
	}

}

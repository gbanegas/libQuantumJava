package libQ.gates;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class GateFactory {

	private static int nrHadamardGate;
	private static int nrCNotGate;
	private static int nrToffoli;
	private static int nrSwapGate;
	private static int nrSigmaXGate;
	private static int nrSigmaYGate;
	private static int nrSigmaZGate;
	private static GateFactory instance = null;

	protected GateFactory() {
		nrCNotGate = 0;
		nrSigmaXGate = 0;
		nrSigmaYGate = 0;
		nrSigmaZGate = 0;
		nrToffoli = 0;
		nrSwapGate = 0;
		nrHadamardGate = 0;

	}

	/**
	 * 
	 * @return
	 */

	public static GateFactory getInstance() {
		if (instance == null) {
			instance = new GateFactory();
		}
		return instance;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public IGate getGate(EGateTypes id) {
		IGate gate = null;
		switch (id) {
		case HADAMARDGATE:
			gate = new HadamardGate();
			nrHadamardGate++;
			break;
		case CNOTGATE:
			gate = new CNOTGate();
			nrCNotGate++;
			break;
		case TOFFOLIGATE:
			gate = new ToffoliGate();
			nrToffoli++;
			break;
		case SIGMAXGATE:
			gate = new SigmaXGate();
			nrSigmaXGate++;
			break;
		case SIGMAYGATE:
			gate = new SigmaYGate();
			nrSigmaYGate++;
			break;
		case SIGMAZGATE:
			gate = new SigmaZGate();
			nrSigmaZGate++;
			break;

		case SWAPGATE:
			gate = new SWAPGate();
			nrSwapGate++;
			break;

		}
		return gate;
	}

	/**
	 * 
	 * @return nrHadamardGate
	 */
	public int getNrHadamardGate() {
		return nrHadamardGate;
	}

	/**
	 * 
	 * @return nrCNotGate
	 */

	public int getNrCNotGate() {
		return nrCNotGate;
	}

	/**
	 * 
	 * @return nrToffoli
	 */
	public int getNrToffoli() {
		return nrToffoli;
	}

	/**
	 * @return the nrSwapGate
	 */
	public static int getNrSwapGate() {
		return nrSwapGate;
	}

	/**
	 * @return the nrXPauliGate
	 */
	public static int getNrXPauliGate() {
		return nrSigmaXGate;
	}

	/**
	 * @return the nrYPauliGate
	 */
	public static int getNrYPauliGate() {
		return nrSigmaYGate;
	}

	/**
	 * @return the nrZPauliGate
	 */
	public static int getNrZPauliGate() {
		return nrSigmaZGate;
	}

}

package libQ.gates;


public class GateFactory {
	
	private static int nrHadamardGate;
	private static int nrCNotGate;
	private static int nrToffoli;
	
	private static GateFactory instance = null;
	
	
	protected GateFactory() {
		
	}
	
	public static GateFactory getInstance() {
	      if(instance == null) {
	         instance = new GateFactory();
	      }
	      return instance;
	   }
	

	public  IGate getGate(EGateTypes id) {
		IGate gate = null;
		switch (id) {
		case E_HadamardGate:
			gate = new HadamardGate();
			nrHadamardGate++;
			break;
		/*
		 * case E_XGate: gate = new XGate(); nrXGate++; break; case E_YGate: gate = new
		 * YGate(); nrYGate++; break; case E_ZGate: gate = new ZGate(); nrZgGate++;
		 * break;
		 */
		case E_CNotGate:
			gate = new CNOTGate();
			nrCNotGate++;
			break;
		case E_ToffoliGate:
			gate = new ToffoliGate();
			nrToffoli++;
			break;
		case E_XPauliGate:
			gate = new XGate();
			break;
		/*
		 * case E_IGate: gate = new IdentityGate(); nrIGate++; break;
		 */
		}
		return gate;
	}

	public int getNrHadamardGate() {
		return nrHadamardGate;
	}

	public int getNrCNotGate() {
		return nrCNotGate;
	}

	public int getNrToffoli() {
		return nrToffoli;
	}

}

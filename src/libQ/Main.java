package libQ;

import java.math.BigInteger;

import exceptions.OperationNotPermitted;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;

public class Main {

	public static void main(String[] args) throws OperationNotPermitted {
		QReg reg = new QReg(new BigInteger("15"), 10);
		reg.quantum_print_qureg();
		IGate h = GateFactory.getInstance().getGate(EGateTypes.E_ToffoliGate);

		h.apply(reg, 3, 2, 0);
		reg.quantum_print_qureg();
		System.out.println(GateFactory.getInstance().getNrCNotGate());
	}

}

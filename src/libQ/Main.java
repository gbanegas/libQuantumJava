package libQ;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import exceptions.OperationNotPermitted;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

public class Main {

	public static void main(String[] args) throws OperationNotPermitted, UnexpectedException {
		QReg reg = new QReg(new BigInteger("15"), 4);
		
		reg.quantum_print_qureg();
		IGate h = GateFactory.getInstance().getGate(EGateTypes.E_HadamardGate);
		h.apply(reg, 1);

		reg.quantum_print_qureg();
		/*h.apply(reg, 3, 2, 0);
		reg.quantum_print_qureg();
		System.out.println(GateFactory.getInstance().getNrCNotGate());	*/	
	}

}

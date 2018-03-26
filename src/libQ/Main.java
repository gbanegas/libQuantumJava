package libQ;

import java.math.BigInteger;

import exceptions.OperationNotPermitted;
import libQ.gates.CNOTGate;
import libQ.gates.IGate;

public class Main {

	public static void main(String[] args) throws OperationNotPermitted {
		QReg reg = new QReg(new BigInteger("4"), 10);
		reg.quantum_print_qureg();
		IGate h = new CNOTGate();

		h.apply(reg, 2, 1);
		reg.quantum_print_qureg();
	}

}

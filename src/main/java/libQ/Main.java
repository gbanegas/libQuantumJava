package libQ;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

public class Main {

	public static void main(String[] args) throws OperationNotPermittedException, UnexpectedException {
		QReg reg = new QReg(new BigInteger("1"), 16);

		System.out.println(reg);
		IGate h = GateFactory.getInstance().getGate(EGateTypes.E_HadamardGate);
		h.apply(reg, 1);
		System.out.println(reg);
		// reg.measure();
		// h.apply(reg, 3);
		/*
		 * h.apply(reg, 2); reg.quantum_print_qureg(); h.apply(reg, 2);
		 * reg.quantum_print_qureg();
		 */
		// reg.toPrint();
		/*
		 * h.apply(reg, 3, 2, 0); reg.quantum_print_qureg();
		 * System.out.println(GateFactory.getInstance().getNrCNotGate());
		 */
	}

}

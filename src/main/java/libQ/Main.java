package libQ;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

public class Main {

	public static void main(String[] args) throws OperationNotPermittedException, UnexpectedException, SizeHandleException {
		QReg reg = new QReg(new BigInteger("1"), 128);

		System.out.println(reg);
		IGate h = GateFactory.getInstance().getGate(EGateTypes.SWAPGATE);
		h.apply(reg, 0,1);
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

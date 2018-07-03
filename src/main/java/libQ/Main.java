package libQ;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.circuit.QuantumCircuit;
import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.register.QReg;

public class Main {

	public static void main(String[] args)
			throws OperationNotPermittedException, UnexpectedException, SizeHandleException {
		QReg reg = new QReg(new BigInteger("1"), 4);
		QuantumCircuit circ = new QuantumCircuit(reg);

		System.out.println(reg);
		circ.addCNotGate(0, 1);
		System.out.println(circ.getReg());
		circ.drawCircuit();
		/*IGate h = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		h.apply(reg, 0);
		System.out.println(reg);
		h = GateFactory.getInstance().getGate(EGateTypes.SIGMAXGATE);
		h.apply(reg, 0);
		System.out.println(reg);
		// reg.setWidth(reg.getWidth() + 1);
		BigInteger re = QMeasurement.measureQBit(reg, 0);
		System.out.println("measured: " + re);
		System.out.println(reg);*/
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

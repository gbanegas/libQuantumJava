package libQ.algorithms.examples;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QMeasurement;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class GroverAlgorithm {
	//TODO: check why it is not working.

	static void final_measure(QReg reg)
			throws UnexpectedException, OperationNotPermittedException, SizeHandleException {
		IGate hadamard = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		hadamard.apply(reg, reg.getWidth());
		reg.setWidth(reg.getWidth() + 1);
		reg.measureQBitAtPosition(reg.getWidth() - 1);
	}

	static void grover(int target, QReg reg)
			throws UnexpectedException, OperationNotPermittedException, SizeHandleException {
		int i;
		IGate hadarmard;
		oracle(target, reg);

		for (i = 0; i < reg.getWidth(); i++) {
			hadarmard = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
			hadarmard.apply(reg, i);
		}

		inversion(reg);

		for (i = 0; i < reg.getWidth(); i++) {
			hadarmard = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
			hadarmard.apply(reg, i);
		}
	}

	private static void inversion(QReg reg)
			throws UnexpectedException, OperationNotPermittedException, SizeHandleException {
		int i;
		IGate gate;
		for (i = 0; i < reg.getWidth(); i++) {
			gate = GateFactory.getInstance().getGate(EGateTypes.SIGMAXGATE);
			gate.apply(reg, i); // NOT every Qubit
		}
		gate = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		gate.apply(reg, reg.getWidth() - 1);

		if (reg.getWidth() == 3) {
			gate = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
			gate.apply(reg, 0, 1, 2); // (control1, Control2, Target)
		} else {
			gate = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
			gate.apply(reg, 0, 1, reg.getWidth() + 1);

			for (i = 1; i < reg.getWidth() - 1; i++) {
				gate = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
				gate.apply(reg, i, reg.getWidth() + i, reg.getWidth() + i + 1);
			}
			gate = GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
			gate.apply(reg, reg.getWidth() + i, reg.getWidth() - 1);

			for (i = reg.getWidth() - 2; i > 0; i--) {
				gate = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
				gate.apply(reg, i, reg.getWidth() + i, reg.getWidth() + i + 1);
			}
			gate = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
			gate.apply(reg, 0, 1, reg.getWidth() + 1);
		}
		gate = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
		gate.apply(reg, reg.getWidth() - 1);

		for (i = 0; i < reg.getWidth(); i++) {
			gate = GateFactory.getInstance().getGate(EGateTypes.SIGMAXGATE);
			gate.apply(reg, i);// NOT every Qubit
		}

	}

	private static void oracle(int state, QReg reg)
			throws UnexpectedException, OperationNotPermittedException, SizeHandleException {
		int i;
		IGate toffoli;
		IGate sig;
		for (i = 0; i < reg.getWidth(); i++) {
			int result = (state & (1 << i));
			if (result != 0) {
				sig = GateFactory.getInstance().getGate(EGateTypes.SIGMAXGATE);
				sig.apply(reg, i); // if it is not 011 change to -x
			}
		}

		toffoli = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		toffoli.apply(reg, 0, 1, reg.getWidth() + 1);

		for (i = 1; i < reg.getWidth(); i++) {
			toffoli = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
			toffoli.apply(reg, i, reg.getWidth() + i, reg.getWidth() + i + 1);
		}

		IGate cnot = GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		cnot.apply(reg, reg.getWidth() + i, reg.getWidth());

		for (i = reg.getWidth() - 1; i > 0; i--) {
			toffoli = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
			toffoli.apply(reg, i, reg.getWidth() + i, reg.getWidth() + i + 1);
		}

		toffoli = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		toffoli.apply(reg, 0, 1, reg.getWidth() + 1);

		for (i = 0; i < reg.getWidth(); i++) {
			int result = (state & (1 << i));
			if (result != 0) {
				sig = GateFactory.getInstance().getGate(EGateTypes.SIGMAXGATE);
				sig.apply(reg, i); // if it is not 011 change to -x
			}
		}

	}

	public static void main(String[] args)
			throws OperationNotPermittedException, UnexpectedException, SizeHandleException {
		int i, N, width = 0;
		System.out.println("Starting...");

		N = 3; // Number to Search
		width = 3; // 2^3 qubits

		QReg reg = new QReg(BigInteger.ZERO, width);

		for (i = 0; i < reg.getWidth(); i++) {
			IGate hadamard = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);
			hadamard.apply(reg, i);
		}

		System.out.println(reg);

		int number_of_iterations = (int) ((Math.PI / 4) * Math.sqrt((1 << reg.getWidth()))); // 2.22
		System.out.println("Iterating " + number_of_iterations + " times");

		for (i = 1; i <= number_of_iterations; i++) {
			System.out.println("Iteration: " + i);
			grover(N, reg);
		}

		final_measure(reg);

		for (i = 0; i < reg.getSize(); i++) {
			// if(reg.state[i] == N)
			BigInteger state = reg.getState().get(i);
			double prob = QMeasurement.quantumProbabilityInline(reg.getAmplitude().get(i));

			System.out.println("Found state: " + state + " with probability: " + prob);

		}

	}

}

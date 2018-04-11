package libQ.examples;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.exceptions.SizeHandleException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

public class HadamardGateExample {

	public static void main(String[] args) throws UnexpectedException, OperationNotPermittedException, SizeHandleException {
		// Create a base register with or without the initial value.
		QReg register = new QReg(new BigInteger("0"), 4);
		// see the content of register, in this case 0011
		System.out.println(register);

		// Create a gate hadamard gate for see more gates see EGateTypes enumaration
		IGate hadamard = GateFactory.getInstance().getGate(EGateTypes.HADAMARDGATE);

		// Apply the gate calling the method "apply" and the proper parameters.
		hadamard.apply(register, 0);

		// see the content of register, in this case the result of hadamard and puting the 0 bit in superposition
		System.out.println(register);

	}

}

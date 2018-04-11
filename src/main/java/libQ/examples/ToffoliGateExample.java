package libQ.examples;

import java.math.BigInteger;

import libQ.exceptions.OperationNotPermittedException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

public class ToffoliGateExample {
	public static void main(String[] args) throws OperationNotPermittedException {
		// Create a base register with or without the initial value.
		QReg register = new QReg(new BigInteger("3"), 4);
		// see the content of register, in this case 0011
		System.out.println(register);

		// Create a gate toffoli gate for see more gates see EGateTypes enumaration
		IGate toffoli = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);

		//Apply the gate calling the method "apply" and the proper parameters. 
		toffoli.apply(register, 0, 1, 2);
		
		// see the content of register, in this case the result of Toffoli 0111
		System.out.println(register);
		

	}
}

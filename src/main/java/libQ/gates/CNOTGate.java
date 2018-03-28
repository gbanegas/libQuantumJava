package libQ.gates;

import java.math.BigInteger;

import exceptions.OperationNotPermittedException;
import libQ.register.QReg;

public class CNOTGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) {
		int i;
		for (i = 0; i < reg.getSize(); i++) {
			/* Flip the target bit of a basis state if the control bit is set */
			BigInteger tmp_1 = reg.getState().get(i);
			if ((tmp_1.and((BigInteger.ONE.shiftLeft(controlQBit))).compareTo(BigInteger.ZERO) > 0)) {
				BigInteger tmp_2 = tmp_1.xor((BigInteger.ONE.shiftLeft(targetQBit)));
				reg.setStateAtPosition(i, tmp_2);
			}
		}
		return true;

	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("CNOT just need one control bit");
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("CNOT need one control bit and a target bit");
	}

}

package libQ.gates;

import java.math.BigInteger;

import exceptions.OperationNotPermittedException;
import libQ.register.QReg;

public class ToffoliGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("ToffoliGate needs two controls bits");
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit) {
		for (int i = 0; i < reg.getSize(); i++) {
			/*
			 * Flip the target bit of a basis state if both control bits are set
			 */
			BigInteger tmp_1 = reg.getState().get(i);
			if ((tmp_1.and((BigInteger.ONE.shiftLeft(controlQBit1))).compareTo(BigInteger.ZERO) > 0)) {
				if (tmp_1.and((BigInteger.ONE.shiftLeft(controlQBit2))).compareTo(BigInteger.ZERO) > 0) {
					BigInteger tmp_2 = tmp_1.xor((BigInteger.ONE.shiftLeft(targetQBit)));
					reg.setStateAtPosition(i, tmp_2);
				}
			}
		}
		return true;
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("ToffoliGate needs two controls bits");
	}

}

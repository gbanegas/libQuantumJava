package libQ.gates;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import libQ.exceptions.OperationNotPermittedException;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
class SigmaXGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaXGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaXGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException {

		for (int i = 0; i < reg.getSize(); i++) {
			/* Flip the target bit of each basis state */
			BigInteger tmp = BigInteger.ONE.shiftLeft(targetQBit);
			BigInteger nState = reg.getState().get(i).xor(tmp);
			reg.setStateAtPosition(i, nState);

		}
		return true;
	}

}

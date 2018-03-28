package libQ.gates;

import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;

import libQ.exceptions.OperationNotPermittedException;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class SigmaZGate implements IGate {

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaZGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaZGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException {
		for (int i = 0; i < reg.getSize(); i++) {
			/* Multiply with -1 if the target bit is set */
			BigInteger tmp = BigInteger.ONE.shiftLeft(targetQBit);
			BigInteger result = reg.getState().get(i).and(tmp);
			if (result.compareTo(BigInteger.ZERO) != 0) {
				Complex tmp_c = reg.getAmplitude().get(i).multiply(Complex.ZERO.negate());
				reg.setAmplituteAtPosition(i, tmp_c);
				// reg->amplitude[i] *= -1;
			}
		}
		return true;
	}

}

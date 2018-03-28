package libQ.gates;

import java.math.BigInteger;
import java.rmi.UnexpectedException;

import org.apache.commons.math3.complex.Complex;

import libQ.exceptions.OperationNotPermittedException;
import libQ.register.QReg;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class SigmaYGate implements IGate {
	
	@Override
	public Boolean apply(QReg reg, int target, float gamma) throws OperationNotPermittedException, UnexpectedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit, int targetQBit) throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaYGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int controlQBit1, int controlQBit2, int targetQBit)
			throws OperationNotPermittedException {
		throw new OperationNotPermittedException("SigmaYGate just need one target bit");
	}

	@Override
	public Boolean apply(QReg reg, int targetQBit) throws OperationNotPermittedException {
		for (int i = 0; i < reg.getSize(); i++) {
			/*
			 * Flip the target bit of each basis state and multiply with +/- i
			 */
			BigInteger tmp = BigInteger.ONE.shiftLeft(targetQBit);
			BigInteger nState = reg.getState().get(i).xor(tmp);
			reg.setStateAtPosition(i, nState);
			// reg->state[i] ^= ((MAX_UNSIGNED) 1 << target);

			BigInteger tmp_2 = reg.getState().get(i).and(tmp);

			if (tmp_2.compareTo(BigInteger.ZERO) != 0) {
				Complex tmp_c1 = reg.getAmplitude().get(i).multiply(Complex.I);
				reg.setAmplituteAtPosition(i, tmp_c1);
				// reg->amplitude[i] *= IMAGINARY;
			} else {
				Complex minus_I = Complex.I.negate();
				Complex tmp_c1 = reg.getAmplitude().get(i).multiply(minus_I);
				reg.setAmplituteAtPosition(i, tmp_c1);
				// reg->amplitude[i] *= -IMAGINARY;
			}
		}
		return true;
	}

}

package libQ.utils;

import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;

import libQ.register.QReg;

public class GateApplication {

	/**
	 * 
	 * @param target
	 * @param m
	 * @param reg
	 */
	public static void applyQMatrix(int target, QMatrix m, QReg reg) {
		int i;
		int addsize = 0;

		if (reg.getHashw() != 0) {
			reconstructHash(reg);
			for (i = 0; i < reg.getSize(); i++) {
				/* determine whether XORed basis state already exists */
				BigInteger tmp = reg.getState().get(i).xor(BigInteger.ONE.shiftLeft(target));

				if (QuantumUtils.getQState(tmp, reg).compareTo(new BigInteger("-1")) == 0)
					addsize++;
			}

			for (i = 0; i < addsize; i++) {
				reg.setStateAtPosition(i + reg.getSize(), BigInteger.ZERO);
				reg.setAmplituteAtPosition(i + reg.getSize(), Complex.ZERO);
			}
		}
		// TODO: MIssing part check to do it

	}

	/**
	 * 
	 * @param reg
	 */
	private static void reconstructHash(QReg reg) {
		int i;
		if (reg.getHashw() == 0)
			return;

		for (i = 0; i < (1 << reg.getHashw()); i++)
			reg.setHashValueAtPosition(i, BigInteger.ZERO);
		for (i = 0; i < reg.getSize(); i++)
			QuantumUtils.addHash(reg, i);

	}

}

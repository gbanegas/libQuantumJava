package libQ.utils;

import java.math.BigInteger;

import libQ.register.QReg;

public class QuantumUtils {

	/**
	 * 
	 * @param a
	 * @param reg
	 * @return
	 */

	public static BigInteger getQState(BigInteger a, QReg reg) {

		int i;

		if (reg.getHashw() == 0)
			return a;

		i = quantum_hash64(a, reg.getHashw());

		while (!reg.getHash().get(i).equals(BigInteger.ZERO)) {
			if (reg.getState().get((reg.getHash().get(i).subtract(BigInteger.ONE).intValue())).compareTo(a) == 0)
				return reg.getHash().get(i).subtract(BigInteger.ONE);
			i++;
			if (i == (1 << reg.getHashw()))
				i = 0;
		}
		return new BigInteger("-1");
	}

	/**
	 * 
	 * @param reg
	 * @param index
	 */
	public static void addHash(QReg reg, int index) {
		int i, mark = 0;

		i = quantum_hash64(reg.getState().get(index), reg.getHashw());

		while (reg.getHash().get(i).compareTo(BigInteger.ZERO) != 0) {
			i++;
			if (i == (1 << reg.getHashw())) {
				if (mark == 0) {
					i = 0;
					mark = 1;
				}

			}
		}

		reg.setHashValueAtPosition(i, new BigInteger(Integer.toString(index + 1)));

	}

	private static int quantum_hash64(BigInteger key, int width) {
		BigInteger k32;
		BigInteger tmp_1 = key.and(new BigInteger("4294967295"));
		BigInteger tmp_2 = new BigInteger("32").shiftLeft(tmp_1.intValue());
		k32 = (tmp_1.xor(tmp_2));

		k32 = k32.multiply(new BigInteger("2654404609"));
		int result = k32.intValue() >> (32 - width);

		return result;
	}

}

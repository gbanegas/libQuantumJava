package libQ.utils;

import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;

import libQ.register.QReg;

public class QMeasurement {

	public static void quantum_delete_qureg_hashpreserve(QReg reg) {
		// TODO Auto-generated method stub

	}

	public static void quantum_state_collapse(int position, BigInteger value, QReg reg) {
		int i, j, k;
		int size = 0;
		double d = 0;
		BigInteger lpat = BigInteger.ZERO, rpat = BigInteger.ZERO, pos2;

		pos2 = BigInteger.ONE.shiftLeft(position);

		/*
		 * Eradicate all amplitudes of base states which have been ruled out by the
		 * measurement and get the norm of the new register
		 */

		for (i = 0; i < reg.getSize(); i++) {
			BigInteger tmp = reg.getState().get(i).and(pos2);

			if (((tmp.compareTo(BigInteger.ZERO) != 0) && (value.compareTo(BigInteger.ZERO) != 0))
					|| ((tmp.compareTo(BigInteger.ZERO) == 0) && (value.compareTo(BigInteger.ZERO) == 0))) {
				d += quantumProbabilityInline(reg.getAmplitude().get(i));
				size++;
			}
		}

		/* Build the new quantum register */

		out.width = reg.width - 1;
		out.size = size;
		out.amplitude = calloc(size, sizeof(COMPLEX_FLOAT));
		out.state = calloc(size, sizeof(MAX_UNSIGNED));

		if (!(out.state && out.amplitude))
			quantum_error(QUANTUM_ENOMEM);

		quantum_memman(size * (sizeof(COMPLEX_FLOAT) + sizeof(MAX_UNSIGNED)));
		out.hashw = reg.hashw;
		out.hash = reg.hash;

		/*
		 * Determine the numbers of the new base states and norm the quantum register
		 */

		for (i = 0, j = 0; i < reg.size; i++) {
			if (((reg.state[i] & pos2) && value) || (!(reg.state[i] & pos2) && !value)) {
				for (k = 0, rpat = 0; k < pos; k++)
					rpat += (MAX_UNSIGNED) 1 << k;

				rpat &= reg.state[i];

				for (k = sizeof(MAX_UNSIGNED) * 8 - 1, lpat = 0; k > pos; k--)
					lpat += (MAX_UNSIGNED) 1 << k;

				lpat &= reg.state[i];

				out.state[j] = (lpat >> 1) | rpat;
				out.amplitude[j] = reg.amplitude[i] * 1 / (float) sqrt(d);

				j++;
			}
		}

	}

	public static double quantumProbabilityInline(Complex complex) {
		double result = (complex.getReal() * complex.getReal()) + (complex.getImaginary() * complex.getImaginary());
		return result;
	}

}

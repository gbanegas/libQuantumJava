package libQ.register.utils;

import java.math.BigInteger;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

import libQ.register.QMeasurement;
import libQ.register.QReg;


/**
 * 
 * @author Gustavo Banegas
 *
 */
public class GateApplication {

	private static double EPSILON = 1e-6;
	
	//TODO: try to speed up here with parallelism

	/**
	 * 
	 * @param target
	 * @param m
	 * @param reg
	 * @throws UnexpectedException
	 */
	public static void applyQMatrix(int target, QMatrix m, QReg reg) throws UnexpectedException {
		int i, decsize = 0;
		int addsize = 0;
		double limit = 0;
		BigInteger iset, j;
		Complex t, tnot;
		BigInteger tmp_limit = BigInteger.ONE.shiftLeft(reg.getWidth());

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

		int k = reg.getSize();
		limit = (1.0 / (tmp_limit.doubleValue())) * EPSILON;

		List<Boolean> done = new ArrayList<>();
		for (i = 0; i < reg.getSize(); i++) {
			done.add(Boolean.FALSE);
		}
		for (i = 0; i < reg.getSize(); i++) {
			if (!done.get(i)) {
				/* determine if the target of the basis state is set */
				BigInteger tmp_1 = BigInteger.ONE.shiftLeft(target);

				iset = reg.getState().get(i).and(tmp_1);

				tnot = Complex.ZERO;

				BigInteger tmp_2 = reg.getState().get(i).xor(tmp_1);
				j = QuantumUtils.getQState(tmp_2, reg);
				// j = quantum_get_state(reg->state[i] ^ ((MAX_UNSIGNED) 1<<target), *reg);
				if (j.compareTo(BigInteger.ZERO) >= 0)
					tnot = reg.getAmplitude().get(j.intValue());

				t = reg.getAmplitude().get(i);

				if (iset.compareTo(BigInteger.ZERO) != 0) {
					Complex tmp_c1 = (m.getT()[2].multiply(tnot));
					Complex tmp_c2 = (m.getT()[3].multiply(t));
					Complex tmp_c3 = (tmp_c1.add(tmp_c2));
					reg.setAmplituteAtPosition(i, tmp_c3);
				} else {
					Complex tmp_c1 = (m.getT()[0].multiply(t));
					Complex tmp_c2 = (m.getT()[1].multiply(tnot));
					Complex tmp_c3 = (tmp_c1.add(tmp_c2));
					reg.setAmplituteAtPosition(i, tmp_c3);
					// reg->amplitude[i] = m.t[0] * t + m.t[1] * tnot;
				}

				if (j.compareTo(BigInteger.ZERO) >= 0) {
					if (iset.compareTo(BigInteger.ZERO) != 0) {
						Complex tmp_c1 = (m.getT()[0].multiply(tnot));
						Complex tmp_c2 = (m.getT()[1].multiply(t));
						Complex tmp_c3 = (tmp_c1.add(tmp_c2));
						reg.setAmplituteAtPosition(j.intValue(), tmp_c3);
						// reg->amplitude[j] = m.t[0] * tnot + m.t[1] * t;
					} else {
						Complex tmp_c1 = (m.getT()[2].multiply(t));
						Complex tmp_c2 = (m.getT()[3].multiply(tnot));
						Complex tmp_c3 = (tmp_c1.add(tmp_c2));
						reg.setAmplituteAtPosition(j.intValue(), tmp_c3);
						// reg->amplitude[j] = m.t[2] * t + m.t[3] * tnot;
					}
				} else /* new basis state will be created */
				{

					if ((Complex.equals(m.getT()[1], Complex.ZERO)) && (iset.compareTo(BigInteger.ZERO) != 0))
						break;
					if (((Complex.equals(m.getT()[2], Complex.ZERO))) && (iset.compareTo(BigInteger.ZERO) == 0))
						break;

					BigInteger tmp_t = BigInteger.ONE.shiftLeft(target);
					BigInteger tmp_tx = reg.getState().get(i).xor(tmp_t);

					// reg->state[k] = reg->state[i] ^ ((MAX_UNSIGNED) 1 << target);
					reg.setStateAtPosition(k, tmp_tx);

					if (iset.compareTo(BigInteger.ZERO) != 0) {
						Complex tmp_c1 = (m.getT()[1].multiply(t));
						reg.setAmplituteAtPosition(k, tmp_c1);
						// reg->amplitude[k] = m.t[1] * t;
					} else {
						Complex tmp_c1 = (m.getT()[2].multiply(t));
						reg.setAmplituteAtPosition(k, tmp_c1);
						// reg->amplitude[k] = m.t[2] * t;
					}

					k++;
				}

				if (j.intValue() >= 0)
					done.set(j.intValue(), Boolean.TRUE);
			}

		}
		reg.setSize(reg.getSize() + addsize);

		/* remove basis states with extremely small amplitude */

		if (reg.getHashw() > 0) {
			j = BigInteger.ZERO;
			for (i = 0; i < reg.getSize(); i++) {
				if (QMeasurement.quantumProbabilityInline(reg.getAmplitude().get(i)) < limit) {
					j = j.add(BigInteger.ONE);
					decsize++;
				}

				else if (j.intValue() > 0) {
					int result = i - j.intValue();
					reg.setStateAtPosition(result, reg.getState().get(i));
					reg.setAmplituteAtPosition(result, reg.getAmplitude().get(i));
					// reg->state[i-j] = reg->state[i];
					// reg->amplitude[i-j] = reg->amplitude[i];
				}
			}

			if (decsize > 0) {
				reg.setSize(reg.getSize() - decsize);
				Boolean isOkTrunkState = reg.trunkState();
				Boolean isOkTrunkAmplitute = reg.trunkAmplitute();
				if (!(isOkTrunkState && isOkTrunkAmplitute)) {
					throw new UnexpectedException("Error trunking state or amplitute");
				}
				
			}
		}
		
		QuantumUtils.quantumDecohere(reg);

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

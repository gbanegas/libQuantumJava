package libQ.register;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.complex.Complex;

import libQ.utils.QMeasurement;

public class QReg {
	private int width; /* number of qubits in the qureg */
	private int size; /* number of non-zero vectors */
	private int hashw; /* width of the hash array */
	private List<Complex> amplitude;
	private List<BigInteger> state;
	private List<BigInteger> hash;

	public QReg(BigInteger initval, int width) {
		this.width = width;
		this.size = 1;
		this.hashw = width + 2;
		BigInteger tmp = BigInteger.ONE.shiftLeft(hashw);
		hash = new ArrayList<>();
		for (BigInteger i = BigInteger.ZERO; !i.equals(tmp); i = i.add(BigInteger.ONE)) {
			hash.add(i);
		}

		this.amplitude = new ArrayList<>();
		this.state = new ArrayList<>();

		this.amplitude.add(Complex.ONE);
		for (int i = 1; i < size; i++) {
			this.amplitude.add(Complex.ZERO);
		}
		this.state.add(initval);

	}

	/*
	 * public QReg(int size, int width) { this.width = width; this.size = size;
	 * this.hashw = 0;
	 * 
	 * this.hash = BigInteger.ZERO;
	 * 
	 * this.amplitude = new ArrayList<>(); this.state = new ArrayList<>(); for(int i
	 * =0; i < size;i++) { this.amplitude.add(Complex.ZERO); } for(int i = 0; i <
	 * width; i++) this.state.add(BigInteger.ZERO);
	 * 
	 * // this.amplitude.add(Complex.ONE);
	 * 
	 * }
	 */
	public void quantum_print_qureg() {
		int i, j;

		for (i = 0; i < this.size; i++) {
			System.out.print("(");
			Double amplitude = this.amplitude.get(i).getReal();
			System.out.print(Double.parseDouble(String.format("%.7f", amplitude)));
			System.out.print(" |");
			for (j = this.width - 1; j >= 0; j--) {
				if (j % 4 == 3)
					System.out.print(" ");
				Boolean myBoolean = (((BigInteger.ONE.shiftLeft(j)).and(this.state.get(i)))
						.compareTo(BigInteger.ZERO) > 0);
				Integer result = (myBoolean) ? 1 : 0;
				System.out.print(result);
			}

			System.out.println(">)");
		}

		System.out.println();
	}

	public BigInteger measureQBitAtPosition(int position) {
		int i;
		BigInteger result = BigInteger.ZERO;
		double pa = 0, r;
		BigInteger pos2;

		pos2 = BigInteger.ONE.shiftLeft(position);

		/* Sum up the probability for 0 being the result */

		for (i = 0; i < this.size; i++) {
			BigInteger tmp = this.state.get(i).and(pos2);
			if ((tmp.compareTo(BigInteger.ZERO) == 0)) {
				pa += QMeasurement.quantumProbabilityInline(amplitude.get(i));
			}
		}

		/*
		 * Compare the probability for 0 with a random number and determine the result
		 * of the measurement
		 */

		r = quantum_frand();

		if (r > pa)
			result = BigInteger.ONE;

		QMeasurement.quantum_state_collapse(position, result, this);

		QMeasurement.quantum_delete_qureg_hashpreserve(this);

		return result;
	}

	public BigInteger measure() {
		double r;
		int i;

		/* Get a random number between 0 and 1 */

		r = quantum_frand();

		for (i = 0; i < this.size; i++) {
			/*
			 * If the random number is less than the probability of the given base state -
			 * r, return the base state as the result. Otherwise, continue with the next
			 * base state.
			 */

			r -= QMeasurement.quantumProbabilityInline(this.amplitude.get(i));
			if (0 >= r)
				return this.state.get(i);
		}
		return BigInteger.ZERO;
	}

	

	private double quantum_frand() {
		Random r = new Random();
		return r.nextDouble();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the hashw
	 */
	public int getHashw() {
		return hashw;
	}

	/**
	 * @param hashw
	 *            the hashw to set
	 */
	public void setHashw(int hashw) {
		this.hashw = hashw;
	}

	/**
	 * @return the amplitude
	 */
	public List<Complex> getAmplitude() {
		return amplitude;
	}

	/**
	 * @param amplitude
	 *            the amplitude to set
	 */
	public void setAmplitude(List<Complex> amplitude) {
		this.amplitude = amplitude;
	}

	/**
	 * @return the state
	 */
	public List<BigInteger> getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(List<BigInteger> state) {
		this.state = state;
	}

	/**
	 * @return the hash
	 */
	public List<BigInteger> getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(List<BigInteger> hash) {
		this.hash = hash;
	}

	public void setStateAtPosition(int index, BigInteger tmp) {
		if (index >= this.state.size()) {
			this.state.add(index, tmp);
		} else {
			this.state.set(index, tmp);
		}

	}

	public void setAmplituteAtPosition(int index, Complex tmp) {
		if (index >= this.amplitude.size()) {
			this.amplitude.add(index, tmp);
		} else {
			this.amplitude.set(index, tmp);
		}
	}

	public void setHashValueAtPosition(int index, BigInteger tmp) {
		if (index >= this.amplitude.size()) {
			this.hash.add(index, tmp);
		} else {
			this.hash.set(index, tmp);
		}

	}

	public Boolean trunkState() {
		this.state = this.state.subList(0, size);

		return true;

	}

	public Boolean trunkAmplitute() {
		this.amplitude = this.amplitude.subList(0, size);
		return true;
	}

}

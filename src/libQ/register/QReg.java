package libQ.register;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.complex.Complex;

public class QReg {
	private int width; /* number of qubits in the qureg */
	private int size; /* number of non-zero vectors */
	private int hashw; /* width of the hash array */
	private List<Complex> amplitude;
	private List<BigInteger> state;
	private BigInteger hash;

	public QReg(BigInteger initval, int width) {
		this.width = width;
		this.size = 1;
		this.hashw = width + 2;
		this.hash = BigInteger.ONE.shiftLeft(hashw);

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
			System.out.print(this.amplitude.get(i).getReal());
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

	public BigInteger measureQBitPosition(int position) {
		//TODO: implement
		return null;
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

			r -= quantum_prob_inline(this.amplitude.get(i));
			if (0 >= r)
				return this.state.get(i);
		}
		return BigInteger.ZERO;
	}

	private double quantum_prob_inline(Complex complex) {
		double result = (complex.getReal() * complex.getReal()) + (complex.getImaginary() * complex.getImaginary());
		return result;
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
	public BigInteger getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(BigInteger hash) {
		this.hash = hash;
	}

	public void setStateAtPosition(int i, BigInteger tmp_2) {
		this.state.set(i, tmp_2);

	}

}

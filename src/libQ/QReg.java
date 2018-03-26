package libQ;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
	void quantum_print_qureg() {
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

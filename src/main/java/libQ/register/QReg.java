package libQ.register;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

/**
 * 
 * @author Gustavo Banegas
 *
 */
public class QReg {
	private int width; /* number of qubits in the qureg */
	private int size; /* number of non-zero vectors */
	private int hashw; /* width of the hash array */
	private List<Complex> amplitude;
	private List<BigInteger> state;
	private List<BigInteger> hash;

	/**
	 * Constructor of Quantum Register
	 * 
	 * @param width
	 *            desired size of the quantum register
	 */
	public QReg(int width) {
		this.width = width;
		this.size = 1;
		this.hashw = width + 2;
		// BigInteger tmp = BigInteger.ONE.shiftLeft(hashw);
		hash = new ArrayList<>();

		if (width < 17) {
			BigInteger tmp = BigInteger.ONE.shiftLeft(hashw);

			for (BigInteger i = BigInteger.ZERO; i.compareTo(tmp) != 0; i = i.add(BigInteger.ONE)) {
				hash.add(i);
			}

		}

		this.amplitude = new ArrayList<>();
		this.state = new ArrayList<>();

		this.amplitude.add(Complex.ONE);
		for (int i = 1; i < size; i++) {
			this.amplitude.add(Complex.ZERO);
		}
		this.state.add(BigInteger.ZERO);

	}

	/**
	 * 
	 * @param initval
	 *            Initial value that the register will hold. The value will be
	 *            converted in binary.
	 * @param width
	 *            desired size of the quantum register
	 */
	public QReg(BigInteger initval, int width) {
		this.width = width;
		this.size = 1;
		this.hashw = width + 2;
		hash = new ArrayList<>();
		if (width < 17) {
			BigInteger tmp = BigInteger.ONE.shiftLeft(hashw);

			for (BigInteger i = BigInteger.ZERO; i.compareTo(tmp) != 0; i = i.add(BigInteger.ONE)) {
				hash.add(i);
			}

		}

		// threadHash.start();

		/*
		 * for (BigInteger i = BigInteger.ZERO; i.compareTo(tmp) != 0; i =
		 * i.add(BigInteger.ONE)) { hash.add(i); }
		 */

		this.amplitude = new ArrayList<>();
		this.state = new ArrayList<>();

		this.amplitude.add(Complex.ONE);
		for (int i = 1; i < size; i++) {
			this.amplitude.add(Complex.ZERO);
		}
		this.state.add(initval);

	}

	@Override
	public String toString() {
		int i, j;
		StringBuilder builder = new StringBuilder();
		for (i = 0; i < this.size; i++) {
			builder.append("(");
			// System.out.print("(");
			Double amplitude = this.amplitude.get(i).getReal();
			builder.append(Double.parseDouble(String.format("%.7f", amplitude)));
			// System.out.print(String.format("%.7f", amplitude));
			builder.append(" |");
			// System.out.print(" |");
			for (j = this.width - 1; j >= 0; j--) {
				if (j % 4 == 3)
					builder.append(" ");
				Boolean myBoolean = (((BigInteger.ONE.shiftLeft(j)).and(this.state.get(i)))
						.compareTo(BigInteger.ZERO) > 0);
				Integer result = (myBoolean) ? 1 : 0;
				builder.append(result);
				// System.out.print(result);
			}
			builder.append(">)");
			builder.append("\n");
			// System.out.println(">)");
		}

		return builder.toString();
	}

	/**
	 * Measured a qubit at the position. It will break the superposition of the
	 * entire register.
	 * 
	 * @param position
	 *            position to read the qubit
	 * @return value "1" or "0".
	 */
	public BigInteger measureQBitAtPosition(int position) {
		return QMeasurement.measureQBit(this, position);
	}

	/**
	 * Measure the register.
	 * 
	 * @return value in decimal for the content of the register.
	 */
	public BigInteger measure() {
		return QMeasurement.measure(this);
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

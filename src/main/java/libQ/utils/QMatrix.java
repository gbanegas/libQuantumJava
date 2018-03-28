package libQ.utils;

import org.apache.commons.math3.complex.Complex;

public class QMatrix {

	private int rows;
	private int cols;
	private Complex t[];
	
	public QMatrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		this.t = new Complex[rows*cols];
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return the t
	 */
	public Complex[] getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Complex[] t) {
		this.t = t;
	}
	
	

}

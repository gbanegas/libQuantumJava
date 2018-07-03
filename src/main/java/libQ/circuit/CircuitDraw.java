package libQ.circuit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.math.BigInteger;
import java.util.HashMap;

import libQ.register.QReg;

public class CircuitDraw extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QReg reg;
	private HashMap<TimeStampQuantum, QuantumOperation> history;

	public CircuitDraw(QReg reg, HashMap<TimeStampQuantum, QuantumOperation> history) {
		this.reg = reg;
		this.history = history;
	}

	public void paint(Graphics g) {
		String hexColor = new String("0x45e5B");
		g.setColor(Color.decode(hexColor));
		// draw a line (starting x,y; ending x,y)
		g.drawLine(30, 10, 100, 10);
		int l = 15;
		for (int i = 0; i < reg.getSize(); i++) {
			for (int j = reg.getWidth() - 1; j >= 0; j--) {

				Boolean myBoolean = (((BigInteger.ONE.shiftLeft(j)).and(reg.getState().get(i)))
						.compareTo(BigInteger.ZERO) > 0);
				Integer result = (myBoolean) ? 1 : 0;
				g.drawString(Integer.toString(result), 10, l);
				l = l + 15;
				// System.out.print(result);
			}
		}

	}

}

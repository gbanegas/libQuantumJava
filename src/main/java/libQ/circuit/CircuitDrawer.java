package libQ.circuit;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import libQ.register.QReg;

public class CircuitDrawer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintReg(List<BigInteger> initial_reg, QReg reg, HashMap<TimeStampQuantum, QuantumOperation> history) {
		CircuitDraw m = new CircuitDraw(initial_reg, reg, history);
		JFrame f = new JFrame("Twilight Zone");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(290, 325);
		f.setLocation(550, 25);
		f.setVisible(true);
		f.add(m);

	}

}

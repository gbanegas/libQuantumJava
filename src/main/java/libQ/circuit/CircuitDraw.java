package libQ.circuit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class CircuitDraw extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		String hexColor = new String("0x45e5B");
		g.setColor(Color.decode(hexColor));
		// draw a line (starting x,y; ending x,y)
		g.drawLine(10, 10, 40, 10);
	}

}

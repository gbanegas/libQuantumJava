package libQ.circuit.draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import libQ.circuit.QuantumOperation;
import libQ.circuit.TimeStampQuantum;
import libQ.register.QReg;

public class CircuitDraw extends Canvas {

	private static final int X_DISTANCE = 45;

	private static final int Y_DISTANCE = 15;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QReg reg;
	private HashMap<TimeStampQuantum, QuantumOperation> history;
	private List<BigInteger> initial_reg;

	public CircuitDraw(List<BigInteger> initial_reg, QReg reg, HashMap<TimeStampQuantum, QuantumOperation> history) {
		this.reg = reg;
		this.history = history;
		this.initial_reg = initial_reg;
	}

	public void paint(Graphics g) {
		// draw a line (starting x,y; ending x,y)
		this.setBackground(Color.WHITE);
		int l = Y_DISTANCE;
		int regSize = reg.getWidth() - 1;
		for (int i = 0; i < reg.getSize(); i++) {
			for (int j = reg.getWidth() - 1; j >= 0; j--) {
				Boolean myBoolean = (((BigInteger.ONE.shiftLeft(j)).and(initial_reg.get(i)))
						.compareTo(BigInteger.ZERO) > 0);
				Integer result = (myBoolean) ? 1 : 0;
				g.drawString(Integer.toString(regSize) + " | ", 0, l);
				g.drawString(Integer.toString(result), 30, l);
				myBoolean = (((BigInteger.ONE.shiftLeft(j)).and(reg.getState().get(i))).compareTo(BigInteger.ZERO) > 0);
				result = (myBoolean) ? 1 : 0;
				g.drawString(Integer.toString(result), 510, l);
				g.drawLine(X_DISTANCE, l - 5, 500, l - 5);
				l = l + Y_DISTANCE;
				regSize--;

				// System.out.print(result);
			}
		}
		List<TimeStampQuantum> listOperations = new ArrayList<TimeStampQuantum>(history.keySet());
		Collections.sort(listOperations);
		int posX = X_DISTANCE + Y_DISTANCE;
		for (int i = 0; i < listOperations.size(); i++) {
			QuantumOperation operation = history.get(listOperations.get(i));
			int posY_control1 = 0;
			int posY_target = 0;
			int posY_control2 = 0;
			switch (operation.getOperationType()) {
			case CNOTGATE:
				posY_control1 = getPosOfBit(reg.getWidth(), operation.getControl1());
				posY_target = getPosOfBit(reg.getWidth(), operation.getTarget());
				drawCNot(g, posX, posY_control1, posY_target);
				posX = posX + X_DISTANCE;

				break;
			case TOFFOLIGATE:
				posY_control1 = getPosOfBit(reg.getWidth(), operation.getControl1());
				posY_control2 = getPosOfBit(reg.getWidth(), operation.getControl2());
				posY_target = getPosOfBit(reg.getWidth(), operation.getTarget());
				drawToffoliGate(g, posX, posY_control1, posY_control2, posY_target);
				posX = posX + X_DISTANCE;
				break;

			case SWAPGATE:
				posY_control1 = getPosOfBit(reg.getWidth(), operation.getControl1());
				posY_target = getPosOfBit(reg.getWidth(), operation.getTarget());
				drawSwapGate(g, posX, posY_control1, posY_target);
				posX = posX + X_DISTANCE;
				break;
		
				
			default:
				break;
			}

		}

	}

	private void drawSwapGate(Graphics g, int posX, int posY_control1, int posY_target) {
		int radiusFilledDot = 2;
		Graphics2D g2d = (Graphics2D) g;

		Shape filleDot1 = new Ellipse2D.Double(posX - radiusFilledDot, posY_control1 - radiusFilledDot,
				2.0 * radiusFilledDot, 2.0 * radiusFilledDot);
		Shape filleDot2 = new Ellipse2D.Double(posX - radiusFilledDot, posY_target - radiusFilledDot,
				2.0 * radiusFilledDot, 2.0 * radiusFilledDot);

		g2d.fill(filleDot1);
		g2d.fill(filleDot2);
		g.drawLine(posX, posY_control1 + 2, posX, posY_target - 2);

		g.drawLine(posX - 4, posY_control1 - 4, posX + 4, posY_control1 + 4);
		g.drawLine(posX + 4, posY_control1 - 4, posX - 4, posY_control1 + 4);

		g.drawLine(posX - 4, posY_target - 4, posX + 4, posY_target + 4);
		g.drawLine(posX + 4, posY_target - 4, posX - 4, posY_target + 4);

	}

	private void drawToffoliGate(Graphics g, int posX, int posY_control1, int posY_control2, int posY_target) {
		// TODO Auto-generated method stub
		int radiusFilledDot = 3;
		int radiusHoledDot = 5;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		Shape filleDot1 = new Ellipse2D.Double(posX - radiusFilledDot, posY_control1 - radiusFilledDot,
				2.0 * radiusFilledDot, 2.0 * radiusFilledDot);
		Shape filleDot2 = new Ellipse2D.Double(posX - radiusFilledDot, posY_control2 - radiusFilledDot,
				2.0 * radiusFilledDot, 2.0 * radiusFilledDot);

		Shape holedDot = new Ellipse2D.Double(posX - radiusHoledDot, posY_target - radiusHoledDot - 1,
				2.0 * radiusHoledDot, 2.0 * radiusHoledDot);

		g2d.draw(holedDot);
		g2d.fill(filleDot1);
		g2d.fill(filleDot2);
		g.drawLine(posX, posY_control1, posX, posY_target - 6);
		g.drawLine(posX, posY_control2, posX, posY_target - 6);

	}

	private void drawCNot(Graphics g, int posX, int posY_control1, int posY_target) {
		int radiusFilledDot = 3;
		int radiusHoledDot = 5;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		Shape filleDot = new Ellipse2D.Double(posX - radiusFilledDot, posY_control1 - radiusFilledDot,
				2.0 * radiusFilledDot, 2.0 * radiusFilledDot);

		Shape holedDot = new Ellipse2D.Double(posX - radiusHoledDot, posY_target - radiusHoledDot - 1,
				2.0 * radiusHoledDot, 2.0 * radiusHoledDot);

		g2d.draw(holedDot);
		g2d.fill(filleDot);
		g.drawLine(posX, posY_control1, posX, posY_target - 6);

	}

	private int getPosOfBit(int width, int control1) {
		int result = (width * Y_DISTANCE) - (control1 * Y_DISTANCE);
		return result - 4;
	}

}

package libQ.circuit.draw;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import libQ.circuit.QuantumOperation;
import libQ.circuit.TimeStampQuantum;
import libQ.register.QReg;

public class CircuitDrawer {

	/**
	 * 
	 */

	public void paintReg(List<BigInteger> initial_reg, QReg reg, HashMap<TimeStampQuantum, QuantumOperation> history) {
		CircuitDraw m = new CircuitDraw(initial_reg, reg, history);

		JFrame frame = new JFrame("");
		frame.add(m);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(290, 325);
		// frame.setLocation(550, 25);
		frame.pack();
		frame.setVisible(true);

		// frame.setContentPane(pane);
		try {
			getSaveSnapShot(frame, "temp");
		} catch (Exception e) { // TODOAuto-generated catch block
			e.printStackTrace();
		}
	}

	private BufferedImage getScreenShot(Component component) {

		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		// paints into image's Graphics
		component.paintAll((image.getGraphics()));
		return image;
	}

	public void getSaveSnapShot(Component component, String fileName) throws Exception {
		BufferedImage img = getScreenShot(component);
		// write the captured image as a PNG
		ImageIO.write(img, "png", new File(fileName));
	}

}

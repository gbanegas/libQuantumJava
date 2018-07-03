package libQ.circuit;

import java.util.HashMap;

import libQ.exceptions.OperationNotPermittedException;
import libQ.gates.EGateTypes;
import libQ.gates.GateFactory;
import libQ.gates.IGate;
import libQ.register.QReg;

/**
 * Class for create a quantum circuit.
 * 
 * @author Gustavo Banegas
 *
 */
public class QuantumCircuit {

	private IGate h;
	private QReg reg = null;
	private HashMap<TimeStampQuantum, QuantumOperation> history;

	public QuantumCircuit(int width) {
		this.setReg(new QReg(width));
		history = new HashMap<>();
	}

	public QuantumCircuit(QReg reg) {
		this.setReg(reg);
		history = new HashMap<>();
	}

	public void addCNotGate(int controlQBit, int targetQBit) {
		h = GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		try {
			h.apply(reg, controlQBit, targetQBit);
			history.put(new TimeStampQuantum(System.currentTimeMillis()),
					new QuantumOperation(EGateTypes.CNOTGATE, controlQBit, targetQBit));
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}

	public void addToffoliGate(int controlQBit, int controlQBit2, int targetQBit) {
		h = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		try {
			h.apply(reg, controlQBit, controlQBit2, targetQBit);
			history.put(new TimeStampQuantum(System.currentTimeMillis()),
					new QuantumOperation(EGateTypes.TOFFOLIGATE, controlQBit, controlQBit2, targetQBit));
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}

	public void addSwapGate(int controlQBit, int targetQBit) {
		h = GateFactory.getInstance().getGate(EGateTypes.SWAPGATE);
		try {
			h.apply(reg, controlQBit, targetQBit);
			history.put(new TimeStampQuantum(System.currentTimeMillis()),
					new QuantumOperation(EGateTypes.SWAPGATE, controlQBit, targetQBit));
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}

	public void drawCircuit() {
		CircuitDrawer drawer = new CircuitDrawer();
		drawer.paintReg(reg, history);

	}

	public QReg getReg() {
		return reg;
	}

	private void setReg(QReg reg) {
		this.reg = reg;
	}

}

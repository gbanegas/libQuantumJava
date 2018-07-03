package libQ.circuit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private List<BigInteger> initial_reg = null;
	private HashMap<TimeStampQuantum, QuantumOperation> history;
	private long order;

	public QuantumCircuit(int width) {
		this.setReg(new QReg(width));
		history = new HashMap<>();
		order = 0;
		this.initial_reg = new ArrayList<>(reg.getState());
	}

	public QuantumCircuit(QReg reg) {
		this.setReg(reg);
		history = new HashMap<>();
		order = 0;
		this.initial_reg = new ArrayList<>(reg.getState());
	}

	public void addCNotGate(int controlQBit, int targetQBit) {

		h = GateFactory.getInstance().getGate(EGateTypes.CNOTGATE);
		try {
			h.apply(reg, controlQBit, targetQBit);
			history.put(new TimeStampQuantum(order),
					new QuantumOperation(EGateTypes.CNOTGATE, controlQBit, targetQBit));
			order++;
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}
	
	

	public void addToffoliGate(int controlQBit, int controlQBit2, int targetQBit) {
		h = GateFactory.getInstance().getGate(EGateTypes.TOFFOLIGATE);
		try {
			h.apply(reg, controlQBit, controlQBit2, targetQBit);
			history.put(new TimeStampQuantum(order),
					new QuantumOperation(EGateTypes.TOFFOLIGATE, controlQBit, controlQBit2, targetQBit));
			order++;
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}

	public void addSwapGate(int controlQBit, int targetQBit) {
		h = GateFactory.getInstance().getGate(EGateTypes.SWAPGATE);
		try {
			h.apply(reg, controlQBit, targetQBit);
			history.put(new TimeStampQuantum(order),
					new QuantumOperation(EGateTypes.SWAPGATE, controlQBit, targetQBit));
			order++;
		} catch (OperationNotPermittedException e) {
			e.printStackTrace();
		}

	}

	public void drawCircuit() {
		CircuitDrawer drawer = new CircuitDrawer();
		drawer.paintReg(initial_reg, reg, history);

	}

	public QReg getReg() {
		return reg;
	}

	private void setReg(QReg reg) {
		this.reg = reg;
	}

}

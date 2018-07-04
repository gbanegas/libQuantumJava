package libQ.circuit.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import libQ.circuit.QuantumCircuit;
import libQ.circuit.QuantumOperation;
import libQ.circuit.TimeStampQuantum;

public class CircuitOptmizer {

	QuantumCircuit qCircuit = null;

	public CircuitOptmizer(QuantumCircuit quantumCircuit) {
		this.qCircuit = quantumCircuit;
	}

	public void start() {
		/*
		 * ThreadStatus thrd = new ThreadStatus(); thrd.setName("ThreadStatus #1");
		 * thrd.start();
		 */
		// thrd.waiting = false;
		HashMap<TimeStampQuantum, QuantumOperation> quantumCircuitHist = qCircuit.getHistory();

		MutableGraph<Integer> operationDependencyGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
		for (int i = 0; i < qCircuit.getReg().getWidth(); i++) {
			operationDependencyGraph.addNode(i);
		}
		List<TimeStampQuantum> listOperations = new ArrayList<TimeStampQuantum>(quantumCircuitHist.keySet());
		for (int i = 0; i < listOperations.size(); i++) {
			QuantumOperation operation = quantumCircuitHist.get(listOperations.get(i));
			int controlBit1 = -1;
			int controlBit2 = -1;
			int targetBit = -1;
			switch (operation.getOperationType()) {

			case CNOTGATE:
				controlBit1 = operation.getControl1();
				targetBit = operation.getTarget();
				operationDependencyGraph.putEdge(targetBit, controlBit1);
				break;
			case TOFFOLIGATE:
				controlBit1 = operation.getControl1();
				controlBit2 = operation.getControl2();
				targetBit = operation.getTarget();
				operationDependencyGraph.putEdge(targetBit, controlBit2);
				operationDependencyGraph.putEdge(targetBit, controlBit1);
				break;

			case SWAPGATE:
				controlBit1 = operation.getControl1();
				targetBit = operation.getTarget();
				operationDependencyGraph.putEdge(targetBit, controlBit1);
				break;

			default:
				break;
			}
		}
		System.out.println(operationDependencyGraph);
		Set<MutableGraph<Integer>> parallelOperations = findIndependentGraphs(operationDependencyGraph);
		for (MutableGraph<Integer> graph : parallelOperations) {
			System.out.println(graph);
		}

		// thrd.notice();

	}

	private Set<MutableGraph<Integer>> findIndependentGraphs(MutableGraph<Integer> operationDependencyGraph) {
		HashSet<MutableGraph<Integer>> graphSet = new HashSet<MutableGraph<Integer>>();
		Set<Integer> graphNodes = operationDependencyGraph.nodes();

		for (Integer node : graphNodes) {
			Set<Integer> successors = operationDependencyGraph.successors(node);
			if (successors.size() == 0) {
				continue;
			}
			if (successors.size() == 1) {
				MutableGraph<Integer> tempGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
				tempGraph.addNode(node);
				Integer succesor = successors.iterator().next();
				tempGraph.addNode(succesor);
				tempGraph.putEdge(node, succesor);
				graphSet.add(tempGraph);

			} else {

				MutableGraph<Integer> tempGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
				tempGraph.addNode(node);
				Iterator<Integer> itSuccesors = successors.iterator();
				while (itSuccesors.hasNext()) {
					Integer succesor = itSuccesors.next();
					tempGraph.addNode(succesor);
					tempGraph.putEdge(node, succesor);
				}
				graphSet.add(tempGraph);
			}
		}
		return graphSet;
	}

}

package libQ.circuit.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import libQ.circuit.OperationNode;
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

		MutableGraph<OperationNode> operationDependencyGraph = GraphBuilder.directed().allowsSelfLoops(false).build();

		List<TimeStampQuantum> listOperations = new ArrayList<TimeStampQuantum>(quantumCircuitHist.keySet());
		for (int i = 0; i < listOperations.size(); i++) {
			QuantumOperation operation = quantumCircuitHist.get(listOperations.get(i));
			int controlBit1 = -1;
			int controlBit2 = -1;
			int targetBit = -1;
			OperationNode opNode1;
			OperationNode opNode2;
			OperationNode targetNode;
			switch (operation.getOperationType()) {

			case CNOTGATE:
				controlBit1 = operation.getControl1();
				targetBit = operation.getTarget();

				opNode1 = getNode(controlBit1, operation.getUniqueID(), operationDependencyGraph);
				if (opNode1 == null)
					opNode1 = new OperationNode(controlBit1, operation.getUniqueID());

				targetNode = getNode(targetBit, operation.getUniqueID(), operationDependencyGraph);
				if (targetNode == null)
					targetNode = new OperationNode(targetBit, operation.getUniqueID());

				operationDependencyGraph.putEdge(targetNode, opNode1);
				break;
			case TOFFOLIGATE:
				controlBit1 = operation.getControl1();
				controlBit2 = operation.getControl2();
				targetBit = operation.getTarget();

				opNode1 = getNode(controlBit1, operation.getUniqueID(), operationDependencyGraph);
				if (opNode1 == null)
					opNode1 = new OperationNode(controlBit1, operation.getUniqueID());

				opNode2 = getNode(controlBit2, operation.getUniqueID(), operationDependencyGraph);
				if (opNode2 == null)
					opNode2 = new OperationNode(controlBit2, operation.getUniqueID());

				targetNode = getNode(targetBit, operation.getUniqueID(), operationDependencyGraph);
				if (targetNode == null)
					targetNode = new OperationNode(targetBit, operation.getUniqueID());

				operationDependencyGraph.putEdge(targetNode, opNode1);
				operationDependencyGraph.putEdge(targetNode, opNode2);

				break;

			case SWAPGATE:
				controlBit1 = operation.getControl1();
				targetBit = operation.getTarget();

				opNode1 = getNode(controlBit1, operation.getUniqueID(), operationDependencyGraph);
				if (opNode1 == null)
					opNode1 = new OperationNode(controlBit1, operation.getUniqueID());

				targetNode = getNode(targetBit, operation.getUniqueID(), operationDependencyGraph);
				if (targetNode == null)
					targetNode = new OperationNode(targetBit, operation.getUniqueID());

				operationDependencyGraph.putEdge(targetNode, opNode1);
				break;

			default:
				break;
			}
		}

		System.out.println(operationDependencyGraph);
		Set<MutableGraph<OperationNode>> parallelOperations = findIndependentGraphs(operationDependencyGraph);
		if (parallelOperations.size() > 1) {
			/*for (MutableGraph<OperationNode> graph : parallelOperations) {
				
				System.out.println(graph);
			}*/
			HashMap<TimeStampQuantum, QuantumOperation> reorderedOperations = reorderOperations(quantumCircuitHist,
					parallelOperations);

			//qCircuit.setHistory(reorderedOperations);
		}

		// thrd.notice();

	}

	private OperationNode getNode(int controlBit, String uniqueID,
			MutableGraph<OperationNode> operationDependencyGraph) {
		Set<OperationNode> nodes = operationDependencyGraph.nodes();
		for (OperationNode node : nodes) {
			if (node.getqBitPos() == controlBit)
				return node;

		}
		return null;
	}

	private HashMap<TimeStampQuantum, QuantumOperation> reorderOperations(
			HashMap<TimeStampQuantum, QuantumOperation> quantumCircuitHist,
			Set<MutableGraph<OperationNode>> parallelOperations) {
		HashMap<TimeStampQuantum, QuantumOperation> reorderedOperations = new HashMap<>();
		ArrayList<MutableGraph<OperationNode>> sortedGraphs = new ArrayList<MutableGraph<OperationNode>>(parallelOperations);
		for(MutableGraph<?> graph: sortedGraphs) {
			System.out.println(graph);
		}
		/*
		 * reorderedOperations.put(new TimeStampQuantum(order), ); order++;
		 */
		return reorderedOperations;
	}

	private Set<MutableGraph<OperationNode>> findIndependentGraphs(
			MutableGraph<OperationNode> operationDependencyGraph) {
		HashSet<MutableGraph<OperationNode>> graphSet = new HashSet<MutableGraph<OperationNode>>();
		Set<OperationNode> graphNodes = operationDependencyGraph.nodes();

		for (OperationNode node : graphNodes) {
			Set<OperationNode> successors = operationDependencyGraph.successors(node);
			if (successors.size() == 0) {
				continue;
			}
			if (successors.size() == 1) {
				MutableGraph<OperationNode> tempGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
				tempGraph.addNode(node);
				OperationNode succesor = successors.iterator().next();
				tempGraph.addNode(succesor);
				tempGraph.putEdge(node, succesor);
				graphSet.add(tempGraph);

			} else {

				MutableGraph<OperationNode> tempGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
				tempGraph.addNode(node);
				Iterator<OperationNode> itSuccesors = successors.iterator();
				while (itSuccesors.hasNext()) {
					OperationNode succesor = itSuccesors.next();
					tempGraph.addNode(succesor);
					tempGraph.putEdge(node, succesor);
				}
				graphSet.add(tempGraph);
			}
		}
		return graphSet;
	}

}

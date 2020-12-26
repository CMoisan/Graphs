package Bellmanford;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;

import AdjacencyList.DirectedValuedGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;

public class Bellmanford {
	public static DirectedValuedGraph findShortestPathWithBellman(DirectedValuedGraph graph, DirectedNode source){

		int numberOfIteration = 0;
		
		numberOfIteration = graph.getNbArcs() - 1;

		int[] values = new int[graph.getNbNodes()];
		int[] predecessors = new int[graph.getNbNodes()];
		Arrays.fill(values, Integer.MAX_VALUE);
		Arrays.fill(predecessors, Integer.MAX_VALUE);
		values[source.getLabel()] = 0;
		predecessors[source.getLabel()] = source.getLabel();

		LinkedList<DirectedNode> queue = new LinkedList<>();
		queue.add(source);

		LinkedList<DirectedNode> updatedNodes = new LinkedList<>();

		for (int i = 0; i < numberOfIteration; i++) {
			while (!queue.isEmpty()) {
				DirectedNode currentNode = queue.poll();

				for(Entry<DirectedNode, Integer> adjacencyPair : currentNode.getSuccs().entrySet())
				{
					int totalCost = adjacencyPair.getValue() + values[currentNode.getLabel()];

					if (totalCost < values[adjacencyPair.getKey().getLabel()]) {
			            LinkedList<DirectedNode> shortestPath = new LinkedList<>(currentNode.getShortestPath());
			            shortestPath.add(currentNode);
						adjacencyPair.getKey().setShortestPath(shortestPath);
						updatedNodes.add(adjacencyPair.getKey());
					}
				}
			}

			queue = new LinkedList<>(updatedNodes);
			updatedNodes.clear();
		}

		while (!queue.isEmpty()) {
			DirectedNode currentNode = queue.poll();
			
			for(Entry<DirectedNode, Integer> adjacencyPair : currentNode.getSuccs().entrySet())
			{
				int totalCost = adjacencyPair.getValue() + values[currentNode.getLabel()];

				if (totalCost < values[adjacencyPair.getKey().getLabel()]) {
					updatedNodes.add(adjacencyPair.getKey());
				}
			}
		}

		return graph;
	}
	
	public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(6, false, false, true, false, 100001);
        DirectedValuedGraph g = new DirectedValuedGraph(mat);
		DirectedNode a = new DirectedNode(0);
		DirectedNode b = new DirectedNode(1);
		DirectedNode c = new DirectedNode(2);
		DirectedNode d = new DirectedNode(3);
		DirectedNode e = new DirectedNode(4);
		DirectedNode f = new DirectedNode(5);
        GraphTools.afficherMatrix(mat); 
        
        g.removeArc(a, d);
        g.removeArc(b, a);
        g.removeArc(c, f);
        g.removeArc(d, f);
        g.removeArc(e, a);
        
        g.addArc(a, b, 10);
        g.addArc(a, c, 15);
        g.addArc(b, d, 12);
        g.addArc(c, e, 10);
        g.addArc(b, f, 15);
        g.addArc(d, e, 2);
        g.addArc(d, f, 1);
        g.addArc(f, e, 5);
        System.out.println(g);
        GraphTools.afficherMatrix(g.toAdjacencyMatrix());        
        g = findShortestPathWithBellman(g, g.getNodeOfList(a));
        
        for(DirectedNode n : g.getNodes()) 
        {
        	System.out.println("Node "+n.getLabel()+"'s shortest path from source : "+n.getShortestPath());
        }
	}
}

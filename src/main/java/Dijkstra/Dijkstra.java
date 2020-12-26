package Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import AdjacencyList.DirectedValuedGraph;
import AdjacencyList.UndirectedGraph;
import AdjacencyList.UndirectedValuedGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;
import Nodes.UndirectedNode;

public class Dijkstra {
	
	public static DirectedValuedGraph calculateShortestPathFromSource(DirectedValuedGraph graph, DirectedNode source) {

        source.setDistance(0);

    	Set<DirectedNode> unsettledNodes = new HashSet<>();
    	Set<DirectedNode> settledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            DirectedNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<DirectedNode, Integer> adjacencyPair : graph.getNodeOfList(currentNode).getSuccs().entrySet()) {
                DirectedNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(DirectedNode evaluationNode, Integer edgeWeigh, DirectedNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DirectedNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static DirectedNode getLowestDistanceNode(Set<DirectedNode> unsettledNodes) {
        DirectedNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DirectedNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
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
        g = calculateShortestPathFromSource(g, g.getNodeOfList(a));
        
        for(DirectedNode n : g.getNodes()) 
        {
        	System.out.println("Node "+n.getLabel()+"'s shortest path from source : "+n.getShortestPath());
        }
        
		}
}
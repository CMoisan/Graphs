package profondeur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import AdjacencyList.UndirectedGraph;
import GraphAlgorithms.GraphTools;
import Nodes.UndirectedNode;
import utils.Sommet;

public class Profondeur {
	public static void explorerSommet(UndirectedNode s, List<Integer> a, Stack<UndirectedNode> b) 
	{
		a.add(s.getLabel());
		for (UndirectedNode t : s.getNeighbours().keySet()) 
		{
			if(!a.contains(t.getLabel())) 
			{
				b.push(t);
			}
		}
	}
	
	public static void explorerGraph(UndirectedGraph g, int Sommet) 
	{
		List<Integer> a = new ArrayList<Integer>();
		Stack<UndirectedNode> b = new Stack<UndirectedNode>();
		b.add(g.getNodes().get(Sommet));
		while (!b.isEmpty()) 
		{
			if(!a.contains(b.firstElement().getLabel())) {
			explorerSommet(b.pop(),a,b);
			}
		}
		showTrace(a);
	}
	
	public static void showTrace(List<Integer> a) 
	{		
		for(int s : a) 
		{
			System.out.print(s + " --> ");
		}
		System.out.print("END");
	}

	public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(6, 0, false, true, false, 100001);
        UndirectedGraph g = new UndirectedGraph(mat);
		UndirectedNode zero = new UndirectedNode(0);
		UndirectedNode un = new UndirectedNode(1);
		UndirectedNode deux = new UndirectedNode(2);
		UndirectedNode trois = new UndirectedNode(3);
		UndirectedNode quatre = new UndirectedNode(4);
		UndirectedNode cinq = new UndirectedNode(5);
        GraphTools.afficherMatrix(mat);
		g.addEdge(zero, deux);
		g.addEdge(quatre, deux);
		g.addEdge(zero, un);
		g.addEdge(un, trois);
		g.addEdge(un, cinq);
        GraphTools.afficherMatrix(mat);
		explorerGraph(g,0);
		}
}

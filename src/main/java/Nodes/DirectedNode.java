package Nodes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectedNode extends AbstractNode {

    //--------------------------------------------------
    // 				Class variables
    //--------------------------------------------------

    private Map<DirectedNode, Integer> succs;
    private Map<DirectedNode, Integer> preds;
    private List<DirectedNode> shortestPath;
    private Integer distance = Integer.MAX_VALUE;

    //--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------

    public DirectedNode(int i) {
        super(i);
        this.succs = new LinkedHashMap<>();
        this.preds = new LinkedHashMap<>();
        shortestPath = new LinkedList<>();
    }
    
    public DirectedNode(int i, int p) {
        super(i,p);
        this.succs = new LinkedHashMap<>();
        this.preds = new LinkedHashMap<>();
        shortestPath = new LinkedList<>();
    }

    // ------------------------------------------
    // 				Accessors
    // ------------------------------------------

    /**
     * @return the list of successors of the current node this
     */
    public Map<DirectedNode, Integer> getSuccs() {
        return this.succs;
    }

    /**
     * @return the list of predecessors of the current node this
     */
    public Map<DirectedNode, Integer> getPreds() {
        return preds;
    }

    /**
     * @return the number of successors of node this
     */
    public int getNbSuccs() {
        return succs.size();
    }

    /**
     * @return the number of predecessors of node this
     */
    public int getNbPreds() {
        return preds.size();
    }

    /**
     * @param suc the new list of successors for node this
     */
    public void setSuccs(Map<DirectedNode, Integer> suc) {
        this.succs = suc;
    }

    /**
     * @param pre the new list of predecessors for nodes this
     */
    public void setPreds(Map<DirectedNode, Integer> pre) {
        this.preds = pre;
    }

    /**
	 * add a new successor with its value cost. If the successor exists, the weight is changed.
	 */
	public void addSucc(DirectedNode v,int val) {
		this.succs.put(v, val);
	}
   
	/**
	 * add a new predecessor with its value cost. If the predecessor exists, the weight is changed.
	 */
	public void addPred(DirectedNode v,int val) {
		this.preds.put(v, val);
	}
	
/*	public Map<DirectedNode, Integer> getAdjacentNodes() 
	{
		Map<DirectedNode, Integer> adja = new HashMap<>();
		adja.putAll(this.preds);
		adja.putAll(this.succs);
		return adja;
	}*/
	
	public void setShortestPath (List<DirectedNode> list) 
	{
		this.shortestPath = list;
	}
	
	public void setShortestPath (DirectedNode node) 
	{
		this.shortestPath.add(node);
	}
	
	public List<DirectedNode> getShortestPath () 
	{
		return this.shortestPath;
	}
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}

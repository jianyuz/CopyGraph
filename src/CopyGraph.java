import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * how to clone a graph
 * @author zhouzhou
 *
 */
public class CopyGraph {

	public static void main(String[] args) throws CloneNotSupportedException{
		Node test= new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		test.getNeighbors().add(b);
		test.getNeighbors().add(c);
		b.getNeighbors().add(test);
		b.getNeighbors().add(c);
		d.getNeighbors().add(c);
		System.out.println("Original:\n" + test.printGraph());
		
		Node res = bfClone(test);
		
		System.out.println("-------------------------");
		System.out.println("result:\n" + res.printGraph());
		
		HashMap<Node, Node> visited = new HashMap<Node, Node>();
		
		res = dfClone(test, visited);
		
		System.out.println("-------------------------");
		System.out.println("result1:\n" + res.printGraph());
	}
	
	public static Node bfClone(Node graph) throws CloneNotSupportedException{
		if(null == graph) return null;
		
		Queue<Node> q = new ConcurrentLinkedQueue<Node>();
		HashMap<Node, Node> visited = new HashMap<Node, Node>();
		
		Node graphCopy = (Node)(graph.clone());
		q.add(graph);
		visited.put(graph, graphCopy);
		
		Node curNode, neighbor;
		while(!q.isEmpty()){
			curNode = q.poll();
			CopyOnWriteArrayList<Node> neighbors = curNode.getNeighbors();
			for(Iterator<Node> it = neighbors.iterator(); it.hasNext();){
				neighbor = (Node)it.next();
				if(!visited.containsKey(neighbor)){
					Node copy = (Node)neighbor.clone();
					//remember we have visited the code
					visited.put(neighbor, copy);
					visited.get(curNode).getNeighbors().add(copy);
					//add to queue for next round of scan
					q.add(neighbor);
				}else{
					visited.get(curNode).getNeighbors().add(visited.get(neighbor)); //same as add copy. just no queue operation.
				}
				
			}
		}
		
		return graphCopy;
	}
	
	
	/**
	 * recursive depth first copy
	 * tree traversal plus neighboring relationship copying
	 * not single direction traversal
	 * need to keep visited structure. the visited structure not a list but a map.
	 * 
	 * @param graph
	 * @param visited
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public static Node dfClone(Node graph, HashMap<Node, Node> visited) throws CloneNotSupportedException{
		if(null == graph) return null;
		
		Node graphCopy = (Node)graph.clone();
		visited.put(graph, graphCopy);
		CopyOnWriteArrayList<Node> neighbors = graph.getNeighbors();

		Node neighbor;
		for(Iterator<Node> it = neighbors.iterator(); it.hasNext();){
			neighbor = (Node)it.next();
			if(!visited.containsKey(neighbor)){
				Node copy = dfClone(neighbor, visited);
				visited.put(neighbor, copy);
				graphCopy.getNeighbors().add(copy);
			}else{
				graphCopy.getNeighbors().add(visited.get(neighbor));
			}
		}
		
		return graphCopy;
	}
}


import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


public class Node implements Cloneable {
	private CopyOnWriteArrayList<Node> neighbors;
	private String ID;
	
	public Node(String ID){
		this.ID = ID;
		this.neighbors = new CopyOnWriteArrayList<Node>();
	}
	
	
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Node copy= new Node(this.ID);
		return copy;
	}


	public CopyOnWriteArrayList<Node> getNeighbors() {
		return this.neighbors;
	}


	public void setNeighbors(CopyOnWriteArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}
		


	public String toString(){
		return ID;
	}
	
	public String printGraph(){
		StringBuilder sb = new StringBuilder();
		Queue<Node> q = new ConcurrentLinkedQueue<Node>();
		Set<Node> visited = new HashSet<Node>();
		q.add(this);
		visited.add(this);
		Node currNode, neighbor;
		while(!q.isEmpty()){
			currNode = q.poll();
			sb.append(currNode.getID());
			sb.append("-->");
			for(Iterator<Node> it = currNode.getNeighbors().iterator(); it.hasNext();){
				neighbor = (Node)it.next();
				sb.append(neighbor.getID() + " ");
				if(!visited.contains(neighbor)){
					q.add(neighbor);
				}
				visited.add(neighbor);
			}
			sb.append("\n");
			
		}
		return sb.toString();
	}
	
}

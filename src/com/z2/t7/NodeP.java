package com.z2.t7;

public class NodeP {

	private int id;
	private NodeP lChild;
	private NodeP rChild;
	private NodeP parent;
	
	public NodeP(int id){
		this(id, null, null);
		this.parent = null;
	}
	
	public NodeP(int id, NodeP l, NodeP r){
		this.id = id;
		this.lChild = l;
		this.rChild = r;
		if(this.lChild != null)
		this.lChild.setParent(this);
		if(this.rChild != null)
		this.rChild.setParent(this);
	}

	
	public NodeP getParent() {
		return parent;
	}

	public void setParent(NodeP parent) {
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NodeP getlChild() {
		return lChild;
	}

	public void setlChild(NodeP lChild) {
		this.lChild = lChild;
		this.lChild.setParent(this);
	}

	public NodeP getrChild() {
		return rChild;
	}

	public void setrChild(NodeP rChild) {
		this.rChild = rChild;
		this.rChild.setParent(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeP other = (NodeP) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NodeP [id=" + id + "]";
	}
	
	
	

}

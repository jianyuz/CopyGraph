package InMemoryFile;

import java.util.BitSet;
import java.util.Date;
import java.util.List;

/**
 * B+ tree fan out
 * reduce the number of IO operations to find element in tree.
 * btree grow split and recursively
 * it grows at root.
 * find bucket then insert
 * 
 * deletion
 * find leaf 
 * remove entry.
 * half full.
 * done.
 * if less.
 * redistrubte borrow from sibling.
 * 
 * allow range. no need to rebalance a lot.
 * all the leaf nodes at the same level
 * every node has at most m childern
 * every nonleaf node has at least m/2 children
 * internal nodes ordered set of elements an children.
 * bTree is used in file system quick random access a block.
 * adding linked list at bottome level avoid read block
 * 
 * @author zhouzhou
 *
 */
public class FileStore {

	public long getTotalSpace(){
		return 0;
	}
	
	public long getUnallocatedSpace(){
		return 0;
	}
	
	static class DataBlock{
		byte[] block = new byte[1024];
	}
	
	static class INode{
		List<DataBlock> dataBlocks;
	}
	
	static class MetaData{
		int size;
		Date created, last_modified;
	}
	
	static class InMemFile{
		String path;
		String otherData;
		List<INode> nodes;
	}
	
	static class InMemDirectory{
		List<InMemFile> files;
		String path;
		List<INode> nodes;
	}
	
	private DataBlock[] datablocks = new DataBlock[1000];
	private BitSet datablockUsed = new BitSet();
	
}

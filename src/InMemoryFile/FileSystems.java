package InMemoryFile;

import java.net.URI;

public abstract class FileSystems {

	public abstract Iterable<Path> getRootDirectories();
	
	public abstract Iterable<FileStore> getFileStores();

	public static FileSystem getDefault(){
		return null;
	}
	
	public static FileSystem getFileSystem(URI uri){
		return null;
	}
}

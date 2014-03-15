package InMemoryFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.channels.ByteChannel;
import java.util.List;
import java.util.Map;

public class Files {
	public static boolean exists(Path path){
		return true;
	}
	
	public static boolean notExists(Path path){
		return true;
	}
	
	public static boolean isReadable(Path p){
		return true;
	}
	
	public static boolean isWritable(Path p){
		return true;
	}
	
	public static boolean isExecutable(Path p){
		return true;
	}
	
	public static boolean isRegularFile(Path p){
		return true;
	}
	
	public static boolean isSymbolicLink(Path p){
		return true;
	}
	
	public static boolean isSameFile(Path p1, Path p2){
		return true;
	}
	
	public boolean deleteFile(Path p){
		return true;
	}
	
	public boolean copy(Path s, Path d){
		return true;
	}
	
	public boolean move(Path s, Path d){
		return true;
	}
	
	public static FileTime  getLastModifiedTime(Path path){
		return null;
	}
	
	public static boolean isDirectory(Path path){
		return true;
	}
	
	public UserPrinciple getOwner(Path path){
		return null;
	}
	
	public Object getAttribute(Path path, String attribute){
		return null;
	}
	
	public static Map<String, Object> readAttributes(Path path, String attributes){
		return null;
	}
	
	public FileStore getFileStore(Path path){
		return null;
	}

	public byte[] readAllBytes(Path path){
		return null;
	}
	
	public List<String> readAllLines(Path path){
		return null;
	}
	
	public void write(Path path, byte[] bytes){
		return;
	}
	
	public static BufferedReader newBufferedRead(Path path){
		return null;
	}
	
	public static InputStream newInputStream(Path path){
		return null;
	}
	
	public static ByteChannel newByteChannel(Path path){
		return null;
	}
	
	public static Path createFile(Path path){
		return null;
	}
	
	public static Path createTempFile(Path path){
		return null;
	}
	
	public static Path createDirectory(Path dir){
		return null;
	}
	
	public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob){
		return null;
	}
	
	public static void createSymbolicLink(Path link, Path target){
		
	}
	
	public static Path walkFileTree(Path start, FileVisitor fv){
		return null;
	}
}

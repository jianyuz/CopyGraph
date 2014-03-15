package InMemoryFile;

public interface FileVisitor {

	void postVisitDirectory();
	void preVisitDirectory();
	void visideFile();
	void visitFileFailed();
}

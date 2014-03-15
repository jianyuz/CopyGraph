package InMemoryFile;

import java.util.Iterator;

public interface DirectoryStream<T> {

	Iterator<T> iterator();
}

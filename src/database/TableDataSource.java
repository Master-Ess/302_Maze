package database;

import java.io.IOException;
import java.util.Set;

public interface TableDataSource {
	void addSaveFile(SaveFile file) throws IOException;
	
	SaveFile getSaveFile(String fileName) throws IOException, ClassNotFoundException;
	
	int getSize();
	
	void deleteSaveFile(String fileName);
	
	void close();
	
	Set<String> fileNameSet();
	
	void updateSaveFile(SaveFile fileName);
}

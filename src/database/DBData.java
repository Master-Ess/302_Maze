package database;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class DBData {
	DefaultListModel listModel;
	DataTable data;
	
	public DBData(){
		listModel = new DefaultListModel();
		data = new DataTable();
		
		for (String filename : data.fileNameSet()) {
			listModel.addElement(filename);
		}
	}
	
	public void add(SaveFile file) {
		listModel.addElement(file.getFileName());
		try {
			data.addSaveFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove(Object fileName) {
		listModel.removeElement(fileName);
		data.deleteSaveFile((String)fileName);
	}
	
	public void persist() {
		data.close();
	}
	
	public SaveFile get(String fileName) {
		try {
			return data.getSaveFile(fileName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ListModel getModel() {
		return listModel;
	}
	
	public int getSize() {
		return data.getSize();
	}
}

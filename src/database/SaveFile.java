package database;

import java.io.Serializable;

import util.MazeDataStructure;

public class SaveFile implements Serializable{
	private static final long serialVersionUID = -7092701502990374424L;

	   private String filename;

	   private String author;

	   private String company;

	   private MazeDataStructure data;
	   
	   private String created_on;
	   
	   private String edited_on;

	public MazeDataStructure getData() {
		return data;
	}

	public void setData(MazeDataStructure Data) {
		data = Data;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String Company) {
		company = Company;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String Author) {
		author = Author;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String FileName) {
		filename = FileName;
	}

	public String getEdited_on() {
		return edited_on;
	}

	public void setEdited_on(String edited_on) {
		this.edited_on = edited_on;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

}

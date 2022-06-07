package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

import util.MazeDataStructure;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class DataTable implements TableDataSource{
	
	public static final String CREATE_TABLE =
	           "CREATE TABLE IF NOT EXISTS saves ("
	                   + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
	                   + "filename VARCHAR(30),"
	                   + "author VARCHAR(30),"
	                   + "company VARCHAR(20),"
	                   + "data BLOB,"
	                   + "created_on VARCHAR(10),"
	                   + "edited_on VARCHAR(10));";

	   private static final String INSERT_SAVEFILE = "INSERT INTO saves (filename, author, company, created_on, edited_on, data) VALUES (?, ?, ?, ?, ?, ?);";

	   private static final String GET_SAVES = "SELECT filename FROM saves";

	   private static final String GET_SAVEFILE = "SELECT * FROM saves WHERE filename=?";

	   private static final String DELETE_SAVEFILE = "DELETE FROM saves WHERE filename=?";

	   private static final String COUNT_ROWS = "SELECT COUNT(*) FROM saves";
	   
	   private static final String UPDATE_SAVEFILE = "UPDATE saves SET edited_on = ?, data = ? WHERE filename = ?";

	   private Connection connection;

	   private PreparedStatement addSaveFile;

	   private PreparedStatement getSavesList;

	   private PreparedStatement getSaveFile;

	   private PreparedStatement deleteSaveFile;

	   private PreparedStatement rowCount;
	   
	   private PreparedStatement updateSaveFile;

	   public DataTable() {
	      connection = DBConnection.getInstance();
	      try {
	         Statement st = connection.createStatement();
	         st.execute(CREATE_TABLE);
	         /* BEGIN MISSING CODE */
	         addSaveFile = connection.prepareStatement(INSERT_SAVEFILE);
	         getSavesList = connection.prepareStatement(GET_SAVES);
	         getSaveFile = connection.prepareStatement(GET_SAVEFILE);
	         deleteSaveFile = connection.prepareStatement(DELETE_SAVEFILE);
	         rowCount = connection.prepareStatement(COUNT_ROWS);
	         updateSaveFile = connection.prepareStatement(UPDATE_SAVEFILE);
	         /* END MISSING CODE */
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	   }

	public void addSaveFile(SaveFile file) throws IOException {
		ByteArrayOutputStream bAout = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(bAout);
		try {
			objOut.writeObject(file.getData());
			objOut.flush();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now();  
			ByteArrayInputStream input = new ByteArrayInputStream(bAout.toByteArray());
			addSaveFile.setString(1,file.getFileName());
			addSaveFile.setString(2,file.getAuthor());
			addSaveFile.setString(3,file.getCompany());
			addSaveFile.setString(4, dtf.format(now));
			addSaveFile.setString(5, dtf.format(now));
			addSaveFile.setBytes(6, bAout.toByteArray());
			addSaveFile.execute();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			objOut.close();
            bAout.close();
		}
	}

	public SaveFile getSaveFile(String fileName) throws IOException, ClassNotFoundException {
		SaveFile file = new SaveFile();
		ObjectInputStream objIn = null;
		ByteArrayInputStream bIn = null;
		ResultSet brs = null;
		ResultSet rs = null;
		try {
			getSaveFile.setString(1, fileName);
			rs = getSaveFile.executeQuery();
			rs.next();
			file.setFileName(rs.getString("filename"));
			file.setAuthor(rs.getString("author"));
			file.setCompany(rs.getString("company"));
			file.setEdited_on(rs.getString("edited_on"));
			file.setCreated_on(rs.getString("created_on"));
			
			if(brs.next()) {
				Blob mazeBlob = brs.getBlob("data");
				bIn = new ByteArrayInputStream(mazeBlob.getBytes(1, (int)mazeBlob.length()));
				objIn = new ObjectInputStream(bIn);
				file.setData((MazeDataStructure) objIn.readObject());
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			objIn.close();
			bIn.close();
		}
		return file;
		
	}

	public int getSize() {
		ResultSet rs = null;
		int rows = 0;
		try {
			rs = rowCount.executeQuery();
			rs.next();
			rows = rs.getInt(1);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return rows;
	}

	public void deleteSaveFile(String fileName) {
		try {
			deleteSaveFile.setString(1, fileName);
			deleteSaveFile.executeQuery();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
	    	ex.printStackTrace();
	    }		
	}

	public Set<String> fileNameSet() {
		Set<String> names = new TreeSet<String>();
	    ResultSet rs = null;
		try {
			rs = getSavesList.executeQuery();
			while(rs.next()) {
				names.add(rs.getString("filename"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return names;
	}

	public void updateSaveFile(SaveFile file) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now(); 
			updateSaveFile.setString(1, dtf.format(now));
			updateSaveFile.setBlob(2, (Blob) file.getData());
			updateSaveFile.setString(3, file.getFileName());
			updateSaveFile.executeQuery();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}

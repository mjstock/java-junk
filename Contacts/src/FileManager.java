import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/*
 * A basic file manager class. All the functions needed in the reading and writing
 * of files.
 */
/**
 * 
 * @author Morgan Stock
 *
 */
public class FileManager {
	//Class variables 
	private FileReader reader;
	private BufferedReader bufferedReader;
	private FileWriter writer;
	private BufferedWriter bufferedWriter;
	private String filePathName;
	private boolean isOpenForRead = false;
	private boolean isOpenForWrite = false;
	
	//constructor
	public FileManager(String file){
		this.filePathName = file;
	}
	
	//read all records
	public ArrayList<String> readAll(){
		this.closeWriteFile();
		this.openForRead();
		ArrayList<String>recordList = new ArrayList<String>();
		try{
			String record = null;
			while((record = bufferedReader.readLine())!=null){
				recordList.add(record);
			}
		}catch(IOException ex){
			JOptionPane.showMessageDialog(null, "Read All Error","File",JOptionPane.ERROR_MESSAGE);
		}
		this.closeReadFile();
		return recordList;
		
	}

	// write record to file
	public void write(String record) {
		if (!this.isOpenForWrite) {
			this.openForWrite();
		}
		try {
			bufferedWriter.write(record);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Write Error", "File",
					JOptionPane.ERROR_MESSAGE);
		}
		
		this.closeWriteFile();

	}

	// read record from file
	private void openForRead() {
		try {
			reader = new FileReader(filePathName);
			bufferedReader = new BufferedReader(reader);
			isOpenForRead = true;
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "File not Found", "File",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	// open file for write
	private void openForWrite() {
		try {
			writer = new FileWriter(filePathName, true);
			bufferedWriter = new BufferedWriter(writer);
			isOpenForWrite = true;
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "File Open Error", "File",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// close write file
	private void closeWriteFile() {
		if (isOpenForWrite) {
			try {
				bufferedWriter.close();
				isOpenForWrite = false;
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Close for Write Error",
						"File", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// close read file
	private void closeReadFile() {
		if (isOpenForRead) {
			try {
				bufferedReader.close();
				isOpenForRead = false;
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Close for Read Error",
						"File", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}

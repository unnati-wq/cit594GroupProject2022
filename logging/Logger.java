package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	private FileWriter filewrtr;
	private PrintWriter printwrtr;
	private boolean fileSet=false;
	
	private static Logger log= new Logger();
	
	private Logger () {
	
	}
	
	public void write(String STR) {
		if (fileSet==true) {
			printwrtr.println(System.currentTimeMillis()+" "+STR);
		}
		else {
			System.err.println(System.currentTimeMillis()+" "+STR);
		}
	
	}
	
	public static Logger getinstance() {
		
		return log;
	}
	
	public void FileSet(String filename) {
		if (filename==null || filename.isEmpty()) {
			fileSet=false;
			return;
		}
		FileClose();
		try {
			File file=new File(filename);
			filewrtr= new FileWriter(file);
			printwrtr= new PrintWriter(filewrtr);
			file.createNewFile();
			fileSet=true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void FileClose() {
		try {
			if (filewrtr!=null) {
				filewrtr.close();
			}
			
			if (printwrtr!=null) {
				printwrtr.close();
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

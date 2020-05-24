package se.unlogic.standardutils.csv;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;


public class CSVWriter implements Closeable{

	private final Writer writer;
	private final String delimiter;
	private final int columns;
	
	public CSVWriter(Writer writer, String delimiter, int columns) {

		super();
		this.writer = writer;
		this.delimiter = delimiter;
		this.columns = columns;
	}
	
	public CSVRow createRow(){
		
		return new CSVRow(columns);
	}
	
	public void writeRow(CSVRow row) throws IOException{
		
		for(String cell : row.getCells()){
			
			if(cell != null){
				
				if(cell.contains(delimiter) || cell.contains("\n") || cell.contains("\r")){
					
					writer.write("\"");
					writer.write(cell.replaceAll("\"", ""));
					writer.write("\"");
				
				}else{
					
					writer.write(cell);
				}
			}
			
			writer.write(delimiter);
		}
		
		writer.write("\n");
	}
	
	@Override
	public void close() throws IOException{
		
		writer.close();
	}
}

package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.PopulationData;

public class PopulationReader {
	
	String CSVFileName;
	Logger CSVlogger= Logger.getinstance();
	
	
	public PopulationReader (String CSVFileName) {
		this.CSVFileName = CSVFileName;
	}
	public Map<String, Integer> read(){
		
		// Hashmap of zip codes with their assoicated population data
		Map<String, Integer> populationDataMap=new HashMap<String, Integer>();
		
		try(BufferedReader bufferedReader= new BufferedReader (new FileReader (CSVFileName))) {
			CSVlogger.write(CSVFileName);
			Map<String, Integer> headerMap=Parser.headerSort(bufferedReader.readLine());
			
			String line;
			int population;
			String zipCode;
			
			
			while((line=bufferedReader.readLine())!=null) {
				
				List<String> CSVLines= Parser.CSVparser(line);
				
				if (headerMap.containsKey("zip_code")){
					zipCode=CSVLines.get(headerMap.get("zip_code"));
					int zip;
					if(zipCode.length() == 5) {
						try {
						population=Integer.parseInt(CSVLines.get(headerMap.get("population")));
						zip = Integer.parseInt(zipCode);
						}
						catch (NumberFormatException e) {
							continue;
						}
						PopulationData pop= new PopulationData (zipCode,population);
						if (!populationDataMap.containsKey(zipCode)) {
							populationDataMap.put(zipCode, population);
						}
						
					}
				}
			}
		}
			
			
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return populationDataMap;
		
	}

}

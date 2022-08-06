package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.CovidData;

public class CSVReader extends Reader {
	String CSVFileName;
	Logger CSVlogger= Logger.getinstance();
	
	
	public CSVReader (String CSVFileName) {
		this.CSVFileName = CSVFileName;
	}
	@Override
	public Map<String, HashMap<String, CovidData>> read(){
		
		//Nested Map. <String of Timestamp& date, nested Hashmap of zip codes with their assoicated covid data>
		Map<String, HashMap<String, CovidData>> covidDataMap=new HashMap<String, HashMap<String, CovidData>>();
		
		try(BufferedReader bufferedReader= new BufferedReader (new FileReader (CSVFileName))) {
			CSVlogger.write(CSVFileName);
			Map<String, Integer> headerMap=Parser.headerSort(bufferedReader.readLine());
			
			String line;
			long covidNeg;
			long covidPos;
			long deaths;
			long hospitalized;
			long partVax;
			long fullVax;
			long boostedVax;
			String timeStamp;
			String zipCode;
			
			
			while((line=bufferedReader.readLine())!=null) {
				
				List<String> CSVLines= Parser.CSVparser(line);
				
				if (headerMap.containsKey("etl_timestamp") && headerMap.containsKey("zip_code")){
					zipCode=CSVLines.get(headerMap.get("zip_code"));
					timeStamp=CSVLines.get(headerMap.get("etl_timestamp"));
					
					if (!zipCode.isEmpty() && zipCode.matches("^\\d{5,}") && !timeStamp.isEmpty()) {
															
						timeStamp=timeStamp.substring(0,10);
						
						Map<String, Long> rowMap=new HashMap<String, Long>();
						
						for (String str: headerMap.keySet()) {
							CSVLines.get(headerMap.get(str));
							long value;
							try {
								value=Long.parseLong(CSVLines.get(headerMap.get(str)));
							}
							catch (NumberFormatException e) {
								value=0;
							}
							rowMap.put(str, value);
							
						}
		
						CovidData covidEntry= new CovidData (rowMap.get("NEG"), rowMap.get("POS"), rowMap.get("deaths"), rowMap.get("hospitalized"), rowMap.get("partially_vaccinated"), rowMap.get("fully_vaccinated"),rowMap.get("boosted"));
						
						if (!covidDataMap.containsKey(timeStamp)) {
							covidDataMap.put(timeStamp, new HashMap<String, CovidData>());
							covidDataMap.get(timeStamp).put(zipCode, covidEntry);
						}
						else {
							covidDataMap.get(timeStamp).put(zipCode, covidEntry);
						}
					}
				}
			
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return covidDataMap;
		
	}
	
}

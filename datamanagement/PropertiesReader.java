package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.CovidData;
import edu.upenn.cit594.util.PropertiesData;

public class PropertiesReader {
	String CSVFileName;
	Logger CSVlogger= Logger.getinstance();
	
	
	public PropertiesReader (String CSVFileName) {
		this.CSVFileName = CSVFileName;
	}
	public Map<String, PropertiesData> read(){
		
		
		Map<String, PropertiesData> PropertiesDataMap=new HashMap<String,PropertiesData>();
		
		try(BufferedReader bufferedReader= new BufferedReader (new FileReader (CSVFileName))) {
			CSVlogger.write(CSVFileName);
			Map<String, Integer> headerMap=Parser.headerSort(bufferedReader.readLine());
			
			String line;
			long marketValue;
			long totalLiveableArea;
			String zipCode;
			
			while((line=bufferedReader.readLine())!=null) {
				
				List<String> CSVLines= Parser.CSVparser(line);
				
				if (headerMap.containsKey("zip_code")){
					zipCode=CSVLines.get(headerMap.get("zip_code"));
					marketValue = Long.parseLong(CSVLines.get(headerMap.get("market_value")));
					totalLiveableArea = Long.parseLong(CSVLines.get(headerMap.get("total_livable_area")));
					if (!zipCode.isEmpty() && zipCode.length() >= 5) {
						zipCode = zipCode.substring(0,5);
						long value;								
						try {
							value=Long.parseLong(zipCode);
						}
						catch (NumberFormatException e) {
							continue;
						}
						PropertiesData propData = new PropertiesData(marketValue, totalLiveableArea, zipCode);
						if(!PropertiesDataMap.containsKey(zipCode)) {
							PropertiesDataMap.put(zipCode, propData);
						}
						else {
							PropertiesDataMap.get(zipCode).getdata().add(propData);
						}
					}
		
						
						
					}
				}
			
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return PropertiesDataMap;
		
	}

}

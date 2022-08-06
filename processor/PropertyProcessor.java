package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.PropertiesData;

public class PropertyProcessor {
	
	int avgMV;
	int avgLA;
	int MVperCapita;
	
	public int avgMarketValue(String FileName) {
		PropertiesReader read = new PropertiesReader(FileName);
		Map<String, PropertiesData> PropertiesDataMap=new HashMap<String,PropertiesData>();
		
		return avgMV;
		
	}
	
	public int avgLivableArea() {
		
		return avgLA;
		
	}
	
	public int MVperCapita() {
		
		return MVperCapita;
		
	}

}

package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.PropertiesData;

public class PropertyProcessor {
	
	long avgMV = 0;
	long avgLA;
	long MVperCapita;
	
	public long avgMarketValue(String FileName, String zipCode) {
		PropertiesReader read = new PropertiesReader(FileName);
		Map<String, PropertiesData> PropertiesDataMap=new HashMap<String,PropertiesData>();
		PropertiesDataMap = read.read();
		for (String zip : PropertiesDataMap.keySet()) {
			if(zip.equals(zipCode)) {
				PropertiesData data = PropertiesDataMap.get(zip);
				ArrayList <PropertiesData> propdata = new ArrayList <PropertiesData> ();
				propdata = data.getdata();
				long totalprop = 0;
				long mv;
				long sum = 0;
				for(int i = 0; i<propdata.size(); i++) {
					PropertiesData inddata = propdata.get(i);
					mv = inddata.getMarketValue();
					if(mv == 0) {
						continue;
					}
					else {
						totalprop++;
						sum = sum + mv; 
					}
					
				}
				avgMV = sum/totalprop;
				return avgMV;
			}
			
		}
		return avgMV;
		
		
	}
	
	public long avgLivableArea() {
		
		return avgLA;
		
	}
	
	public long MVperCapita() {
		
		return MVperCapita;
		
	}

}

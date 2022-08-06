package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationReader;

public class Populationprocessor {
	
	int sum = 0;
		
	public int totalpop(String FileName) {
		
		PopulationReader read = new PopulationReader(FileName);
		Map<String, Integer> populationDataMap=new HashMap<String, Integer>();
		populationDataMap = read.read();
		for (String zip : populationDataMap.keySet()) 
        {
 
            int pop = populationDataMap.get(zip);
            sum = sum + pop;
        }

		
		
		return sum;
		
	}

}

package edu.upenn.cit594.datamanagement;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.util.CovidData;

public abstract class Reader {

	public abstract Map<String, HashMap<String, CovidData>> read();
	
}

package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.CovidData;


public class JSONReader extends Reader {
	String JSONFileName;
	Logger JSONlogger= Logger.getinstance();
	public JSONReader (String JSONFileName) {
		this.JSONFileName = JSONFileName;
	}
	@Override
	public Map<String, HashMap<String, CovidData>> read(){
		
		//Nested Map. <String of Timestamp& date, nested Hashmap of zip codes with their assoicated covid data>
		Map<String, HashMap<String, CovidData>> covidDataMap=new HashMap<String, HashMap<String, CovidData>>();
		
		try(FileReader fileReader = new FileReader (new File (JSONFileName))) {
			JSONlogger.write(JSONFileName);
			Object JSONData= new JSONParser().parse(fileReader);
			JSONArray JArray=(JSONArray) JSONData;
			for (int i=0; i<JArray.size();i++) {
				JSONObject line=(JSONObject)JArray.get(i);
				if (line.containsKey("zip_code")&& line.containsKey("etl_timestamp")) {
					long covidNeg=(Long)line.getOrDefault("NEG",0);
					long covidPos=(Long)line.getOrDefault("POS",0);
					long deaths=(Long)line.getOrDefault("deaths",0);
					long hospitalized=(Long)line.getOrDefault("hospitalized",0);
					long partVax=(Long)line.getOrDefault("partially_vaccinated",0);
					long fullVax=(Long)line.getOrDefault("fully_vaccinated",0);
					long boostedVax=(Long)line.getOrDefault("boosted",0);
					String zipCode= (String)line.get("zip_code");
					String timeStamp= (String)line.get("etl_timestamp");
					timeStamp=timeStamp.substring(0,10);
					CovidData entry=new CovidData(covidNeg, covidPos, deaths, hospitalized, partVax, fullVax, boostedVax);
				if (!covidDataMap.containsKey(timeStamp)) {
					covidDataMap.put(timeStamp, new HashMap<String, CovidData>());
					covidDataMap.get(timeStamp).put(zipCode, entry);
				}
				else {
					covidDataMap.get(timeStamp).put(zipCode, entry);
				}
				}
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return covidDataMap;
		
	}
	}
package edu.upenn.cit594.util;

public class PopulationData {
	
	String zip;
	
	int population;
	
	public PopulationData(String zip, int population) {
		
		this.zip = zip;
		this.population = population;
		
	}
	
	public int getPopulation() {
		return population;
	}

}

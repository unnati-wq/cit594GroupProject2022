package edu.upenn.cit594.util;

public class CovidData {
	
	long covidNeg;
	long covidPos;
	long deaths;
	long hospitalized;
	long partVax;
	long fullVax;
	long boostedVax;
	
	public CovidData(long covidNeg, long covidPos, long deaths,	long hospitalized, long partVax, long fullVax, long boostedVax) {
		this.covidNeg=covidNeg;
		this.covidPos=covidPos;
		this.deaths=deaths;
		this.hospitalized=hospitalized;
		this.partVax=partVax;
		this.fullVax=fullVax;
		this.boostedVax=boostedVax;
	}

	public long getCovidNeg() {
		return covidNeg;
	}

	public long getCovidPos() {
		return covidPos;
	}

	public long getDeaths() {
		return deaths;
	}

	public long getHospitalized() {
		return hospitalized;
	}

	public long getPartVax() {
		return partVax;
	}

	public long getFullVax() {
		return fullVax;
	}

	public long getBoostedVax() {
		return boostedVax;
	}

}

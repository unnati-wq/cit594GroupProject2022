package edu.upenn.cit594.datamanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.util.CovidData;

public class Parser {
	
	public static Map<String, Integer> headerSort(String str){
		List<String> headerList= Parser.CSVparser(str);
		Map<String, Integer> headerSort  = new HashMap<String, Integer>();
		for (int i=0; i<headerList.size(); i++) {
			headerSort.put(headerList.get(i), i);
		}
		return headerSort;
	}
	
	public static List<String> CSVparser(String CSVLine) {
		boolean quote=false;
		int charindex=0;
		String word;
		ArrayList<String> str = new ArrayList<String>();
		
		for (int i=0; i<CSVLine.length(); i++) {
			if (CSVLine.charAt(i)=='\"') {
				quote=!quote;
			}
			else if (CSVLine.charAt(i)==',') {
				if (quote==false) {
					word=CSVLine.substring(charindex,i);
					if (word==null || word.isEmpty()) {
						str.add("");
					}
					else if (word.charAt(0)=='\"' && word.charAt(word.length()-1)=='\"') {
						word=word.substring(1,word.length()-1);
						str.add(word);
					}
					else {
						str.add(word);
					}
				charindex=i+1;
				}
			}
		}
		word=CSVLine.substring(charindex);
		if (!word.isEmpty()) {
			if (word.charAt(0)=='\"' && word.charAt(word.length()-1)=='\"') {
				word=word.substring(1,word.length()-1);
				str.add(word);
			}
			else {
				str.add(word);
			}
		}
		return str;
	}
}


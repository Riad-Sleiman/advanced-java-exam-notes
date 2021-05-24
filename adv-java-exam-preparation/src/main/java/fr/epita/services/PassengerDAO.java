package fr.epita.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fr.epita.datamodel.Passenger;

public class PassengerDAO {
	
	/** 
	 * @author Riad Sleiman
	 * @param path represents the path of the data.txt 
	 * @return result is the list of all passengers present in the data file
	 * */
	public List<Passenger> getAllPassengers(String path) throws IOException{
		List<Passenger> result = new ArrayList<Passenger>();
		
		List<String> lines = Files.readAllLines(new File(path).toPath());
		lines.remove(0);
		for (int i = 0; i < lines.size()-1; i++){
			String[] parts = lines.get(i).split(";");
			
			String age = parts[2].trim();
			if (age.equals(""))
				age = "0";
			Passenger p = new Passenger(parts[0].trim(),parts[1].trim(),(int)(Double.parseDouble(age.trim())),parts[3].trim(),Integer.parseInt(parts[4].trim()));
			result.add(p);
		}
		
		return result;
	}
	
	/** 
	 * @param pList represents the list of passengers
	 * @return pLis the modified version of pList that gets sorted
	 * */
	public List<Passenger> sortPassengers(List<Passenger> pList){
		
		for(int i = 0 ; i < pList.size()-1; i++) {
			for(int j = i+1; j < pList.size();j++) {
				String class1 = Character.toString(pList.get(i).getpClass().charAt(0));
				String class2 = Character.toString(pList.get(j).getpClass().charAt(0));
				if(Integer.parseInt(class1) < Integer.parseInt(class2)) {
					Passenger temp = pList.get(i);
					pList.set(i, pList.get(j));
					pList.set(j, temp);	
				}
			}
		}
		return pList;
	}
}

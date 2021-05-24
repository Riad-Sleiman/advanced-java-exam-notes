package fr.epita.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import fr.epita.datamodel.Passenger;
import fr.epita.services.PassengerDAO;
import org.junit.Assert;

public class PassengerTest {
	
	//Create an instance of the DAO class
	PassengerDAO pdao = new PassengerDAO();

	@Test
	public void PassengerReadTest() throws IOException {
		//Calls the get all passengers function with the path to the data file
		List<Passenger> passengers = pdao.getAllPassengers("data.txt");
		
		//Checks that the age of the first element matches the age in the list
		Assert.assertEquals(29, passengers.get(0).getAge());
		
		//Calls the sort function and stores the result in the passengers list
		passengers = pdao.sortPassengers(passengers);
		
		//Checks that the age of the first passenger matches the age of the lowest class.
		Assert.assertEquals(63, passengers.get(0).getAge());
			
	}
	
}

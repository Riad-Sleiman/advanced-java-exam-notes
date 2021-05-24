package fr.epita.services.data.api;

import java.util.List;

import fr.epita.datamodel.Choice;


public interface IChoiceDAO {

	public List<Choice> search(Choice choiceQBE) throws DataAccessException;
	
	public void update(Choice choice) throws DataAccessException;
	
	public void delete(Choice choice) throws DataAccessException;
	
	public void create(Choice choice)throws DataAccessException;
}

package fr.epita.services.data.api;

import java.util.List;

import fr.epita.datamodel.Answer;

public interface IAnswerDAO {
	
	public List<Answer> search(Answer answerQBE) throws DataAccessException;
	
	public void update(Answer answer) throws DataAccessException;
	
	public void delete(Answer answer) throws DataAccessException;
	
	public void create(Answer answer)throws DataAccessException;
	
}

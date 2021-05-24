package fr.epita.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import fr.epita.datamodels.Question;
import fr.epita.datamodels.Quiz;


public class QuizDAO {
	MongoClient client = new MongoClient("localhost",27017);
	MongoDatabase database = client.getDatabase("test");
	MongoCollection<Document> collection = database.getCollection("quizzes");
	
	QuestionDAO qdao = new QuestionDAO();
	
	public void insert(Quiz quiz) {
		Document quizDoc = new Document("name",quiz.getName())
				.append("overallDifficulty", quiz.getOverallDifficulty());
		
		List<ObjectId> questionIds = new ArrayList<ObjectId>();
		
		for(Question q : quiz.getQuestions()) {
			questionIds.add(new ObjectId(q.getId()));
		}
		
		quizDoc.append("questions", questionIds);
		
		collection.insertOne(quizDoc);
	}
	
	public List<Quiz> search(Quiz quiz){
		BasicDBObject query = new BasicDBObject();
	    query.put("name", quiz.getName());
	    query.put("overallDifficulty", quiz.getOverallDifficulty());
	    
	    List<ObjectId> questionIds = new ArrayList<ObjectId>();
		
		for(Question q : quiz.getQuestions()) {
			questionIds.add(new ObjectId(q.getId()));
		}
		
	    query.put("questions", questionIds);
	    
	    List<Quiz> results = new ArrayList<Quiz>();
	    
	    FindIterable<Document> iterable = collection.find(query);
	    MongoCursor<Document> cursor = iterable.iterator();
	    try {
	    while(cursor.hasNext()) {
	    	Document element = cursor.next();
	    	Quiz q = new Quiz(element.get("id").toString(),
	    			element.get("name").toString(),
	    			element.getInteger("overallDifficulty").intValue(),
	    			new ArrayList<Question>());
	    	
	    	List<Question> resQuestions = new ArrayList<Question>();
	    	List<ObjectId> resIds = element.getList("quesitons",ObjectId.class);
	    	
	    	for(ObjectId id : resIds) {
	    		resQuestions.add(qdao.search(new Question(id.toString())).get(0));
	    	}
	    	
	    	q.setQuestions(resQuestions);
	    	results.add(q);
	    }
	    } finally {
		    cursor.close();
		    }
		    return results;
	}
	
	public void update(Quiz quizToRemove, Quiz quizToAdd) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(quizToRemove.getId()));
		query.put("name", quizToRemove.getName());
		query.put("overallDifficulty", quizToRemove.getOverallDifficulty());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("_id", new ObjectId(quizToAdd.getId()));
		newDocument.put("name", quizToAdd.getName());
		newDocument.put("overallDifficulty", quizToAdd.getOverallDifficulty());
		
		List<ObjectId> questionIds = new ArrayList<ObjectId>();
		
		for(Question q : quizToAdd.getQuestions()) {
			questionIds.add(new ObjectId(q.getId()));
		}
		newDocument.put("questions", questionIds);
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject); 
	}
	
	public void delete(Quiz quiz) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(quiz.getId()));
		query.put("name", quiz.getName());
		query.put("overallDifficulty", quiz.getOverallDifficulty());
		
		collection.deleteMany(query);
	}
}

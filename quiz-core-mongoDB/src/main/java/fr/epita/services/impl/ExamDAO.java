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

import fr.epita.datamodels.Answer;
import fr.epita.datamodels.Exam;
import fr.epita.datamodels.Quiz;

public class ExamDAO {
	MongoClient client = new MongoClient("localhost",27017);
	MongoDatabase database = client.getDatabase("test");
	MongoCollection<Document> collection = database.getCollection("exams");
	
	QuizDAO qdao = new QuizDAO();
	AnswerDAO adao = new AnswerDAO();
	
	public void insert(Exam exam) {
		Document examDoc = new Document("title",exam.getTitle())
				.append("quiz", new ObjectId(exam.getQuiz().getId()));
		
		List<ObjectId> answerIds = new ArrayList<ObjectId>();
		
		for(Answer a : exam.getAnswers()) {
			answerIds.add(new ObjectId(a.getId()));
		}
		
		examDoc.append("questions", answerIds);
		
		collection.insertOne(examDoc);
	}
	
	public List<Exam> search(Exam exam){
		BasicDBObject query = new BasicDBObject();
	    query.put("title", exam.getTitle());
	    query.put("quiz", new ObjectId(exam.getQuiz().getId()));
	    
	    List<ObjectId> answerIds = new ArrayList<ObjectId>();
		
		for(Answer a : exam.getAnswers()) {
			answerIds.add(new ObjectId(a.getId()));
		}
	    
		query.put("answers", answerIds);
	    
	    List<Exam> results = new ArrayList<Exam>();
	    
	    FindIterable<Document> iterable = collection.find(query);
	    MongoCursor<Document> cursor = iterable.iterator();
	    try {
	    while(cursor.hasNext()) {
	    	Document element = cursor.next();
	    	Exam a = new Exam(element.get("id").toString(),
	    			element.get("title").toString(),
	    			qdao.search(new Quiz(element.get("quiz").toString())).get(0),
	    			new ArrayList<Answer>());
	    	
	    	List<Answer> resAnswers = new ArrayList<Answer>();
	    	List<ObjectId> resIds = element.getList("answers",ObjectId.class);
	    	
	    	for(ObjectId id : resIds) {
	    		resAnswers.add(adao.search(new Answer(id.toString())).get(0));
	    	}
	    	
	    	a.setAnswers(resAnswers);
	    	results.add(a);
	    }
	    } finally {
		    cursor.close();
		    }
		    return results;
	}
	
	public void update(Exam examToRemove, Exam examToAdd) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(examToRemove.getId()));
		query.put("title", examToRemove.getTitle());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("_id", new ObjectId(examToAdd.getId()));
		newDocument.put("title", examToAdd.getTitle());
		
		List<ObjectId> answersIds = new ArrayList<ObjectId>();
		
		for(Answer a : examToAdd.getAnswers()) {
			answersIds.add(new ObjectId(a.getId()));
		}
		newDocument.put("answers", answersIds);
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject); 
	}
	
	public void delete(Exam exam) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(exam.getId()));
		query.put("title", exam.getTitle());
		
		collection.deleteMany(query);
	}
}

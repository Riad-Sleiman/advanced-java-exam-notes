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
import fr.epita.datamodels.Question;
import fr.epita.datamodels.Student;

public class AnswerDAO {
	MongoClient client = new MongoClient("localhost",27017);
	MongoDatabase database = client.getDatabase("test");
	MongoCollection<Document> collection = database.getCollection("answers");
	
	StudentDAO sdao = new StudentDAO();
	QuestionDAO qdao = new QuestionDAO();
	
	public void insert(Answer answer) {
		Document answerDoc = new Document("text",answer.getText())
				.append("questionId", new ObjectId(answer.getQuestion().getId()))
				.append("studentId", new ObjectId(answer.getStudent().getId()));
		
		collection.insertOne(answerDoc);
	}
	
	public List<Answer> search(Answer answer){
		BasicDBObject query = new BasicDBObject();
	    query.put("text", answer.getText());
	    //query.put("questionId",new ObjectId(answer.getQuestion().getId()));
	    //query.put("studentId", new ObjectId(answer.getStudent().getId()));
	    List<Answer> results = new ArrayList<Answer>();
	    
	    FindIterable<Document> iterable = collection.find(query);
	    MongoCursor<Document> cursor = iterable.iterator();
	    try {
	    while(cursor.hasNext()) {
	    	Document element = cursor.next();
	    	
	    	Answer a = new Answer(element.getObjectId("_id").toString(),
	    			element.get("text").toString(),
	    			sdao.search(new Student(element.getObjectId("studentId").toString())).get(0),
	    			qdao.search(new Question(element.getObjectId("questionId").toString())).get(0));
	    	results.add(a);
	    }
	    } finally {
		    cursor.close();
		    }
		    return results;
	}
	
	public void update(Answer answerToRemove, Answer answerToAdd) {
		BasicDBObject query = new BasicDBObject();
		//query.put("_id", new ObjectId(answerToRemove.getId()));
		query.put("text", answerToRemove.getText());
		//query.put("studentId", new ObjectId(answerToRemove.getStudent().getId()));
		//query.put("questionId", new ObjectId(answerToRemove.getQuestion().getId()));
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("text", answerToAdd.getText());
		//newDocument.put("studentId", new ObjectId(answerToAdd.getStudent().getId()));
		//newDocument.put("questionId", new ObjectId(answerToAdd.getQuestion().getId()));
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject); 
	}
	
	public void delete(Answer answer) {
		BasicDBObject query = new BasicDBObject();
		//query.put("_id", new ObjectId(answer.getId()));
		query.put("text", answer.getText());
		//query.put("studentId", new ObjectId(answer.getStudent().getId()));
		//query.put("questionId", new ObjectId(answer.getQuestion().getId()));
		
		collection.deleteMany(query);
	}
		
}

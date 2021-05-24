package fr.epita.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import fr.epita.datamodels.Choice;
import fr.epita.datamodels.Question;

public class QuestionDAO {
	MongoClient client = new MongoClient("localhost",27017);
	MongoDatabase database = client.getDatabase("test");
	MongoCollection<Document> collection = database.getCollection("questions");
	
	public void insert(Question question) {
		
		Document questionDoc = new Document("subject",question.getSubject());
		List<Document> choices = new ArrayList<Document>();
		for(Choice choice : question.getChoices()) {
			Document choiceDoc = new Document("choiceLable",choice.getChoiceLabel())
					.append("valid", choice.isValid())
					.append("optionNumber", choice.getOptionNumber());
			choices.add(choiceDoc);
		}
		questionDoc.append("choices", choices);
		collection.insertOne(questionDoc);
		
	}
	
	public List<Question> search(Question question){
		BasicDBObject query = new BasicDBObject();
	    query.put("subject", question.getSubject());
	    List<Question> results = new ArrayList<Question>();
		
	    FindIterable<Document> iterable = collection.find(query);
	    MongoCursor<Document> cursor = iterable.iterator();
	    try {
	    while(cursor.hasNext()) {
	    	Document element = cursor.next();
	    	Question q = new Question(element.get("_id").toString(),
	    			element.get("subject").toString(),new ArrayList<Choice>());
	    	
	    	//TODO: Get the embedded list of choices and add them to a list of choices and pass it  to the object
	    	
	    	/*List<Document> cDoc = element.get;
	    	List<Choice> cList = new ArrayList<Choice>();
	    	for(Document doc : cDoc) {
	    		System.out.println(doc);
	    		Choice c = new Choice(
	    				doc.getInteger("optionNumber"),
	    				doc.get("choiceLabel").toString(),
	    				doc.getBoolean("valid"));
	    		cList.add(c);
	    	}
	    	q.setChoices(cList);*/
	    results.add(q);
	    }
	    } finally {
	    cursor.close();
	    }
	    return results;
		
	}
	
	public void update(Question questionToRemove,Question questionToAdd) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", questionToRemove.getId());
		query.put("subject", questionToRemove.getSubject());
		//query.put("choices", questionToRemove.getChoices());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("subject", questionToAdd.getSubject());
		//newDocument.put("choices", questionToAdd.getChoices());
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject); 
	}
	
	public void delete(Question question) {
		BasicDBObject query = new BasicDBObject();
		//query.put("_id", question.getId());
		query.put("subject", question.getSubject());

		collection.deleteMany(query);
	}
}

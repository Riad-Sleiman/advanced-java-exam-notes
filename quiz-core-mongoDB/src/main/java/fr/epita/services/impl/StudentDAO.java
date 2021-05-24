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
import fr.epita.datamodels.Student;

public class StudentDAO {

	MongoClient client = new MongoClient("localhost",27017);
	MongoDatabase database = client.getDatabase("test");
	MongoCollection<Document> collection = database.getCollection("students");
	
	public void insert(Student student) {
		Document studentDoc = new Document("name",student.getName())
				.append("id", student.getStudentId());
		collection.insertOne(studentDoc);
	}
	
	public List<Student> search(Student student){
		BasicDBObject query = new BasicDBObject();
	    query.put("name", student.getName());
	    query.put("id", student.getStudentId());
	    List<Student> results = new ArrayList<Student>();
		
	    FindIterable<Document> iterable = collection.find(query);
	    MongoCursor<Document> cursor = iterable.iterator();
	    try {
	    while(cursor.hasNext()) {
	    	Document element = cursor.next();
	    	Student s = new Student(element.get("_id").toString(),
	    			element.get("name").toString(),
	    			element.get("id").toString());
	    results.add(s);
	    }
	    } finally {
	    cursor.close();
	    }
	    return results;
		
	}
	
	public void delete(Student student) {
		BasicDBObject theQuery = new BasicDBObject();
		theQuery.put("name", student.getName());
		theQuery.put("_id", new ObjectId(student.getId()));
		theQuery.put("id", student.getStudentId());
		collection.deleteMany(theQuery);
	}
	
	public void update(Student studentToRemove,Student studentToAdd) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", studentToRemove.getId());
		query.put("name", studentToRemove.getName());
		query.put("id", studentToRemove.getStudentId());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", studentToAdd.getName());
		newDocument.put("id", studentToAdd.getStudentId());
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		collection.updateOne(query, updateObject); 
	}
}

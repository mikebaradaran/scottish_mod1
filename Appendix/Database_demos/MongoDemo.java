package qa.databases;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDemo {
    public static void main(String[] args) {
    	
    	Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
    	
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase db = mongoClient.getDatabase("testdb");
            MongoCollection<Document> collection = db.getCollection("users");

            Document doc = new Document("name", "Mike").append("age", 70);
            collection.insertOne(doc);
          
//            collection.deleteMany(new Document());   // Delete ALL documents
            
            String json = "{ 'name': 'Bob', 'age': 29, 'City':'London' }";
            collection.insertOne(Document.parse(json));

            for (Document d : collection.find()) {
                System.out.println(d.toJson());
            }
        }
    }
}


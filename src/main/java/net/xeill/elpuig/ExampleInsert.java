package net.xeill.elpuig;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Hello world!
 */
public class ExampleInsert {
    public static void main(String[] args) {

        try {
            MongoDBController mongoDBController = new MongoDBController();
            MongoClient mongoClient = mongoDBController.getMongoClient();

            MongoDatabase db = mongoClient.getDatabase("exemples");
            MongoCollection<Document> coll = db.getCollection("mascotes");

            coll.drop();

            Document document = new Document("nom", "Buffy")
                    .append("edat", 3)
                    .append("espècie", "gat");
            System.out.println(document.toJson());
            coll.insertOne(document);
            System.out.println(document.toJson());
            //mongoClient.close();
        } catch (MongoException me) {
            System.err.println(me);
        }
    }


}

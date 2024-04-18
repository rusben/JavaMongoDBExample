package net.xeill.elpuig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class ExampleFilter {

    public static void main(String[] args) {
        MongoDBController mongoDBController = new MongoDBController();
        MongoClient mongoClient = mongoDBController.getMongoClient();

        MongoDatabase db = mongoClient.getDatabase("exemples");
        MongoCollection<Document> collection = db.getCollection("punts");

        collection.drop();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                collection.insertOne(new Document("x", x).append("y", y));
            }
        }

        // db.punts.find({x:3})
        Bson filter = new Document("x", 3);
        List<Document> result = collection.find(filter).into(new ArrayList<Document>());
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
        System.out.println();

        // db.punts.find({x:{$lt:3},y:{$gte:3}})
        // Sintaxi basada en documents
        filter = new Document("x", new Document("$lt", 3))
                .append("y", new Document("$gte", 3));
        result = collection.find(filter).into(new ArrayList<Document>());
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
        // Sintaxi basada en Filters
        filter = Filters.and(Filters.lt("x", 3), Filters.gte("y", 3));
        // Igual que l'anterior amb els mètodes de Filters importats de format estàtica
        // filter = and(lt("x", 3), gte("y", 3));

        mongoClient.close();
    }
}

package net.xeill.elpuig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ExampleFind {

    public static void main(String[] args) {
        MongoDBController mongoDBController = new MongoDBController();
        MongoClient mongoClient = mongoDBController.getMongoClient();

        MongoDatabase db = mongoClient.getDatabase("exemples");
        MongoCollection<Document> collection = db.getCollection("punts");

        collection.drop();

        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                collection.insertOne(new Document("x",x).append("y", y));
            }
        }

        // Obtenir el primer element
        Document first = collection.find().first();
        System.out.println(first.toJson());
        System.out.println();
        // Recuperar tots els elements en una List
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for (Document doc : all) {
            System.out.println(doc.toJson());
        }
        System.out.println();
        // Iterar per tots els elements sense guardar-los
        try (MongoCursor<Document> cursor = collection.find().iterator();) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }
        System.out.println();
        // Iterar per tots els elements, estil Java 8
        collection.find().forEach((Document doc) -> System.out.println(doc.toJson()));
        System.out.println();

        // Comptar la quantitat d'elements
        System.out.println(collection.countDocuments());

        // Obtenir tots els valors diferents que té un camp
        collection.distinct("x", Integer.class).forEach((Integer n)->System.out.println(n));

        mongoClient.close();
    }

}

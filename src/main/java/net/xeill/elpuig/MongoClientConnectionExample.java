package net.xeill.elpuig;

import com.mongodb.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class MongoClientConnectionExample {
    public static void main(String[] args) {
        // Replace the placeholder with your Atlas connection string
            String uri = "mongodb://192.168.22.122:27017";
        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        // ConnectionString connectionString = new ConnectionString("mongodb://host1:27017,host2:27017,host3:27017/");
        // MongoClient mongoClient = MongoClients.create(connectionString);

        // ServerAddress seed1 = new ServerAddress("host1", 27017);
        // ServerAddress seed2 = new ServerAddress("host2", 27017);
        // ServerAddress seed3 = new ServerAddress("host3", 27017);
        // MongoClientSettings settings = MongoClientSettings.builder()
        //        .applyToClusterSettings(builder ->
        //               builder.hosts(Arrays.asList(seed1, seed2, seed3)))
        //        .build();
        // MongoClient mongoClient = MongoClients.create(settings);

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
        }
    }
}

// Original work
// https://gitlab.com/joanq/DAM-2n-POO-i-acces-a-dades/-/tree/master/M6UF3/1-mongodb/codi/src/main/java/mongodb
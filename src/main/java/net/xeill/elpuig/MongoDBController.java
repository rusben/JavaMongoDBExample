package net.xeill.elpuig;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBController {

    String user = "admin";
    String password = "password";
    String serverIP = "192.168.22.122";
    String serverPort = "27017";

    public MongoClient getMongoClient() {

        // Replace the placeholder with your Atlas connection string
        String uri = "mongodb://"+user+":"+password+"@"+serverIP+":"+serverPort;
        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            return mongoClient;
        } catch (MongoException me) {
            System.err.println(me);
        }

        return null;
    }
}

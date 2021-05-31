package me.byungjin.Connection;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.byungjin.Managers.DBManager;

public class DBConnection {
	private MongoClient mongoClient = null;
	private MongoDatabase mongoDB = null;
	private MongoCollection<Document> collection = null;	
	
	public void bulidSetting() throws Exception {
		if(mongoClient != null) {
			mongoClient.close();			
		}else {
			mongoClient = MongoClients.create(
			         MongoClientSettings.builder()
	                 .applyToClusterSettings(builder ->
	                         builder.hosts(Arrays.asList(new ServerAddress(DBManager.getDestination(), DBManager.getPORT()))))
	                 .build());
			mongoDB = mongoClient.getDatabase(DBManager.getDataBaseName());
			collection = mongoDB.getCollection(DBManager.getCollectionName());
		}
	}
	
	public void insertItem(Document o) throws Exception {
		if(collection != null) {
			collection.insertOne(o);			
		}
	}	
	
	public FindIterable<Document> receiveItems(String channel) throws Exception {
		if(collection != null) {
			Document query = new Document();
			query.append("channel", channel);
			return collection.find(query);
		}
		return null;
	}
}

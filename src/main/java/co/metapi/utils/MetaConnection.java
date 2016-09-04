package co.metapi.utils;

import co.metapi.exception.CollectionNotFoundException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MetaConnection {

	private String host;
	private int port;
	private String dbName;
	
	private MongoClient mongoClient;
	private DB db;
	

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public DBCollection getConnection(String collection) throws Exception {
		if(mongoClient==null){
			mongoClient = new MongoClient(host, port);
			db = mongoClient.getDB(dbName);
		}
		if(db.collectionExists(collection)){
			return db.getCollection(collection);
		}else{
			throw new CollectionNotFoundException(collection);
		}
	}

}

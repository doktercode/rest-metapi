package co.metapi.utils;

import co.metapi.exception.CollectionNotFoundException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MetaConnection {

	private String host;
	private int port;
	private String dbName;
	private String username;
	private String password;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings("deprecation")
	public DBCollection getConnection(String collection) throws Exception {
		if(mongoClient==null){
			mongoClient = new MongoClient(host, port);
			db = mongoClient.getDB(dbName);
			db.authenticate(username, password.toCharArray());
		}
		if(db.collectionExists(collection)){
			return db.getCollection(collection);
		}else{
			throw new CollectionNotFoundException(collection);
		}
	}

}

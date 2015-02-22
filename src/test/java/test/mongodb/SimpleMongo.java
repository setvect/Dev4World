package test.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class SimpleMongo {

	public static void main(String args[]) throws Exception {
		SimpleMongo testRunner = new SimpleMongo();
		testRunner.mongoTest("localhost", 27017, "testdb");
		
		System.out.println("끝");
	}

	public void mongoTest(String ip, int port, String dbname) throws Exception {
		MongoClient mongoClient = new MongoClient(new ServerAddress(ip, port));
		DB db = mongoClient.getDB(dbname);

		DBCollection userTable = db.getCollection("userTable");
		BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("type", "database").append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));

		userTable.insert(doc);
	}

}
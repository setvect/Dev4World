package test.mongodb;

import java.net.UnknownHostException;

import com.dev4world.ctmemo.CtmemoTestUtil;
import com.dev4world.ctmemo.CtmemoVo;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class CtMemoMongoCrud {
	public static void main(String args[]) throws Exception {
		CtMemoMongoCrud testRunner = new CtMemoMongoCrud();
		// testRunner.insert("localhost", 27017, "testdb");
		testRunner.select("localhost", 27017, "testdb");
		System.out.println("끝.");
	}

	public void insert(String ip, int port, String dbname) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new ServerAddress(ip, port));
		DB db = mongoClient.getDB(dbname);
		CtmemoVo ctmemo = CtmemoTestUtil.getCtmemoTestData();

		Gson gson = new Gson();
		DBCollection coll = db.getCollection("userTable");
		BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(ctmemo));
		coll.insert(obj);
	}

	private void select(String ip, int port, String dbname) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new ServerAddress(ip, port));
		DB db = mongoClient.getDB(dbname);

		DBCollection coll = db.getCollection("userTable");
		DBCursor cursor = coll.find(new BasicDBObject("ctmemoSeq", 0));
		while (cursor.hasNext()) {
			DBObject dbobj = cursor.next();
			CtmemoVo memo = (new Gson()).fromJson(dbobj.toString(), CtmemoVo.class);
			System.out.println(memo);
		}
	}
}
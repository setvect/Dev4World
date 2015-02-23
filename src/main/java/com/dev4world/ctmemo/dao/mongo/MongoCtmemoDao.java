package com.dev4world.ctmemo.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.CtmemoVo;
import com.dev4world.ctmemo.dao.CtmemoDao;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@Repository
public class MongoCtmemoDao implements CtmemoDao {
	private static final String DB_NAME = "dev4word";
	private static final String COLLECTION_NAME = "CTMEMO";
	@Inject
	private MongoClient mongoClient;

	@Override
	public CtmemoVo getCtmemo(int ctmemoId) {
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		DBObject dbobj = coll.findOne(new BasicDBObject("ctmemoSeq", ctmemoId));
		CtmemoVo memo = (new Gson()).fromJson(dbobj.toString(), CtmemoVo.class);
		return memo;
	}

	@Override
	public int getMaxZindex() {
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		DBCursor cursor = coll.find().sort(new BasicDBObject("zIndex", -1));
		if (cursor.size() == 0) {
			return 1;
		}

		cursor.hasNext();
		DBObject dbobj = cursor.next();
		CtmemoVo memo = (new Gson()).fromJson(dbobj.toString(), CtmemoVo.class);
		return memo.getzIndex() + 1;
	}

	@Override
	public List<CtmemoVo> listCtmemo(CtmemoSearchCondition condition) {
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		DBCursor cursor = coll.find();
		cursor.hasNext();

		List<CtmemoVo> result = new ArrayList<CtmemoVo>();
		while (cursor.hasNext()) {
			DBObject dbobj = cursor.next();
			CtmemoVo memo = (new Gson()).fromJson(dbobj.toString(), CtmemoVo.class);
			result.add(memo);
		}
		return result;
	}

	@Override
	public void insertCtmemo(CtmemoVo ctmemo) {
		DB db = mongoClient.getDB(DB_NAME);
		Gson gson = new Gson();
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(ctmemo));
		coll.insert(obj);
	}

	@Override
	public void updateCtmemo(CtmemoVo ctmemo) {
		DB db = mongoClient.getDB(DB_NAME);
		Gson gson = new Gson();
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		BasicDBObject obj = (BasicDBObject) JSON.parse(gson.toJson(ctmemo));
		coll.update(new BasicDBObject("ctmemoSeq", ctmemo.getCtmemoSeq()), obj);
	}

	@Override
	public void deleteCtmemo(int ctmemoId) {
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);
		coll.remove(new BasicDBObject("ctmemoSeq", ctmemoId));
	}

}

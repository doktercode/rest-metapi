package co.metapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.metapi.utils.MetaConnection;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
public class MetaRepositoryImpl implements MetaRepository {

	@Autowired
	private MetaConnection metaConnection;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map get(String collection, String id) throws Exception {
		DBCollection coll = metaConnection.getConnection(collection);
		Map item = coll.findOne(new ObjectId(id)).toMap();
		item.put("_id", id);
		return item;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> findAll(String collection) throws Exception {
		List<Map> list = new ArrayList<Map>();
		DBCollection coll = metaConnection.getConnection(collection);
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				Map item = obj.toMap();
				item.put("_id", obj.get("_id").toString());
				list.add(item);
			}
			return list;
		} finally {
			cursor.close();
		}

	}

	@Override
	@SuppressWarnings("rawtypes")
	public void create(String collection, Map object) throws Exception {
		DBCollection coll = metaConnection.getConnection(collection);
		coll.insert(new BasicDBObject(object));
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void update(String collection, String id, Map object) throws Exception {
		DBCollection coll = metaConnection.getConnection(collection);
		coll.update(coll.findOne(new ObjectId(id)), new BasicDBObject(object));
	}

	@Override
	public void delete(String collection, String id) throws Exception {
		DBCollection coll = metaConnection.getConnection(collection);
		coll.remove(coll.findOne(new ObjectId(id)));
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean exist(String collection, Map object) throws Exception {
		DBCollection coll = metaConnection.getConnection(collection);
		DBObject obj = coll.findOne(new BasicDBObject(object));
		return obj != null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> query(String collection, Map query) throws Exception {
		List<Map> list = new ArrayList<Map>();
		DBCollection coll = metaConnection.getConnection(collection);
		DBCursor cursor = coll.find(new BasicDBObject(query));
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				Map item = obj.toMap();
				item.put("_id", obj.get("_id").toString());
				list.add(item);
			}
			return list;
		} finally {
			cursor.close();
		}

	}

}

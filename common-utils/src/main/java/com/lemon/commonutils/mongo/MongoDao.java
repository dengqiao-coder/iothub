package com.lemon.commonutils.mongo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author dengqiao
 * @date 2019-12-19 10:04
 */
public abstract class MongoDao<T> {
    @Autowired
    private MongoTemplate mongoTemplate;

    private final Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public void save(T t) {
        mongoTemplate.save(t);
    }

    public T findById(Object id) {
        return mongoTemplate.findById(id, clazz);
    }

    public long delete(T t) {
        DeleteResult deleteResult = mongoTemplate.remove(t);
        return deleteResult.getDeletedCount();
    }

    public long update(Criteria criteria, Update update) {
        if (criteria == null) {
            criteria = new Criteria();
        }
        Query query = new Query();
        query.addCriteria(criteria);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, clazz);
        return updateResult.getModifiedCount();
    }

    public long deleteById(Object id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, clazz);
        return deleteResult.getDeletedCount();
    }

    public long updateById(Map<String, Object> attributeMap) {
        if (attributeMap == null || attributeMap.size() == 0) {
            return 0;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(attributeMap.get("id")));
        Update update = new Update();
        Iterator<String> it = attributeMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object object = attributeMap.get(key);
            update.set(key, object);
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, clazz);
        return updateResult.getModifiedCount();
    }

    public T findOne(String colName, Object value) {
        Criteria criteria = Criteria.where(colName).is(value);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, clazz);
    }

    public List<T> findList(String colName, Object value) {
        Criteria criteria = Criteria.where(colName).is(value);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, clazz);
    }

    public List<T> findList(Criteria criteria) {
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, clazz);
    }

    public Criteria queryLike(Criteria criteria, String colName, String value) {
        Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
        if (criteria == null) {
            return Criteria.where(colName).regex(pattern);
        } else {
            return criteria.and(colName).regex(pattern);
        }
    }

    public Page<T> findPage(Criteria criteria, int pageNo, int pageSize) {
        return findPage(criteria, pageNo, pageSize, null, null);
    }

    public Page<T> findPage(Criteria criteria, int pageNo, int pageSize, Sort.Direction direction, String... orderBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (direction != null && orderBy != null && orderBy.length > 0) {
            pageable = PageRequest.of(pageNo, pageSize, direction, orderBy);
        }
        return findPage(criteria, pageable);
    }

    public Page<T> findPage(Criteria criteria, Pageable pageable) {
        if (criteria == null) {
            criteria = new Criteria();
        }
        Query query = new Query();
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, clazz);
        query.with(pageable);
        List<T> items = mongoTemplate.find(query, clazz);
        return PageableExecutionUtils.getPage(items, pageable, () -> count);
    }


}

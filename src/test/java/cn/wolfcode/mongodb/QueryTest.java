package cn.wolfcode.mongodb;

import cn.wolfcode.mongodb.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@SpringBootTest
public class QueryTest {

    @Autowired
    private MongoTemplate template;

    // 分页查询文档，显示第2页，每页显示3个，按照id升序排列
    @Test
    public void testQuery1() throws Exception {
        //新建查询对象
        Query query = new Query();
        //将分页条件传进去
        //第一种方法
        //query.skip(3).limit(3);
        //第二种方法 使用pageable
        Pageable Pageable = PageRequest.of(1, 3);
       // Pageable Pageable = PageRequest.of(1, 3,Sort.Direction.ASC,"_id");
        query.with(Pageable);
        //将排序条件加上去
        //第一种方法
        //query.with(Sort.by(Sort.Direction.ASC,"_id"));
        //第二种方法
        query.with(Sort.by(Sort.Order.asc("_id")));

        //查询
        List<User> list = template.find(query, User.class, "users");
        list.forEach(System.out::println);


    }
    // 查询所有name为小明的文档
    @Test
    public void testQuery2()throws Exception{
        //新建查询对象
        Query query = new Query();
        //将查询条件封装进限制对象中然后在封装进query对象中
        query.addCriteria(Criteria.where("name").is("小明"));
        //将query作为条件传进find方法中
        List<User> list = template.find(query, User.class, "users");
        list.forEach(System.out::println);
    }

    // 查询所有name为小王或者age<22的文档
    @Test
    public void testQuery3()throws Exception{
        //新建查询对象
        Query query = new Query();
        //将查询条件封装进限制对象中然后在封装进query对象中
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("name").is("小王"), Criteria.where("age").lt(22));
        query.addCriteria(criteria);
        //将query作为条件传进find方法中
        List<User> list = template.find(query, User.class, "users");
        list.forEach(System.out::println);
    }

    // 查询所有name含有wang并且20<=age<=25的文档
    @Test
    public void testQuery4()throws Exception{
        //新建查询对象
        Query query = new Query();
        //将查询条件封装进限制对象中然后在封装进query对象中
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("name").regex(".*wang.*"),Criteria.where("age").lte(25).gte(20));
        query.addCriteria(criteria);
        //将query作为条件传进find方法中
        List<User> list = template.find(query, User.class, "users");
        list.forEach(System.out::println);
    }

    /**
     * mongodb聚合查询
     * @throws Exception
     */
    // 查询所有age的平均值
    @Test
    public void testQuery5()throws Exception{
        //新建查询对象
        Aggregation aggregation = newAggregation(group("null").avg("age").as("平均值"));
        AggregationResults<HashMap> results = template.aggregate(aggregation, "users", HashMap.class);
        results.forEach(System.err::println);

    }

}

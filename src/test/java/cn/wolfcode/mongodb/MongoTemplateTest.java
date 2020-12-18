package cn.wolfcode.mongodb;

import cn.wolfcode.mongodb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {

    /**
     * 使用mongoTemplate这个对象,这个是spring继承mongo时提供的一个对象
     * 用来操作mongoDB中的数据
     */
    
    @Autowired
    private MongoTemplate template;


    /**
     * 需求:// 分页查询文档，显示第2页，每页显示3个，按照id升序排列
     * @throws Exception
     */
    @Test
    public void testList()throws Exception{

        //新建一个查询条件对象
        Query query = new Query();
        //将排序规则设置到query中(有以下三种方法可行)
        //query.with(new Sort(Sort.Direction.ASC,"id"));
        //query.with(Sort.by(Sort.Order.asc("id")));
        //query.with(new Sort(Sort.Order.asc("id")));
        //将分页条件设置到query中
        query.skip(3).limit(3);
        List<User> users = template.find(query, User.class, "users");
        users.forEach(System.out::println);
    }


    /**
     * 查询所有name为lili的文档
     * @throws Exception
     */
    @Test
    public void testList2()throws Exception{
        //新建查询对象query
        Query query = new Query();
        //将查询条件封装进query方法中
        //由于是等值条件查询需要新建一个criteria对象
        Criteria criteria = new Criteria();
        //将查询条件设置到criteria对象中,返回一个封装了查询条件的新对象
        Criteria newCriteria = criteria.where("name").is("lili");
        //将返回的criteria的新对象封装进query对象中
        query.addCriteria(newCriteria);
        List<User> users = template.find(query, User.class, "users");
        users.forEach(System.err::println);
    }


    /**
     * 查询所有name为小明或者age<28的文档
     * @throws Exception
     */
    @Test
    public void testList3()throws Exception{
        //创建一个查询对象
        Query query = new Query();
        //创建一个设置查询条件的对象
        Criteria criteria = new Criteria();
        //构建限制条件(判断条件之间的逻辑关系)
        //Criteria newCriteria = criteria.where("name").is("小明").orOperator(new Criteria().where("age").lt(28));
        Criteria newCriteria = criteria.orOperator(criteria.where("name").is("小明"), criteria.where("age").lt(28));
        //将构建查询条件的对象封装到query对象中
        query.addCriteria(newCriteria);
        List<User> users = template.find(query, User.class, "users");
        users.forEach(System.out::println);
    }
    /**
     * 查询所有name含有wang并且30<=age<=32的文档
     * @throws Exception
     */
    @Test
    public void testList4()throws Exception{
        //创建一个查询对象
        Query query = new Query();
        //创建一个设置查询条件的对象
        Criteria criteria = new Criteria();
        //构建限制条件(判断条件之间的逻辑关系)
        //二者之间的关系是与的关系 也即是:{$and:[{name:{"$regex":".*wang.*"}},{age:{$gte:30,$lte:32}}]}
        Criteria newCriteria = criteria.andOperator(criteria.where("name").regex(".*wang.*"), criteria.where("age").lte(32).gte(30));
        //将构建查询条件的对象封装到query对象中
        query.addCriteria(newCriteria);
        List<User> users = template.find(query, User.class, "users");
        users.forEach(System.out::println);
    }



}

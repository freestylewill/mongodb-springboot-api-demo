package cn.wolfcode.mongodb;

import cn.wolfcode.mongodb.domain.User;
import cn.wolfcode.mongodb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest//这个注解是springboot自带的注解,贴上这个以后可以不用贴这个contextConfiguration()注解
public class MongodbDemoApplicationTests {

    /**
     * 使用的是自己新建一个接口继承mongo仓库,那么我们写的这个接口也就可以拥有
     * mongo仓库中的一些crud的方法
     */

    @Autowired
    private UserRepository userMongoRepository;


    //新增一个文档
    @Test
    public void testSaveOrUpdate() throws Exception {

//        User user = new User(null, 11L, "小红", 24);

//        userMongoRepository.save(user);
    }

    //删除一个文档
    @Test
    public void testDelete() throws Exception {
//        userMongoRepository.deleteById(new ObjectId("5ed89f1b40573e2d4c0a40d5"));
    }
/*
    //查询一个文档
    @Test
    public void testGet() throws Exception {
//        Optional<User> user = userMongoRepository.findById(new ObjectId("5ed89da140573e2e942d0ef3"));
//        System.out.println(user);
//
//        List<User> list = userMongoRepository.findByName("wang da");
//        list.forEach(System.out::println);

        List<User> user = userMongoRepository.findByAgeLessThanEqual(25);
        user.forEach(System.err::println);
    }*/

    //查询所有的文档
    @Test
    public void testList() throws Exception {
        List<User> list = userMongoRepository.findAll();
        // list.forEach(System.out::println);
        list.forEach(System.err::println);
    }


}

package cn.wolfcode.mongodb;

import cn.wolfcode.mongodb.domain.User;
import cn.wolfcode.mongodb.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest//这个注解是springboot自带的注解,贴上这个以后可以不用贴另外两个注解了,
public class CRUDTest {


    @Autowired
    private IUserService userService;


    //test需要使用junit5测试的包才可以---org.junit.jupiter.api.Test;
    @Test
    public void testSave()throws Exception{
        User user = new User();
        user.setName("小明");
        user.setAge(22);
        user.setHobby(Arrays.asList("java","c++","vue","android"));
        userService.save(user);
    }


    @Test
    public void testUpdate()throws Exception{

        //mongodb的更新操作是全量更新,如果传入的参数没有值,则更新的话会将原来的值进行覆盖
        /**
         * 因此使用先查询出来
         * 再替换
         * 最后进行更换
         */
        //查询
        User user = userService.get("5edb737670d10e4e424a38db");
        //替换
        user.setName("小王");

       /* User user = new User();
        user.setName("小红");
        user.setAge(18);
        user.setHobby(Arrays.asList("java","c++","vue","android"));*/
        //修改
        userService.update(user);
    }

    @Test
    public void testDelete()throws Exception{
        userService.delete("5edb74c71ec09f64ad4e04e6");
    }

    @Test
    public void testGet()throws Exception{
        User user = userService.get("5edb737670d10e4e424a38db");
        System.out.println(user);
    }

    @Test
    public void testListAll()throws Exception{
        List<User> users = userService.listAll();
        users.forEach(System.err::println);
    }




}

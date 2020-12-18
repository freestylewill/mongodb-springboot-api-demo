package cn.wolfcode.mongodb;

import cn.wolfcode.mongodb.domain.User;
import cn.wolfcode.mongodb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SpringDataJPATest {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过名字和年龄查询
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
        List<User> user = userRepository.findByNameAndAge("小明", 22);
        System.out.println(user);
    }
    /**
     * 通过名字或年龄查询
     * @throws Exception
     */
    @Test
    public void test2()throws Exception{
        List<User> list = userRepository.findByNameOrAge("小明", 18);
        list.forEach(System.err::println);
    }
    /**
     * 大于某个年龄
     * @throws Exception
     */
    @Test
    public void test3()throws Exception{
        List<User> list = userRepository.findByAgeGreaterThan(25);
        list.forEach(System.err::println);

    }
    /**
     * 大于等于某个年龄
     * @throws Exception
     */
    @Test
    public void test5()throws Exception{
        List<User> list = userRepository.findByAgeGreaterThanEqual(18);
        list.forEach(System.err::println);
    }
    /**
     * 小于某个年龄
     * @throws Exception
     */
    @Test
    public void test6()throws Exception{
        List<User> list = userRepository.findByAgeLessThan(20);
        list.forEach(System.err::println);
    }
    /**
     * 小于等于某个年龄
     * @throws Exception
     */
    @Test
    public void test7()throws Exception{
        List<User> list = userRepository.findByAgeLessThanEqual(18);
        list.forEach(System.err::println);
    }
    /**
     * 查询某个年龄段的用户
     * @throws Exception
     */
    @Test
    public void test8()throws Exception{
        List<User> list = userRepository.findByAgeBetween(23,27);
        list.forEach(System.err::println);
    }
    /**
     * 大于某个年龄的用户(使用after)
     * @throws Exception
     */
    @Test
    public void test9()throws Exception{
        List<User> list = userRepository.findByAgeAfter(26);
        list.forEach(System.err::println);
    }
    /**
     * 小于某个年龄的用户(使用before)
     * @throws Exception
     */
    @Test
    public void test10()throws Exception{
        List<User> list = userRepository.findByAgeBefore(23);
        list.forEach(System.err::println);
    }
    /**
     * 查询名字为空的用户
     * @throws Exception
     */
    @Test
    public void test11()throws Exception{
        List<User> list = userRepository.findByNameIsNull();
        list.forEach(System.err::println);
    }
    /**
     * 查询名字不为空的用户
     * @throws Exception
     */
    @Test
    public void test12()throws Exception{
        List<User> list = userRepository.findByNameIsNotNull();
        list.forEach(System.err::println);
    }
    /**
     * 查询名字中以w开头的用户
     * @throws Exception
     */
    @Test
    public void test13()throws Exception{
        List<User> list = userRepository.findByNameStartingWith("w");
        list.forEach(System.err::println);
    }
    /**
     * 查询名字中以w结尾的用户
     * @throws Exception
     */
    @Test
    public void test14()throws Exception{
        List<User> list = userRepository.findByNameEndingWith("w");
        list.forEach(System.err::println);
    }
    /**
     * 查询名字中包含wang的用户
     * @throws Exception
     */
    @Test
    public void test15()throws Exception{
        List<User> list = userRepository.findByNameLike("wang");
        list.forEach(System.err::println);
    }
    /**
     * 查询名字中包含wang的用户
     * @throws Exception
     */
    @Test
    public void test16()throws Exception{
        List<User> list = userRepository.findByNameNotLike("wang");
        list.forEach(System.err::println);
    }
    /**
     * 查询名字中包含wang的用户
     * @throws Exception
     */
    @Test
    public void test17()throws Exception{
        List<User> list = userRepository.findByNameContaining("wang");
        list.forEach(System.err::println);
    }
    /**
     * 查询name相同的用户进行年龄升序排序
     * @throws Exception
     */
    @Test
    public void test18()throws Exception{
        List<User> list = userRepository.findByNameOrderByAgeAsc("小明");
        list.forEach(System.err::println);
    }
    /**
     * 查询name不是某个值的用户
     * @throws Exception
     */
    @Test
    public void test19()throws Exception{
        List<User> list = userRepository.findByNameNot("小明");
        list.forEach(System.err::println);
    }
    /**
     * 查询集合中有的id的集合
     * @throws Exception
     */
    @Test
    public void test20()throws Exception{
        //创建一个id集合
        ArrayList<String> list = new ArrayList<>();
        list.add("5edb737670d10e4e424a38db");
        list.add("5edb790f2b0e207aa556bf70");
        List<User> list1 = userRepository.findByIdIn(list);
        list1.forEach(System.err::println);
    }
    /**
     * 查询集合中没有的id的集合
     * @throws Exception
     */
    @Test
    public void test21()throws Exception{
        //创建一个id集合
        ArrayList<String> list = new ArrayList<>();
        list.add("5edb737670d10e4e424a38db");
        list.add("5edb790f2b0e207aa556bf70");
        List<User> list1 = userRepository.findByIdNotIn(list);
        list1.forEach(System.err::println);
    }

    /**
     * 查询age为true的用户
     * @throws Exception
     */
    @Test
    public void test22()throws Exception{
        List<User> list = userRepository.findByAgeTrue();
        list.forEach(System.err::println);
    }
    /**
     * 查询age为false的用户
     * @throws Exception
     */
    @Test
    public void test23()throws Exception{
        List<User> list = userRepository.findByAgeFalse();
        list.forEach(System.err::println);
    }
    /**
     * 查询name不区分大小写
     * @throws Exception
     */
    @Test
    public void test24()throws Exception{
        List<User> list = userRepository.findByNameIgnoreCase("LiLi");
        list.forEach(System.err::println);
    }

}

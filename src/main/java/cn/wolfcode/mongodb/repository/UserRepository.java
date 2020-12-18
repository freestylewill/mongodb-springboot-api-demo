package cn.wolfcode.mongodb.repository;

import cn.wolfcode.mongodb.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自定义一个接口(这个接口相当于以前mybatis中的mapper接口)继承MongoRepository，
 * 泛型1：domain类型 该接口操作实体
 * 泛型2：文档主键类型 实体的主键类型
 * 贴上@Repository注解，底层会创建出动态代理对象，交给Spring管理 可以贴可以不贴
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // 使用Spring Data命名规范做高级查询

    //通过名字和年龄进行查询
    List<User> findByNameAndAge(String name,Integer age);

    //通过名字或者年龄进行查询
    List<User> findByNameOrAge(String name,Integer age);

    //通过名字进行查询
    List<User> findByName(String name);
    //通过年龄范围查询
    List<User> findByAgeBetween(Integer min,Integer max);
    //查询小于某个年龄
    List<User> findByAgeLessThan(Integer age);
    List<User> findByAgeBefore(Integer age);
    //查询小于等于某个年龄
    List<User> findByAgeLessThanEqual(Integer age);
    //查询大于某个年龄
    List<User> findByAgeGreaterThan(Integer age);
    List<User> findByAgeAfter(Integer age);
    //查询大于等于某个年龄
    List<User> findByAgeGreaterThanEqual(Integer age);
    //查询名字为null的
    List<User> findByNameIsNull();
    //查询名字不为null的
    List<User> findByNameIsNotNull();
    //查询名字中含有xxx的
    List<User> findByNameLike(String name);
    //查询名字中不含有xxx的
    List<User> findByNameNotLike(String name);
    //查询名字以xxx前缀开始的
    List<User> findByNameStartingWith(String name);
    //查询名字以xxx后缀结束的
    List<User> findByNameEndingWith(String name);
    //查询名字中包含某个字符("%xxx%")
    List<User> findByNameContaining(String name);
    //通过age查询 然后按年龄进行升序排序
    List<User> findByNameOrderByAgeAsc(String name);
    //查询名字不是xxx的用户
    List<User> findByNameNot(String name);
    //查询list集合中存在的id的用户
    List<User> findByIdIn(List<String> ids);
    //查询list集合中不存在的id的用户
    List<User> findByIdNotIn(List<String> ids);
    //查询用户中某个字段为true的用户
    List<User> findByAgeTrue();
    //查询用户中某个字段为false的用户
    List<User> findByAgeFalse();
    //查询用户的名字不区分大小写
    List<User> findByNameIgnoreCase(String name);




}
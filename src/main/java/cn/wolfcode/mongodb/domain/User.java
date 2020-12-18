package cn.wolfcode.mongodb.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document("users") //设置文档所在的集合
public class User {
    /*@Id //文档的id使用ObjectId类型来封装，并且贴上@Id注解
    private ObjectId _id;*/
    @Id
    private String id;

    //id只存在一个,下面的这个id可省略
    //private Long id;
    private String name;
    private Integer age;
    private List<String> hobby = new ArrayList<>();
}
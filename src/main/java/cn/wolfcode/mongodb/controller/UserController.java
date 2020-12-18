package cn.wolfcode.mongodb.controller;

import cn.wolfcode.mongodb.domain.User;
import cn.wolfcode.mongodb.service.IUserService;
import cn.wolfcode.mongodb.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private IUserService userService;


    @GetMapping("/list")
    public Object list(){
        return userService.listAll();
    }

    @PostMapping("/saveOrUpdate")
    public Object saveOrUpdate(User user){
        //判断id是否为空
        if (StringUtils.hasLength(user.getId())) {
            userService. update(user);
        }else {
            userService.save(user);
        }
        return new JsonResult();
    }
    @GetMapping("/get")
    public Object get(String id){
        return userService.get(id);
    }

}

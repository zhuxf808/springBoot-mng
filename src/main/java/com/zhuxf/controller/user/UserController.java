package com.zhuxf.controller.user;

import com.alibaba.druid.support.json.JSONUtils;
import com.zhuxf.bean.pojo.User;
import com.zhuxf.common.JsonUtils;
import com.zhuxf.common.RedisUtils;
import com.zhuxf.common.ResultData;
import com.zhuxf.common.enums.RedisPrefixEnum;
import com.zhuxf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/getList")
    public ResultData getList(){
        List<User> list = userService.getList();
        redisUtils.set(RedisPrefixEnum.USER_INFO.getKey(), JsonUtils.objectToJson(list),6000);
        return ResultData.isOk(list);
    }

}

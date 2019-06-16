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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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


    /**
     * 模拟登录接口 假的 为了测试拦截器暂时写的
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultData login(HttpServletRequest request){
        List<User> list = userService.getList();
        Map<String,Object> resultMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            String token = UUID.randomUUID().toString().replace("-","");
            User user = list.get(0);
            redisUtils.set(token,JsonUtils.objectToJson(user));
            resultMap.put("user",user);
            resultMap.put("token",token);
        }
        //生成返回的token
        return ResultData.isOk(resultMap);
    }
}

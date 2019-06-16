package com.zhuxf.dao.user;

import com.zhuxf.bean.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> getList(@Param("paramsMap") Map<String,Object> paramsMap);

}

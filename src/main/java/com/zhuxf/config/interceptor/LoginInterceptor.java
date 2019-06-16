package com.zhuxf.config.interceptor;

import com.zhuxf.bean.pojo.User;
import com.zhuxf.bean.pojo.UserCacheThread;
import com.zhuxf.common.JsonUtils;
import com.zhuxf.common.RedisUtils;
import com.zhuxf.common.ResultData;
import com.zhuxf.common.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.Addressing;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Object obj = valueOperations.get(token);
            if (obj instanceof User){
                UserCacheThread.setUser((User) obj);
                return true;
            }else{
                String resu = JsonUtils.objectToJson(ResultData.isFail(ResultEnum.LOGIN_FAIL));
                assert resu != null;
                response.getWriter().write(resu);
                response.getWriter().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                String resu = JsonUtils.objectToJson(ResultData.isFail(ResultEnum.LOGIN_FAIL));
                response.getWriter().write(resu);
                response.getWriter().close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

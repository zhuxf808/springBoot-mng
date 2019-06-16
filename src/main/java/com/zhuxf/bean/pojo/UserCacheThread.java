package com.zhuxf.bean.pojo;

import com.zhuxf.bean.pojo.User;

public class UserCacheThread {

    private static final ThreadLocal<User> LOCAL_USERS = new ThreadLocal<>();

    public static void setUser(User users) {
        LOCAL_USERS.set(users);
    }

    public static User getUsers() {
        return LOCAL_USERS.get();
    }

}

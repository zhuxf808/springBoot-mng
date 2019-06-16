package com.zhuxf.common.enums;

/**
 * redis缓存前缀
 * @author zhuxf
 * @date 2019-05-28 15:28:18
 */
public enum RedisPrefixEnum{

    USER_INFO("USER_INFO","用户信息缓存");

    private String key;

    private String remark;



    RedisPrefixEnum(String key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public String getRemark() {
        return remark;
    }
}


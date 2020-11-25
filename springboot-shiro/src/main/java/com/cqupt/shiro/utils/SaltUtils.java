package com.cqupt.shiro.utils;

import java.util.UUID;

public class SaltUtils {
    /**
     * 生成盐的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n) {
        String substring = UUID.randomUUID().toString().substring(0, n);
        return substring;
    }
}

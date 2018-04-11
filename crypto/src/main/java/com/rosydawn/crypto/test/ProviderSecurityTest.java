package com.rosydawn.crypto.test;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * Created by Vincent.
 */
public class ProviderSecurityTest {
    public static void main(String[] args) {
        // 遍历目前环境中的安全提供者
        for (Provider provider : Security.getProviders()) {
            // 打印当前提供者的信息
            System.out.println("Provider: " + provider);
            System.out.println("该Provider的键值：");
            // 遍历提供者Set实体
            for (Map.Entry<Object, Object> entry : provider.entrySet()) {
                // 打印提供者键值
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}

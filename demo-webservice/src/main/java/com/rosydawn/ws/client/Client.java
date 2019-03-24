package com.rosydawn.ws.client;

import com.rosydawn.ws.server.HelloWorld;
import com.rosydawn.ws.server.User;
import com.rosydawn.ws.server.UserImpl;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.util.Map;

/**
 * @author Vincent
 */
public final class Client {
    private static final QName SERVICE_NAME = new QName("http://server.ws.rosydawn.com/", "HelloWorld");
    private static final QName PORT_NAME = new QName("http://server.ws.rosydawn.com/", "HelloWorldPort");

    private Client() {
    }

    public static void main(String[] args) {
        Service service = Service.create(SERVICE_NAME);
        // 端点地址
        String endpointAddress = "http://localhost:9000/helloWorld";

        // 如果Web服务部署到Tomcat上，端点地址应该为：
        // as described in sample README, endpoint should be changed to:
        // String endpointAddress = "http://localhost:8080/context_name/hello_world";

        // 将端口添加服务
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        HelloWorld helloWorld = service.getPort(HelloWorld.class);

        // 另一种实现：使用CXF获取HelloWorld代理类对象
        /*//创建一个JaxWs的代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        //设置接口类型
        factory.setServiceClass(HelloWorld.class);
        //设置访问地址
        factory.setAddress("http://localhost:9000/helloWorld");
        //获得代理类实例
        HelloWorld helloWorld = (HelloWorld) factory.create();*/


        System.out.println(helloWorld.sayHi("Putin", 66));

        User user = new UserImpl("Bill Gates");
        System.out.println(helloWorld.sayHiToUser(user));

        user = new UserImpl("Trump");
        System.out.println(helloWorld.sayHiToUser(user));

        user = new UserImpl("Universe");
        System.out.println(helloWorld.sayHiToUser(user));

        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = helloWorld.getUsers();
        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
        }
    }
}

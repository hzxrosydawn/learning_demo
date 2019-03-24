package com.rosydawn.ws.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.xml.ws.Endpoint;

/**
 * @author Vincent
 */
public class Server {
    public static void main(String[] args) throws Exception {
        HelloWorldImpl implementor = new HelloWorldImpl();
        String address = "http://localhost:9000/helloWorld";
        System.out.println("Starting Server...");

        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        // 可以不显式设置ServiceClass，但是最好还是设置，因为这样服务端可以与创建的客户端具有相同的名称
        // 仅使用实现类可能会出现细微的问题。下面一行代码是可选的，但建议保留
        factoryBean.setServiceClass(HelloWorld.class);
        factoryBean.setAddress(address);
        factoryBean.setServiceBean(implementor);
        factoryBean.create();
    }
}

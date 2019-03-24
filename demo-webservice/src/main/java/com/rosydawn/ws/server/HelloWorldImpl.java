package com.rosydawn.ws.server;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A Java class that implements a web service must specify either the @WebService or @WebServiceProvider annotation.
 * Both annotations cannot be present.
 *
 * If the annotation references an SEI through the endpointInterface attribute, the SEI must also be annotated with the
 * @WebService annotation.
 *
 *
 * @author Vincent
 */
@WebService(endpointInterface = "com.rosydawn.ws.server.HelloWorld",
        targetNamespace = "http://server.ws.rosydawn.com/",
        serviceName = "HelloWorld",
        portName = "HelloWorldPort")
public class HelloWorldImpl implements HelloWorld {
    /**
     * use a map as database storing users
     */
    Map<Integer, User> users = new LinkedHashMap<>();

    @Override
    public String sayHi(String name, int age) {
        System.out.println("sayHi called");
        return String.format("Hello,%s, you are %d years old", name, age);
    }

    @Override
    public String sayHiToUser(User user) {
        System.out.println("sayHiToUser called");
        users.put(users.size() + 1, user);
        return "Hello "  + user.getName();
    }

    @Override
    public Map<Integer, User> getUsers() {
        System.out.println("getUsers called");
        return users;
    }
}

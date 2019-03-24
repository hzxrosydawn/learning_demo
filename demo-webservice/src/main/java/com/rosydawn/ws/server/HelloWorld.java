package com.rosydawn.ws.server;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

/**
 * The @WebService annotation marks a Java class as implementing a Web service or marks a service endpoint interface
 * (SEI) as implementing a web service interface. This annotation is applicable on a client or server SEI or a server
 * endpoint implementation class.
 *
 * @author Vincent
 */
@WebService(name = "HelloWorld", targetNamespace = "http://server.ws.rosydawn.com/", wsdlLocation="WEB-INF/wsdl/HelloWorld.wsdl")
public interface HelloWorld {
    /**
     * To make sure your parameter is named correctly in the xml you should use the name element of @WebParam to specify
     * the name of the parameter explicitly. As java interfaces do not store the parameter name in the .class file. So
     * if you leave out the annotation your parameter will be named arg0.
     *
     * @param name
     * @param age
     * @return
     */
    String sayHi(@WebParam(name = "name") String name, @WebParam(name = "age")int age);

    /**
     * Advanced use case of passing an Interface in. JAX-WS/JAXB does not
     * support interfaces directly. Special XmlAdapter classes need to
     * be written to handle them.
     *
     * @param user
     * @return
     */
    String sayHiToUser(@WebParam(name = "user")User user);

    /**
     * Map passing.
     * JAXB also does not support Maps. It handles Lists great, but Maps are
     * not supported directly. They also require use of a XmlAdapter to map
     * the maps into beans that JAXB can use.
     *
     * @return
     */
    @XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
    Map<Integer, User> getUsers();
}

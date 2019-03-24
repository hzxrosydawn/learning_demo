package com.rosydawn.ws.server;

import javax.xml.bind.annotation.XmlType;

/**
 * JAXB(Java Architecture for XML Binding) is the default data binding for CXF. If you don't specify one
 * of the other data bindings in your Spring configuration or through the API, you will get JAXB.
 *
 * See https://github.com/javaee/jaxb-v2
 *
 * JAXB uses Java annotation combined with files found on the classpath to build the mapping between XML and Java. JAXB
 * supports both code-first and schema-first programming. The schema-first support the ability to create a client proxy,
 * dynamically, at runtime. See the CXF DynamicClientFactory class.
 *
 * Releases of CXF since 2.3.x have used the JDK7 default of JAXB 2.2, however Maven users running on JDK
 * 6 will need to use the Java endorsed override mechanism to use JAXB 2.2 instead of JAXB 2.1.
 *
 * @author Vincent
 * @see
 */
@XmlType(name = "User")
public class UserImpl implements User {
    String name;

    public UserImpl() {
    }

    public UserImpl(String name) {
        name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}

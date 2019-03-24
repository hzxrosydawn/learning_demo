package com.rosydawn.ws.server;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author Vincent
 */
@XmlJavaTypeAdapter(UserAdapter.class)
public interface User {
    String getName();
}
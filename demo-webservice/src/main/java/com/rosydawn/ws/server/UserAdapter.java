package com.rosydawn.ws.server;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Vincent
 */
public class UserAdapter extends XmlAdapter<UserImpl, User> {
    @Override
    public UserImpl marshal(User user) {
        if (user instanceof UserImpl) {
            return (UserImpl) user;
        }
        return new UserImpl(user.getName());
    }

    @Override
    public User unmarshal(UserImpl user) {
        return user;
    }
}
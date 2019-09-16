package com.zg.websocket2.entity;

import javax.security.auth.Subject;
import java.security.Principal;

public class MyPrincipal implements Principal {

    private User user;

    public MyPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getId();
    }

}

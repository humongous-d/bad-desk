package me.piguy.baddesk;

import java.util.HashMap;

public class SimpleCredentialChecker  implements CredentialChecker {

    @Override
    public boolean checkPassword(String username, String password) {
        return ConfigurationManager.getInstance().api.login(username, password);

    }
}

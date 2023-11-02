package me.piguy.baddesk;

import java.util.HashMap;

public class SimpleCredentialChecker  implements CredentialChecker {

    private HashMap<String, String> credentials;
    public SimpleCredentialChecker() {

        credentials = new HashMap<>();
        credentials.put("admin", "nimda");
        credentials.put("Kunal", "lanuk");
        credentials.put("Slava", "avals");
        credentials.put("Artur", "rutra");


    }

    @Override
    public boolean checkPassword(String username, String password) {
        String storedPassword = credentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}

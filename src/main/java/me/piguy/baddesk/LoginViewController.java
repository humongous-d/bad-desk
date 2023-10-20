package me.piguy.baddesk;

import me.piguy.baddesk.database.Database;
import me.piguy.baddesk.database.MongoUserDB;

public class LoginViewController {
    Database db;

    public void login() {
        db.get();
    }

    public void initialize() {
        db = new MongoUserDB();
    }
}
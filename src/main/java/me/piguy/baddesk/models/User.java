package me.piguy.baddesk.models;

//{
//        "id": "6531161b90e89a45c135735b",
//        "username": "theanimeman",
//        "name": "Joey",
//        "password": "$2b$12$.C/C0BopYaZ664Pxsfi71.Vwtn08vmYR.cBbk8rHb5cwxg2XYTexu",
//        "role": "user",
//        "expiry": null,
//        "hasActiveSeeker": false
//}
public record User(String id, String username, String name, String role) {}

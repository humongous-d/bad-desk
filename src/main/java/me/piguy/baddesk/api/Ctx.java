package me.piguy.baddesk.api;

import me.piguy.baddesk.utils.JSONException;
import me.piguy.baddesk.utils.JSONUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ctx extends CtxBase{
    public Ctx(String url, String method) {
        super(url, method);
    }

    public Map<String, Object> toMap() {
        try {
            return JSONUtility.jsonToMap(getBody());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HashMap<String, Object>> toList() {
        try {
            return JSONUtility.jsonToList(getBody());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

package me.piguy.baddesk.api;

import me.piguy.baddesk.utils.JSONException;
import me.piguy.baddesk.utils.Utility;

import java.util.HashMap;
import java.util.Map;

public class Ctx extends CtxBase{
    public Ctx(String url, String method) {
        super(url, method);
    }

    public Map<String, Object> toMap() {
        try {
            return Utility.jsonToMap(getBody());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

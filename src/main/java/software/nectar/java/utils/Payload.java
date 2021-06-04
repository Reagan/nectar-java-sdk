package software.nectar.java.utils;

import org.json.JSONObject;

import java.util.HashMap;

public class Payload {

    private HashMap<String, Object> params;

    public Payload(HashMap<String, Object> params) {
        setParams(params);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        params.forEach((key,value) -> obj.put(key, value));
        return obj;
    }
}

package com.demoproject.springmvc.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishat on 10/26/15.
 */
public class ActionMessages implements Serializable {

    private String type;
    private Map<String, String> message = new HashMap<>();

    public ActionMessages(String type, Map<String, String> message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}

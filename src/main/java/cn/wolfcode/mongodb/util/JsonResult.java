package cn.wolfcode.mongodb.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {

    private boolean success=true;
    private String msg;

    public JsonResult() {

    }


    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}

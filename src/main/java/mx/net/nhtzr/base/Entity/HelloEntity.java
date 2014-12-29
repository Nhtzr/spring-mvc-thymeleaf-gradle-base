package mx.net.nhtzr.base.Entity;

import java.util.Date;

public class HelloEntity {
    private Date init;
    private Date end;
    private String message;

    public Date getInit() {
        return init;
    }

    public void setInit(Date init) {
        this.init = init;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

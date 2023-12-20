package pt.iade.joaquimclaudio.atividade.models.results;

import java.io.Serializable;

public class Response implements Serializable {
    private String msg;
    private Object obj;

    public Response(){

    }
    public Response(String msg, Object obj){
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }
}

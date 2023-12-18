package pt.iade.joaquimclaudio.server.models.results;

public class Response {
    private String message;
    private Object obj;

    public Response() {
    }

    public Response(String message, Object obj){
        this.message = message;
        this.obj = obj;
    }


    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return obj;
    }
}

package pt.iade.joaquimclaudio.atividade.models.results;

public class DeleteResponse extends Response{
    private final boolean deleted;

    public DeleteResponse(String message, Object obj, boolean deleted){
        super(message, obj);
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}

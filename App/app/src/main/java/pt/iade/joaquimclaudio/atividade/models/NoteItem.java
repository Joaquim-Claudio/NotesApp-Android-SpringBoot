package pt.iade.joaquimclaudio.atividade.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.joaquimclaudio.atividade.models.results.DeleteResponse;
import pt.iade.joaquimclaudio.atividade.models.results.Response;
import pt.iade.joaquimclaudio.atividade.utilities.LocalDateJsonAdapter;
import pt.iade.joaquimclaudio.atividade.utilities.WebRequest;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    @JsonAdapter(LocalDateJsonAdapter.class)
    private LocalDate creationDate;
    @JsonAdapter(LocalDateJsonAdapter.class)
    private LocalDate modifiedDate;
    private boolean important;

    public NoteItem() {
        this(0, "", "", LocalDate.now(), false);
    }

    public NoteItem(int id, String title, String content, LocalDate creationDate, boolean important) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = creationDate;
        this.important = important;
    }

    //  Fetch a list of items from the WebServer and populates 'items' with it.
    public static void List(ListResponse response){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes"));
                    String resp = request.performGetRequest();

                    // JsonObject jsonObject = new Gson().fromJson(resp, JsonObject.class);
                    JsonArray array = new Gson().fromJson(resp, JsonArray.class);

                    for (JsonElement e : array){
                        items.add(new Gson().fromJson(e, NoteItem.class));
                    }
                    response.response(items);

                }catch (Exception e){
                    Log.e("NoteItem.List", e.toString());
                }
            }
        });
        thread.start();
    }

    //  Fetch an item from the WebServer using the given 'id'
    public static void GetById(int id, GetByIdResponse response){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes/"+id));
                    String resp = request.performGetRequest();

                    NoteItem item = new Gson().fromJson(resp, NoteItem.class);
                    response.response(item);
                }catch (Exception e){
                    Log.e("NoteItem.GetById", e.toString());
                }
            }
        });
        thread.start();
    }


    //  Insert or Update an item to WebServer
    public void save(SaveResponse response){
        Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/notes"));
                        String resp = req.performPostRequest(NoteItem.this);

                        // Get the new ID from the server's response.
                        NoteItem respItem = new Gson().fromJson(resp, NoteItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/notes/" + id));
                        req.performPostRequest(NoteItem.this);
                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("NoteItem.save", e.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
        thread.start();
    }

    //  Delete an item to WebServer
    public void delete(DeleteResult response){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (id != 0){
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes/"+id));
                        String resp = request.performDeleteRequest();

                        DeleteResponse result = new Gson().fromJson(resp, DeleteResponse.class);
                        response.response(result);
                    }
                }catch (Exception e){
                    Log.e("NoteItem.delete", e.toString());
                }
            }
        });
        thread.start();
    }



    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }


    public interface ListResponse{
        public void response(ArrayList<NoteItem> items);
    }
    public interface GetByIdResponse{
        public void response(NoteItem item);
    }
    public interface SaveResponse{
        public void response();
    }
    public interface DeleteResult {
        public void response(DeleteResponse result);
    }
}
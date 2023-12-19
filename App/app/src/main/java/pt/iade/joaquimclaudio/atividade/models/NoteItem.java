package pt.iade.joaquimclaudio.atividade.models;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public static ArrayList<NoteItem> List(){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        //  TODO: implement the fetch to the WebServer

        //  Simulates the fetch to WebServer
        items.add(new NoteItem(1, "Math test next week",
                "Ask Jane to send the last math topics", LocalDate.now(), false));

        items.add(new NoteItem(2, "Download the new Ed Sheeran's album",
                "", LocalDate.now(), true));

        items.add(new NoteItem(3, "Android Notes project",
                "Create a Notes app. Must be released on Wednesday (20th december)", LocalDate.now(), false));

        items.add(new NoteItem(4, "Discrete Math Presentation",
                "SQL queries to return the required results", LocalDate.now(), true));

        items.add(new NoteItem(5, "Spring-Boot WebServer",
                "Create a WebServer using Spring-Boot framework as ti is required to P.O.O evaluation",
                LocalDate.now(), true));

        return items;
    }

    //  Fetch an item from the WebServer using the given 'id'
    public static NoteItem GetById(int id){
        // TODO: implement the fetch to the WebServer

        //Simulates the fetch to WebServer
        return new NoteItem(id, "Testing GetById method",
                "Its just a simulation", null, false);
    }


    //  Insert or Update an item to WebServer
    public void save(){
        Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/notes"));
                        String response = req.performPostRequest(NoteItem.this);

                        // Get the new ID from the server's response.
                        NoteItem respItem = new Gson().fromJson(response, NoteItem.class);
                        id = respItem.getId();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/notes/" + id));
                        req.performPostRequest(NoteItem.this);
                    }
                } catch (Exception e) {
                    Log.e("NoteItem", e.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
        thread.start();
    }

    //  Delete an item to WebServer
    public void delete(){
        if(id != 0) {
            //  Deleting an existing item
            //  TODO: implement the delete to WebServer
        }
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
}
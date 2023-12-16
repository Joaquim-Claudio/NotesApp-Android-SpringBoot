package pt.iade.joaquimclaudio.atividade.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements java.io.Serializable{
    private int id;
    private String title;
    private String content;
    private Calendar creationDate;
    private Calendar modifiedDate;
    private boolean important;

    public NoteItem() {
        this(0, "", "", new GregorianCalendar(), false);
    }

    public NoteItem(int id, String title, String content, Calendar creationDate, boolean important) {
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
                "Ask Jane to send the last math topics", new GregorianCalendar(), false));

        items.add(new NoteItem(2, "Download the new Ed Sheeran's album",
                "", new GregorianCalendar(), true));

        items.add(new NoteItem(3, "Android Notes project",
                "Create a Notes app. Must be released on Wednesday (20th december)", new GregorianCalendar(), false));

        items.add(new NoteItem(4, "Discrete Math Presentation",
                "SQL queries to return the required results", new GregorianCalendar(), true));

        items.add(new NoteItem(5, "Spring-Boot WebServer",
                "Create a WebServer using Spring-Boot framework as ti is required to P.O.O evaluation",
                new GregorianCalendar(), true));

        return items;
    }

    //  Fetch an item from the WebServer using the given 'id'
    public static NoteItem GetById(int id){
        // TODO: implement the fetch to the WebServer

        //Simulates the fetch to WebServer
        return new NoteItem(id, "Testing GetById method",
                "Its just a simulation", new GregorianCalendar(), false);
    }

    //  Insert or Update an item to WebServer
    public void save(){
        if(id == 0) {
            //  Inserting a new item to WebServer
            //  TODO: implement the insert to WebServer

            //Simulates inserting a new item to WebServer
            id = new Random().nextInt(1000) + 1;
        }
        else{
            //  Updating an existing item
            //  TODO: implement the update to WebServer
        }
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
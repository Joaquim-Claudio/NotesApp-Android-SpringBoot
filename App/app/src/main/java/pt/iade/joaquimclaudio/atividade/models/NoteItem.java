package pt.iade.joaquimclaudio.atividade.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NoteItem implements java.io.Serializable{
    private int id;
    private String title;
    private String content;
    private Calendar creationDate;
    private Calendar modifiedDate;

    public NoteItem() {
        this(0, "", "", new GregorianCalendar());
    }

    public NoteItem(int id, String title, String content, Calendar creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = creationDate;
    }

    //  Fetch a list of items from the WebServer and populates 'items' with it.
    public static ArrayList<NoteItem> List(){
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        //  TODO: implement the fetch to the WebServer

        //  Simulates the fetch to WebServer
        items.add(new NoteItem(1, "Math test next week",
                "Ask Jane to send the last math topics", new GregorianCalendar()));

        items.add(new NoteItem(2, "Download the new Ed Sheeran's album",
                "", new GregorianCalendar()));

        items.add(new NoteItem(3, "Android Notes project",
                "Create a Notes app. Must be released on Wednesday (20th december)", new GregorianCalendar()));

        items.add(new NoteItem(4, "Discrete Math Presentation",
                "SQL queries to return the required results", new GregorianCalendar()));

        items.add(new NoteItem(5, "Spring-Boot WebServer",
                "Create a WebServer using Spring-Boot framework as ti is required to P.O.O evaluation",
                new GregorianCalendar()));

        return items;
    }

    //  Fetch an item from the WebServer using the given 'id'
    public static NoteItem GetById(int id){
        // TODO: implement the fetch to the WebServer

        //Simulates the fetch to WebServer
        return new NoteItem(id, "Testing GetById method",
                "Its just a simulation", new GregorianCalendar());
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
}
package pt.iade.joaquimclaudio.server.models;

import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;

public class Note {
    private static int next_id = 1;
    private int id;
    private String title;
    private String content;
    private LocalDate creationDate;
    private LocalDate modifiedDate;
    private boolean important;

    public Note() {

    }

    public Note(String title, String content, LocalDate creationDate, LocalDate modifiedDate, boolean important) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.important = important;
        this.id = next_id;
        next_id++;
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

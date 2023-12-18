package pt.iade.joaquimclaudio.server.models.repositories;

import org.jetbrains.annotations.Nullable;
import pt.iade.joaquimclaudio.server.models.Note;

import java.util.ArrayList;

public class NoteRepository {
    private static ArrayList<Note> notes = new ArrayList<Note>();

    public static void populate() {

    }

    public static ArrayList<Note> getNotes(){
        return notes;
    }

    public static @Nullable Note getNoteById(int id) {
        for (Note n : notes){
            if (n.getId() == id){
                return n;
            }
        }
        return null;
    }

    public static Note addNote(Note note){
        Note newNote = new Note(note.getTitle(), note.getContent(), note.getCreationDate(),
                note.getModifiedDate(), note.isImportant());
        notes.add(newNote);
        return getNoteById(newNote.getId());
    }

    public static boolean removeNote(int id){
        return notes.removeIf(n -> n.getId() == id);
    }

}

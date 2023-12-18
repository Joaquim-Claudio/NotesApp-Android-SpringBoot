package pt.iade.joaquimclaudio.server.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.joaquimclaudio.server.models.Note;
import pt.iade.joaquimclaudio.server.models.repositories.NoteRepository;
import pt.iade.joaquimclaudio.server.models.results.Response;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/notes")
public class NoteController {
    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Note> getNotes(){
        logger.info("Sending all the notes.");
        return NoteRepository.getNotes();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note getNoteById(@PathVariable("id") int id){
        logger.info("Sending the note with id="+id+".");
        return NoteRepository.getNoteById(id);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note addNote(@RequestBody Note note){
        logger.info("Adding a new note.");
        return NoteRepository.addNote(note);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteNote(@PathVariable("id") int id){
        logger.info("Deleting note with id="+id);
        if (NoteRepository.removeNote(id)){
            return new Response(id+" successfully deleted.", null);

        }else return new Response(id+" was not found.", null);
    }
}

package dotcom.si.notes.controllers;

import dotcom.si.notes.DTO.NoteDTO;
import dotcom.si.notes.DTO.NoteMapper;
import dotcom.si.notes.models.Note;

import dotcom.si.notes.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NotesController {

    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/public/notes")
    public ResponseEntity<List<NoteDTO>> getAllNotesPublic() {
        List<NoteDTO> notes = NoteMapper.convertAllToDto(noteService.getAllNotes());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/notes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        List<NoteDTO> notes = NoteMapper.convertAllToDto(noteService.getAllNotes());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable int id) {
        NoteDTO note = NoteMapper.convertToDto(noteService.getNoteById(id));
       return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/notes/new")
    public ResponseEntity<Note> addNote(@RequestBody @Valid Note newNote) {
        Note createdNote = noteService.createNote(newNote);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @PutMapping("/notes/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(id, note);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    @DeleteMapping("/notes/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable int id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //ADDITIONAL APIS
    @PatchMapping("/notes/{id}/addtofavourite")
    public ResponseEntity<Note> addToFavourite(@PathVariable int id) {
         noteService.updateFavourite(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }





}

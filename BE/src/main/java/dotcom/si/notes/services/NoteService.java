package dotcom.si.notes.services;

import dotcom.si.notes.exceptions.NoteNotFoundException;
import dotcom.si.notes.models.Note;
import dotcom.si.notes.repositories.NoteRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepo noteRepo;

    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }


    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }


    public Note getNoteById(int id) {
        return noteRepo.findById(id).orElseThrow(()-> new NoteNotFoundException("Note by id " + id +" not found"));
    }

    public Note createNote(Note newNote) {
//        if(newNote.getDate() == null) {
//            newNote.setDate(LocalDate.now()); //if is not set in the front end - should be, because to the user is important the date he created, the server date can be different from the users (timezones)
//        }
        return noteRepo.save(newNote);
    }

    public Note updateNote(int id, Note note) {
        Note n = noteRepo.findById(id).orElseThrow(()-> new NoteNotFoundException("Note not found, can't update"));
        note.setId(id);
        return noteRepo.save(note);
    }

    public void deleteNoteById(int id) {
        Note n = noteRepo.findById(id).orElseThrow(()-> new NoteNotFoundException("Note not found, can't delete"));
        noteRepo.deleteById(id);
    }

    public void updateFavourite(int id) {
        Note n = noteRepo.findById(id).orElseThrow(()-> new NoteNotFoundException("Note not found, can't update favourite"));
        noteRepo.updateFavourite(id);
    }
}

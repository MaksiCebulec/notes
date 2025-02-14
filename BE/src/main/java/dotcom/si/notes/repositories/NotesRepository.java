package dotcom.si.notes.repositories;

import dotcom.si.notes.models.Note;
import dotcom.si.notes.models.Tag;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class NotesRepository {


    public List<Note> notes = new ArrayList<Note>();


    public List<Note> getAllNotes() {
        notes.add(new Note(123, "Note 1", "This is Note 1", LocalDate.now(), "Business", false));
        return notes;
    }


    public Optional<Note> getNoteById(int id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst();
    }

    public void createNote(Note newNote) {
        notes.add(newNote);
    }

    public void updateNote(int id, Note note) {
        for(Note n : notes) {
            if(n.getId() == id) {
                notes.set(notes.indexOf(n), note);
                break;
            }
        }
    }

    public void deleteNote(int id) {
        notes.removeIf(note -> note.getId() == id);
    }
}

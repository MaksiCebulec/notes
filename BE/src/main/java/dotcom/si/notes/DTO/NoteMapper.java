package dotcom.si.notes.DTO;

import dotcom.si.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteMapper {
    public static NoteDTO convertToDto(Note note) {
        NoteDTO noteDto = new NoteDTO();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setDescription(note.getDescription());
        noteDto.setDate(note.getDate());
        noteDto.setTag(note.getTag());
        noteDto.setFavourite(note.isFavourite());

        return noteDto;
    }

    //for converting multiply (List)
    public static List<NoteDTO> convertAllToDto(List<Note> notes) {
        List<NoteDTO> convertedNotes = new ArrayList<>();
        for (Note note : notes) {
            NoteDTO noteDto = new NoteDTO();
            noteDto.setId(note.getId());
            noteDto.setTitle(note.getTitle());
            noteDto.setDescription(note.getDescription());
            noteDto.setDate(note.getDate());
            noteDto.setTag(note.getTag());
            noteDto.setFavourite(note.isFavourite());
            convertedNotes.add(noteDto);
        }
        return convertedNotes;
    }
}

package dotcom.si.notes.exceptions;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String msg) {
        super(msg);
    }
}

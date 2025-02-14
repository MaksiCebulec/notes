package dotcom.si.notes.repositories;

import dotcom.si.notes.models.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Note n SET n.favourite = NOT n.favourite WHERE n.id = ?1")
    public void updateFavourite(int id);
}

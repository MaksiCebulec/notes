package dotcom.si.notes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @NotEmpty
    private String title;
    private String description;
    private LocalDate date;
    private String tag;
    private boolean favourite;

    public Note() {}

    public Note(int id, String title, String description, LocalDate date, String tag, boolean favourite) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.tag = tag;
        this.favourite = favourite;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

}

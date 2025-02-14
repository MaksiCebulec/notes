import { Component, OnInit } from '@angular/core';
import { ApiNotesService } from '../services/api-notes.service';
import { Note } from '../models/note';
import { HttpErrorResponse } from '@angular/common/http';
import { NgFor } from '@angular/common';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  public notes: Note[] = [];

  public showAddForm: boolean = false;
  public showEditForm: boolean = false;

  public editNote!: Note;

  public selectedFilter!: string;
  public filteredNotes: Note[] = [];

  constructor(private apiNotesService: ApiNotesService) { }

  ngOnInit(): void {
    this.getNotes();
    this.selectedFilter = "all";
    this.filterNotes();

  }

  public getNotes(): void {
    this.apiNotesService.getNotes().subscribe((response: Note[]) => {
      this.notes = response.sort((a, b) => {
        return a.id - b.id
      });
      this.filteredNotes = this.notes;
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addNoteClicked(): void {
    this.showAddForm = true;
  }

  public deleteNote(id: number) {
    if(confirm("Are you sure you want to delete this note?")) {

      this.apiNotesService.deleteNote(id).subscribe((response) => {
        console.log(response);
        this.getNotes();
      },
        error => {
          console.log(error.message);
        }
      );
    }
  }

  public updateNoteClicked(note: Note) {
    this.showEditForm = true;
    this.editNote = note;
  }

  public onUpdateNote(updateForm: NgForm) {
    this.apiNotesService.updateNote(this.editNote.id, updateForm.value).subscribe((response) => {
      console.log(response);
      this.getNotes();
    },
      error => {
        console.log(error.message);
      }
    );
    this.showEditForm = false;
  }

  public onAddNote(addForm: NgForm): void {
    console.log(addForm.value);
    this.apiNotesService.addNote(addForm.value).subscribe(repsonse => {
      console.log(repsonse);
      this.getNotes();
    },
      error => {
        console.log(error.message);
      }
    );

    this.showAddForm = false;
  }

  public addToFavourite(id: number) {
    this.apiNotesService.addToFavourite(id).subscribe((response) => {
      console.log(response);
      //this.getNotes();
      console.log("dd");
      this.getNotes();
      this.filterNotes();
    },
      error => {
        console.log(error.message);
      }
    );
  }

  public closeForm(): void {
    this.showAddForm = false;
    this.showEditForm = false;
  }

  public filterNotes(): void {
    console.log(this.filteredNotes);
    console.log(this.notes);
    console.log(this.selectedFilter)
    switch (this.selectedFilter) {
      case 'all':
        this.filteredNotes = this.notes;
        break;
      case 'favourites':
        console.log("fav");
        this.filteredNotes = this.notes.filter(note => {
          return note.favourite;
        });
        break;
      case 'createdToday':
        this.filteredNotes = this.notes.filter(note => {
          let today = new Date().toISOString().slice(0, 10)
          return note.date == today;
        });
        break;
      default:
        break;  
    }
  }


}

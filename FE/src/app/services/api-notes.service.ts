import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../models/note';
import { format } from 'date-fns';

@Injectable({
  providedIn: 'root'
})
export class ApiNotesService {

  private apiServerUrl: string = 'http://localhost:8080';

  constructor( private http: HttpClient) { }

  public getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiServerUrl}/notes/all`);
  }

  public deleteNote(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiServerUrl}/notes/note/delete/${id}`);
  }

  public updateNote(id: number, note: Note): Observable<Note> {
    return this.http.put<Note>(`${this.apiServerUrl}/notes/note/update/${id}`, note);
  }

  public addNote(note: Note): Observable<Note> {
    const today = new Date().toISOString().split('T')[0]; 
    console.log(today);
    note.date = today;
    return this.http.post<Note>(`${this.apiServerUrl}/notes/note/new`, note);
  }

  public addToFavourite(id: number): Observable<Note> {
    return this.http.patch<Note>(`${this.apiServerUrl}/notes/note/${id}/addtofavourite`, null);
  }
}

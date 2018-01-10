import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class NoteService {
  public API = 'http://localhost:8080';
  public NOTE_API = this.API + '/note';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.NOTE_API);
  }

}

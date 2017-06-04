import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Book} from '../models/book';

@Injectable()
export class RemoveBookService {

  constructor(private http: Http) { }

  sendBook(bookId: number) {
    const url = 'http://localhost:8181/books/' + bookId;

    const headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.delete(url, {headers: headers});
  }

}

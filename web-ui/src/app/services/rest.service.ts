import {Http, Response, Headers} from '@angular/http';
import {Inject, OnInit} from '@angular/core';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";

@Injectable()
export class RestService implements OnInit {
  private headers: Headers = new Headers();

  constructor(@Inject(Http) private http: Http) {
  }

  ngOnInit(): void {
    this.headers.append('Content-Type', 'application/json');
    this.headers.append("Accept", 'application/json');
  }

  public doGet(url: string): Observable<Response> {
    return this.http.get(url, {headers: this.headers});
  }

  public doPost(url: string, dataToSend: Object): Observable<Response> {
    return this.http.post(url, dataToSend, {headers: this.headers});
  }
}

import {Inject, Injectable, OnInit} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "../../../node_modules/rxjs/Observable";
import {Subject} from "rxjs";
import {Response} from "../model/response-dto";

@Injectable()
export class DataService {
  private _session: string;
  private subject = new Subject<Response>();

  insertMessage(message) {
    this.subject.next(message);
  }

  clearMessage() {
    this.subject.next();
  }

  getMessage(): Observable<Response> {
    return this.subject.asObservable();
  }

  public get session(): string {
    return this._session;
  }

  public set session(value: string) {
    this._session = value;
  }
}

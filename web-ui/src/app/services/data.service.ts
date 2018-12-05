import {Inject, Injectable, OnInit} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import {Observable} from "../../../node_modules/rxjs/Observable";

@Injectable()
export class DataService {
  private _session: string;

  public get session(): string {
    return this._session;
  }

  public set session(value: string) {
    this._session = value;
  }
}

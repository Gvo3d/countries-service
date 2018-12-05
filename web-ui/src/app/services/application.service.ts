import {Injectable} from "@angular/core";
import {RestService} from "./rest.service";
import {WebSocketService} from "./web-socket.services";
import {DataService} from "./data.service";
import {Ng4FilesStatus, Ng4FilesSelected, Ng4FilesService, Ng4FilesConfig} from 'angular4-files-upload';

@Injectable()
export class ApplicationService {

  constructor(private _rest: RestService, private _wsService: WebSocketService, private _data: DataService,  private _ng4FilesService: Ng4FilesService) {
  }

  get rest(): RestService {
    return this._rest;
  }

  get ws(): WebSocketService {
    return this._wsService;
  }

  get data(): DataService {
    return this._data;
  }

  get ng4FilesService(): Ng4FilesService {
    return this._ng4FilesService;
  }
}

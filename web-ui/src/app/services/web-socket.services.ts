import {Injectable, OnDestroy, OnInit} from '@angular/core';
import * as stompjs from "stompjs";
import {Client, Frame, Message} from "stompjs";
import * as SockJS from "sockjs-client";
import {Subject} from "rxjs/Subject";

import {RestService} from "./rest.service";
import {Constants} from "../utils/constants";
import {Subscription} from "rxjs/Subscription";
import {DataService} from "./data.service";
import {Response} from "../model/response-dto";

@Injectable()
export class WebSocketService {
  private stompClient: Client;
  private stompClientSubscriber: Subscription;

  constructor(private rest: RestService, private data: DataService) {
    this.init();
  }

  public init(): void {
    console.log("Trying to establish WS connection to remote server");
    this.rest.doGet(Constants.getSessionUrl()).subscribe(x => {
      this.data.session = x.text();
      console.log("Got identity from remote server: "+x.text());
      this.establishSocketConnection();
    });
  }

  private establishSocketConnection(): void{
      const socket: WebSocket = new SockJS(Constants.getWebSocketUrl());
      this.stompClient = stompjs.over(socket);
      this.stompClient.connect('', '', () => {
        console.log("Websocket connection established with: "+Constants.getWebSocketUrl());
        this.stompClient.debug = null;
        this.stompClientSubscriber = this.stompClient.subscribe(Constants.getQueueUrl(this.data.session), (message: Message) => {
          this.onMessage(message);
        });
      }, error => console.log('Error in socket\'s connection: ' + error));
  }

  private onMessage(message: Message) {
    let data: Response = JSON.parse(message.body);
    this.data.insertMessage(data);
  }

  destroy(): void {
    this.stompClientSubscriber.unsubscribe();
    this.stompClient.disconnect(() => {});
  }
}

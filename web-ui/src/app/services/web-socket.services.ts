import {Injectable, OnDestroy, OnInit} from '@angular/core';
import * as stompjs from "stompjs";
import {Client, Frame, Message} from "stompjs";
import * as SockJS from "sockjs-client";
import {Subject} from "rxjs/Subject";

import {RestService} from "./rest.service";
import {Observable} from "rxjs/Observable";
import {Constants} from "../utils/constants";
import {SubscribedMessage} from "../model/socket-message-dto";
import {Subscription} from "rxjs/Subscription";
import {DataService} from "./data.service";


/*
For socket connection. For using disable comments in constructor for "establishSocketConnection()" method.
 */

@Injectable()
export class WebSocketService implements OnDestroy {

  private messageSource = new Subject<any>();
  messageReceived: Observable<any> = this.messageSource.asObservable();
  private stompClient: Client;
  private stompClientSubscriber: Subscription;

  constructor(private rest: RestService, private data: DataService) {
    //this.establishSocketConnection();
  }

  public init(): void {
    console.log("Trying to establish WS connection to remote server");
    this.rest.doGet(Constants.getSessionUrl()).subscribe(x => {
      this.data.session = x.text();
      console.log("Got identity from remote server: "+x.text());
      this.establishSocketConnection();
    });
  }

  public send(message): void {
    const isSocketConnected = (typeof this.stompClient !== 'undefined' && this.stompClient === 'CONNECTED');
    setTimeout(() => {
      message.identity = this.data.session;
      console.log("Sending: "+message.toString());
      this.stompClient.send(Constants.getWebSocketUploadUrl(), {}, JSON.stringify(message));
    }, isSocketConnected ? 0 : 4000);
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

  // private establishSocketConnection(): void{
  //   this.rest.doGet(Constants.getSessionUrl()).subscribe(response => {
  //     this.identity = response.text();
  //     const socket: WebSocket = new SockJS(Constants.getWebSocketUrl());
  //     this.stompClient = stompjs.over(socket);
  //     this.stompClient.connect('', '', () => {
  //       this.stompClient.debug = null;
  //       this.stompClientSubscriber = this.stompClient.subscribe(Constants.getQueueUrl(this.identity), (message: Message) => {
  //         this.onMessage(message);
  //       });
  //     }, error => console.log('Error in socket\'s connection: ' + error));
  //   });
  // }

  private onMessage(message: Message) {
    let json: JSON = JSON.parse(message.body);
    this.messageSource.next(json);
    console.log(json)
  }

  ngOnDestroy(): void {
    this.stompClientSubscriber.unsubscribe();
    this.stompClient.disconnect(() => {});
  }
}

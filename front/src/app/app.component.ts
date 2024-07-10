//import { Component } from '@angular/core';
//import * as Stomp from 'stompjs';
//import * as SockJS from 'sockjs-client';@Component({
// selector: 'app-root',
// templateUrl: './app.component.html',
// styleUrls: ['./app.component.css']
//})export class AppComponent {  title = 'WebSocketChatRoom';  greetings: string[] = [];  disabled = true;  newmessage: string;  private stompClient = null;  constructor(){}  ngOnInit() {    this.connect();  }  setConnected(connected: boolean) {    this.disabled = !connected;
//    if (connected) {
//      this.greetings = [];
//    }  }  connect() {    const socket = new SockJS('http://localhost:8080/testchat');
//    this.stompClient = Stomp.over(socket);
//    const _this = this;
//    this.stompClient.connect({}, function (frame) {
//      console.log('Connected: ' + frame);
//      _this.stompClient.subscribe('/start/initial', function(hello){
//        console.log(JSON.parse(hello.body));
//        _this.showMessage(JSON.parse(hello.body));
//      });
//   });
//  }  sendMessage() {    this.stompClient.send(
//      '/current/resume',
//      {},
//      JSON.stringify(this.newmessage)
//    );
//    this.newmessage = "";  }  showMessage(message) {    this.greetings.push(message);  }}

import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Client, Message } from '@stomp/stompjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'WebSocketChatRoom';
  greetings: string[] = [];
  disabled = true;
  newmessage: string = '';
  private stompClient: Client | null = null;

  constructor() {}

  ngOnInit() {
    this.connect();
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;
    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    const socket = new SockJS('http://localhost:8080/testchat');
    this.stompClient = new Client({
      webSocketFactory: () => socket,
      debug: (str) => { console.log(str); },
      reconnectDelay: 5000,
    });

    const _this = this;

    this.stompClient.onConnect = function (frame) {
      console.log('Connected: ' + frame);
      _this.stompClient?.subscribe('/start/initial', function (message: Message) {
        console.log(JSON.parse(message.body));
        _this.showMessage(JSON.parse(message.body));
      });
    };

    this.stompClient.onStompError = function (frame) {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    this.stompClient.activate();
  }

  sendMessage() {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.publish({
        destination: '/current/resume',
        body: JSON.stringify(this.newmessage)
      });
      this.newmessage = '';
    }
  }

  showMessage(message: string) {
    this.greetings.push(message);
  }
}


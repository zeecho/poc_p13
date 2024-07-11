import { Component, OnInit } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
})
export class ChatComponent implements OnInit {
  greetings: string[] = [];
  disabled = true;
  newmessage: string = '';
  private stompClient: Stomp.Client | null = null;

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
    this.stompClient = new Stomp.Client({
      webSocketFactory: () => socket,
      debug: (str) => { console.log(str); },
      reconnectDelay: 5000,
    });

    const _this = this;

    this.stompClient.onConnect = function (frame) {
      console.log('Connected: ' + frame);
      _this.stompClient?.subscribe('/start/initial', function (message) {
        console.log(JSON.parse(message.body));
        const parsedMessage = JSON.parse(message.body);
        _this.showMessage(`${parsedMessage.username}: ${parsedMessage.message}`);
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
        body: JSON.stringify({username: localStorage.getItem('username'), message: this.newmessage})
      });
      this.newmessage = '';
    }
  }

  showMessage(message: string) {
    this.greetings.push(message);
  }
}

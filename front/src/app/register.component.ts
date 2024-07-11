import { Component } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent {  
  username: string = '';
  password: string = '';
  email: string = '';
  firstName: string = '';
  lastName: string = '';
  dateOfBirth: string = '';
  address: string = '';

  constructor(private authService: AuthService) {}

  register() {
    const user = {
      username: this.username,
      password: this.password,
      email: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      dateOfBirth: this.dateOfBirth,
      address: this.address
    };
    this.authService.register(user).subscribe(response => {
      console.log('User registered successfully');
    });
  }
}

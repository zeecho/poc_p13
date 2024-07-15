## What is it?

This is a Proof of Concept for the chat of Your Car Your Way (OpenClassrooms Projet 13).

It's mostly based on this tutorial: https://medium.com/linkit-intecs/real-time-updating-chat-room-using-angular-6-spring-boot-web-sockets-cc1a6e9d4944

You can create an account (by clicking on Register) and then Login (passwords can be anything, they are not checked for this PoC).

Once logged in, you'll be able to go to the Chat page and start talking.


## Set-up the database

The project uses mariadb 11. You need to have it installed on your machine.

There is an SQL script for creating the schema: `resources/base.sql`
And there is one to add example data: `resources/example_data.sql`

You can import all of this by creating a database through your mysql console. 

First let's open the mysql console with (using your credentials):
`mysql -u USERNAME -pPASSWORD`

and then you can create a database like this (the user you're using must have the rights to do so):
`CREATE DATABASE your_car_your_way;`

if you want to use another name than "your_car_your_way" for the database you can choose whatever you want).

Once done, you can exit the mysql console (Ctrl+D).

And then you can launch this command (still using your credentials and the name you gave to your DB instead of your_car_your_way if neeeded):
`mysql -u USERNAME -pPASSWORD your_car_your_way < resources/base.sql`

You should now have everything you need in your db. 

Now think about editing the file `back/src/main/resources/application.properties`, replacing "your_car_your_way" by your database name, "user" by your actual username and password by your actual password:
spring.datasource.url=jdbc:mysql://localhost:3306/mdd?allowPublicKeyRetrieval=true
spring.datasource.username=user
spring.datasource.password=123456


## Back

The back is a Spring Boot 2.6 project (with Maven), with Java 17.


### Build

You can build the project using this command:

`mvn clean:install`

## Front

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.2.

Don't forget to install your node_modules before starting (`npm install`).

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.




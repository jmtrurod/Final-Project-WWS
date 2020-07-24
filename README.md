# Final-Project-WWS

## Introduction
World Wide Spain (WWS) is an app which purpose is to connect Spanish people all over the World (for sure, as it grows it could implement all nationalities!).

Imagine the next situation which is probably very familiar to you: you go to a foreign country and you spend a lot of money into very bad restaurants or you spend a lot of money too visiting very famous places which in the end didn't worth at all because they are there just to hunt tourists like you!

You probably would like to get in touch with people with the same background as you that have already lived where you're going to go and can recommend you the best places to go, eat, visit or whatever!

This is the intenction of this app.

## How does it Work
It's pretty simple. There are two roles: USER and ADMIN.

There's only one ADMIN and no more can be created. It's username is *admin* and it's password is also *admin*.
Admin can visit City portals and User profiles. They have two special habilities:
* They can delete any post they want.
* They can create City portals.

On the other hand, we have USERS. You can create one of them by clicking on *REGISTER* in the login page or you can use an existing one: *cameron*, *chandler*, *joeytriv* or *phoebebuff*. In any of theese cases, password is equal to username.
Users can also visit City portals an other User profiles. Special topics about them:
* They can create a Post from their profile about any of the already existing cities.
* They can only delete their own posts.
* They can chat with other users.
* They can show interest or dislike to any post but only once.

## Chats
* Conversations aren't stored anywhere.
* Two users will be able to communicate in case both of them are at the same time in the chat portal.

## Development
Many different technologies have been involved in the development of this app:
* Spring-Boot as framework to develop BACK.
* Angular as framework to develop FRONT, applying also Angular Material and Bootstrap.
* Hystrix to avoid cascading-failure.
* Feign to manage conexion between edge-microservice and the other microservices.
* Websocket to allow low latency conexion in chats.
* SQL databases to store Security Users, normal Users and Posts.
* Mongo database to store Cities.
* Heroku to deploy Back.
* Firebase to deploy Front.
* Swagger to generate documentation.

## Documentation
You can visit all end-points in the following URL:

https://wws-edge-service.herokuapp.com/swagger-ui.html

## Be Patient
This web application uses microservices structure. It has the following microservices:
* Edge-Service
* User-Service
* Post-Service
* City-Service
* Security-Service
* Websocket-Service

A free account int Heroku has been used for deployment, so be patient on the beginning when using this app because all microservices must wake up for a logic-fast use.

## Persistance in databases
The Mongo Database (used for City-Service) has been deployed using Mongo Atlas. This means there will be persistance with related City Portals.

There won't be persistance in the left data due to h2-database in heroku forgets its data when the related microservice sleeps.

## Improvements
There's a lot of job left to conform a real and professional app that could have been achieved in case this project didn't last only 7 days:
* Chats should store conversations and advise users when they are talking to another user who is offline.
* When a post karma turns lower to -5 (for example), a notification should be sent admin so he could revise this post.
* Users should register with sent-by-email key and not so easily as they can do now.
* Profile and City portal should show posts in a Pageable way.
* ... and better front, for sure!!

## Link
Don't waste your time and try the app with the following link: 

https://front-wws.firebaseapp.com/

Remember to be patient on the beginning!!


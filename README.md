# README.md for Blog Application

I am following a Spring Boot + Kotlin tutorial (found here https://spring.io/guides/tutorials/spring-boot-kotlin)

## Things I've added to the tutorial

1. Create a new user page + button to get to that page
2. Create a new article + button to navigate to that page
3. View all users
4. View all articles
5. Navigation bar on all pages
6. Banner to show on all pages (except new user/article pages)

## Navigation Links when running the application

- `http://localhost:8080/home` --> Home page
- `http://localhost:8080/users` --> View all users
- `http://localhost:8080/articles` --> View all articles
- `http://localhost:8080/user/new` --> Create a new user
- `http://localhost:8080/article/new` --> Create a new article
- `http://localhost:8080/article/{title-of-article}` --> View a specific article

## TODO: 

1. Add a way to delete users/articles
2. Fix duplicate tables being created in the database
   - `USERS` (get rid of creating)
   - `users` (writing)
   - `ARTICLES` (get rid of creating)
   - `articles` (writing)
   - Also figure out how to not have the DB edit every change to the code (md files, 
   anything not related to the DB, etc.)
3. Clean up webpage look
   - Edit the format of the articles
   - Make the date/time stamp look more clean
   - Create a better looking banner
4. Make the code clean (according to the rules of clean code + look at rt-domain-pro, etc.
to get a better idea)
   - Separate repositories
   - Separate controllers
     - Make the controller RESTful controllers??
   - Separate entities (to models?)
   - Create tests that cover most of the code
     - Postman tests
     - Unit tests
     - Integration tests
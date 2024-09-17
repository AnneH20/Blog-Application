# README.md for Blog Application

I am following a Spring Boot + Kotlin tutorial (found here https://spring.io/guides/tutorials/spring-boot-kotlin)

## Things I've added to the tutorial

1. Create a new user page + button to navigate to that page
2. Create a new article + button to navigate to that page
3. Delete a user page + button to navigate to that page
4. Delete an article page + button to navigate to that page
5. View all users - Displays "no users" if users are 0
6. View all articles - Dislays "no articles" if articles are 0
7. Navigation bar on all pages
8. Banner to show on all pages
9. Home Page displays on `/` or `/home`
10. The "headline" field is now the article description
    - First 5 words of an article - If less than 5 words, display no description

## Navigation Links when running the application

- `http://localhost:8080/` **OR** `http://localhost:8080/home` --> Home Page
- `http://localhost:8080/users` --> View all users
- `http://localhost:8080/articles` --> View all articles
- `http://localhost:8080/user/new` --> Create a new user
- `http://localhost:8080/user/delete` --> Delete a user
- `http://localhost:8080/article/new` --> Create a new article
- `http://localhost:8080/article/delete` --> Delete an article
- `http://localhost:8080/article/{title-of-article}` --> View a specific article (use hyphens, - , between words)

## TODO: 

1. Fix duplicate tables being created in the database
   - `USERS` (get rid of creating)
   - `users` (writing)
   - `ARTICLES` (get rid of creating)
   - `articles` (writing)
   - Also figure out how to not have the DB edit every change to the code (md files, 
   anything not related to the DB, etc.)
   - Migrate to MySQL DB
2. Clean up webpage look
   - Edit the format of the articles
   - Make the date/time stamp look more clean
   - Create a better looking banner
3. Make the code clean (according to the rules of clean code + look at rt-domain-pro, etc.
to get a better idea)
   - Separate repositories
   - Separate controllers
     - ~~Make the controller RESTful controller~~ **Cannot do because of the HTML, can test REST controller in Postman**
   - Separate entities (to models?)
4. Create tests that cover most of the code
   - Postman tests
   - Unit tests
   - Integration tests
5. Add verification
   - Must have a valid username to delete it (case-sensitive)
   - Must fill out all fields to create a new user/write a new article
   - Must have a valid username to write an article (case-sensitive)
6. Add a search bar to search for articles

# Film Query App

## Description
This program allows the user to search a data base by inputting commands in the console. A user is able to look up information from a database that will then retrieve the information requested using SQL queries. The user is able to search using numerical values which represent an Actor's ID, or Film ID, depending on the specific information a user may want. The user is also able to do a search using keywords, for example, if the user searched "stamp", then the program would do a query and match any instances where the word "stamp" may appear in the database. The program would then out put the Film's ID, Title, Description, Year released, and the Language it is in. This program makes use of JDBC in order to make a query. I have used joins in order to group multiple tables and get the results needed.
## Technologies Used
Java
SQL
Maven

## Lessons Learned
I have gained a deeper understanding of Joins using mySQL, as well as creating and experimenting with creating separate classes in order to represent the films and actor objects. I have also had to handle exceptions in this project in order to make the program run smoothly and not display a stack trace directly to the user, and instead displaying the appropriate message so the user can know how to proceed next. I used a do while loop, as well as a switch statement in order to create the menu in which the user will use to engage with the program. 
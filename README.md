## FILMQUERY

## Week 7 HomeWork SD-22

## OVERVIEW

- On the surface it looks like a simple little app, where the user is allowed to
look up actors and films from a database to get information about each.

- This app brings together some of the lessons learned this week.  It combines
the below listed technologies.  The premises is to teach the student how to use
SQL commands within Java and reach across to a local server to extract queries
and print them out using a database accessor.

## TECHNOLOGIES

* Java
* SQL (specifically mySql)
* sdvid (database)
* Apache Maven
* Eclipse
* Terminal
* Atom (text editor)

## HOW TO RUN
- Self-explanatory; run the app and a popup menu appears, giving the user a few
options. Not complicated on the users side.  This is a build-upon app that will in
the future, do more.  But for now a basis for the programmer to learn on.

## LESSONS LEARNED

- The database accessor objective was the power horse of this project. I learned
the basis of Maven, which allows the programmer to reach across from within a Java
program in Eclipse to extract data.  We had to first start with building, and later
adding dependencies into a pom.xml document.  From there a connection was needed
from Java to a database in-which a handshake between the two was needed and achieved
by passing a user name and password. Java was just the language we used to talk to
Apache Maven that made the queries to our local server that holds our SQL database (sdvid)
on. It was important to have a prepared statement that was passed thru a connection
created. We used standard SQL commands within this prepared statement.  As a result
it was helpful to get a good understanding of SQL commands prior to attempting this
project.

- When fellow programmers look at my SQL commands and then at my naming conventions
I used to send the received back data to an output or into a list, I was constantly
being told I did not need so much.  But I did this to prevent confusion both on my
part and on any future readers part.  I liked the concept introduced to us in class of
always using the table.name method.  Then I will always know what specifically I am
asking for and from where I am getting it from.  Additionally setting the information
on the Java side with a longer name that is very specific tells anybody reading the
code exactly what is being set as it gets sent to the class to be working with the
data.

- The introduction to SQL this week was enjoyable. I found it entertaining to solve
the connection problem using JOIN ON and the LIKE%this% in addition to getting a
understanding of databases and their overview.

# Expenses Manager
This is a CRUD project I made for a subject which is meant to help you manage your expenses. This is the first project I developed with **Spring Framework**.

You can:
* Add expenses
* Remove expenses
* Search for expenses

You can add expenses only for the current month.

***
### Main technologies
These are the **main technologies** used in the project:
1. Java
2. Spring Framework (JPA, Security)
3. Hibernate ORM
4. Vue JS
5. Axios
6. jQuery
7. Html, CSS and Bootstrap

***
# Deployment
In order to **deploy** the project you are going to need:
1. Any IDE with support for Java EE (Be it eclipse, IntelliJ, etc...)
2. Apache Tomcat (download zip from https://tomcat.apache.org/download-90.cgi)

MySQL is used as a database.

Follow this tutorial in order to set up Tomcat in Eclipse: https://crunchify.com/step-by-step-guide-to-setup-and-install-apache-tomcat-server-in-eclipse-development-environment-ide/


Import the project and wait for the dependencies to solve.

Open **persistence.xml, database.properties, application.properties** and setup the url, user and password.

Once that's setup, now it's time to discuss the database. Although an ORM is used, I decided that it's better to use a prebuilt sql file with the tables already defined.

Open the **database** folder and import them in a database with the same name as the file (2 databases. One for each file, **usuarios** holds the users and **expensemanager** holds the expenses).

Once that's done you're ready. Open the project in the port you've setup the project and start by registering a user. After, login and you can start testing the web-app.

# Documentation
You can find the javadocs inside the **doc** folder.

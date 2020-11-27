# JEE Project - Team 3 - V2

# Table of contents:

- [Pre-reqs](#pre-reqs)
- [Getting started](#getting-started)
- [JEE + JPA](#JEE-/-JPA")
  - [Project Structure](#project-structure)
  - [Building the project](#building-the-project)
  - [Testing](#testing)
- [Dependencies](#dependencies)
  - [`dependencies`](#dependencies-1)
  - [`text dependencies`](#devdependencies)

# Pre-reqs

To build and run this app locally you will need a few things:

- Install Glassfish 5
- Derby Server
- An IDE (Intellij/Netbeans or Eclipse Pro)

# Getting started

- Clone the repository

```
git clone https://github.com/victoria-cs-doe/Project-Team3-JEE-JPA-Version.git
```

- Create a JavaDB database : 
  * Name : ST2EEDB
  * Root : adm
  * Password : adm

- Execute scripts :

    * To create tables
    ```sh
    docs/database/tables/script_db_derby.sql
    ```
  
    * To generate data
     ```sh
    docs/database/data/data.sql

- Build and run the project


# JEE / JPA

## Project Structure

The full folder structure of this app is explained below:

> **Note!** Make sure you have already built the app with maven

| Name                               | Description                                                                                                |
| ---------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **docs**                           | Contains documents and sql scripts                                                                         |
| **src**                            | Contains your source code that will be compiled to the war file                                            |
| **src/../forms**                   | Contains functions to handle forms                                                                         |
| **src/../controllers**             | Controllers define servlets controllers                                                                    |
| **src/../models**                  | Models classes from entities                                                                               |
| **src/../services/authentication** | Contains services to handle authentication login                                                           |
| **src/../services/database**       | Contains service to load persistence-unit                                                                  |
| **src/../services/entity**         | Contains services of each entities to perform sql actions                                                  |
| **src/../uti/**                    | Contains sub-utils packages / Validation data methods and attach fields to entity methods                  |
| **src/../util/constants**          | Contains classes of constants : Views, Paths, Attributes, Fields                                           |
| **src/../util/filters**            | Contains filters                                                                                           |
| **test**                           | Contains your tests. Separate from source because there is a different build process.                      |

## Building the project

To build the project, configure the glassfish server and add `jpaversion:war exploded` to deployment.
Build project, it's generate a war file


### Running the build

Start the glassfish server with the war file

## Testing

For this project, we choose Junit, Jmeter, Mokito and sonarcude as our test frameworks.

### Running tests

Simply run `mvn surefire:test`.
Note this will also generate a coverage report.

# Dependencies

Dependencies are managed through `pom.xml`.
In that file you'll find two sections:

## `dependencies`

| Package                 | Description                                                |
| ----------------------- | ---------------------------------------------------------- |
| javax.persistence-api   | Java(TM) Persistence API                                   |
| javax.servlet-api       | Java Servlet API                                           |
| jstl                    | Jakarta Standard Tag Library                               |
| org.json                | JSON library                                               |
| hibernate-entitymanager | Hibernate ORM                                              |
| hibernate-validator     | Hibernate's Jakarta Bean Validation                        |
| derby                   | Contains the core Apache Derby database engine, which also includes the embedded JDBC driver.     |
| derbynet                | Contains the Apache Derby network server, which allows remote clients to connect to Derby databases over a network connection using the Derby client JDBC driver.                            |
| derbyclient             | The Derby client JDBC driver, used to connect to a Derby server over a network connection.                 |

## `test dependencies`

| Package      | Description                                                             |
| ------------ | ----------------------------------------------------------------------- |
| junit-jupiter-api       | Module "junit-jupiter-api" of JUnit 5.     |
| junit-jupiter-engine         | Module "junit-jupiter-engine" of JUnit 5.             |
| mockito-core | Mockito mock objects library core API and implementation   |
| mockito-all         | Mock objects library for java                                         |


To install or update these dependencies you can use `yarn`.

## `JUnit tests`
![alt text](http://docs/tests/JUnit tests.png)

# Analyze public data with Hibernate
Console application for managing Countries data using Hibernate and H2 file database.

Project was meant to follow a set of requirements. Base requirements were as follows:

1. Gradle project set up correctly, including a build file with necessary dependencies and the default directory structure for
the Gradle Java plugin:
  - src/main/java
  - src/main/resources
2. Project contains a well-formed Hibenate configuration file, accurately speciying the following:
  - Connection URL
  - DB driver class
  - Mapped entity class
3. Only one SessionFactory is instantiated and reused troughout the application to create sessions
4. Uses Hibernate to retrieve lists of entity objects and single entity objects
5. Uses transactions with Hibernate sessions to save and delete single entity objects
6. All data is presented as well formatted table, using column formatting and 2 decimals of precision
7. Column headings are present
8. NULL database values as "--"
9. Maximum and minimum are calculated accurately for each indicator
10. Correlation coefficient between the two indicators is calculated accurately
11. UI contains functioning menu options for all features:
  - Viewing data table
  - Viewing statistics
  - Adding a country
  - Editing a country
  - Deleting a country

Additional requirements (also implemented):

1. Uses the builder pattern to create new entieties
2. Correlation coefficient is calculated without the use of a third-party library
3. Maximum and minimum are calculated with the use of Java streams


To check my other work please go to:

- https://github.com/grzegorzkonczak/instateam-with-spring-and-hibernate - Project team management web application using Spring with Hibernate.
- https://github.com/grzegorzkonczak/todo-api-with-spark - REST API for "TODO" application using Spark framework
- https://github.com/grzegorzkonczak/countries-of-the-world-with-spring - Spring web application that displays information about 5 countries
- https://github.com/grzegorzkonczak/personal-blog - Simple web blog application built using Spark Framework
- https://github.com/grzegorzkonczak/Soccer-League-Organizer - Console based soccer team management application
- https://github.com/grzegorzkonczak/how_many_in_jar_game - Console based implementation of "How many in jar" game

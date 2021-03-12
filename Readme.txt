Brief information: 
The topic of our project on the subject of OOP Java is "Vigilance Simulator". 
This project is a portable simulator for the development of alertness, as well as a partial development of the user's speed when writing various types of texts. 
When compiling our code, the console opens, with which the user can select a specific text that is stored in the database, or select a random text from the database. 
The user also has the ability to add their own material (text) to the database, after which they can independently use this simulator. 
If the selected texts are successfully written, the person will receive a congratulatory notification about the successful completion of the operation (a special message will be displayed that the user's name, text number, time and print speed will be embedded in the database, namely in the honor table). 
If the person made the slightest mistake when performing the task, there will be a notification that the user made a mistake, and his data will not be embedded in the honor table.

Brief description of classes:
In this project, we use 3 classes (Main, DB, and Time). 
The Main class connects to the database server and exits the connection. 
Along with this is an infinite loop, which is meant as a menu for several options for further user actions. 
Most of the code is written in the DB class, and it is the main one in the project. 
The class contains methods that directly interact with tables in the database, and the methods that are responsible for implementing the methods specified in the Main class are registered. 
The Time class is created to control the time at which the user will make changes in the console.
When writing a project, we use: database linking, encapsulation, principles of inheritance and polymorphism, etc.

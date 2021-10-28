# Canvas Application  

Coding challenge for Front Office Developer interview.

## How to Use  
____  

### To run tests (All)  
```shell
mvn clean package failsafe:integration-test
```  
Or only Unit tests  
```shell
mvn clean test
```  

### To run the application  

From your IDE, the starting point is MainApplication.main, 
or you can use the uber-jar (include all dependencies) created on ```mvn package```.  

```shell
java -jar target/canvas-challenge-1.0-SNAPSHOT-runnable.jar
```  


## Some context  

I decided to work on this exercise as if it was a real application.  
Therefore, even if this use case is simple, I designed the architecture as if I was building a complex application,
to show you how I could design my applications.
<br>  
I did not push the game up to include Spring dependencies, as it would have been excessive.
The only dependencies I used are **Guava** and **Lombok** to avoid a lot of boilerplate code.

I created a fake context ```ApplicationContext``` to perform DI and provide the application with the principal
'beans' (not real bean objects) it requires in order to run.  

```CanvasApplication``` is the real application, ```MainApplication``` just create & run it. I added
this layer to have more control on the main method (here, *run*) and being able to inject i/o for testing purpose
(MainApplication just provide System i/o, but I can test with my own i/o and perform assertions on the visual output).  


## Code Architecture  

The main loop is in ```CanvasApplication```. Each time a user supply a command, it is parsed and 
a ```CommandService``` transfer the command to the relevant ```CommandExecutor```. For each command type, 
we have 1 ```CommandExecutor```. I added 2 new commands (Point, HELP) from the initial guidelines to show you how easy it is to extend the features of the application.  
Common verifications performed by executors are shared, either in an ```**Utils``` class or in a shared abstract parent. 
When working on the canvas is necessary, components call the ```CanvasService``` that is knowledgeable to work on it.  
It is the only interface to the application's ```Canvas```.

The ```Canvas``` is inspired by canvas in vector graphics editors (i.e, Inkscape, Illustrator). Thus, a shape can be added
outside the ```Canvas```, it will just not be displayed until the canvas is enlarged. To continue on this logic inspired by real-life 
graphics applications, a ```CommandExecutor``` that want to draw on the canvas don't do it directly, they have to create a mask ```(DrawTable)```. 
The ```CanvasService``` will then apply this mask to the existing canvas.   

By default, new points has priority, but I changed the signature of some commands to add the possibility to override this hierarchy.  
See ```CommandType```, or directly in the application trigger the **HELP** command.

I also decided to do not use *System.out* and *System.in* directly to print and request inputs, that's why there is an io package. Thanks
to that, it is easier to test my output and the behavior of my components. Also, it adds a potential evolution to our application:
now it is very easy to change our input/output sources from the stds.  


## WHAT HAPPENS IF...  

Here are some implementation choices I made as the guidelines do not discuss these points.  

**Shape is outside the canvas...**: As explained earlier, this application acts like the Inkscape editor. It is possible to draw outside the canvas,
it just won't display in the final result until you enlarge the canvas enough for the shape to be visible. To prevent
users to be confused, a *warning* message displays to explain this choice. I preferred this solution rather
than throwing an Exception, the application now has a new feature, and I avoid throwing a frustrating error to the user.  

**A new shape overlaps an old one...**: By default, new items have the priority. Some commands allow the user to override this parameter,
as the Line and Rectangle commands. See ```CommandType```, optional parameter --low.

**The color argument is missing from command's body...**: As we already have a "default" color, 'x', I decided to use it by default. Therefore,
the color argument of the existing commands is optional, the user do not need to add

**Fill performed on a shape, not on an empty space...**: I decided to interpret this command as the bucket tool of Inkscape.  
If the command designate an existing point on the canvas, it and all its adjacent siblings will be colorized into the new color.

**Fill performed outside the canvas...**: This is the only "drawing-command" that is not allowed to perform action outside the canvas.
This is to avoid infinite recursion, based on my implementation of the *fillArea* method. It uses the canvas's borders as a limit frame.
(hint: The fill function propagate from origin point to all the top, bottom, left, right points inside the canvas limit. Recursively.).

**Unknown command or empty...**: I display an error message to help the user to understand how to use this application.  

**Unexpected error happens..**: I catch all unexpected exceptions in the main loop to display it (and its stackTrace).
It won't make the application fail.  



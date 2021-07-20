Eclipse shortcuts

Crtl +1 for quickfix
To run specific junit , highlight that Junit and run as Junit


 
Crtl +Space for Autofill
 
Views are like different windows in eclipse like project explorer, console, problems , etc..
 
Perspective is arrangement of different windows like Java perspective project explorer in left, console in down like that.
In Debug perspective, you see break point windows, out line , failure trace.
 
 
ALT + up arrow/ down arrow -> to move selected line up and down
 
Open file / class
CTRL + SHFT +R  -> to open recourse   type camel case to search faster SH for StringHelper class
CTRL + SHFT +R  -> to open type 

CTRL + m -> to expand class
 
CTRL + T  -> to see completed hierarchy of that class.
CTRL + L  -> to go line number
CTRL + Q  -> to go to last edited line
CTRL + D -> to delete the line
CTRL + O  -> to see outline
CTRL + E  -> to shift editor.
 
ALT + SHFT + R --> to refactor or rename local variable /method/ class names
	Another to above command to highlight the word and modify, it modifies all places where the word it used
ALT + SHFT + M --> to refactor or extract part and create a method  
ALT + SHFT + I -->  reverse to above extract . It is inline refactor.
ALT + SHFT + L -->  extract local variable.
ALT + SHFT + I -->   reverse to above extract . It is inline variable. 
Extract constant  -> right click > refactor > Extract constant
 
ALT + SHFT + S -->  to generate setters ,getters and others
 
Formatting source code : to format we need right click > source > format
To automacticall format every time you save code
Window > preferences > save action > check perform selected action > format
 
Debug mode:
 
Maven commands
 
      if it is web application , go that folder 
      type cmd  > mvn clean install  -> to build download all jars
      mvn help : effective-pom  ->  to see effective pom
     mvn tomcat7: run  -> to run the web application
     mvn  -X clean -> to run in DEBUG mode
 
 
Not need , we can see in eclipse pom.xml by clicking dependency 
In cmd  
mvn dependency : tree
mvn dependency : sources
 
 
mvn help : effective-settings  ->  to see where the local mvn repository is
Output:       <localRepository>C:\Users\sagar\.m2\repository</localRepository>
         
 
 
Eclipse Commands 

CTRL + SPACE  autofill
main
sysout

CTRL + 1  auto suggestion

CTRL + SHFT + R open resourse like .java when you know exact file name
CTRL + SHFT + T opens .class file like hashmap, arrayList
CTRL + SHFT + L list all short cuts

F3  opens declaration code
F4  opens type hierarchy like inheritence

Refactoring short cuts

Toggle mark occurrences :  if you highlight some variable, it shows all occurrences of that variable
Toggle Breadcrumb :  it show hierarchy of function highlited
 
ALT+SFT+R  -> to rename a variable / Method /interface / Class
 
ALT+SFT+M -> to Extract  Method /interface / Class
ALT+SFT+L -> to Extract expression to variable.
ALT+SFT+ T  -> to get Extract Menu.
 
ALT+SFT+I   -> to inline a variable / Method /interface / Class
ALT+SFT+C -> to add  parameters to Method.
 
ALT+SFT+ F  -> to format source  code 
 
Save actions  --> to set like auto format code when edit and save an organize anything you want to customise
			Like remove unused imports, variables , etc..
			 
Views :  Different views serve different purse like console, project explorer, type hirearchy
Perspective : Java perspective will have different views, debug perspective will have different views like debug, break points, 
                        Java browsing will have different  views like call hierarchy when you want to understand the code.
                        Different perspectives like git, JPA, resource,
                        you can create new perspective  like adding new views.
 
Plugins :    
		checkstyle
		Find bug
		infinitest
		
		
		
		Added on 27-02-2020 07:40 PM 
		
		To know implementations of a selected interface  , move cursor over it , it shows declaration, implementation or right click on that interface and select F4
		
		CTRL + SFT + G to look for references in workspace
		
		
		You need to tell these 3 things in order to setup spring
		What are the beans ?
		What are the dependencies of a bean ?
		Where are the beans to search for (location or package of beans) ?
		
		
		Spring-config.xml shortcuts
		
		To go to selected value referred CTRL +SFT+ K
		
		Select ref= "addrs" , select addrs and press F3 , it goes to address bean defination
		
		
Plurasight



latest till above
=============================================================================================================

One of best shortcut is below 2.

Crtl +1 for quickfix ->  it will show alternatives or options that are available at that point of time. 

Crtl +Space for Autofill 
 like sysout , main

Views are like different windows in eclipse like project explorer, console, problems , etc..

Perspective is arrangement of different windows like Java perspective project explorer in left, console in down like that.
In Debug perspective, you see break point windows, out line , failure trace.

To run specific junit , highlight that Junit and run as Junit

ALT + up arrow/ down arrow -> to move selected line up and down

Open file / class
CTRL + SHFT +R  -> to open recourse   type camel case to search faster SH for StringHelper class
CTRL + SHFT +R  -> to open type 

CTRL + T  -> to see completed hierarchy of that class.
CTRL + L  -> to go line number
CTRL + Q  -> to go to last edited line
CTRL + D -> to delete the line
CTRL + O  -> to see outline
CTRL + E  -> to shift editor.

ALT + SHFT + R --> to refactor or rename local variable /method/ class names
	Another to above command to highlight the word and modify, it modifies all places where the word it used
ALT + SHFT + M --> to refactor or extract part and create a method  
ALT + SHFT + I -->  reverse to above extract . It is inline refactor.
ALT + SHFT + L -->  extract local variable.
ALT + SHFT + I -->   reverse to above extract . It is inline variable. 
Extract constant  -> right click > refactor > Extract constant

ALT + SHFT + S -->  to generate setters ,getters and others

Formatting source code : to format we need right click > source > format
To automacticall format every time you save code
Window > preferences > save action > check perform selected action > format

Debug mode:

Maven commands

      if it is web application , go that folder 
      type cmd  > mvn clean install  -> to build download all jars
      mvn help : effective-pom  ->  to see effective pom
     mvn tomcat7: run  -> to run the web application
     mvn  -X clean -> to run in DEBUG mode


Not need , we can see in eclipse pom.xml by clicking dependency 
In cmd  
mvn dependency : tree
mvn dependency : sources


mvn help : effective-settings  ->  to see where the local mvn repository is
Output:       <localRepository>C:\Users\sagar\.m2\repository</localRepository>
         


Eclipse Commands 

Toggle mark occurrences :  if you highlight some variable, it shows all occurrences of that variable
Toggle Breadcrumb :  it show hierarchy of function highlited

ALT+SFT+R  -> to rename a variable / Method /interface / Class

ALT+SFT+M -> to Extract  Method /interface / Class
ALT+SFT+L -> to Extract variable.
ALT+SFT+ T  -> to get Extract Menu.

ALT+SFT+I   -> to inline a variable / Method /interface / Class
ALT+SFT+C -> to add  parameters to Method.

ALT+SFT+ F  -> to format source  code 

Save actions  --> to set like auto format code when edit and save an organize anything you want to customise
			Like remove unused imports, variables , etc..
			
Views :  Different views serve different purse like console, project explorer, type hirearchy
Perspective : Java perspective will have different views, debug perspective will have different views like debug, break points, 
                        Java browsing will have different views like call hierarchy when you want to understand the code.
                        Different perspectives like git, JPA, resource,
                        you can create new perspective  like adding new views.

Plugins :    




Code Quality:

	1.Readability
	2.Reuseablility
	3.Unit testing
	4.NFR's (Nonfunctional requirements like security, scalability, performance, maintabality )
	
Coding standards
	1. Naming of everything ( naming of class, variable, method)
	2. Complexity ( how many conditions your method have )
	3. Size of methods & classes ( as small as possible  like less then  200 lines)
	4. Duplication ( typically duplication should be 5% or less)
	5. Readability of code 
	1, 5 needs to check manually by doing peer code review
	2,3,4 can be done using tools like sonar tells us
	
Code complexity : is measure of how complex your code is , how difficult to understand like if it is more if , for loops
 you want code complexity as low as possible. Sonar shows code complexity.

Legacy code : means not only code , any code which you cannot improve the design further is legacy code. 
Any which did not have proper unit tests,  making enhancement to it is difficult
Code with outdated framework
Code with technical debt

Code coverage:  how many lines of your code is covered by unit tests.
Code smell : all about specific patterns in code which signal that there is specific defect that might be possible
	Bad pattern there is a chance of defect when you see the code
	Static analysis tools like sonar will identify these.
	
	You can easy understand what are code smell in sonar
	Patterns like  using deprecated methods, switch statement should have default clause , created new Exception but not extended , etc..

Code duplication: same snippet(lines) of code repeat in multiple places
If we want to modify such code, let's say it is repeated in 10 places, we need to modify all places.
When you have code duplication, maintaining your code is very difficult
You can measure tool like sonar. Less 5% is standard.
How to avoid duplication in java is move duplicated block of code to super class inside a method or create utility class or method and use it all sub classes.

 

https://www.codeproject.com/tips/806856/what-is-technical-debt
In every software system, there are areas of the code that are difficult to change, that strike fear into us when we are required to change them. Those areas of the code that make you think "Oh please no". They are difficult to modify or extend, and thus working in those areas are prone to making the system less stable. They will also generally require a greater degree of regression testing to ensure that you have not inadvertently modified other areas of the system.
Technical Debt may be introduced into the code base by conscious decision (in order to make this deadline, we will deliberately and consciously write something "quick and dirty"). Or Technical Debt may be introduced unconsciously by factors including:
• low skilled developers
• ever changing requirements
• impossible deadlines
• poor project planning
• all of the above
In short, you have acquired Technical Debt. You have taken a shortcut with the code to meet a deadline, and at some point, you are expected to repay the debt, i.e., to refactor the code and remove all the hard coded values, shortcuts, etc. that were introduced to meet the deadline.

From <https://www.codeproject.com/tips/806856/what-is-technical-debt> 


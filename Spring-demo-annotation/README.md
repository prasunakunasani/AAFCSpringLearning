###### S1 Section 7, Lecture 52 - Spring configuration with Java Annotations Inversion of Control Overview - Component Scanning
**What are Java Annotations**
- Special labels/marks added to Java classes
- Provide meta-data about class
    - eg: the shoe info below
    - Java annotations are simply meta data about the class
- Processed at compile time or run-time for special processing
- ![Boot metadata]()
**Annotation Example**
- ![Annotations example]()
- The annotation here tells the compiler: "Hey, we're gonna implement the interface, extend the class and override the method exactly as listed in the interface or the parent class". 
- Compiler will check class and make sure you are actually overiding the method
    - as long as you override exactly as advertised, everything will work just fine
    - If there are problems, compiler will just give a compilation error
    
**Why Spring Configuratio with Annotations?**
- XML configuration can be verbose
    - for large projects like 30 or 100 beans. Each would be have to be listed and it's a lot of work. So, instead, can: 
- Configure your Spring beans with Annotations
- Annotations minimizes the XML configuration 
    - Annotations are like metadata where an annotation is given to a given class

**Scanning for Component Classes**
- Spring will scan your Java classes for special annotations
    - when it finds a class with a special spring annotation, it'll: 
- Automatically register the beans in the Spring container
    - Spring will help out and do a lot of work in the background by scanning your classes
    
**Development Process**
1) Enable component scanning in Spring config file
2) Add the @Component Annotation to your Java classes
3) Retrieve bean from Spring container

**Step 1: enable component scanning in Spring config file**
```xml
<beans ... >
    <context:component-scan base-package="springdemo"/>
</beans>
```
- Spring will scan this base-package (recursively)
    - spring will scan all classes in this package and sub-packages and identify all the @component annotation and register them with the Spring container
        - happens in the background automatically

**Step 2: Add the @Component Annotation to your Java classes**

```java
@Component("thatSillyCoach")
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley"; 
    }
}
```
- @Component tells Spring that this class is a special Spring bean that needs to be registered
    - "thatSillyCoach" is the bean ID that we want to use
        - Spring will register this bean automatically as a Spring bean and have this bean ID
        - bean ID can be anything
-Spring will scan for @Component annotation, automatically register it with the given bean id

**Step 3: Retrive bean from Spring container**
- Same coding as before....nothing changes
```java
Coach theCoach = context.getBean("thatSillyCoach", Coach.class)
```
- the bean ID just has to match what you put in the @Component annotation 

###### S1 Section 7, Lecture 53 - Annotations Project Setup
- Go into Eclipse
- This lecture wasn't done in completion since not currently using Eclipse
- In Idea, create new Spring project and download the Spring files. 
    - selected Spring 4.3.18.RELEASE
- in the future to add more jars specific to Spring MVC, can just try to add a module with those specific jars, copy the jars over and then delete the temp module

###### S1 Section 7, Lecture 54,55 - Explicit Component Names - Write some code
- Here: 
- Create a new xml file: 
    - Right click src folder, New->XML Configuration File->Spring config->just write the name 'applicationContext' or something
    - configure it when Idea prompts you to
- - To applicationContext.xml, add the context:component scan line
    - this line will scan all the @component annotated classes
- whatever package name you write in the application context, you need to make sure that you right click src, New->package, give the exact same name
- Create Coach interface, TennisCoach class and AnnotationDemo App

###### S1 Section 7, Lecture 56 - Default Component Names - Overview
- We already learned: 
    1) Specify the bean id in the component annotation
    ```java
      @Component("thatSillyCoach")
      public class TennisCoach implements Coach{...
    ```
    - 'thatSillyCoach' was the bean id. 
    - Now, we can remove the bean id   
- Spring also supports Default Bean IDs
    - Default bean id: the class name, make first letter lower-case
    - eg: if Class Name was TennisCoach -> Default Bean Id becomes tennisCoach
- Code example
```java
      @Component("thatSillyCoach")
      public class TennisCoach implements Coach{...
```
```java
//get the bean from spring container
Coach theCoach = context.getBean("tennisCoach",Coach.class); 
```
    - here, we are using the default bean id tennisCoach. 

###### S1 Section 7, Lecture 57 - Default Component Names - Write some code
- Here: 
- just removed the manual bean id and changed the name of the retrieved bean id in AnnotationDemoApp.class

###### S1 Section 7, Lecture 58 - Practical Activity #4 - Inversion of Control with Annotations
- Here: 
Practice Activity #4 - Inversion of Control with Java Annotations

1) Define a new Coach implementation using Annotations
2) Reference the new coach implementation in your main application.
3) Test your application to verify you are getting information from your new coach implementation.

Compare your code to the solution. The solution is available here:
- http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip

###### S1 Section 8, Lecture 59 - Spring configuration with Java Annotations Dependency Injection - Constructor Injection - Overview

###### S1 Section 8, Lecture 60 - FAQ - What if there are Multiple Implementations

###### S1 Section 8, Lecture 61, 62 - Constructor Injection - Write some code

###### S1 Section 8, Lecture 63 - FAQ - Constructor Injection - Autowired Optional? 

###### S1 Section 8, Lecture 64 - Setter Injection - Overview

###### S1 Section 8, Lecture 65 - Setter Injection - Write some code

###### S1 Section 8, Lecture 66 - Method Injection

###### S1 Section 8, Lecture 67 - Field Injection - Overview

###### S1 Section 8, Lecture 68 - Field Injection - Write some code

###### S1 Section 8, Lecture 69 - Which Injection Type Should You Use? 

###### S1 Section 8, Lecture 70 - Qualifiers for Dependency Injection - Overview

###### S1 Section 8, Lecture 71,72 - Qualifiers for Dependency Injection - Write some code

###### S1 Section 8, Lecture 73 - Annotations - Default Bean Names (special case)

###### S1 Section 8, Lecture 74 - Using @Quantifiers with Constructors - Overview

###### S1 Section 8, Lecture 75 - How to inject properties file using Java annotations

###### S1 Section 8, Lecture 76 - Practical Activity #5 - Dependency Injection with Annotations

###### S1 Section 9, Lecture 77 - @Scope Annotation - Overview

###### S1 Section 9, Lecture 78 - @Scope Annotation - Write Some Code

###### S1 Section 9, Lecture 79 - Bean Lifecycle Method Annotations - Overview

###### S1 Section 9, Lecture 80 - Note about @PostConstruct and @PreDestroy Method Signatures

###### S1 Section 9, Lecture 81 - Note For Java 9 users - @PostConstruct and @PreDestroy Method Signatures

###### S1 Section 9, Lecture 82 - Bean Lifecycle Method Annotations - Write some code

###### S1 Section 9, Lecture 83 - Note about Destroy Lifecycle and Prototype Scope

###### S1 Section 9, Lecture 84 - Practical Activity #6 - Bean Scopes with Annotations

###### S1 Section 10, Lecture 85 - Spring Configuration with Java Code - Overview

###### S1 Section 10, Lecture 86 - Spring Configuration with Java Code - Write Some code

###### S1 Section 10, Lecture 87 - Defining Spring Beans with Java Code - Overview

###### S1 Section 10, Lecture 88,89 - Defining Spring Beans with Java Code - Write some code

###### S1 Section 10, Lecture 90 - Injecting Values from Properties File - Overview

###### S1 Section 10, Lecture 91, 92 - Injecting Values from Properties File - Write some code

###### S1 Section 10, Lecture 93 - FAQ - Problems with Injecting Values - Value not returning ${foo.e-mail}

###### S1 Section 10, Lecture 94 - Practice Activity #7 - IoC and DI with Java Configuration

###### S1 Section 11, Lecture 95 - Spring MVC Overview

###### S1 Section 11, Lecture 96 - Spring MVC - Behind the Scenes

###### S1 Section 11, Lecture 97 - Development Environment Checkpoint

###### S1 Section 11, Lecture 98,99 - Spring MVC Configuration - Overview

###### S1 Section 11, Lecture 100 - Spring MVC Configuration - JAR files

###### S1 Section 11, Lecture 101 - Spring MVC Configuration - Config files

###### S1 Section 11, Lecture 102 - FAQ: How to configure the Spring Dispatcher Servlet using all Java Code (no xml)

###### S1 Section 12, Lecture 103 - Creating a Spring Home Controller and View - Overview

###### S1 Section 12, Lecture 104,105 - Creating a Spring Home Controller and View - Write Some Code

###### S1 Section 12, Lecture 106 - FAQ: HELP! My Spring MVC Controller is not working. What to do?

###### S1 Section 12, Lecture 107 - FAQ: HELP! Can't Start Tomcat - Ports are in Use! 

###### S1 Section 12, Lecture 108 - FAQ: How does Component Scan Work - Your Package Names are Different! 

###### S1 Section 12, Lecture 109 - Reading HTML Form Data - Overview

###### S1 Section 12, Lecture 110,111,112 - Reading HTML Form Data - Write Some Code
  
###### S1 Section 12, Lecture 113 - Adding Data to the Spring Model - Overview
 
###### S1 Section 12, Lecture 114, 115 - Adding Data to the Spring Model - Write some code

###### S1 Section 12, Lecture 116 -  FAQ: How to use CSS, JavaScript and Images in Spring MVC Web App

###### S1 Section 12, Lecture 117 - Bonus: Deploying To Tomcat using WAR files

###### S1 Section 13, Lecture 118 - Spring MVC: Binding Request Params - Overview

###### S1 Section 13, Lecture 119 - Binding Request Params - Write Some Code 

###### S1 Section 13, Lecture 120 - Controller Level Request Mapping - Overview

###### S1 Section 13, Lecture 121, 122 - Controller Level Request Mapping - Write Some Code

###### S1 Section 13, Lecture 123 - FAQ: How does "processForm" work for "/hello"?

###### S1 Section 14, Lecture 124 - Spring MVC Form Tags Overview

###### S1 Section 14, Lecture 125 - Text Fields - Overview

###### S1 Section 14, Lecture 126,127,128 - Text Fields - Write some Code  

###### S1 Section 14, Lecture 129 - Drop-Down Lists - Overview 

###### S1 Section 14, Lecture 130,131 - Drop-Down Lists - Write Some Code

###### S1 Section 14, Lecture 132 - FAQ: Use properties file to load country options

###### S1 Section 14, Lecture 133 - Radio Buttons - Overview

###### S1 Section 14, Lecture 134 - Radio Buttons - Write Some Code

###### S1 Section 14, Lecture 135 - FAQ: How to populate radiobuttons with items from Java class?

###### S1 Section 14, Lecture 136 - Checkboxes - Overview

###### S1 Section 14, Lecture 137,138 - Checkboxes - Write Some Code

###### S1 Section 15, Lecture 139 - Spring MVC Form Validation - Overview

###### S1 Section 15, Lecture 140 - Setting up Dev Environment for Form Validation 

###### S1 Section 15, Lecture 141 - Installing Validation Files

###### S1 Section 15, Lecture 142 - Checking for Required Fields - Overview

###### S1 Section 15, Lecture 143 - Special Notes about BindingResult and Parameter Order

###### S1 Section 15, Lecture 144 - Add Validation Rule to Coustomer Class

###### S1 Section 15, Lecture 145 - Display Validation Error Messages on HTML form

###### S1 Section 15, Lecture 146,147 - Perform Validation in Controller Class

###### S1 Section 15, Lecture 148 - Update Confirmation Page

###### S1 Section 15, Lecture 149 -  Test the Validation Rule for Required Fields

###### S1 Section 15, Lecture 150 - Add Pre-processing Code with @InitBinder - Overview

###### S1 Section 15, Lecture 151 - Add Pre-processing Code with @InitBinder - Write Some code

###### S1 Section 16, Lecture 152 - Validating a Number Range - Overview

###### S1 Section 16, Lecture 153 - Validating a Number Range - Write Some Code

###### S1 Section 16, Lecture 154 - Applying Regular Expressions - Overview

###### S1 Section 16, Lecture 155 - Applying Regular Expressions - Write Some Code 

###### S1 Section 16, Lecture 156 - How to make Integer Field Required: freePasses

###### S1 Section 16, Lecture 157 - How to Handle String input for Integer Fields - Custom Message

###### S1 Section 16, Lecture 158 - How to Handle String input for Integer Fields - Configure Resource Bundle

###### S1 Section 16, Lecture 159 - How to Handle String input for Integer Fields - Deep Dive

###### S1 Section 16, Lecture 160 - FAQ: How to make Integer field required and handle Strings: freePasses

###### S1 Section 17, Lecture 161 - Custom Form Validation - Overview

###### S1 Section 17, Lecture 163,164 - Creating a Custome Java Annotation

###### S1 Section 17, Lecture 165 - Developing the ConstraintValidator

###### S1 Section 17, Lecture 166 - Adding Validation Rule to the Entity and Form

###### S1 Section 17, Lecture 167 - Testing the Custom Validation Rule

###### S1 Section 17, Lecture 168 - FAQ: Spring MVC Custom Validation - Possible to validate with multiple strings?

###### S1 Section 18, Lecture 169 - Hibernate Overview

###### S1 Section 18, Lecture 170 - Hibernate and JDBC

###### S1 Section 19, Lecture 171 - Hiberate 5.2 Requires Java 8

###### S1 Section 19, Lecture 172 - Hiberate Development Environment Overview

###### S1 Section 19, Lecture 173,174 - Installing MySQL on MS Windows and Mac 
 
###### S1 Section 19, Lecture 175 - Setting up Database Table

###### S1 Section 19, Lecture 176 - Setting up Hiberate in Eclipse 

###### S1 Section 19, Lecture 177 - Testing your JDBC Connection 

###### S1 Section 20, Lecture 178 - Hibernate Development Process Overview

###### S1 Section 20, Lecture 178 - Creating the Hibernate Configuration File

###### S1 Section 20, Lecture 178 - Hibernate Annotations - Part 1

###### S1 Section 20, Lecture 178 - HEADS UP - for Java 9 USERS

###### S1 Section 20, Lecture 178 - HEADS UP - Java 9 USERS - Eclipse Generate toString() fails

###### S1 Section 20, Lecture 178 - Hibernate Annotations - Part 2

###### S1 Section 20, Lecture 178 - FAQ: Why we are using JPA Annotation instead of Hibernate?

###### S1 Section 21, Lecture 185,186 - Creating and Saving Java Objects

###### S1 Section 21, Lecture 187 - Primary Keys - Overview

###### S1 Section 21, Lecture 188 - Primary Keys - Write some code

###### S1 Section 21, Lecture 189 - Primary Keys - Changing the Starting Index

###### S1 Section 21, Lecture 190 - Reading Objects with Hibernate

###### S1 Section 21, Lecture 191 - Querying Objects with Hibernate - Overview

###### S1 Section 21, Lecture 192 - Special Note: About Deprecated Method in Hibernate 5.2 

###### S1 Section 21, Lecture 193,194 - Querying Objects with Hibernate - Write Some Code

###### S1 Section 21, Lecture 195 - FAQ: How To View Hibernate SQL Parameter Values

###### S1 Section 21, Lecture 196 - Updating Objects with Hibernate - Overview

###### S1 Section 21, Lecture 197 - Updating Objects with Hibernate - Write some code

###### S1 Section 21, Lecture 198 - Deleting Objects with Hibernate - Overview

###### S1 Section 21, Lecture 199 - Deleting Objects with Hibernate - Write some code

###### S1 Section 21, Lecture 200 - Practice Activity #8 - Hibernate Development

###### S1 Section 21, Lecture 201 - FAQ: How to read Dates with Hibernate

###### S1 Section 22, Lecture 202 - Advanced Mappings Overview

###### S1 Section 22, Lecture 203 - Database Concepts

###### S1 Section 23, Lecture 204,205,206 - @OneToOne - Overview

###### S1 Section 23, Lecture 207 - @OneToOne - Run Database Scripts

###### S1 Section 23, Lecture 208 - @OneToOne - Write Some Code - Prep Work

###### S1 Section 23, Lecture 209 - @OneToOne - Write Some Code - Create InstructorDetail class

###### S1 Section 23, Lecture 210 - @OneToOne - Write Some Code - Create Instructor class

###### S1 Section 23, Lecture 211,212 - @OneToOne - Write Some Code - Build Main App

###### S1 Section 23, Lecture 213 - @OneToOne - Delete an Entry

###### S1 Section 23, Lecture 214 - @OneToOne - Bi-Directional Overview

###### S1 Section 23, Lecture 215 - @OneToOne - Bi-Directional - Create Relationship

###### S1 Section 23, Lecture 216 - @OneToOne - Bi-Directional - Develop Main App

###### S1 Section 23, Lecture 217 - @OneToOne - Refactoring and Exception Handling

###### S1 Section 23, Lecture 218 - @OneToOne - Bi-Directional - Cascade Delete

###### S1 Section 23, Lecture 219,220 - @OneToOne - Bi-Directional - Delete Only InstructorDetail

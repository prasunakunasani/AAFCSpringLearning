###### S1 Section 7, Lecture 52 - Spring configuration with Java Annotations Inversion of Control Overview - Component Scanning
**What are Java Annotations**
- Special labels/marks added to Java classes
- Provide meta-data about class
    - eg: the shoe info below
    - Java annotations are simply meta data about the class
- Processed at compile time or run-time for special processing
- ![Boot metadata](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-07-25%2016_41_30-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
**Annotation Example**
- ![Annotations example](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-07-26%2015_09_53-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
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
**Demo Example**
- Our Coach already provides daily workouts
- Now will also provide daily fortunes
    - New Helper: FortuneService
    - This is a dependency
    - ![Dependency](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-04-11%2015_09_51-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
**What is Spring AutoWiring**
- For dependency injection, Spring can use auto wiring
    - autowires objects together
- Spring will look for a class that matches a given property 
    - matches by type: class or interface
- Once Spring finds a match, it will inject it automatically ...hence it is autowired
**Autowiring Example**
- Injecting a FortuneService into a Coach implementation
- Spring will scan all the @Components
    - Sees that to satisfy a dependency, it needs to inject a FortuneService
    - So, Spring checks to see if any one implements FortuneService interface???
- If so, Spring will grab that Component/Bean and inject it. 
    - For example: HappyFortuneService
    - Spring will find the implementation and automatically inject it into our class

**Autowiring Injection Types**
- Constructor Injection
- Setter Injection
- Field Injection

**Development Process - Constructor Injection**
1) Define the dependency interface and class
2) Create a constructor in your class for injections
3) Configure the dependency injection with @Autowired Annotation
   
**Step 1: Define the dependency interface and class**
```java
//File: FortuneService.java
public interface FortuneService {
    public String getFortune();     
}
```

```java
//File:HappyFortuneService.java
@Component
public class HappyFortuneService implements FortuneService{
    
    public String getFortune(){
        return "Today is your lucky day"; 
    }
}
```
- implementation of the interface
- New: Have @Component annotation so Spring can  find this implementation for the Spring Container

**Step 2: Create a constructor in your class for injections**
```java
@Component
public class TennisCoach implements Coach{
    
    private FortuneService fortuneService; 
    
    public TennisCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService; 
    }
}
```
- Tennis Coach has a constructor with the same name as the class
- We pass in the dependency and make a basic assignment

**Step 3: Configure the dependency injection with @Autowired annotation
```java
//file: TennisCoach.java
@Component
public class TennisCoach implements Coach{
    
    private FortuneService fortuneService; 
    
    @Autowired
    public TennisCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService; 
    }
}
```
- At the constructor, we have @Autowired
    - For the parameter being pass (theFortuneService), we want Spring to automatically wire up with component
    - Instead of doing it long hand using XMLconfig, we are making use of this @Autowired annotation
- In the background, Spring realizes that it needs to satisfy this dependency
    - Spring will find a bean that implements FortuneService
- Meaning, Spring will scan all the components, find the component, implements this FortuneService interface (HappyFortuneService), take that bean, inject it (autowire) here into the tennis coach
- ![No Assembly Required](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-02%2020_00_22-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Overview: 
- Simply retrieving the Coach object from the object factory
- For the object factory, all the dependencies (helper objects) are being handled (injected) by making use of annotations and autowiring
- When we simply retrieve the Coach, we get it fully assembled
    - We get the Coach object and it's FortuneService together cause' of Spring

###### S1 Section 8, Lecture 60 - FAQ - What if there are Multiple Implementations
AUTOWIRING
**FAQ: What if there are multiple FortuneService implementations?**
- As in, based on the above, Spring will scan for a component that implements FortuneService interface
- In our example, we're setting it up so HappyFortuneService implements FortuneService. So, this meets are requirements
- what is there are multiple components that implement FortuneService? Which one will Spring pick then?
- QUESTION
    - When using autowiring, what if there are multiple FortuneService implementations? Like in the image below?
    - ![Multiple FortuneService implementations](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-03%2012_07_34-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- ANSWER
    - Spring has special support to handle this case. Use the @Qualifier annotation. 

###### S1 Section 8, Lecture 61, 62 - Constructor Injection - Write some code
- Here: 

###### S1 Section 8, Lecture 63 - FAQ - Constructor Injection - Autowired Optional? 
- Question
    - I have commented the Autowired annotation. But still it worked. How did it work?
 ```java
    //@Autowired
    //this is the constructor
    public TennisCoach(FortuneService theFortuneService) {
        System.out.println(" theFortuneService " + theFortuneService);
        fortuneService = theFortuneService;
    }
```
- Answer: 
    - This is a new feature of Spring 4.3.
    - Here is the snippet from the Spring Docs.
    - Section 1.9.2: Autowired
        - As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. However, if several constructors are available, at least one must be annotated to teach the container which one to use.
            - Eg: - In our case, the target bean is TennisCoach 
    - "I personally prefer to use the @Autowired annotation because it makes the code more readable. But as mentioned, the @Autowired is not required for this scenario."
    - Link to the docs: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation 

###### S1 Section 8, Lecture 64 - Setter Injection - Overview
- So far covered Constructor injection
- Setter Injection is injecting dependencies by calling setter methods in your class
- Same autowiring example: 
    - Injecting Fortune Service into a Coach implementation
    - Spring will scan @Components
    - Spring checks if any of the beans implements FortuneService interface
    - If so, injects them. For eg: HappyFortuneService

**Development Process - Setter Injection**
1) Create setter method(s) in your class for injections
2) Configure the dependency injection with @Autowired Annotation

**Step 1: Create setter method(s) in your class for injections**
```java
//TennisCoach.java
@Component
public class TennisCoach implements Coach {
    private FortuneService fortuneService; 
    
    public TennisCoach(){}
    
    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService; 
    }
    ...
}
```
- defining a setter method for the fortuneService

**Step 2: Configure the dependency injection with Autowired Annotation**
```java
//TennisCoach.java
@Component
public class TennisCoach implements Coach {
    private FortuneService fortuneService; 
    
    public TennisCoach(){}
    
    @Autowired
    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService; 
    }
    ...
}
```
- we set the Autowired annotation so Spring will make it available for dependency injection

###### S1 Section 8, Lecture 65 - Setter Injection - Write some code
Here: 

###### S1 Section 8, Lecture 66 - Method Injection
- Can inject dependencies by calling ANY method on your class
- Simply give: @Autowired
- insetad of setter method, can use any method
```java
//TennisCoach.java
@Component
public class TennisCoach implements Coach {
    private FortuneService fortuneService; 
    
    public TennisCoach(){}
    
    @Autowired
    public void doSomeCrazyStuff(FortuneService fortuneService){
        this.fortuneService = fortuneService; 
    }
    ...
}
```
- The autowired here will use this to autowired the function

###### S1 Section 8, Lecture 67 - Field Injection - Overview
- Inject dependencies by setting field values on your class directly (even private fields)
    - In the background, accomplished by using Java Reflection
    
**Development Process - Field Injection**
1) Configure the dependency injection with Autowired Annotation
    - Applied directly to the field
    - No need for setter methods

**Step 1: Configure the dependency injection with Autowired Annotation**
```java
//TennisCoach.java
public class TennisCoach implements Coach{
    @Autowired
    private FortuneService fortuneService; 
    
    public TennisCoach(){}
    
    // no need for setter method
}
```
- here, instead of using contructor injection or setter injection, we place it directly on the field
    - BTS, Spring will set this field and create the object using Java technonology called reflection

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

###### S1 Section 24, Lecture 221,222 - @OneToMany - Bi-Directional Overview

###### S1 Section 24, Lecture 223 - @OneToMany - Bi-Directional - Database Prep Work

###### S1 Section 24, Lecture 224 - @OneToMany - Bi-Directional - Create Course Mapping

###### S1 Section 24, Lecture 225 - @OneToMany - Bi-Directional - Define Course Mapping

###### S1 Section 24, Lecture 226 - @OneToMany - BiDirectional - Update Instructor

###### S1 Section 24, Lecture 227 - @OneToMany - BiDirectional - Add Instructor to Database

###### S1 Section 24, Lecture 228 - @OneToMany - BiDirectional - Create Courses for Instructor

###### S1 Section 24, Lecture 229 - @OneToMany - BiDirectional - Retrive Insturctor Courses 

###### S1 Section 24, Lecture 230 - @OneToMany - BiDirectional - Delete a Course

###### S1 Section 25, Lecture 231,232 - Eager vs Lazy Loading - Overview

###### S1 Section 25, Lecture 233 - Eager vs Lazy Loading - Coding - Eager

###### S1 Section 25, Lecture 234 - Eager vs Lazy Loading - Coding - Lazy

###### S1 Section 25, Lecture 235 - Eager vs Lazy Loading - Closing the Session

###### S1 Section 25, Lecture 236 - Eager vs Lazy Loading - Coding - Resolve Lazy Loading Issue

###### S1 Section 25, Lecture 237 - Eager vs Lazy Loading - Coding - HQL JOIN FETCH

###### S1 Section 25, Lecture 238 - FAQ: How to load the courses at a later time in the application?

###### S1 Section 26, Lecture 239,240 - @OneToMany - Uni-Directional - Overview

###### S1 Section 26, Lecture 241 - FAQ: @JoinColumn ...where does it find the column?

###### S1 Section 26, Lecture 242 - @OneToMany - Uni-Directional - Set up database tables

###### S1 Section 26, Lecture 243 - @OneToMany - Uni-Directional - Create Review Class

###### S1 Section 26, Lecture 244 - @OneToMany - Uni-Directional - Configure Fetch Type

###### S1 Section 26, Lecture 245 - @OneToMany - Uni-Directional - Create Course Reviews

###### S1 Section 26, Lecture 246 - @OneToMany - Uni-Directional - Get Course Reviews

###### S1 Section 26, Lecture 247 - @OneToMany - Uni-Directional - Delete Course Reviews

###### S1 Section 27, Lecture 248,249 - @ManyToMany - Overview

###### S1 Section 27, Lecture 250 - @ManyToMany - Set up database tables

###### S1 Section 27, Lecture 251 - @ManyToMany - Update Course class

###### S1 Section 27, Lecture 252 - @ManyToMany - Configure Course for many-to-many

###### S1 Section 27, Lecture 253 - @ManyToMany - Configure Student for many-to-many

###### S1 Section 27, Lecture 254 - @ManyToMany - Create a Main App

###### S1 Section 27, Lecture 255 - @ManyToMany - Review app output

###### S1 Section 27, Lecture 256 - @ManyToMany - Add more course for a student

###### S1 Section 27, Lecture 257 - @ManyToMany - Verify Data in Join Table

###### S1 Section 27, Lecture 258 - @ManyToMany - Get Courses for Student

###### S1 Section 27, Lecture 259 - @ManyToMany - Delete a Course

###### S1 Section 27, Lecture 260 - @ManyToMany - Delete a Student

###### S1 Section 28, Lecture 261 - Project Overview and Demo

###### S1 Section 28, Lecture 262 - Source Files for Spring MVC + Hibernate Web App

###### S1 Section 28, Lecture 263 - Set up Sample Data for Database

###### S1 Section 28, Lecture 264,265 - Test Database Connection

###### S1 Section 28, Lecture 266,268 - Set up Dev Environment

###### S1 Section 28, Lecture 267 - HEADS UP - FOR JAVA 9 USERS - Spring MVC CRUD

###### S1 Section 28, Lecture 261 -    








































 
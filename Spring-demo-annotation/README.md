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

###### S1 Section 7, Lecture 58 - Practical Activity #4 - Inversion of Control with Annotations

###### S1 Section 8, Lecture 59 - Spring configuration with Java Annotations Dependency Injection - Constructor Injection - Overview

###### S1 Section 8, Lecture 60 - FAQ - What if there are Multiple Implementations

###### S1 Section 8, Lecture 61, 62 - Constructor Injection - Write some code
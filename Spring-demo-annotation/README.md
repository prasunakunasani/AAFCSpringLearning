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
- Compiler will check class and make sure you are actually overriding the method
    - as long as you override exactly as advertised, everything will work just fine
    - If there are problems, compiler will just give a compilation error
    
**Why Spring Configuration with Annotations?**
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
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/37d9858eb35002b65b68b3eccf4e15fef0a391f3
- Create a new xml file: 
    - Right click src folder, New->XML Configuration File->Spring config->just write the name 'applicationContext' or something
    - configure it when Idea prompts you to
- - To applicationContext.xml, add the context:component scan line
    - this line will scan all the @component annotated classes
- whatever package name you write in the application context, you need to make sure that you right click src, New->package, give the exact same name
- Create Coach interface, TennisCoach class and AnnotationDemo App

###### S1 Section 7, Lecture 56 - Default Component Names - Overview
- We already learned: 
    - Specify the bean id in the component annotation
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
- just removed the manual bean id and changed the name of the retrieved bean id in AnnotationDemoApp.class

###### S1 Section 7, Lecture 58 - Practical Activity #4 - Inversion of Control with Annotations
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/c00e7cfc5c7af6fb9f9d2171e783b81c5bc05b8a  
**Practice Activity #4 - Inversion of Control with Java Annotations**

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

**Step 3: Configure the dependency injection with @Autowired annotation**
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
    - Instead of doing it long hand using XML config, we are making use of this @Autowired annotation
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
**AUTOWIRING FAQ: What if there are multiple FortuneService implementations?**
- As in, based on the above, Spring will scan for a component that implements FortuneService interface
- In our example, we're setting it up so HappyFortuneService implements FortuneService. So, this meets are requirements
- what if there are multiple components that implement FortuneService? Which one will Spring pick then?
- QUESTION
    - When using autowiring, what if there are multiple FortuneService implementations? Like in the image below?
    - ![Multiple FortuneService implementations](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-03%2012_07_34-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- ANSWER
    - Spring has special support to handle this case. Use the @Qualifier annotation. 

###### S1 Section 8, Lecture 61, 62 - Constructor Injection - Write some code
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/92becc174dc8eb295436c6edc3cca808e93f23e1

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
    - Link to the docs: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-autowired-annotation, 
        - https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/ 

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
Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/a00b646a8199b8b5ddfd410380afabbd570254b5

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
- here, instead of using constructor injection or setter injection, we place it directly on the field
    - BTS, Spring will set this field and create the object using Java technology called reflection
- Spring will call the default constructor and inject a fortuneService implementation directly into the class using java's reflection

###### S1 Section 8, Lecture 68 - Field Injection - Write some code
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/a00b646a8199b8b5ddfd410380afabbd570254b5
- Spring will inject the fortuneService, print out our daily workout and then print the happy fortune
    - the happy fortune is coming from fortuneService wired  

###### S1 Section 8, Lecture 69 - Which Injection Type Should You Use?
- We covered Constructor Injection, Setter Injection and Field injection 
- Choose a style and stay consistent in your project
- Is one better than the other? 
    - You'll end up getting the same functionality so..not really
    - Even in spring documentation, they say you get same functionality 
- However, Field Injection gives a warning in Intellij: https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it 
    - For mandatory dependencies or when aiming for immutability, use constructor injection
    - For optional or changeable dependencies, use setter injection
    - Avoid field injection in most cases
    - Spring documentation: https://docs.spring.io/spring/docs/5.0.8.RELEASE/spring-framework-reference/core.html#spring-core 
****Constructor-based or setter-based DI? from Spring****
```text
- Since you can mix constructor-based and setter-based DI, it is a good rule of thumb to use constructors for mandatory dependencies and setter methods or configuration methods for optional dependencies. Note that use of the @Required annotation on a setter method can be used to make the property a required dependency.
- The Spring team generally advocates constructor injection as it enables one to implement application components as immutable objects and to ensure that required dependencies are not null. Furthermore constructor-injected components are always returned to client (calling) code in a fully initialized state. As a side note, a large number of constructor arguments is a bad code smell, implying that the class likely has too many responsibilities and should be refactored to better address proper separation of concerns.
- Setter injection should primarily only be used for optional dependencies that can be assigned reasonable default values within the class. Otherwise, not-null checks must be performed everywhere the code uses the dependency. One benefit of setter injection is that setter methods make objects of that class amenable to reconfiguration or re-injection later. Management through JMX MBeans is therefore a compelling use case for setter injection.
- Use the DI style that makes the most sense for a particular class. Sometimes, when dealing with third-party classes for which you do not have the source, the choice is made for you. For example, if a third-party class does not expose any setter methods, then constructor injection may be the only available form of DI.
```
- Field injection drawbacks: 
    - You cannot create immutable objects, as you can with constructor injection
    - Your classes have tight coupling with your DI container and cannot be used outside of it
    - Your classes cannot be instantiated (for example in unit tests) without reflection. You need the DI container to instantiate them, which makes your tests more like integration tests
    - Your real dependencies are hidden from the outside and are not reflected in your interface (either constructors or methods)
    - It is really easy to have like ten dependencies. If you were using constructor injection, you would have a constructor with ten arguments, which would signal that something is fishy. But you can add injected fields using field injection indefinitely. Having too many dependencies is a red flag that the class usually does more than one thing, and that it may violate the Single Responsibility Principle.
   
**When to use setter injection over constructor injection**
- ****Constructor Injection:**** We are injecting the dependencies through Constructor.
- Generally we can use for ****Mandatory dependencies.****
- If you use the Constructor injection there is one disadvantage called ****"Circular Dependency".****
- ****Circular Dependency:**** Assume A and B. A is dependent on B. B is dependent on A. In this constructor injection will be failed. At that time Setter injection is useful.
- If Object state is not inconsistent it won't create Object.
- ****Setter Injection:**** We are injecting the dependencies through Setter methods.
- This is useful for ****Non-Mandatory dependencies.****
- It is possible to ****re injecting**** dependencies by using ****Setter Injection****. It is ****not possible**** in ****Constructor injection.****

###### S1 Section 8, Lecture 70 - Qualifiers for Dependency Injection - Overview
- So far, using Autowiring where Spring scans for components to see if anyone is implementing the FortuneService interface
- But what happens if there are multiple implementations out there? Which one will Spring pick? How will it know which one to pick? 
- ![Multiple FortuneService implementations](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-03%2012_07_34-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Spring will give an error message if there are multiple implementations:
- ![Error messages](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-15%2013_02_20-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- That there was a error that it was expecting a single implementation but found 4. And then it'll give all four
- Solution..Need to tell Spring which bean to use
**Solution: Be specific! - @Qualifier**
```java
@Component
public class TennisCoach implements Coach{
    
    @Autowired
    @Qualifier("happyFortuneService")
    private FortuneService fortuneService; 
    ... 
}
```  
- ![desired bean id](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-15%2013_05_48-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Giving bean id of what we want to use
    - the bean id is just the default bean id 
- In qualifier, you simply specify the bean id that you want to inject  
**Injection Types**
- Can apply @Qualifier annotation to: 
    - Constructor injection
    - Setter injection methods
    - Field injection
    
###### S1 Section 8, Lecture 71,72 - Qualifiers for Dependency Injection - Write some code
- stubbing the FortuneService implementations 
- making sure they all have @Component
- Idea also warns about the multiple implementations in Tennis Coach fortuneService private field

###### S1 Section 8, Lecture 73 - Annotations - Default Bean Names (special case)
**Annotations - Default Bean Names - The Special Case**
- In general, when using Annotations, for default bean name, Spring uses the following rule. 
- If the annotation value doesn't indicate a bean name, an appropriate name will be built based on the short name of the class (with the first letter lower-cased
    - Eg: HappyFortuneService --> happyFortuneService
- However, for ***special case of when BOTH the first and second characters are upper case, then the name is NOT converted***
- For the case of RESTFortuneService
    - Eg: RESTFortuneService --> RESTFortuneService
    - No conversion since the first characters are upper case
- BTS, Spring uses the Java Beans Introspector to generate the default bean names. 
- Screen shot of the documentation for the key method: ![decapitalize documentation](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-15%2013_58_10-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png) 
- Link to documentation: https://docs.oracle.com/javase/8/docs/api/java/beans/Introspector.html#decapitalize(java.lang.String) 
- As always, you can give explicit names to your beans.
```java
@Component("foo")
public class RESTFortuneService .... {
}
```
- Then you can access it using the name of "foo". 

###### S1 Section 8, Lecture 74 - Using @Qualifier with Constructors - Overview
**Using @Qualifier with Constructors**
- @Qualifier is a nice feature, but it is tricky when used with Constructors.
- The syntax is much different from other examples and not exactly intuitive.  Consider this the "deep end of the pool" when it comes to Spring configuration
- You have to place the @Qualifier annotation inside of the constructor arguments. 
- Here's an example from the classroom example. It's updated to make use of constructor injection, with @Autowired and @Qualifier. Make note of the code in comment ///block below:
```java
package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    // define a default constructor
    public TennisCoach() {
        System.out.println(">> TennisCoach: inside default constructor");
    }
    //////////////////////////////////////
    @Autowired
    public TennisCoach(@Qualifier("randomFortuneService") FortuneService theFortuneService) {

        System.out.println(">> TennisCoach: inside constructor using @autowired and @qualifier");
        
        fortuneService = theFortuneService;
    }
    //////////////////////////////////////
            
    /*
    @Autowired
    public void doSomeCrazyStuff(FortuneService theFortuneService) {
        System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
        fortuneService = theFortuneService;
    }
    */
    
    /*
    @Autowired
    public TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
    */
    
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
```
- For detailed documentation on using @Qualified with Constructors, see this link in the Spring Reference Manual
    - https://docs.spring.io/spring/docs/5.0.8.RELEASE/spring-framework-reference/core.html#beans-autowired-annotation-qualifiers
    - setter and method injection is done the same way as constructor injection: 
```java
public class MovieRecommender {

    private MovieCatalog movieCatalog;

    private CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    public void prepare(@Qualifier("main")MovieCatalog movieCatalog,
            CustomerPreferenceDao customerPreferenceDao) {
        this.movieCatalog = movieCatalog;
        this.customerPreferenceDao = customerPreferenceDao;
    }

    // ...
}
```
todo - see how injection is done for multiple dependencies that need qualifiers. Just two Qualifiers?

###### S1 Section 8, Lecture 75 - How to inject properties file using Java annotations
**FAQ: How to inject properties file using Java annotations**
- This solution will show you how inject values from a properties file using annotations. The values will no longer be hard coded in the Java code. 
1) Create a properties file to hold your properties. It will be a name value pair. 
    - New text file:  src/sport.properties
```properties
foo.email=myeasycoach@luv2code.com
foo.team=Silly Java Coders
```
2) Load the properties file in the XML config file
    - File: applicationContext.xml
    - This should appear just after the <context:component-scan .../> line
```xml
<context:property-placeholder location="classpath:sport.properties"/> 
```
3) Inject the properties values into your Swim Coach: SwimCoach.java
```java
@Component
public class SwimCoach implements Coach {
	...
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	...
```
- Can download entire code from here: http://www.luv2code.com/downloads/spring-hibernate/spring-props-annotation-demo.zip

###### S1 Section 8, Lecture 76 - Practical Activity #5 - Dependency Injection with Annotations
**Practice Activity #5 - Dependency Injection with Annotations**
1) Define a new implementation for the FortuneService.
    - Your fortune service should read the fortunes from a file.
    - The fortune service should load the fortunes into an array
    - When the getFortune() method is called it would return a random fortune from the array.
2. Inject your new dependency into your Coach implementation
3. Test your application to verify you are getting random fortunes based on your fortunes file.

Solution: http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip 

- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/acefcb7fdac6ec169a6e9c52ee3e3df0983b7a1d 
- What you learned: 
    - In a properties file, you can just put multiple values using commas and use them for the array using the @Value annotation
    - As long as there is a @Component annotation to an implementation, it will go into the constructor of that class...
---
**FAQ: I'm getting null for the fortunes.**  
QUESTION
 - When I create an array of fortunes using this
 - String[] data= {a,b,c,d,e};
 - The array is always null. Why?
ANSWER
- The root cause is this is a Spring Bean Lifecycle issue.
- When Spring creates the beans it follows this general process
    1. Construct an instance of class
    2. Inject dependencies
    3. Set properties etc (@Value)
- In your case, when you initialized the array using this code
    - // create an array of strings
    - private String[] data = { a, b, c, d, e };
- The Spring Bean lifecycle was at step #1 above. It created an instance ... but during the assignment of the string array, the properties/fields for a, b, c, d, e haven't been set yet using @Value. That doesn't happen later until step #3.  So that's why you had null with the field assignment.
- When you made mods to your code and moved the assignment into the getFortune() method, then by the time this method is invoked all steps 1-3 are already complete and it works as desired.
---
- For this use case, the recommended solution is to use the @PostConstruct annotation. This is called at the end of the bean lifecycle process. So all of steps 1-3 are already completed and then you can safely perform assignments. This is the best use case for making any custom assignments in your code.
- Here's the updated code for your service. Make note of this section:
```java
  private String[] data;
    
    @PostConstruct
    public void setupMyData() {
        
        data = new String[5];
        
        data[0] = a;
        data[1] = b;
        data[2] = c;
        data[3] = d;
        data[4] = e;
        
    }
```
---
**NOTE: JAVA 9 USERS HEADS UP**  
- If you are using Java 9 and want to make use of @PostConstruct annotation, then you'll need to add support for @PostConstruct. See this link for how to set it up.
 - https://www.udemy.com/spring-hibernate-tutorial/learn/v4/t/lecture/9120288?start=0
---
###### S1 Section 9, Lecture 77 - @Scope Annotation - Overview
**Bean Scopes**
- Scope refers to the lifecycle of a bean
- How long does the bean live?
- How many instances are created?
- How is the bean shared?

**Default Scope**
- Default scope is singleton

**Refresher: What is a Singleton?**
- Spring Container creates only one instance of the bean, by default
- It is cached in memory
- All requests for the bean will return a SHARED reference to the SAME bean

![What is a singleton](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-07-20%2014_42_03-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- When the reference is returned for both, they give the same reference and point to the same area in memory

**Explicitly Specify Bean Scope**
```java
@Component
@Scope("singleton")
public class TennisCoach implements Coach {
    ...
}
```
- Use @Scope to specify the scope

**Prototype Scope Example**
- Prototype scope: new object for each request
```java
@Component
@Scope("prototype")
public class TennisCoach implements Coach {
    ...
}
```
![Prototype Scope Example](https://github.com/whereismybaymax/AAFCjavaJunitLearning/blob/master/Notes/Images/2018-07-20%2014_55_58-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Every time the tennisCoach bean is referenced, it creates a new object for each request
- In this Eg, Coach theCoach is in one area of memory and then Coach alphaCoach will create a new object 
    - Two dif objects, two dif areas in memory

**When to use Prototype scope**
- https://stackoverflow.com/questions/21969044/when-to-use-spring-prototype-scope
- when you need stateful beans {?} 
- A good practice is to pass dependencies through the constructor. Therefore, you should never use scope prototype. Instead, you should use new or a singleton factory.
- Imagine that you must build a real-time system for vehicle tracking, and you will have like 2.000.000 cars sharing information each 5 seconds, In the server side, you will work with two or more distinct group configurations, one for Cars and another one for Trucks. With this simple scenario, if you design your application to work with distinct configuration groups in memory you will achieve more performance.
- So, in this case when the server receives a new message from a Truck for example the server will get the configuration in memory, from a class VehicleGroupConfiguration and then apply which behavior this message should be, like time-out, retry... of course there are many ways to implement this situation, but with this simple example you can realize good scenarios to handle this configuration.

###### S1 Section 9, Lecture 78 - @Scope Annotation - Write Some Code
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/10758e889f617745dbad7ea6e129d3c4d61ebeff
- Lessons Learned: 
- If Spring is creating multiple objects cause' you used @Scope("prototype"), the constructor will be called for each bean. 
    - So, two beans means calling constructor twice

###### S1 Section 9, Lecture 79 - Bean Lifecycle Method Annotations - Overview
**Bean Lifecycle Methods/Hooks**
- You can add custom code during ****bean initialization****
    - Calling custom business logic methods
    - Setting up handles to resources (db, sockets, file, etc)
- You can add custom code during ****bean destruction**** 
    - Calling custom code business logic method
    - Clean up handles to resources (db, sockets, files, etc)

**Development Process**
- Define your methods for init and destroy
- Add Annotations @PostConstruct and @PreDestroy

**Init: method configuration**
```java
@Component
public class TennisCoach implements Coach{
    
    @PostConstruct
    public void doMyStartupStuff() {...}
}
```
- As the annotation says, this code will execute after constructor ****and**** after injection of dependencies

**Destroy: method configuration**
```java
@Component
public class TennisCoach implements Coach{
    
    @PreDestroy
    public void doMyCleanupStuff() {...}
}
```
- Code will execute ****before**** bean is destroyed

###### S1 Section 9, Lecture 80 - Note about @PostConstruct and @PreDestroy Method Signatures
**Special Note about @PostConstruct and @PreDestroy Method Signatures**
- The method referring below is the method where you'll use the annotations

****Access modifier****  
The method can have any access modifier (public, protected, private)

****Return type****   
The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.

****Method name****   
The method can have any method name.

****Arguments****  
The method can not accept any arguments. The method should be no-arg.

###### S1 Section 9, Lecture 81 - Note For Java 9 users - @PostConstruct and @PreDestroy Method Signatures
**HEADS UP - FOR JAVA 9, 10 and 11 USERS - @PostConstruct and @PreDestroy**

- If you are using Java 9, 10 or 11, then you will encounter an error when using @PostConstruct and @PreDestroy in your code.
- These are the steps to resolve it. Come back to the lecture if you hit the error. 
- ****Error****
    - Eclipse is unable to import @PostConstruct or @PreDestroy
    - This happens because of Java 9 and higher. 
- When using Java 9 and higher, javax.annotation has been removed from its default classpath. That's why we Eclipse can't find it.
- ****Solution****
1) Download the javax.annotation-api-1.2.jar from http://central.maven.org/maven2/javax/annotation/javax.annotation-api/1.2/javax.annotation-api-1.2.jar
2) Copy the JAR file to the lib folder of your project
---
- Use the following steps to add it to your Java Build Path.
3) Right-click your project, select ****Properties****
4) On left-hand side, click ****Java Build Path****
5) In top-center of dialog, click ****Libraries****
6) Click Classpath and then Click ****Add JARs ...****
7) Navigate to the JAR file ****<your-project>/lib/javax.annotation-api-1.2.jar****
8) Click ****OK**** then click ****Apply and Close****

Eclipse will perform a rebuild of your project and it will resolve the related build errors.

###### S1 Section 9, Lecture 82 - Bean Lifecycle Method Annotations - Write some code

###### S1 Section 9, Lecture 83 - Note about Destroy Lifecycle and Prototype Scope
- For "prototype" scoped beans, Spring does not call the @PreDestroy method.  Gasp!
- Here is the answer from the Spring reference manual. Section 7.5.2: 
    - https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-prototype
---
- In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean:
    - the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance. 
- Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. 
- The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 
- To get the Spring container to release resources held by prototype-scoped beans, try using a custom bean post-processor, which holds a reference to beans that need to be cleaned up.
---
- This also applies to XML configuration.

###### S1 Section 9, Lecture 84 - Practical Activity #6 - Bean Scopes with Annotations
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/f44de3cb6a4bee70d6639865eb1971b6ed3a3c23
**Practice Activity #6 - Bean Scopes with Annotations**
1) Modify your file based fortune service to make use of the @PostConstruct annotation
2) In the @PostConstruct code, read the fortunes from the file system
3) Test your application and verify your @PostConstruct code is being executed. Hint, add some println statements.

Compare your code to the solution. The solution is available here:
- http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip

Lessons Learned: 
- You did the previous practical activity wrong. It meant to use a text file, not a properties file
- The current answer to this is based on the solution that shows how to read a file like in C++ in stream and out stream

###### S1 Section 10, Lecture 85 - Spring Configuration with Java Code - Overview
**Java Configuration**
- Instead of configuring Spring container using XML
- Configure the Spring Container with Java Code 
    - No XML!
    
**3 Ways of Configuring Spring Container**

- Gone through the first two
- Full XML Config - where you list each bean in context file
- XML Component Scan - using annotations
- Next one will just be writing java source code to configure the container

**Development Process**
1) Create a Java class and annotate as ****@Configuration****
2) Add component scanning support: ****@ComponentScan**** (optional)
3) Read Spring Java configuration class
4) Retrieve bean from Spring Container

**Step 1: Create a Java class and annotate as @Configuration**
```java
@Configuration
public class SportConfig{
    ...
}
```

**Step 2: Add component scanning support: @ComponentScan**
```java
@Configuration
@ComponentScan("com.springdemo")
public class SportConfig {
    
}
```
- can manually add bean (will talk about it later) but for now, can use easy approach and do ComponentScan
    - Tell spring to just scan the package for beans and register with container
    
**Step 3: Read Spring Java configuration class**
```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class)
```
- here we simply read the configuration class we just created and give a reference to the Config we just gave
- It will create the spring container based on the java source configuration and make context available for us

**Step 4: Retrieve bean from Spring container**
```java
Coach theCoach = context.getBean("tennisCoach", Coach.class)
``` 

###### S1 Section 10, Lecture 86 - Spring Configuration with Java Code - Write Some code
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/814bc37d8fa59e5bc83983bd55d54c6ba50e36ac 

###### S1 Section 10, Lecture 87 - Defining Spring Beans with Java Code - Overview
**Defining Beans in Spring**
- Before used Full XML Config file
- Now gonna use Java Configuration class to add beans 

**Our New Coach**
- No special annotations
```java
public class SwimCoach implements Coach{
    ...
}
```
- Coach will have FortuneService dependency injected

**Development Process**
1) Define method to expose bean
2) Inject bean dependencies
3) Read Spring Java configuration class
4) Retrieve bean from Spring Container

**Step1: Define method to expose bean**
```java
@Configuration
public class SportConfig{
    
    @Bean
    public Coach swimCoach() {
        SwimCoach mySwimCoach = new SwimCoach(); 
        return mySwimCoach; 
    }
}
```
- @Bean defines a bean. 
- Here, we'll provide a method that returns SwimCoach 
- Internally, inside this method, we create an instance of SwimCoach bean and return it
- So, the method name is the actual beanid that'll be registered by the Spring Container
- NOT using ComponentScan here
    - In this configuration, we will define each bean individually in the configuration class

**What about our dependencies**
- How will we inject our dependencies cause' we know our Coach will need FortuneService

**Step 2: Inject bean dependencies**
```java
@Configuration
public class SportConfig{
    
    @Bean
    public FortuneService happyFortuneService()
    {
        return new HappyFortuneService(); 
    }
    
    @Bean
    public Coach swimCoach() {
        SwimCoach mySwimCoach = new SwimCoach(happyFortuneService()); 
        return mySwimCoach; 
    }
}
```
**Step3: Read Spring Java configuration class**

- creating an another method that returns a new instance of HappyFortuneService
- Again, the bean method name is the bean id that Spring will use when it's registers the bean with the application Context
- Then, can just use it later on in swimCoach
    - using the bean reference at the top

**Step 4: Read Spring Java configuration class**
```java
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
```
**Step 4: Retreive bean from Spring container**
```java
    Coach theCoach = context.getBean("swimCoach", Coach.class)
``` 
- the bean id here is the actual method name from the config class

**XML vs Java code Config, @Component vs @Bean**
- this section exists cause' you are super confused as to why the advantages and disadvantages of these dif ones  
****XML vs Java code Config****
- In a nutshell:
    - XML configs defines configs in a text file separate from source code. Changes to configs do not require recompiling source code.
    - Annotation can make use of @ComponentScan to minimize explicit configs
    - Java Code: hard-codes configuration in source code. Any change to configs requires recompiling source code.
---
- "Full Disclaimer: I personally prefer XML configs with @ComponentScan. I like to keep my configs separate from the source code."
---
- Here's a blog post on this topic. The author is in favor of hard-coding config information in the Java source code. Also, read the comments at the end of the blog post.
- Warning: It's from 2012...You only understood until the Patterns section
- https://blog.codecentric.de/en/2012/07/spring-dependency-injection-styles-why-i-love-java-based-configuration/
    - Disadvantages with XML
        - can't get errors before starting ApplicationContext
        - verbose so the configuration files get big, need to split them
        - can't easily search through split XMLs, need to rely on full-text-search
        - To build up libraries, it's hard to find XML configuration files in jars on the classpath and even harder to detect references in those files. 
        - these might be less drastic if you use the right tools
    - Navigable configurations: Can connect configurations.
        - dif configuration files can have responsibility for different components
        - By importing, all Spring beans from one config file are available in the other.   
        - Eg: 
```java
@Configuration
public class PartnerConfig {
 
	@Bean
	public PartnerService partnerService() {
		return new PartnerServiceImpl();
	}
 
}
```
---
```java
@Configuration
@Import(PartnerConfig.class)
public class CashingConfig {
 
	@Autowired
	private PartnerConfig partnerConfig;
 
	@Bean
	public CashingService cashingService() {
		return new CashingServiceImpl(partnerConfig.partnerService());
	}
}
```
   
****@Component vs @Bean****
- https://stackoverflow.com/questions/10604298/spring-component-versus-bean 
- @Component Preferable for component scanning and automatic wiring.
- When should you use @Bean?
    - Sometimes automatic configuration is not an option. 
    - When? Let's imagine that you want to wire components from 3rd-party libraries (you don't have the source code so you can't annotate its classes with @Component)
    - so automatic configuration is not possible.
- The @Bean annotation returns an object that spring should register as bean in application context. The body of the method bears the logic responsible for creating the instance.
- Another case: 
    - Let's consider I want specific implementation depending on some dynamic state. @Bean is perfect for that case.
        - However there is no way to do that with @Component.
```java
    @Bean
    @Scope("prototype")
    public SomeService someService() {
        switch (state) {
        case 1:
            return new Impl1();
        case 2:
            return new Impl2();
        case 3:
            return new Impl3();
        default:
            return new Impl();
        }
    }
```
- todo - see if it's possible to do java config with component scan annotation and then assign @component to the classes you want as beans. 
- todo - You seriously need to look more into the Q and A for this lecture to make sure you get a solid understanding of the uses, when to use, how to use, etc
###### S1 Section 10, Lecture 88,89 - Defining Spring Beans with Java Code - Write some code
- this time, there are no annotations in SadFortuneService and SwimCoach
    - so no component scan 

###### S1 Section 10, Lecture 90 - Injecting Values from Properties File - Overview
![Reading from properties file](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-08-20%2016_22_37-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- the properties file has an e-mail and a team
- we'll read info from this file and inject it into the SwimCoach

**Development Process**
1) Create Properties File
2) Load Properties File in Spring config
3) Reference values from Properties file

**Step 1: Create Properties file**
- sport.properties
```properties
foo.email=myeasycoach@luv2code.com
foo.team=Awesome Java Coders
```
- foo.blah is the 'property name' and the right side values is the 'property value'

**Step 2: Load Properties File in Spring config**
```java
@Configuration
@PropertySource("classpath:sport.properties")
public class SportConfig{
    
}
```
- give a reference to the @propertysource by saying that file is located in our classpath and then give name of properties file

**Step 3: Reference Values from Properties File**
```java
//File: SwimCoach.java
public class SwimCoach implements Coach {
    @Value("${foo.email}")
    private String email; 
    
    @Value ("${foo.team}")
    private String team; 
}
```
- what you're doing about is called 'field injection' to inject those values
- the name of the property needs into be in a dollar sign curly brace format
- later on, SwimCoach can use those values

###### S1 Section 10, Lecture 91, 92 - Injecting Values from Properties File - Write some code
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/360de022c480d783efdf207d0f6e9b82e6e56aea
- add properties in 'src' directory, not the package
- in SwimJavaConfigDemoApp.java, changed from using the Coach interface to SwimCoach because SwimCoach has new menthods that Coach doesn't. 

###### S1 Section 10, Lecture 93 - FAQ - Problems with Injecting Values - Value not returning ${foo.e-mail}
QUESTION:

I am running the code for Java Configuration and injecting values from props file. However, I'm getting:
    ${foo.email}  
    ${foo.team}  
Instead of the actual property values. How can I resolve this?

ANSWER: 
This is an issue with Spring versions.
If you are using Spring 4.2 and lower, you will need to add the code in ///.
```text
package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
// @ComponentScan("com.luv2code.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {

/////////////////////    
    // add support to resolve ${...} properties
    @Bean
    public static PropertySourcesPlaceholderConfigurer
                    propertySourcesPlaceHolderConfigurer() {
        
        return new PropertySourcesPlaceholderConfigurer();
    }
///////////////////////    
    // define bean for our sad fortune service
    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }
    
    // define bean for our swim coach AND inject dependency
    @Bean
    public Coach swimCoach() {
        SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());
        
        return mySwimCoach;
    }
}
```
In Spring 4.3 and higher, they removed this requirement. As a result, you don't need this code.

###### S1 Section 10, Lecture 94 - Practice Activity #7 - IoC and DI with Java Configuration
****Practice Activity #7 - Inversion of Control and Dependency Injection with Java Code****
- Here: https://github.com/whereismybaymax/AAFCSpringLearning/commit/1d8e5a1b6a70f5a9d3c8c16ff7aa9367f686fa06 
- Note: In this practice activity, do not use component scanning.
1) Create a new Coach implementation.
2) Create a new fortune service implementation (return a single hard-coded fortune)
3) Create a Java configuration class to perform IoC and DI with those classes.
4) Develop a main application to retrieve your coach implementation.
5) Test your application and verify your coach implementation is wired up as desired.
-  The solution is available here:
  - http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-practice-activities.zip

###### S1 Section 11, Lecture 95 - Spring MVC Overview
**What is Spring MVC**
- Framework for building web applications in Java
- Based on Model-View-Controller design pattern
- Leverages features of the Core Spring Framework (IoC, DI)

![MVC](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-09-05%2015_53_37-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
- Basically have an incoming request from the browser that will encounter Spring MVC front controller. 
- This will delegate the request to controller code. You basically create the model and send the model to the front controller. 
- The controller contains the business logic. The controller will pass the model to the view template. 
- The view template is like a html page that will render the response to the browser

**Spring MVC Benefits**
- The Spring way of building web apps UIs in Java
- Leverage a set of reusable UI components - available as spring jsp customer tags
- Help manage application state for web requests
- Process from data: validation, conversion, etc
- Flexible configuration for the view layer so not limited to jsp 

Spring documentation: https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc 

###### S1 Section 11, Lecture 96 - Spring MVC - Behind the Scenes
**Components of a Spring MVC Applications**
- A set of ****web pages**** to layout UI components
- A collection of spring ****beans**** (controllers, services, etc)
- ****Spring configuration**** (XML, Annotations or Java)

**How Spring MVC Works behind the scenes**
![FrontController](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-09-20%2016_14_15-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)

- Front controller is also known as ****DispatcherServlet****
    - It's part of the Spring Framework
    - Already developed by Spring Dev Team
    - So, you dont' have to create this. It's given in the Spring jar files and out of the box
    - Front controller will delegate the request to other objects or items in our system
    
- As a developer, You will create: 
    - ****M****odel objects (orange)
        - contain data
    - ****V****iew templates (dark green)
        - actual jsp page (view page to render data)
    - ****C****ontroller classes (yellow)
        - business logic or processing logic
    
**Controller**
- When the front controller has a request, it delegates the request to a controller
- the controller is the code created by developer
- Contains your business logic
    - Handle the request (Eg- read form data)
    - Store/retrieve data (db, web service...)
    - Once you have data and are using it, can place data in model
        - the model is just a container for data and then
    - Send to appropriate view template

**Model**
- Model: contains your data
- Store/retrieve data via backend systems
    - database, web service, etc...
    - Use a spring bean if you like
- Place your data in the model
    - Data can be any Java object/collection
- when controller does an operation to retrieve data from an backend system, you can take that data and place it into the model
- Model is basically your container (box,luggage,suitcase,whatever, not technical term) for shipping data between various part of your Sping MVC application
- model data will get passed over to the view template and they can handle that for displaying the data. 

**View**
- Spring MVC is flexible
    - Supports many view templates
- For now, most common is JSP + JSTL
- model data comes over to your view template and then jsp page can read that model data and display it
- Developer creates a page
     - Displays data 
- View is basically a jsp page that will provide data to the user
- Eg - Say we have a list of students or products, jsp can create a table to display that product/student list
    - else if somebody is signing up for a class/airline flight, view template/page can give a confirmation like 'hey, you're register for class' 

**View Template (more)**
- Other view templates supported
    - Thymeleaf, Groovy
    - Velocity, Freemarker, etc
- For details, see: 
    - www.luv2code.com/spring-mvc-views

###### S1 Section 11, Lecture 97 - Development Environment Checkpoint

**Dev Environment Check Point**

At this point, you should have installed: 
- Apache Tomcat
- Eclipse (Java EE version) (intelij idea in your case)
- Connected Eclipse to Tomcat

- this needs to be setup and configured (if not done, go to Section 3 and do it)
    - you somehow managed to get this far without doing this and just working in Intellij so something is working...
    
**Additional things to do**

- Download Spring MVC source code for upcoming stuff
- Download latest Spring JAR files from Spring website

**Download Spring MVC Starter files**
- For upcoming sections: 
    - From: http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-code-spring-mvc-config-files.zip
- Note from before: 
    - in the future to add more jars specific to Spring MVC, can just try to add a module with those specific jars, copy the jars over and then delete the temp module

###### S1 Section 11, Lecture 98,99 - Spring MVC Configuration - Overview
![MVC](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-09-05%2015_53_37-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
**Spring MVC Configuration Process - part 1**
- Add configurations to file: WEB-INF/web.xml
1) Configure Spring MVC Dispatcher Servlet
2) Set up URL mappings to Spring MVC Dispatcher Servlet

**Spring MVC Configuration Process - part 2**
- Add configurations to file: WEB-INF/spring-mvc-demo-servlet.xml
3) Add support for Spring component scanning
4) Add support for conversion, formatting and validation
5) Configure Spring MVC View Resolver

**Step 1: Configure Spring DispatcherServlet**

```xml
 <!--web.xml-->
<web-app>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring-mvc-demo-servlet.xml</param-value>
        </init-param>
    
        <load-on-startup>1</load-on-startup>    
    </servlet>
</web-app>
```

- In web.xml, need to add entry for Spring Dispatcher servlet (front controller)
- Put in a servlet reference by ging it a name and class (servlet-name, servlet-class)
- Dispatcher servlet is part of the spring framework, so part of the given jar files
- Then, setup the initial parameter by telling where the Spring context configuration is located 
    - this will use the spring-mvc-demo-servlet.xml in this case but could've called this anything as long as it is referenced properly

 **Step 2: Setup URL mappings to Spring MVC Dispatcher Servlet**
 
 ```xml
 <!--web.xml-->
 <web-app>
     <servlet>
         <servlet-name>dispatcher</servlet-name>
         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        ... 
     </servlet>
     
     <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
     
 </web-app>
 ```
 
 - Here, telling the system that for any URL pattern coming in, pass it off to the dispatcher servlet
 - The url pattern his is '/', meaning all web requests coming in should be handled by the dispatcher servlet
    - can have a different pattern here like /blah/*
    - this / handles all requests
- Imp: Make sure the servlet name matches in both (the name in step 1)

**Step 3: Add support for Spring component scanning**
```xml
 <!--spring-mvc-demo-servlet.xml-->
<beans>
    <!--Step 3: Add support for component scanning-->
    <context:component-scan base-package="com.spring-demo-annotation"/>
</beans>
```
   
- This was seen before where it scans package for any special spring beans and make them available 
    - so any @components will be made available and it will put them into spring context

**Step 4: Add support for conversion, formatting and validation**
```xml
<!--spring-mvc-demo-servlet.xml-->
<beans>
    <!--Step 3: Add support for component scanning-->
    <context:component-scan base-package="com.spring-demo-annotation"/>
    
    <!--Step 4: Add support for conversion, formatting and validation support-->
    <mvc:annotation-driven/>
</beans>
```
- when using Spring mvc, this can perform conversion of form data, format form data and can also perform form validation

**Step 5: Spring MVC View Resolver**
```xml
<!--spring-mvc-demo-servlet.xml-->
<beans>
    <!--Step 3: Add support for component scanning-->
    <context:component-scan base-package="com.spring-demo-annotation"/>
    
    <!--Step 4: Add support for conversion, formatting and validation support-->
    <mvc:annotation-driven/>
    
    <!--Step 5: Define Spring MVC view resolver-->
    <bean>
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

- view resolver is basically a facny name for 'how do we display the pages', 'where is the page located'
- At the bottom, make use of InternalResourceViewResolver and then make sure of prefix and suffix

**View Resolver Configs - Explained**
- When your app provides a "view" name, Spring MVC will 
    - prepend the prefix
    - append the suffix

- When your app provides a view name, spring mvc will automatically prepend the prefix and append the suffix
    -  basically tells where to look for files to render the view for your application
    - ![ViewResolverEg]()
    - Here, say we returned a show-student-list.jsp view
        - Automatically, spring will prepend web-inf/view cause' that's the prefix
            - it will now have the view name
        - Then it'll append the suffix (.jsp) 
    - Now, that's the actual path of the view page that will show

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








































 

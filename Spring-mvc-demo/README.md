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

- view resolver is basically a fancy name for 'how do we display the pages', 'where is the page located'
- At the bottom, make use of InternalResourceViewResolver and then make sure of prefix and suffix

**View Resolver Configs - Explained**
- When your app provides a "view" name, Spring MVC will 
    - prepend the prefix
    - append the suffix

- When your app provides a view name, spring mvc will automatically prepend the prefix and append the suffix
    -  basically tells where to look for files to render the view for your application
    - ![ViewResolverEg](https://github.com/whereismybaymax/AAFCSpringLearning/blob/master/Spring-demo-annotation/Images/2018-09-24%2013_59_01-Spring%20%26%20Hibernate%20for%20Beginners%20_%20Udemy.png)
    - Here, say we returned a show-student-list.jsp view
        - Automatically, spring will prepend web-inf/view cause' that's the prefix
            - it will now have the view name
        - Then it'll append the suffix (.jsp) 
    - Now, that's the actual path of the view page that will show

###### S1 Section 11, Lecture 100 - Spring MVC Configuration - JAR files
- In Eclipse, 
    - In Java EE view
    - New->Dynamic Web Project
        - Project name:  spring-mvc-demo
        - keep defaults and hit finish
        - Default structure looks like this: 
            - ![defaultDWP]()
        - copy over spring jars from Spring release (5.0.2 - https://repo.spring.io/release/org/springframework/spring/) into lib
            - In zip file, in libs directory, the core jar files will be there. 
            - select all, copy, move into WEB-INF->lib directory
                - by putting them in the lib directory, they are automatically in build path and in class path
        - need additional files (for this course)
            - http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-code-spring-mvc-config-files.zip 
            - Go to lib in this zip, copy and paste into project lib. 
- In Idea
File->New Project
Spring, select Spring MVC
	- choose download at the bottom instead of using a library (4.3.18 - Intellij Idea default)
	- Ended up creating a folder in the existing Spring learning
- Copied jars from http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-code-spring-mvc-config-files.zip into lib in project
    - Here, lib is not in WEB-INF. It's just inside the project folder 

###### S1 Section 11, Lecture 101 - Spring MVC Configuration - Config files
- Copy starter config files from: http://www.luv2code.com/downloads/udemy-spring-hibernate/solution-code-spring-mvc-config-files.zip 
- copy the web.xml and spring-mvc-demo-servlet.xml
    - move to WEB-INF directory
        - this will work fine in Eclipse since web.xml is not autogenerated I guess. But in idea it is. So just overwrite original web.xml
- These files are the same reviewed above and screwing these up will cause' a lot of problems 
    - So, it's smart to just use some starter files
- Note: 
- In spring-mvc-demo-servlet.xml, the package name might be red initially since the src folder is empty and no package has yet been created.
    - Also, in prefix, the /view/ has yet to be created so we need to create this directory
    - Idea will give a 'Spring Configuration Check' with 'Unmapped Spring Configuration files found. Please configure Spring facet or use 'Create Default Context' to add one including all unmapped files.'
        - This is referring to the 'dispatcher-servlet.xml' that was created by default. 
        - Delete this file and it should remove that message
        
###### S1 Section 11, Lecture 102 - FAQ: How to configure the Spring Dispatcher Servlet using all Java Code (no xml)

**FAQ: How to configure the Spring Dispatcher Servlet using all Java Code (no xml)**
QUESTION
- How to configure the Spring Dispatcher Servlet using all Java Code (no xml)?
ANSWER
- For Spring MVC, all Java config (no xml) is covered later

However, if you just need the code, here are the steps
1) Delete the files: web.xml file and spring-mvc-demo-servlet.xml files
2) Create a new Java package: com.luv2code.springdemo.config
3) Add the following Java files in your package

```java
//DemoAppConfig.java

package com.springdemo.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.springdemo")
public class DemoAppConfig {
 
	// define a bean for ViewResolver
 
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
}
```
--------------
```java
//MySpringMvcDispatcherServletInitializer.java

package com.luv2code.springdemo.config;
 
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DemoAppConfig.class };
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
 
}

```
4) Test your app 
- It should work as desired
- Full project implementation: https://drive.google.com/open?id=1_5__2SggzgFHt7Rs2YYsv5JHRVX5Orq3
- Found in Lecture 403 - Spring MVC All Java Config at [02:28 mark]

###### S1 Section 12, Lecture 103 - Creating a Spring Home Controller and View - Overview
**First Spring MVC Example**
![FirstMVCApp]()

- So far saw the MVC, now, we will setup a request mapping for a given path 
    - Then, will have a home controller that will handle the request which will fort it over to a view template (which will be called main-menu.jsp)

**Development Process**
1) Create Controller class
2) Define Controller method
3) Add Request Mapping to Controller method
4) Return View Name
5) Develop View Page

**Step 1: Create Controller class**
- Annotate class with @Controller
    - @Controller inherits from @Component supports scanning
    
```java
@Controller
public class HomeController {
}
```
- Basically you define a class and annotate it @Controller
    - @Controller basically says that this is a Spring MVC controller 
    - @Controller inherits from @Component
        - So, it's a specialized component that supports web MVC
    - When Spring does component scanning, it also picks up @Controller cause' they inherit/extend from @Component

**Step 2: Define Controller method**
```java
@Controller
public class HomeController{
        public String showMyPage()
        {
            
        }
}
```
- showMyPage() is the controller method
    - Spring MVC is flexible so can use any method name
- return type is String but could've been something else

**Step 3: Add Request Mapping to Controller method**
```java
@Controller

@RequestMapping("/")
public class HomeController{
        public String showMyPage() {
            ...
        }
}
```
- request mapping maps the web request to the given method using the annotation
- give the actual path that you want to map
    - In this case, it's the root
    - This request mapping will handle all kinds of requests including GET, POST, other HTTP methods, etc
- Annotation maps a path to a method name
    - That's why you can choose any method name

**Step 4: Return View Name**
```java
@Controller
public class HomeController{
        @RequestMapping("/")
        public String showMyPage() {
            return "main-menu"; 
        }
}
```
![SpringViewMagic]()
- main-menu is the view name
    - not returning the complete name of the page since we'll make use of the config file that will add the prefix and suffix 
- BTS, Spring will use the info from configuration file and find the view page
    - It will look in the given prefix directory, use the view name and then append the suffix .jsp
    - Here, will look for /WEB-INF/view/main-menu.jsp




**Step 5: Develop View Page**
```jsp
/WEB-INF/view/main-menu.jsp
<html>
    <body>
        <h2>Spring MVC Demo - Home Page</h2
    </body>
</html>
```

###### S1 Section 12, Lecture 104 - Creating a Spring Home Controller and View - Write Some Code
- Create a new package in src
    - the name should be the same as what you put in the xml files
- In package, create new class - HomeController
- In WEB-INF/view, create new jsp file





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








































 

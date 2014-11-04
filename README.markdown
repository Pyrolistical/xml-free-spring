Servlet 3 + Spring Without XML
---
Software should be simple but we keep putting up artifical barriers to our end goals.  XML configuration is one of those barriers as it is annoying to maintain and refactor.

The XML burden was reduced with Spring 2.5 with the addition of annotation based configuration, but you still needed to bootstrap Spring in XML.

However in Servlet 3 and Spring 3.1 we can now drop XML completely and have 100% code based configuration.  This includes the web.xml!  This is all thanks to the Servlet 3 spec!

I wrote simple "Hello world" [XML free Spring project](https://github.com/Pyrolistical/xml-free-spring), but instead of going over the code now, I thought I would explain from the bottom up.  The bootstraping process of Spring can be a bit of magic, so hopefully this will actualize the process.

### XML free bootstrap process:
1. Servlet 3 container finds all classes implementing [ServletContainerInitializer](http://download.oracle.com/javaee/6/api/javax/servlet/ServletContainerInitializer.html)
2. Container executes a Spring implementation [SpringServletContainerInitializer](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/SpringServletContainerInitializer.html)
3. SpringServletContainerInitializer finds all classes implementing [WebApplicationInitializer](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/WebApplicationInitializer.html)
4. Spring executes our root configuration implementation [WebApp](https://github.com/Pyrolistical/xml-free-spring/blob/master/src/main/java/com/github/pyrolistical/config/WebApp.java)
5. Our root configuration create a new Spring context [AnnotationConfigWebApplicationContext](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/context/support/AnnotationConfigWebApplicationContext.html)
6. The context finds all classes with the [@Configuration](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/context/annotation/Configuration.html) annotation
7. The context executes our controller configuration [ControllerConfiguration](https://github.com/Pyrolistical/xml-free-spring/blob/master/src/main/java/com/github/pyrolistical/config/ControllerConfiguration.java)
8. Our controller configuration finds all classes with the [@Controller](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/stereotype/Controller.html) annotation
9. The configuration creates our controller [RootController](https://github.com/Pyrolistical/xml-free-spring/blob/master/src/main/java/com/github/pyrolistical/controller/RootController.java)
10. Finally the context we created in step 5 is fed into the Spring Servlet [DispatcherServlet](http://static.springsource.org/spring/docs/3.1.0.RC1/javadoc-api/org/springframework/web/servlet/DispatcherServlet.html)

Now when we hit [http://localhost:8080](http://localhost:8080) the request goes to the DispatcherServlet which is configured to handle / with the RootController.

There are just two caveats at the moment.  First your application container must support Servlet 3 for this to work.  I used the Jetty 8 Maven plugin for testing.  Second Spring 3.1 is currently in RC1.  You can start playing with it now, but just remember to upgrade to the release version when it comes out next month.

With all the configuration in code it is so easy to refactor and navigate.  No more back and forth with XML.

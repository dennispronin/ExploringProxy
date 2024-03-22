## Exploring proxies

This repository contains proxy pattern explanation and java dynamic proxies code examples.

### Table of Contents

- [Definition](#definition)
- [Use cases](#use-cases)
- [Implementation steps](#implementation-steps)
- [Dynamic proxies](#dynamic-proxies)
- [Java dynamic proxies libraries](#java-dynamic-proxies-libraries)
- [Spring AOP](#spring-aop)

### Definition

The Proxy pattern is a Structural pattern. Structural patterns help organise the codebase, keeping it clean and modular. The Proxy pattern explains how to add new logic to existing code without changing it and without notifying client code of the occurred changes.

### Use cases

**Application proxies**:
- Profiling
- Authentication and authorisation
- Caching
- Logging
- Audit logging
- Transferring local calls to remote calls
- Lazy initialisation / virtual proxy. If an object is too heavy (e.g., entity collection field or high-resolution image), the proxy is placed instead of the real object, and the real object is retrieved only when the client code calls this field
- Deciding which real object will be used. For instance, a proxy might decide to make calls to only one real object, thus supporting the singleton pattern. Alternatively, it might act as a load balancer, making calls to less loaded real objects.
- Mocking actual classes in tests (e.g., Mockito)

**Architectural proxies**:
- Load balancer (e.g., nginx) (reverse proxy)
- Authentication and authorization (e.g., nginx) (reverse proxy)
- Filtering calls from suspicious clients
- Blocking calls to certain websites for users within a network (forward proxy)

### Implementation steps

1. Define an interface for the object if possible. Otherwise, the proxy can only be created using inheritance.
2. Create the proxy class and implement the necessary interface and its methods. Include a field with a reference to the real object to call it if needed.
3. Implement a method or factory that configures and passes a proxy to the client code when it requests the real object.

#### Dynamic proxies

Proxies that are written manually are referred to as static proxies. Dynamic proxy is a concept involving creating a proxy at runtime without the need to write it yourself.

##### Java dynamic proxies libraries
- **[JDK Proxy](https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html)** - Built-in Java set of classes located in the <code>java.lang.reflect</code> package introduced in Java 1.3. Can be used to create interface-based proxy. Used by Spring and Hibernate. Main advantage is it's simplicity and no extra dependencies. Main limitation is inability to create inheritance-based proxy.
- **[cglib](https://github.com/cglib/cglib) (Code Generation Library)** - Well-known Java runtime code manipulation library. Can create both interface and inheritance proxies. Used by Spring, Hibernate and older versions of Mockito, when a class does not have interfaces. Main disadvantage is that it is no longer maintained. Creators of the library suggest migrating to Byte Buddy. Another disadvantage is the absence of any automatic type safety. Programmer should manually check the proxy and original object compatibility. If a programmer fails to do so correctly, there might be a problem in the runtime. It also does not support annotations when defining a proxy.
- **[Byte Buddy](https://github.com/raphw/byte-buddy)** - Another popular Java runtime code manipulation library, the newest of the three. Some of the features it offers: domain specific language which offers high configurability, but has some learning curve; type safety - Byte buddy will fail fast during program initialisation if the configured proxy and the original object are not compatible; handy annotation API. It is used by some of the major frameworks: the recent versions of Mockito and Hibernate.
- **[Javassist](https://github.com/jboss-javassist/javassist)** - The oldest of the three Java runtime code manipulation library. While being a reliable and well-established, Javassist has several disadvantages: not having any fail-fast behaviour when proxy is not compatible with its object; no support for annotations; it is also considered slower than, for example, cglib, as it reads class files instead of already loaded classes. Used by older versions of Hibernate.

#### Spring AOP

**Aspect-Oriented Programming (AOP)** is a programming approach that helps to better organise the use of repeating utility logic such as logging, transaction management, security management, and caching. It emphasizes separating non-business code from business logic, ideally ensuring that business code does not directly call utility methods. This paradigm aligns well with the proxy pattern, as AOP concepts map effectively to the components of a proxy:

- **Aspect**: This corresponds to the proxy's handler class.
- **Advice**: It refers to the handler's method.
- **Join Point**: This represents a method or field call that should be intercepted by the proxy.
- **Pointcut**: When creating a dynamic proxy with any framework, we specify which method or field call should be intercepted.
- **Weaving**: This is the actual process of creating and applying the proxy to the client code.

**Spring** - arguable the most well-known Java web framework, relies heavily on the concept of AOP. with the most notable example being transaction management using the <code>@Transactional</code> annotation. Spring AOP works by creating proxies for objects which require cross-cutting logic. Spring uses JDK proxy when object has an interface or CGLIB, when it does not. (Spring Boot though always uses CGLIB.)

#### References

- https://refactoring.guru/design-patterns/proxy
- https://web.archive.org/web/20150226062232/http://userpages.umbc.edu/~tarr/dp/lectures/DynProxies-2pp.pdf
- https://docs.spring.io/spring-framework/reference/core/aop.html
- http://bytebuddy.net/#/tutorial
- https://stackoverflow.com/questions/2261947/are-there-alternatives-to-cglib
# java-results
A common java rpc/rest result wrapper.

## Concepts

- `results-api` is for the **client-side** libraries (e.g., api, client or sdk), it should be referenced in these libraries.
- `results-support` is for the **server-side** supporting (e.g., result wrapping, exception converting, etc), it should **NOT** be referenced in the client-side libraries.

> NOTE: All modules in this project are not deployed to maven center. Deploy to your own nexus or install to local repository to use them!

## Quick Start

### RPC Example

project structure:

```
foo-project
|-- foo-client
| |-- src
| |-- pom.xml
|-- foo-server
| |-- src
| |-- pom.xml
|-- pom.xml
```

0. Reference the libraries:

   foo-client/pom.xml:
   
   ```xml
   <dependency>
       <groupId>com.github.jasomming.results</groupId>
       <artifactId>results-api</artifactId>
       <version>1.0.0-SNAPSHOT</version>
   </dependency>
   ```
 
   foo-server/pom.xml:

   ```xml
   <dependency>
       <groupId>com.github.jasomming.results</groupId>
       <artifactId>results-support</artifactId>
       <version>1.0.0-SNAPSHOT</version>
   </dependency>
   ```


1. Declare a rpc method with an exact type of result wrapper in client-side library:

   ```java
   import com.github.jasonnming.results.result.generic.SingleResult;

   public interface FooClient
   {
       SingleResult<FooResultCode, FooInfo> queryFooInfo();
   }
   ```

2. Implements it:


   ```java
   import com.github.jasonnming.results.result.support.Results;

   class FooClientImpl implements FooClient
   {
       @Override
       public SingleResult<FooResultCode, FooInfo> queryFooInfo()
       {
           FooInfo fooInfo = ...;
           return Results.singleResult(FooResultCode.SUCCESS, fooInfo);
       }
   }
   ```

### REST Example

project structure:

```
foo-project
|-- src
|-- pom.xml
```

0. Reference the libraries:

   ./pom.xml:

   ```xml
   <dependency>
       <groupId>com.github.jasomming.results</groupId>
       <artifactId>results-support</artifactId>
       <version>1.0.0-SNAPSHOT</version>
   </dependency>
   ```

1. Uses in the controller/handler:


   ```java
   import com.github.jasonnming.results.result.support.Results;

   @RestController
   class FooController
   {
       @GetMapping("/foo")
       public Object queryFooInfo()
       {
           FooInfo fooInfo = ...;
           return Results.singleResult(FooResultCode.SUCCESS, fooInfo);
       }
   }
   ```

---

Enjoy!

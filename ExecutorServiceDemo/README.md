
## **Executor Service**
--------------------
--------------------

Demonstration of performance of executor service over the simple sequencial processing.
### What it does?
This program takes list of urls and checks the staus of url (whether url is running or not). 
Optionally, it takes list of urls as it's input (java argument). If argument is not provided then it uses default set of urls.

To check the status of site, it uses 
   - ExecutorService using callable task
   - Sequential processing
Based on the # of average executions value, program calculates the average time required to complete the process.
Result is indeed isn favor of ExecutorService which takes much lower time to complete the entire process as compare to
sequncial process.

### How to run?
Clone repository and run mvn package. It will create jar file as an output.
Execute program as 
java -cp target/ExecutorServiceDemo-0.0.1-SNAPSHOT.jar com.github.concurrency.CheckUrlServiceDemo
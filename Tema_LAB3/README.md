
# Polyglot Programming with GraalVM - Lab Guide

## Introduction
This document serves as a comprehensive guide for Laboratory 2 focusing on the use of Polyglot programming with GraalVM. GraalVM supports the execution of code written in multiple languages seamlessly, enabling the development of applications that leverage functionalities from Java, Python, JavaScript, and R.

## Setting up GraalVM
1. Download the latest GraalVM Community Edition from [GraalVM GitHub Releases](https://github.com/graalvm/graalvm-ce-builds/releases).
2. Extract the downloaded tar.gz file using:
   ```
   tar xvf graalvm-ce-java11-linux-amd64-22.0.0.2.tar.gz
   ```
3. Replace the version number in the command above with the one you have downloaded.
4. Test the installation:
   ```
   ./bin/java -version
   ```

## Configuring IntelliJ Project
- Open your project containing the example code in IntelliJ.
- To configure the project SDK to GraalVM, navigate to `File → Project Structure → Project`, and ensure GraalVM is selected as the Project SDK. If it’s not listed, add the SDK by clicking `Add SDK → JDK` and navigating to your GraalVM directory.

## Installing Additional Languages
If the application requires languages not pre-installed with GraalVM:
```
./bin/gu install R
./bin/gu install Python
```
Replace `R` and `Python` with the languages needed for your application.

## Lab Tasks
1. **Modify Checksum Calculation**:
   - Adapt the checksum calculation algorithm in Python to use a polynomial formula of at most rank 5.
   - Implement substring functions to remove the first and last characters in the checksum string.
   
2. **Polyglot Application**:
   - Develop a Polyglot application that includes:
     - A Python function generating a random list of 20 integers.
     - A JavaScript function displaying this list.
     - An R function sorting the list, removing the first and last 20% of elements, and calculating the arithmetic mean.
   - Display the final result in the console.

## Compilation and Execution
- Compile and run the Polyglot application using the GraalVM SDK from the command line:
  ```
  /home/student/opt/graalvm-ce-java11-22.0.0.2/bin/javac Polyglot.java
  /home/student/opt/graalvm-ce-java11-22.0.0.2/bin/java Polyglot
  ```

## Debugging and Monitoring
GraalVM offers extensive tools for debugging and performance monitoring. Integration with Chrome Developer Tools is supported, along with other tools like VisualVM for real-time insights.

For more details on profiling and debugging:
- [GraalVM Profiling Tools](https://www.graalvm.org/tools/profiling/)
- [GraalVM Chrome Debugger](https://www.graalvm.org/tools/chrome-debugger/)
- [GraalVM VisualVM](https://www.graalvm.org/tools/visualvm/)

This README provides a framework for navigating the complexities of Polyglot programming in a multi-language environment enabled by GraalVM, ensuring a smooth and efficient development process.

# TicTacToeClient
ITI TicTacToe Client project

## Steps
1. Clone project
2. Create local branch name **Development**
   1. command  ```git branch Development ```
3. Checkout to the Development branch
   1. command ```git checkout Development```
4. Lat step clone the Development branch to your local Development branch
   1. command ```git pull origin Development```

## Note
When developing make sure you are using your local Development branch and when push your changes make sure push top the remote Development branch


## To run the project
1. Install **[intelij](https://www.jetbrains.com/idea/download/#section=windows)**
2. Install **[Maven](https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.zip)**
   1. Unzip downloaded file and copy unzipped folder to **C:\Program Files**
   2. **Edit** Environment variable path add **new**  **C:\Program Files\apache-maven-3.8.2\bin**
   3. **New** Environment variable with name **MAVEN_HOME** and url **C:\Program Files\apache-maven-3.8.2**
   4. open cmd and type ```mvn --version``` to test maven
3. open terminal in intellij and use ```mvn install```
4. make sure you are using JDK17
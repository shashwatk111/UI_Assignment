**Introduction:**

This project covers UI Automation of following use case 

1. Launch the browser. 
2. Open URL - http://www.google.com 
3. Enter the keyword "amazon" in the search bar 
4. print all the search results 
5. Click on the link which takes you to the amazon login page. 
6. login to https://www.amazon.in/ 
7. click on all buttons on search & select Electronics. 
8. search for dell computers 
9. apply the filter of range Rs 30000 to 50000 
10. Validate all the products on the first 2 pages are shown in the range of Rs 30000 to 50000 
11. print all the products on the first 2 pages whose rating is 5 out of 5 
12. add the first product whose rating is 5 out of 5 to the wish list. (Create a new wish list) 
13. Validate the product is added to the wish list 

**Tools and framework used ::**

1. Testing Framework - TestNG 
2. Java - JDK (20.0.1)
3. Build automation tool - Maven (3.9.3)
4. Design Pattern - Page Object Model
5. Programming Language - Java
6. Testing tool	- Selenium
7. IDE - Intellij

**Prerequisites ::**

List of prerequisite that need to be install before using this project ::
1. JDK 1.8 or higher (Latest would be recommended)
2. Maven 
3. Git

Add it's bin directory to system's PATH environment variable to run commands.

**Getting started test execution::**

Step by step instruction to run the automation script ::
1. Make one folder
2. Open cmd in the same folder
3. Run below git command to clone the project 
   - git clone http_git_repository
4. Use cd command to navigate to the directory containing project's pom.xml file
   - cd path_of_project_directory
5. Run "amazon_test_case_run" windows batch file or 
   Run below commands where "pom.xml" file exists
   - mvn clean install 

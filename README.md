This project demonstrates automated API testing using RestAssured, dynamic test data from Excel sheets, and full CI/CD integration with Jenkins and GitHub. The project contains multiple Java classes showcasing REST API GET/POST testing, data-driven testing, and XML content validation.

📋 Project Structure
css
Copy
Edit
RestAssured/
├── src/
│   └── main/
│       └── java/
│           ├── compare_xml_data_to_excel_DATA/
│           │   └── CompareExcelToXml.java
│           ├── get_post_assert_data_with_rest_assured/
│           │   └── test_restAssure.java
│           └── post_data_from_excel_sheet/
│               └── PostXmlAllRowsTest.java
│
├── src/main/resources/
│   └── testdata.xlsx
│   └── testdata2.xlsx
│
├── Jenkinsfile
└── pom.xml
✅ Features
Data-driven API Testing with data pulled from Excel (.xlsx) using Apache POI

REST API Tests using RestAssured

Live Webhook Posting: XML data sent to Webhook.site

Jenkins Pipeline: End-to-end CI/CD with automated Maven build and multi-step testing stages

Detailed Console Logging: Each execution phase is clearly logged with emoji markers for readability

⚙️ Technologies Used
Java 17

Maven 3.9.11

RestAssured

Apache POI (Excel reading)

Jenkins (Pipeline build)

GitHub (SCM)

Webhook.site (mock endpoint)

🚦 Jenkins Pipeline Overview
The Jenkinsfile defines a declarative pipeline with the following stages:

Checkout: Pulls source code from GitHub

Build: Runs mvn clean compile

Compare XML vs Excel: Runs CompareExcelToXml to validate data consistency between a W3Schools XML and Excel

GET/POST/Assert Tests: Executes various API assertions using test_restAssure

Post Data from Excel: Reads Excel data and POSTs it as XML to Webhook.site

Post Actions: Indicates build success/failure visually

✅ All build output is printed clearly in the Jenkins Console.

🧪 API Tests Included
CompareExcelToXml.java
Reads expected menu items and prices from Excel

Fetches XML menu from W3Schools

Compares item names and prices

test_restAssure.java
Verifies XML response status

Checks specific values like food names, descriptions, and prices

Posts a static XML user to a webhook

PostXmlAllRowsTest.java
Reads multiple rows from Excel (testdata.xlsx)

Builds XML for each row

Posts each to Webhook.site with console tracking

▶️ Demo: Git to Jenkins Integration
The video walkthrough (see attachment or repo link) shows:

Making code changes locally

Committing & pushing to GitHub via Maven or Git CLI

Automatically triggering Jenkins pipeline

Seeing full build logs, RestAssured execution, and success indicators

🔧 Requirements
Java 17 installed

Maven configured (mvn -v)

Jenkins setup with:

Git plugin

Maven tool configuration (e.g., maven-3.9.11)

JDK configured (e.g., jdk-17)

🚀 How to Run Locally
Clone the repo:

bash
Copy
Edit
git clone https://github.com/khloudmousad/RestAssured.git
Compile:

bash
Copy
Edit
mvn clean compile
Run individual classes:

bash
Copy
Edit
mvn exec:java -Dexec.mainClass="compare_xml_data_to_excel_DATA.CompareExcelToXml"
mvn exec:java -Dexec.mainClass="get_post_assert_data_with_rest_assured.test_restAssure"
mvn exec:java -Dexec.mainClass="post_data_from_excel_sheet.PostXmlAllRowsTest"
📽️ Watch the Demo
📹 Video shows:

Maven build

GitHub commit

Jenkins pipeline trigger
Multi-step test execution

https://github.com/user-attachments/assets/9b5be5ab-5124-45f8-b58a-03b4e897dbd0




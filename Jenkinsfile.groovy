pipeline {
    agent any

    tools {
        jdk 'jdk-17'         // Must match the name of JDK configured in Jenkins
        maven 'maven-3.9.5'  // Must match Maven tool configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/khloudmousad/RestAssured/blob/master/src/test/java/compare_xml_data_to_excel_DATA/CompareExcelToXml.java'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Java Main') {
            steps {
                sh 'mvn exec:java'
            }
        }
    }

    post {
        success {
            echo '✅ Build and execution successful!'
        }
        failure {
            echo '❌ Something went wrong.'
        }
    }
}

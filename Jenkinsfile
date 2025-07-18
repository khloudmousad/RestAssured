pipeline {
    agent any

    tools {
        jdk 'jdk-17'         // Must match the name of JDK configured in Jenkins
        maven 'maven-3.9.11' // Must match Maven tool configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/khloudmousad/RestAssured'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Class 1') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="compare_xml_data_to_excel_DATA.CompareExcelToXml"'
            }
        }

        stage('Run Class 2') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="get_post_assert_data_with_rest_assured.get_post_assert_data_with_rest_assured"'
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

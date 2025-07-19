pipeline {
    agent any

    tools {
        jdk 'jdk-17'         // Must match the name of JDK configured in Jenkins
        maven 'maven-3.9.11' // Must match Maven tool configured in Jenkins
    }

    stages {
        stage('open git RestAssured project') {
            steps {
                git 'https://github.com/khloudmousad/RestAssured'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('compare xml data with excel data') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="compare_xml_data_to_excel_DATA.CompareExcelToXml"'
            }
        }

        stage('get post assert data with rest assured ') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="get_post_assert_data_with_rest_assured.test_restAssure"'
            }
        }
        stage('post data from excel sheet ') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="post_data_from_excel_sheet.PostXmlAllRowsTest"'
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

pipeline {
    agent any

    tools {
        jdk 'jdk-17'
        maven 'maven-3.9.5'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/xml-excel-comparator.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo '🎉 Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}

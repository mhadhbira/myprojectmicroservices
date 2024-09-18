pipeline {
    agent any

    environment {
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        DB_URL = "jdbc:postgresql://localhost:5432/MyFirstDatabase"
        DB_USERNAME = "postgres"
        DB_PASSWORD = "rania"
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git url: 'https://github.com/mhadhbira/myprojectmicroservices.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                // Clean and build the project using Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {  // Optional step for code quality analysis
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('SonarQube') {  // Ensure SonarQube is configured in Jenkins
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                // Package the application
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and tests succeeded.'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}

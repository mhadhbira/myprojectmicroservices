pipeline {
    agent any

    environment {
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

        stage('Test') {
            steps {
                script {
                    sh 'sleep 10'

                    // Run unit tests
                    sh './mvnw test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis using shell
                withSonarQubeEnv('SonarQube') {
                    sh './mvnw sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                // Package the application
                sh './mvnw package'
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

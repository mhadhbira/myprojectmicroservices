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

        stage('Run PostgreSQL and Build') {
            steps {
                script {
                    // Run PostgreSQL container in the background
                    bat 'docker run -d --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=rania -e POSTGRES_DB=MyFirstDatabase -p 5432:5432 postgres:13'
                    
                    // Wait for PostgreSQL to start
                    bat 'timeout /t 10'

                    // Now build the Spring Boot application using Windows commands
                    bat './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    bat 'docker start postgres'
                    bat 'timeout /t 10'
                    
                    // Run unit tests
                    bat './mvnw test'
                }
            }
        }

        stage('SonarQube Analysis') {  // Optional step for code quality analysis
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat './mvnw sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                // Package the application
                bat './mvnw package'
            }
        }
    }

    post {
        success {
            echo 'Build and tests succeeded.'
            // Stop PostgreSQL container
            bat 'docker stop postgres'
            // Optionally remove the container
            bat 'docker rm postgres'
        }
        failure {
            echo 'Build or tests failed.'
            // Stop PostgreSQL container
            bat 'docker stop postgres'
            // Optionally remove the container
            bat 'docker rm postgres'
        }
    }
}

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
                    sh 'docker run -d --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=rania -e POSTGRES_DB=MyFirstDatabase -p 5432:5432 postgres:13'

                    // Wait for PostgreSQL to start
                    sh 'sleep 10'

                    // Now build the Spring Boot application using Maven
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'docker start postgres'
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
            // Stop PostgreSQL container
            sh 'docker stop postgres'
            // Optionally remove the container
            sh 'docker rm postgres'
        }
        failure {
            echo 'Build or tests failed.'
            // Stop PostgreSQL container
            sh 'docker stop postgres'
            // Optionally remove the container
            sh 'docker rm postgres'
        }
    }
}

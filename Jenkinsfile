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

        stage('Run PostgreSQL and Build') {
            steps {
                script {
                    // Run PostgreSQL container in the background
                    docker.image('postgres:13').withRun('-e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=rania -e POSTGRES_DB=MyFirstDatabase -p 5432:5432') { postgres ->
                        // Wait for PostgreSQL to start
                        sleep 10

                        // Now build the Spring Boot application
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run PostgreSQL container again for testing
                    docker.image('postgres:13').withRun('-e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=rania -e POSTGRES_DB=MyFirstDatabase -p 5432:5432') { postgres ->
                        // Wait for PostgreSQL to start
                        sleep 10

                        // Run unit tests
                        sh 'mvn test'
                    }
                }
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

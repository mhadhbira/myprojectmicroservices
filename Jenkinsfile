pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK 17'  // Ensure JDK 17 is configured in Jenkins
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    tools {
        maven 'Maven 3.8.4'  // Ensure Maven is installed and configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git url: 'https://github.com/yourusername/your-spring-boot-project.git', branch: 'main'
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

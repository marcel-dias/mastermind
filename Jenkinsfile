#!groovy
pipeline {
    agent any
    tools {
        maven 'MVN'
        jdk 'JDK8'
    }
    environment {
        DEPLOY_FAILURE = false
    }

    stages {

        stage('Code Checkout') {
            steps {
                git 'https://github.com/marceldiass/mastermind.git'
            }
        }
        stage('Build') {
            steps {
                echo "Running ${env.BUILD_ID} for EM"

                sh "mvn clean install"
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Get version') {
            steps {
                pom = readMavenPom file: 'pom.xml'
                echo "Project version ${pom.version}"
            }
        }
        stage('Build Docker Images') {
            steps {
                echo "Building docker images..."
            }
        }
        stage('Run SQL Updates') {
            steps {
                echo "Running SQL Updates..."
            }
        }
        stage('Executing Blue/Green Deployment') {
            steps {
                echo "Read ENV Config..."
                echo "Update Container Version..."
                echo "Execute smoke tests..."
                echo "Monitor application stats..."
            }

            post {
                failure {
                    echo 'Executing rollback'
                    echo 'Notify failure'
                }
            }
        }
        stage('Results') {
            steps {
                echo 'Publishing result'
            }
        }
    }
    post {
        failure {
            echo 'The Pipeline failed :('
        }
        success {
            echo 'Successfully deployed the version X of EM and CAS'
        }
    }
}
#!groovy
pipeline {
    agent any
    tools {
        maven 'MVN'
        java 'JDK8'
    }

    stages {

        stage('Preparation') { // for display purposes
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/marceldiass/mastermind.git'
            }
        }
        stage('Build') {
            steps {
                echo "javaHome == ${javaHome}"

                sh "'${mvnHome}/bin/mvn' clean install"
            }
        }
        stage('Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archive 'target/*.jar'
            }
        }
    }
}
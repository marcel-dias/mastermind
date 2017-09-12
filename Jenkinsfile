#!groovy
pipeline {
    agent any
    stages {
        def mvnHome
        def javaHome
        tools {
            maven 'MVN'
        }
        stage('Preparation') { // for display purposes
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/marceldiass/mastermind.git'
                // Get the Maven tool.
                // ** NOTE: This 'M3' Maven tool must be configured
                // **       in the global configuration.
                mvnHome = tool 'MVN'
                javaHome = tool 'JDK8'
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
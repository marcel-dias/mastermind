#!groovy
pipeline {
    agent any
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
            tools {
                maven 'mvn'
                jdk 'JDK8'
            }
            steps {

                echo "Running ${env.BUILD_ID} for EM"

                sh "mvn clean install"
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Images') {
            environment {
                VERSION = readMavenPom().getVersion().toLowerCase()
            }
            steps {
                echo "Project version ${VERSION}"
                echo "Building docker images..."
                sh "mkdir -p test/target"
                sh "cp Dockerfile test/"
                sh "cp start.sh test/"
                sh "cp target/mastermind.jar test/target/"
                dir('test') {
                    script {
                        docker.build("marceldiass/mastermind-pipe:${VERSION}")
                    }
                }

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

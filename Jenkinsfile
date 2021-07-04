pipeline {
    agent any
    stages {
        stage('Get Project from GIT') {
            steps {
				git "https://github.com/RamdeepSingh/basic.automation.framework.git"
            }
        }
        stage('Clean and Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Run Project') {
            steps {
				sh "mvn test -Dbrowser="$Browser Name""
            }
        }
    }
}
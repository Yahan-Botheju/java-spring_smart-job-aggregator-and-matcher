pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'fetched from GitHub'
            }
        }

        stage('Build') {
            steps {
                bat 'gradlew.bat clean build -x test'
            }
        }
    }

    post {
        success {
            echo 'well done, build completed..!!'
        }
        failure {
            echo 'Build is failed. Please check the logs.'
        }
    }
}
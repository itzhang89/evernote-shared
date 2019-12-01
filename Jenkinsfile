def projectName = 'ShareEvernote'

pipeline {
    agent any

    stages {
        stage('Unit Test') {
            steps {
                sh './gradlew :yinxiang:cleanTest :yinxiang:test --tests \'*\''
            }
        }
        stage('Deploy') {
            steps {
                sh './gradlew :yinxiang:bootRuns'
            }
        }
    }
    post {
        always {
            junit 'yinxiang/build/reports/**/*.xml'
        }
    }
}
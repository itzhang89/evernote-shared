def projectName = 'ShareEvernote'

pipeline {
    agent any

    stages {
        stage('Prepare') {
            git url: 'git@gitee.com:ilovestudy1314/ShareEvernote.git', branch: 'master'
        }
    }

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
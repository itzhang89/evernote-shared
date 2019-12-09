def projectName = 'ShareEvernote'

pipeline {
    agent any

    stages {

//        stage('Prepare') {
//            git url: 'git@gitee.com:ilovestudy1314/ShareEvernote.git', branch: 'master'
//        }

        stage('Unit Test') {
            steps {
                sh './gradlew :yinxiang:cleanTest :yinxiang:test'
            }
        }
        stage('Build Jar') {
            steps {
                echo "start to build jar"
                sh './gradlew :yinxiang:clean :yinxiang:bootJar'
            }
        }
        stage('Deloy In Local') {
            steps {
                echo "start to run springboot"
                sh './script/startup.sh'
            }
        }
    }
    post {
        always {
            junit 'yinxiang/build/reports/**/*.html'
        }
    }
}
def projectName = 'ShareEvernote'

pipeline {
    agent any

    parameters {
        password(name: 'devToken', defaultValue: '', description: 'devToken from evernote')
        string(name: 'noteStoreUrl', defaultValue: '', description: 'noteStoreUrl from evernote')
        password(name: 'dbPassword', defaultValue: '', description: 'database password for postgres')
    }

    stages {

//        stage('Prepare') {
//            git url: 'git@gitee.com:ilovestudy1314/ShareEvernote.git', branch: 'master'
//        }

        stage('Unit Test') {
            steps {
                sh './gradlew :yinxiang:clean :yinxiang:test'
            }
        }
        stage('Build Jar') {
            steps {
                echo "start to build jar"
                sh './gradlew :yinxiang:bootJar'
            }
        }
        stage('Deloy In Local') {
            steps {
                echo "start to run springboot"
                sh './script/service.sh start ${params.devToken} ${params.noteStoreUrl} ${params.dbPassword}'
            }
        }
    }
    post {
        always {
            junit 'yinxiang/build/reports/**/*.html'
        }
    }
}
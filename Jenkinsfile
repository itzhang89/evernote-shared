def projectName = 'ShareEvernote'

pipeline {
    agent any

    stages {

//        stage('Prepare') {
//            git url: 'git@gitee.com:ilovestudy1314/ShareEvernote.git', branch: 'master'
//        }

        stage('Unit Test') {
            steps {
                sh './gradlew :yinxiang:cleanTest :yinxiang:test --tests \'*\' -Dyinxiang.dev-token=${devToken} -Dyinxiang.note-store-url=${noteStoreUrl}'
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
                sh './script/restart.sh'
            }
        }
    }
    post {
        always {
            junit 'yinxiang/build/reports/**/*.html'
        }
    }
}
def projectName = 'ShareEvernote'

pipeline {
    agent any

    stages {

//        stage('Prepare') {
//            git url: 'git@gitee.com:ilovestudy1314/ShareEvernote.git', branch: 'master'
//        }

        stage('Unit Test') {
            steps {
                sh './gradlew :yinxiang:cleanTest :yinxiang:test --tests \'*\''
            }
        }
        stage('Build Jar') {
            steps {
                echo "start to build jar"
//                sh './gradlew :yinxiang:clean :yinxiang:bootJar'
            }
        }
        stage('Deloy In Local') {
            steps {
                echo "start to run springboot"
                sh './gradlew :yinxiang:bootRun --args=\'' +
                        '--yinxiang.dev-token=${devToken} ' +
                        '--yinxiang.note-store-url=${noteStoreUrl} ' +
                        '--spring.datasource.url=${dbUrl} ' +
                        '--spring.datasource.username=${dbUserName} ' +
                        '--spring.datasource.password=${dbPassword}\''
            }
        }
    }
    post {
        always {
            junit 'yinxiang/build/reports/**/*.xml'
        }
    }
}
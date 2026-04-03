pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk21'
    }

    environment {
        PATH = "${tool 'jdk21'}/bin:${tool 'maven3'}/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/cbesmhsyn96/KarateAPITestAut.git', branch: 'main'
            }
        }

        stage('Karate Test') {
            steps {
                script {
                    try {
                        sh "mvn clean test -Dtest=example.TestRun"
                    } catch (Exception e) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

    post {
        always {
            publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/karate-reports',
                    reportFiles: 'index.html',
                    reportName: 'Karate Report'
            ])

            echo 'Pipeline tamamlandı.'
        }
    }
}
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
                    sh "mvn clean test -Dtest=users.TestRun"
                }
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'UNSTABLE',
                    fileIncludePattern: '**/*.json',
                    jsonReportDirectory: 'target/karate-reports',
                    sortingMethod: 'ALPHABETICAL'

            publishHTML(target: [
                    allowMissing         : false,
                    alwaysLinkToLastBuild: true,
                    keepAll              : true,
                    reportDir            : 'target/cucumber-html-reports',
                    reportFiles          : 'overview-features.html',
                    reportName           : 'Cucumber Detailed Report'
            ])

            junit '**/target/surefire-reports/*.xml'

            echo 'Pipeline ve Raporlama tamamlandı.'
        }
    }
}
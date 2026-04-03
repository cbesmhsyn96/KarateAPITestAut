pipeline {
    agent {
        node {
            label 'spot_security'
        }
    }
    tools {
        maven 'maven3'
        jdk 'jdk21'
    }
    stages {
        stage('Karate Test') {
            steps {
                script {
                    try {
                        // -Dtest kısmında kendi Runner sınıf adını yazdığından emin ol
                        sh "mvn test -Dtest=TestRun -s mvnsettings.xml"
                    } catch (Exception e) {
                        // Testler fail olsa bile pipeline'ın devam etmesini sağlar
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }
    post {
        always {
            // HTML Publisher eklentisi yüklü olmalıdır
            publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/karate-reports',
                    reportFiles: 'index.html',
                    reportName: 'Karate Report'
            ])
        }
    }
}
pipeline {
    agent any

    stages {
        stage('Delete Repository'){
            steps{
                sh '''
                    rm -rf /var/jenkins_home/workspace/Back/TechBlog-server || true
                '''
            }
        }
        stage('Checkout') {
            steps {
                // GitLab 레포지토리를 클론하는 단계
                //credentialsId에는 설정해둔 gitlab의 credential을 적어주면 된다.
                sh '''
                    git clone -b main https://github.com/pum005/TechBlog-server
                '''
                sh 'pwd'
            }
        }
        stage('Build Jar'){
            tools {
                // Gradle을 사용할 수 있도록 설정합니다.
                //위에서 설정한 Gradle 이름
                gradle 'Gradle'
            }
            steps {
                // Gradle을 실행하여 Jar 빌드를 수행합니다.
                //프로젝트에서 gradlew가 있는 위치로 이동
                sh '''
                    cd TechBlog-server
                    chmod +x gradlew
                    ./gradlew build
                '''
            }
        }
    }
}

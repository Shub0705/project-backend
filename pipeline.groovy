pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/Shub0705/project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t shubhamdoc0705/project-backend-img:latest
                    docker push shubhamdoc0705/project-backend-img:latest
                    docker rmi shubhamdoc0705/project-backend-img:latest
                    kubectl apply -f ./Deploy/

               '''
            }
        }
    }
}

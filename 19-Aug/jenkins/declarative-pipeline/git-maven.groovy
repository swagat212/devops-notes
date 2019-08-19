pipeline {
    agent any
    
    stages{
      stage('Git Checkout'){
        steps{
          git "https://github.com/javahometech/myweb"
        }
      }
      
      stage('Maven Build'){
        agent { docker 'maven:3-alpine' } 
        steps{
            echo 'Hello, Maven'
            sh 'mvn clean package'
         
        }
       }
    }
}

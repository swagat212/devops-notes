pipeline {
    agent any
    
    stages{
      stage('Git Checkout'){
        steps{
          git "https://github.com/javahometech/myweb"
          
          stash includes: '**', name: 'git-src'
        }
      }
      
      stage('Maven Build'){
        agent { docker 'maven:3-alpine' } 
        steps{
            echo 'Hello, Maven'
            unstash 'git-src'
            sh 'mvn clean package'
         
        }
       }
    }
}

pipeline {
    agent any
    stages{
      stage('Welcome'){
         steps{
           echo "This is declarative pipeline"
         }
      }
    
    }
}

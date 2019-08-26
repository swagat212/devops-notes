pipeline{
    environment{
        PATH = "/opt/maven3/bin:$PATH"
    }
    agent { label 'master'}
    
    stages{
        stage('Git Checkout'){
            steps{
                git url: "https://github.com/javahometech/myweb",
                    branch: "master"
            }
            
        }
		
		stage('Maven Build & Package'){
            steps{
                sh "mvn clean package"
                sh "mv ./target/*.war  ./target/myweb.war"
            }
            
        }

        stage('Deploy-Dev'){
            steps{
                sshagent (credentials: ['tomcat-dev']) {
                    // copy war file to tomcat
                    sh 'scp -o StrictHostKeyChecking=no  ./target/myweb.war ec2-user@172.31.43.90:/opt/tomcat8/webapps/'
                    sh 'ssh ec2-user@172.31.43.90 /opt/tomcat8/bin/startup.sh'
                }
            }
            
        }
        
    }
    
}

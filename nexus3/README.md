## Sonatype Nexus3 
- Nexus is repository Manager
- Nexus is used for storing deployable software components like
    - maven dependencies (jar,war,ear)
    - Docker images
    - Yum repo
    - Nuget for DotNet Applications
    - Other formats
    
``` Note: Other repository manager tools are Jfrog Artifactory```

## Step -1  Installing Nexus on Linux
- System Requirements
    - Java 1.8
    - 2 CPU and 4 GB RAM
- Install Java8

      ```
         sudo yum install -y java-1.8.0-openjdk
      ```
- Download and Configure Nexus
     ```
        cd /opt
        sudo wget https://download.sonatype.com/nexus/3/latest-unix.tar.gz
        sudo tar xf latest-unix.tar.gz
        sudo mv nexus-3.18.1-01/ nexus3
        sudo chown -R ec2-user:ec2-user nexus3/ sonatype-work/
        
     ```
 - Configure Nexus as a Service
    - onen following file
    - ``` vi /opt/nexus3/bin/nexus.rc ```
    - Change above file as followes
      ``` run_as_user="ec2-user" ```
    - Create soft link
     ``` 
         sudo ln -s /opt/nexus3/bin/nexus /etc/init.d/ 
         cd /etc/init.d/
         sudo chkconfig --add nexus
         sudo chkconfig nexus on
         sudo service nexus start
     ```
 - Access Nexus through web browser
    - default port is 8081
    - default username admin
    - Get password from your Linux box location (/opt/sonatype-work/nexus3/admin.password)
    - Follow other steps

## Configure Nexus to store docker images
### Step-1 (Configure Nexus)
- Click on settings
- Click on Repositories
- Click 'Create Repository'
- Select Docker (Hosted)
- For Name --> javahome-docker
- Select HTTP checkbox and put ```8083```
- Select Enable Docker V1 API
- Select Online checkbox
- Click on the button 'Create Rpository'

### Step-2 (Go to the server from where you want to upload docker images
- Got Jenkins (In you case any server from wher you are uploading images)
- ``` sudo vi /etc/docker/daemon.json ```
    ```
        {
          "insecure-registries": [
          "172.31.46.218:8083"
          ], "disable-legacy-registry": true
         }
    ```
 - Restart Docker daemon 
 
    ```sudo service docker restart```
    
### Step-3 Build and Upload images to Nexus
- docker pull alpine:latest
- docker tag alpine:latest 172.31.46.218:8083/javahome:1
- login to nexus from command line
    ``` docker login -u admin -p javahome 172.31.46.218:8083 ```
- docker push 172.31.46.218:8083/javahome:1

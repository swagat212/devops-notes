### Introduction to sonarquber
-----
SonarQube is static code analysis tool, it does following checks and improves code quality.
1. Vulnarability Checks
2. Finds code duplicates
3. It finds defects, like memory leaks, database connection leaks
4. Code coverage nothing but howmuch test cases are written by developers
5. many more 

### SonarQube System Requirements
-----
1. SonarQube in java
2. Java is a dependency for Sonar (Java 8)
3. SonarQube requires 2 cpu and 4GB Ram

### Install Sonar Qube
-----
- Install java
  ```
     sudo yum install -y java-1.8.0-openjdk
  ```
- Install SonarQube7

  ```
    cd /opt
    sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-7.0.zip
    sudo unzip sonarqube-7.0.zip
    sudo mv sonarqube-7.0 sonar7
    sudo chown -R ec2-user:ec2-user sonar7/
    
  ```
  
### Configure SonarQube as Service
- Create the file /etc/init.d/sonar with this content:

```
  #!/bin/sh
  #
  # rc file for SonarQube
  #
  # chkconfig: 345 96 10
  # description: SonarQube system (www.sonarsource.org)
  #
  ### BEGIN INIT INFO
  # Provides: sonar
  # Required-Start: $network
  # Required-Stop: $network
  # Default-Start: 3 4 5
  # Default-Stop: 0 1 2 6
  # Short-Description: SonarQube system (www.sonarsource.org)
  # Description: SonarQube system (www.sonarsource.org)
  ### END INIT INFO

  /usr/bin/sonar $*

```
- Run following commands

```
   sudo ln -s /opt/sonar7/bin/linux-x86-64/sonar.sh /usr/bin/sonar
   sudo chmod 755 /etc/init.d/sonar
   sudo chkconfig --add sonar
```
- Update /opt/sonar7/bin/linux-x86-64/sonar.sh
  Find this line (***#RUN_AS_USER=***) and update as follows
```
   RUN_AS_USER=ec2-user
```
- Start the server

```
  sudo service sonar start
```

### Access sonarqube from web browser

```
  http://your-sonar-ip:9000/
```


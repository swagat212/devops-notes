## Uploading Maven artifacts to Nexus3

### Create Maven Project Step-1 
``` 
mvn archetype:generate \
	-DgroupId=in.javahome \
	-DartifactId=app-one \
	-DarchetypeArtifactId=maven-archetype-quickstart \
	-DinteractiveMode=false

```

### Edit pom.xml as followes Step-2

```
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>in.javahome</groupId>
  <artifactId>app-one</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>app-one</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <distributionManagement>
    <snapshotRepository>
       <id>nexusRepo</id>
       <url>http://172.31.46.218:8081/repository/maven-snapshots/</url>
    </snapshotRepository>

   <repository>
       <id>nexusRepo</id>
       <url>http://1172.31.46.218:8081/repository/maven-releases/</url>
   </repository>
</distributionManagement>
</project>

```

### Configure username/password in settings.xml Step-3

```
   vi /opt/maven3/conf/settings.xml
```

```
   <?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <interactiveMode>true</interactiveMode>

  <pluginGroups>
  </pluginGroups>

  <proxies>
  </proxies>

  <servers>
    <server>
      <id>nexusRepo</id>
      <username>admin</username>
      <password>javahome</password>
    </server>
  </servers>

  <mirrors>
  </mirrors>

  <profiles>
  </profiles>

</settings>

```
### Run maven deploy from your project Step-4

```
  mvn clean deploy
```

## Downloading dependencies from Nexus3
- Create proxy repository in Nexus3
  - Settings --> repositories --> CreateRepository
  - Select maven2(proxy)
  - Name -->  javahome-mvn-proxy
  - Proxy --> RemoteStorage  ```https://repo1.maven.org/maven2/```
  - Create Repository
- Create Group repository in Nexus3
  - Settings --> repositories --> CreateRepository
  - Select maven2(group)
  - add maven-release, maven-snapshot and proxy (javahome-mvn-proxy) to the group
  - Create Repository
- Change settings file as follows
  

- Create new project (app-tw0)
  app-two going to have dependency on app-one
``` 
  mvn archetype:generate \
    -DgroupId=in.javahome \
    -DartifactId=app-two \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false

```
  

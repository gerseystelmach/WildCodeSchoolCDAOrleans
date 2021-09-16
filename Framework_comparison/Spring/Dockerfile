FROM openjdk:11
# NB : openjdk:11 is based on a debian version

# Install Tomcat
RUN mkdir /tmp/install_script
ADD .github/scripts/install_tomcat.sh /tmp/install_script.sh
RUN bash /tmp/install_script.sh

# Install maven
RUN apt-get update && apt-get install -y maven 

# Copy local directory
RUN mkdir /app
WORKDIR /app

ADD pom.xml /app/pom.xml

# Download dependencies before building project
RUN mvn install

# Build war
ADD src /app/src
RUN mvn install

# Run Tomcat
ADD .github/scripts/run_tomcat.sh /app/test_as_shell/run_tomcat.sh
ADD .github/scripts/install_war.sh install_war.sh
RUN bash install_war.sh

CMD bash /opt/tomcat/bin/catalina.sh run

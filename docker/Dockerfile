FROM ubuntu:26.04

#ARG
ARG START_SCRIPT

# env
ENV DEBIAN_FRONTEND=noninteractive
ENV MAVEN_HOME /opt/apache-maven-3.9.12
ENV PATH $MAVEN_HOME/bin:$PATH
WORKDIR /java_rep

# update
RUN apt update

# install
RUN apt install -y wget net-tools htop curl

# jdk install
RUN apt install -y openjdk-25-jdk
RUN apt install -y openjdk-25-jdk curl tar --no-install-recommends && rm -rf /var/lib/apt/lists/*

# maven
RUN curl https://dlcdn.apache.org/maven/maven-3/3.9.12/binaries/apache-maven-3.9.12-bin.tar.gz -o /tmp/apache-maven-3.9.12-bin.tar.gz
RUN tar -xzf /tmp/apache-maven-3.9.12-bin.tar.gz -C /opt
RUN rm /tmp/apache-maven-3.9.12-bin.tar.gz

COPY ./${START_SCRIPT} /usr/local/bin/start
RUN chmod +x /usr/local/bin/start
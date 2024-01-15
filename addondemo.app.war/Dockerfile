FROM tomcat:9-jdk17-temurin-jammy
MAINTAINER Nils Israel <nils@sxda.io>
COPY target/io.sxda.scout.apps.addondemo.war /usr/local/tomcat/webapps/ROOT.war
RUN chown -R www-data:www-data /usr/local/tomcat/webapps
USER www-data
CMD ["catalina.sh", "run"]

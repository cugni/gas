mvn clean:clean
mvn war:war
rm home/ccugnasc/apache-tomcat-7.0.35/webapps/GAS_Roo.war
rm home/ccugnasc/apache-tomcat-7.0.35/webapps/GAS_Roo -R
cp /home/ccugnasc/workspace/gas/GAS_Roo/target/GAS_Roo-0.1.0.BUILD-SNAPSHOT.war /home/ccugnasc/apache-tomcat-7.0.35/webapps/GAS_Roo.war
/home/ccugnasc/apache-tomcat-7.0.35/bin/startup.sh
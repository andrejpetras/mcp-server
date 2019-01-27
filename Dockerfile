FROM jboss/wildfly:15.0.1.Final

# Deploy the application
ADD target/*.war /opt/jboss/wildfly/standalone/deployments/

# Update the database and start the server
CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.bind.address=$(hostname -i) -Djboss.bind.address.private=$(hostname -i)

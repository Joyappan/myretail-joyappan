package com.retail.productdetails.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.support.exception.CassandraConnectionFailureException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.retail.productdetails.config.PropertyReader;
import com.retail.productdetails.exception.ApplicationErrorCode;
import com.retail.productdetails.exception.ProductException;

/**
 * 
 * This class handles Cassandra DB connections for the application. Cassandra
 * template is used in this application for DB interactions.
 * 
 * @author Joyappan
 *
 */
@Component
public class DbConnectionUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionUtil.class);
	
	private static Cluster cluster;
	private static Session session;

	@Autowired
	PropertyReader propertyReader;

	/**
	 * Method to get Cassandra DB connection.
	 * 
	 * @return
	 * @throws ProductException
	 */
	public CassandraOperations getDBConnection() throws ProductException {
		CassandraOperations cassandraOps = null;
		try {
			cluster = Cluster.builder().addContactPoint(propertyReader.getCassandraHostname()).build();
			session = cluster.connect(propertyReader.getCassandraDBname());
			cassandraOps = new CassandraTemplate(session);
			return cassandraOps;
		} catch (CassandraConnectionFailureException e) {
			LOGGER.error("Error while creating cassandra connection : " + e.getMessage());
			throw new ProductException(ApplicationErrorCode.DATABASE_CONNECTIONFAILURE_ERROR,
					"Error while creating DB connection.");
		}

	}

}

package com.retail.productdetails.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This class acts as the property reader and the class variables are referenced
 * by the application directly.
 * 
 * @author Joyappan.
 *
 */
@Component
@PropertySource("classpath:productconfiguration.properties")
public class PropertyReader {

	@Value("${product.service}")
	private String productServiceuri;

	@Value("${product.urlpattern}")
	private String productUrlpattern;

	@Value("${cassandra.hostname}")
	private String cassandraHostname;

	@Value("${cassandra.dbname}")
	private String cassandraDBname;

	@Value("${cassandra.columnlist}")
	private String cassandraColumnlist;

	@Value("${cassandra.tablename}")
	private String cassandraTablename;

	@Value("${cassandra.columnid}")
	private String cassandraColumnid;

	@Value("${resttemplate.productdetailsconnectiontimeout}")
	private String productdetailsConnectionTimeout;

	@Value("${resttemplate.productdetailssockettimeout}")
	private String productdetailsSocketTimeout;

	public String getProductServiceuri() {
		return productServiceuri;
	}

	public void setProductServiceuri(String productServiceuri) {
		this.productServiceuri = productServiceuri;
	}

	public String getProductUrlpattern() {
		return productUrlpattern;
	}

	public void setProductUrlpattern(String productUrlpattern) {
		this.productUrlpattern = productUrlpattern;
	}

	public String getCassandraHostname() {
		return cassandraHostname;
	}

	public void setCassandraHostname(String cassandraHostname) {
		this.cassandraHostname = cassandraHostname;
	}

	public String getCassandraDBname() {
		return cassandraDBname;
	}

	public void setCassandraDBname(String cassandraDBname) {
		this.cassandraDBname = cassandraDBname;
	}

	public String getCassandraColumnlist() {
		return cassandraColumnlist;
	}

	public void setCassandraColumnlist(String cassandraColumnlist) {
		this.cassandraColumnlist = cassandraColumnlist;
	}

	public String getCassandraTablename() {
		return cassandraTablename;
	}

	public void setCassandraTablename(String cassandraTablename) {
		this.cassandraTablename = cassandraTablename;
	}

	public String getCassandraColumnid() {
		return cassandraColumnid;
	}

	public void setCassandraColumnid(String cassandraColumnid) {
		this.cassandraColumnid = cassandraColumnid;
	}

	public String getProductdetailsConnectionTimeout() {
		return productdetailsConnectionTimeout;
	}

	public void setProductdetailsConnectionTimeout(String productdetailsConnectionTimeout) {
		this.productdetailsConnectionTimeout = productdetailsConnectionTimeout;
	}

	public String getProductdetailsSocketTimeout() {
		return productdetailsSocketTimeout;
	}

	public void setProductdetailsSocketTimeout(String productdetailsSocketTimeout) {
		this.productdetailsSocketTimeout = productdetailsSocketTimeout;
	}

}

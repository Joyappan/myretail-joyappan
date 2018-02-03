package com.retail.productdetails.data;

import java.math.BigDecimal;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is POJO mapping for pricing information from DB. This POJO is
 * mapped directly to product_price table.
 * 
 * @author Joyappan
 *
 */
@Table(value = "product_price")
public class ProductPrice {

	@JsonIgnore
	@PrimaryKey
	private int id;
	@Column(value = "regular_price")
	private BigDecimal regularPrice;
	@Column(value = "clearance_price")
	private BigDecimal clearancePrice;
	@Column(value = "price_type")
	private String priceType;
	@Column(value = "currency")
	private String currencyType;
	@JsonIgnore
	@Column(value = "create_time")
	private String createTime;
	@JsonIgnore
	@Column(value = "last_updated_time")
	private String lastUpdatedTime;

	@Column(value = "product_price_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public BigDecimal getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(BigDecimal regularPrice) {
		this.regularPrice = regularPrice;
	}

	public BigDecimal getClearancePrice() {
		return clearancePrice;
	}

	public void setClearancePrice(BigDecimal clearancePrice) {
		this.clearancePrice = clearancePrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}

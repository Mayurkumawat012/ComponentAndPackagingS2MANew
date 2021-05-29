package com.componentxProcessing.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ReturnOrderRequest")
public class ProcessRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "UserName")
	private String userName;

	@Column(name = "ContactNumber")
	private Long contactNumber;

	@Column(name = "CreditCardNumber")
	private String creditCardNumber;

	@Column(name = "IsPriorityRequest")
	private Boolean isPriorityRequest;

	@Column(name = "ComponentType") // integral-repair, accessory-replace
	private String componentType;

	@Column(name = "ComponentName")
	private String componentName;

	@Column(name = "Quantity")
	private Integer quantity;

	public ProcessRequest() {
		super();
	}

	public ProcessRequest(int id, String userName, Long contactNumber, String creditCardNumber, Boolean isPriorityRequest,
			String componentType, String componentName, Integer quantity) {
		super();
		this.id = id;
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.creditCardNumber = creditCardNumber;
		this.isPriorityRequest = isPriorityRequest;
		this.componentType = componentType;
		this.componentName = componentName;
		this.quantity = quantity;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Boolean getIsPriorityRequest() {
		return isPriorityRequest;
	}

	public void setIsPriorityRequest(Boolean isPriorityRequest) {
		this.isPriorityRequest = isPriorityRequest;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProcessRequest [userName=" + userName + ", contactNumber=" + contactNumber + ", creditCardNumber="
				+ creditCardNumber + ", isPriorityRequest=" + isPriorityRequest + ", componentType=" + componentType
				+ ", componentName=" + componentName + ", quantity=" + quantity + "]";
	}

}

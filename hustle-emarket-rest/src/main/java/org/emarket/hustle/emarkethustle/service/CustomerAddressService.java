package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.CustomerAddress;

public interface CustomerAddressService
{
	public List<CustomerAddress> getCustomerAddress();

	public List<CustomerAddress> getCustomerAddressByCustomerId(int id);

	public CustomerAddress getCustomerAddressById(int id);

	public void saveCustomerAddress(CustomerAddress customerDetail);

	public void deleteCustomerAddressById(int id);
}

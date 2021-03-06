package service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Customer;
import repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Set<Customer> getAllCustomers() {
		LOG.debug("get all customers");
		HashSet<Customer> result = new HashSet<Customer>(customerRepository.findAll());
		LOG.debug("{} results", result.size());
		return result;
	}
	
	@Override
	public void insertCustomer(Customer customer) {
		LOG.debug("insert customer = {}", customer);
		customerRepository.save(customer);
		LOG.debug("insert completed");
	}

	@Override
	public void updateCustomer(Customer customer) {
		LOG.debug("update customer={}", customer);
		customerRepository.save(customer);
		LOG.debug("update completed");
	}
	
	@Override
	public void deleteCustomer(BigDecimal custNum) {
		LOG.debug("delete customer by CustNum = {}", custNum);
		customerRepository.deleteById(custNum);
		LOG.debug("delete customer completed");
	}
	
	@Override
	public Customer findCustomerById(BigDecimal id) {
		LOG.debug("Find Customer by id={}", id);
		Customer result = customerRepository.findById(id).orElse(null);
		LOG.debug("Find Customer by id result={}", result);
		return result;
	}

	@Override
	public Set<Customer> getCustomersCreditLimitMoreThan(BigDecimal creditLimit) {
		LOG.debug("Getting Customers wtih Credit Limit > " + creditLimit);
		HashSet<Customer> result = new HashSet<Customer>(customerRepository.findByCreditLimitIsGreaterThan(creditLimit));
		LOG.debug("{} results", result.size());
		return result;
	}

}

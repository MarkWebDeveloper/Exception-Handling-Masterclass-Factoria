package dev.mark.exception_handling.customer;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRespository;

    public Customer getCustomer(Long id){

        return customerRespository.findById(id).orElseThrow(() -> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + id));
    }

    public Customer getCustomerChecked(Long id) throws NoSuchCustomerExistsCheckedException {

        return customerRespository.findById(id).orElseThrow(() -> new NoSuchCustomerExistsCheckedException("NO CUSTOMER PRESENT WITH ID = " + id));
    }

    public String addCustomer(Customer customer)
    {
        Customer existingCustomer = customerRespository.findById(customer.getId()).orElse(null);
        
        if (existingCustomer == null) {
            customerRespository.save(customer);
            return "Customer added successfully";
        } else
            throw new CustomerAlreadyExistsException("Customer already exists!!");
    }

    public String updateCustomer(Customer customer){
        Customer existingCustomer = customerRespository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null)
            throw new NoSuchCustomerExistsException("No Such Customer exists!!");
        else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(
                customer.getAddress());
            customerRespository.save(existingCustomer);
            return "Record updated Successfully";
        }
    }
}
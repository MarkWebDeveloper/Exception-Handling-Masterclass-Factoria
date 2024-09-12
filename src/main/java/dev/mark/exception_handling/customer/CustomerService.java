package dev.mark.exception_handling.customer;

public interface CustomerService {

    Customer getCustomer(Long id);

    String addCustomer(Customer customer);

    String updateCustomer(Customer customer);
}
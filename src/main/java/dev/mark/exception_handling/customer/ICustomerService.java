package dev.mark.exception_handling.customer;

public interface ICustomerService {

    Customer getCustomer(Long id);

    Customer getCustomerChecked(Long id) throws NoSuchCustomerExistsCheckedException;

    String addCustomer(Customer customer);

    String updateCustomer(Customer customer);
}
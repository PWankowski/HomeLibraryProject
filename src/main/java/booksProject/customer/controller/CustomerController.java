package booksProject.customer.controller;


import booksProject.customer.dto.CustomerForm;
import booksProject.customer.service.CustomerService;
import booksProject.customer.NoCustomerFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{uuid}")
    public ResponseEntity getCustomer(@PathVariable String uuid) {

         return ResponseEntity.ok(customerService.getCustomer(uuid));
    }
    @GetMapping("/customer/all")
    public ResponseEntity getAllCustomers() {

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/customer/create")
    public ResponseEntity createCustomer(@RequestBody CustomerForm customerForm) {

        return ResponseEntity.ok(customerService.create(customerForm));
    }

    @PutMapping("/customer/update/{uuid}")
    public ResponseEntity updateCustomer(@RequestBody CustomerForm customerForm, @PathVariable String uuid) {

        return ResponseEntity.ok(customerService.update(uuid, customerForm));
    }

    @DeleteMapping("/customer/delete/{uuid}")
    public ResponseEntity deleteCustomer(@PathVariable String uuid) {
        try{
            customerService.delete(uuid);
            return ResponseEntity.ok(String.format("Customer with uuid: %s deleted", uuid));
        } catch (Exception e){
            return new ResponseEntity(String.format("Customer with uuid: %s wasn't deleted", uuid), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = NoCustomerFoundException.class)
    public ResponseEntity handleNoCustomerFoundException(NoCustomerFoundException exception) {
        return  new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}

package springcourse.kursspringboot2dto;

public class Order {
    private Customer customer;
    private Address address;

    public Order(Customer customer, Address address) {
        this.customer = customer;
        this.address = address;
    }

    public Order() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

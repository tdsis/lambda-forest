package br.com.tdsis.lambda.forest.http.handler;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserRequestTest {

    public static final String INVALID_NAME_MESSAGE = "name should be valid";
    public static final String INVALID_ADDRESS_MESSAGE = "address should not be empty";
    
    @Size(min=1, max=50, message=INVALID_NAME_MESSAGE)
    private String name;
    
    @NotBlank(message=INVALID_ADDRESS_MESSAGE)
    private String address;

    public UserRequestTest() {
        
    }
        
    public UserRequestTest(String name,String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

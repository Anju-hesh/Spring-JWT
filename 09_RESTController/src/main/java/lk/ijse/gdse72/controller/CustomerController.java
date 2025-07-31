package lk.ijse.gdse72.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.gdse72.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
//@MultipartConfig
public class CustomerController {

    @PostMapping
    public String saveCustomer(@ModelAttribute CustomerDTO customerDTO){
        System.out.println(customerDTO.getCID() +" " + customerDTO.getCName() +" " + customerDTO.getCAddress());
        return "Customer Saved";
    }

//    @PostMapping
//    public String saveCustomer(CustomerDTO customerDTO){
//        System.out.println(customerDTO.getCID() +" " + customerDTO.getCName() +" " + customerDTO.getCAddress());
//        return "Customer Saved";
//    }

    //Query Parameters
    @PostMapping(params = {"CID", "CName", "CAddress"})
    public String saveCustomerQuery(@RequestParam("CID") String cid,
                                   @RequestParam("CName") String name,
                                   @RequestParam("CAddress") String address){
        System.out.println(cid + " " + name + " " + address);
        return "Customer Saved with Query Parameters";
    }

    //Path Variables
    @PostMapping(path = "path/{CID}/{CName}/{CAddress}/{CCity}")
    public String saveCustomerPath(@PathVariable("CID") String cid,
                                   @PathVariable("CName") String name,
                                   @PathVariable("CAddress") String address,
                                   @PathVariable("CCity") String city){
        System.out.println(cid + " \n" + name + " \n" + address +" \n" + city);
        return "Customer Saved with Path Variables";
    }

    //JSON Body
    @PostMapping(path = "json" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomerJson(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO.getCID() +" " + customerDTO.getCName() +" " + customerDTO.getCAddress());
        return "Customer Saved with JSON Body";
    }

    //Return JSON Response
    @GetMapping(path = "jsonReturn", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomerJson() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCID("C001");
        customerDTO.setCName("Anjana Heshan");
        customerDTO.setCAddress("Digala , Ganegoda");

        System.out.println("Returning Customer JSON" + customerDTO.getCID() + " " + customerDTO.getCName() + " " + customerDTO.getCAddress());
        return customerDTO;
    }
}



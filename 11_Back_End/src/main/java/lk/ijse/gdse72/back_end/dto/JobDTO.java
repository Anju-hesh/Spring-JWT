//package lk.ijse.gdse72.back_end.dto;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//
//public class JobDTO {
//
//    private int id;
//
//    @NotBlank(message = "Job title cannot be empty")
//    private String jobTitle;
//
//    @NotBlank(message = "Company name cannot be empty")
////    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Company name can only contain letters, numbers, and spaces")
//    private String company;
//
//    @NotBlank(message = "Location cannot be empty")
////    @Pattern(regexp = "^[a-zA-Z0-9, ]+$", message = "Location can only contain letters, numbers, commas, and spaces")
//    private String location;
//
//    @NotBlank(message = "Job type cannot be empty")
//    private String type;
//
//    @NotBlank(message = "Job description cannot be empty")
//    private String jobDescription;
//
//    @NotBlank(message = "Status cannot be empty")
////    @Pattern(regexp = "^(Active|Inactive)$", message = "Status must be either 'Active' or 'Inactive'")
//    private String status;
//}


package lk.ijse.gdse72.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {

    private int id;

    @NotBlank(message = "Job title cannot be empty")
    private String jobTitle;

    @NotBlank(message = "Company name cannot be empty")
//    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Company name can only contain letters, numbers, and spaces")
    private String company;

    @NotBlank(message = "Location cannot be empty")
//    @Pattern(regexp = "^[a-zA-Z0-9, ]+$", message = "Location can only contain letters, numbers, commas, and spaces")
    private String location;

    @NotBlank(message = "Job type cannot be empty")
    private String type;

    @NotBlank(message = "Job description cannot be empty")
    private String jobDescription;

    @NotBlank(message = "Status cannot be empty")
//    @Pattern(regexp = "^(Active|Inactive)$", message = "Status must be either 'Active' or 'Inactive'")
    private String status;
}

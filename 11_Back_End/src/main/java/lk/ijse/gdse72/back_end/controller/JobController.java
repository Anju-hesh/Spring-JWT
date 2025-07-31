package lk.ijse.gdse72.back_end.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse72.back_end.dto.JobDTO;
import lk.ijse.gdse72.back_end.service.JobService;
import lk.ijse.gdse72.back_end.util.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
@CrossOrigin

@Slf4j
public class JobController {

//    @PostMapping("created")
//    public String createJob(){
//        return "Job Created";
//    }
//
//    @GetMapping("getall")
//    public String getAllJob(){
//        return "Job Controller";
//    }
//
//    @PutMapping("update")
//    public String updateJob(){
//        return "Job Updated";
//    }
//
//    @PutMapping("changestaus")
//    public String changeJobStatus(){
//        return "Job Deleted";
//    }
//
//    @GetMapping("search")
//    public String searchJob(){
//        return "Job Search";
//    }

    private final JobService jobService;
//    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @PostMapping("create")
    public ResponseEntity<APIResponse<String>> createJob(@RequestBody @Valid JobDTO jobDTO) {
//        System.out.println(jobDTO.getJobTitle() + " \n" + jobDTO.getCompany() + " \n" + jobDTO.getLocation() + " \n" + jobDTO.getType() + " \n" + jobDTO.getJobDescription());

//        logger.info("Job Created Successfully");    // businessLogics -> informations
//        logger.debug("Job Details: {}", jobDTO);    // details of debugging
//        logger.error("Job Creation Failed");        // system errors
//        logger.trace("Job Creation Trace");         // data tracing
//        logger.warn("Job Creation Warning");        // warnings

        log.trace("Creating job with details:");
        log.info("Job creation request received for job title:");
        log.debug("Job details: {}", jobDTO);
        log.error("An error occurred while creating job: ");
        log.warn("Job creation Warning");

        jobService.saveJob(jobDTO);
//        return "Job Created";
        return new ResponseEntity(new APIResponse<>(
                200,
                "Job Created Successfully",
                null
        ), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse<Object>> updateJob(@Valid @RequestBody JobDTO jobDTO) {
        jobService.updateJob(jobDTO);
//        return "Job Updated";
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Updated Successfully",
                null));
    }

    @GetMapping("getall")
    public ResponseEntity<APIResponse<List<JobDTO>>> getAllJob() {
        List<JobDTO> jobDTOS = jobService.getAllJobs();
//        return jobDTOS;
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "All Jobs Retrieved Successfully",
                jobDTOS
        ));
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<APIResponse<String>> changeJobStatus(@Valid @PathVariable int id) {
        jobService.changeJobStatus(id);
//        return "Job Status Updated for ID: " + id;
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Status Updated Successfully for ID: " + id,
                null
        ));
    }

    @GetMapping("search/{id}")
    public ResponseEntity<APIResponse<List<JobDTO>>> searchJob(@Valid @PathVariable String id) {
        List<JobDTO> jobDTOS = jobService.getAllJobsByKeyWord(id);
//        return jobDTOS;
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job Search Results for Keyword: " + id,
                jobDTOS
        ));
    }

    @GetMapping("paging")
    public Page<JobDTO> getAllJobsWithPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int per_page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return jobService.getAllJobsWithPaging(page, per_page, keyword, direction, sort);
    }
}
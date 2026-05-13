package com.exercise.jobapp.review;

import com.exercise.jobapp.company.Company;
import com.exercise.jobapp.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;
    private final CompanyService companyService;

    public ReviewController(ReviewService reviewService, CompanyService companyService) {
        this.reviewService = reviewService;
        this.companyService = companyService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        Company company = companyService.getCompanyById(companyId);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewed = reviewService.addReview(companyId, review);

        if (isReviewed){
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed to add review", HttpStatus.BAD_REQUEST);
    }
}

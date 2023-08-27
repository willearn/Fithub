package com.fithub.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fithub.model.course.Course;
import com.fithub.model.course.ICourseService;


@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

	@Autowired
	private ICourseService cService;

	@Autowired
	private final ResourceLoader resourceLoader;

	public CourseController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@GetMapping("/{cid}")
	public ResponseEntity<?> findCourse(@PathVariable("cid") int cid) {
		try {
			Course resultBean = cService.findById(cid);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCourses() {
		try {
			List<Course> resultList = cService.findAll();
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> insertCourse(@RequestBody Course cBean,
			@RequestParam(required = false) MultipartFile photoContent) {
		System.out.println(cBean);
		try {
			Course resultBean = cService.insert(cBean);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		}   catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 

	}

	@PutMapping("/{cid}")
	public ResponseEntity<?> updateCourse(@RequestBody Course cBean) {
		try {
			Boolean resultBoolean = cService.update(cBean);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCourse(@PathVariable("cid") int cid) {
		try {
			Boolean resultBoolean = cService.deleteById(cid);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteMultiple")
	public ResponseEntity<?> deleteMultipleCourses(@RequestBody List<Integer> cids) {
		try {
			cService.deleteAllById(cids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/uploadImg")
	public ResponseEntity<?> uploadImg(@RequestParam(required = false) MultipartFile photoContent) {
		String imagePath = "C:/SpringBoot/workspace/Fithub/src/main/resources/static/images/course/";
		try {
			if (photoContent != null) {
				String imageFileName = UUID.randomUUID().toString()+ ".jpg";
				File destinationFile = new File(imagePath+imageFileName);
				photoContent.transferTo(destinationFile);

				return new ResponseEntity<>(imageFileName, HttpStatus.OK);
			}
		} catch (IOException  e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/img/{cid}")
	public ResponseEntity<?> getCourseImg(@PathVariable("cid") int cid) {
		Course resultBean = cService.findById(cid);
		String imagePath = "C:/SpringBoot/workspace/Fithub/src/main/resources/static/images/course/";
		String imageName = resultBean.getCourseImgPath();
		try {
			String base64Image = convertImageToBase64(imagePath+imageName);
            System.out.println("Base64 Image: " + base64Image);
			return new ResponseEntity<>(base64Image, HttpStatus.OK);

		} catch (IOException  e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 

	}
	
	
    private String convertImageToBase64(String imagePath) throws IOException {
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            throw new IOException("Image file does not exist.");
        }

        try (FileInputStream imageInputStream = new FileInputStream(imageFile)) {
            byte[] imageBytes = new byte[(int) imageFile.length()];
            imageInputStream.read(imageBytes);

            // Convert image bytes to Base64
            return Base64.getEncoder().encodeToString(imageBytes);
        }
    }
	

}

package com.fithub.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
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
@CrossOrigin(exposedHeaders = {"total-pages","number-of-elements"})
public class CourseController {

	@Autowired
	private ICourseService cService;
	
	private String imagePath = "C:/SpringBoot/workspace/Fithub/src/main/resources/static/images/course/";
//	String imagePath = "classpath:\\static/images/course/";


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
	
	// Author : Chrislafolia 全部課程分頁功能
	@GetMapping("/page")
	public ResponseEntity<?> showAllCoursesInPage(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
			@RequestParam(name="size",defaultValue = "6") Integer dataSize) {
		try {
			// course data 放body
			Page<Course> page = cService.findByPage(pageNumber,dataSize);
			List<Course> courseResultList=page.getContent();			
			
			// TotalPages, numberOfElements 放header 
			MultiValueMap<String, String> mvm=new LinkedMultiValueMap<>();
			mvm.add("total-pages", Integer.toString(page.getTotalPages()));
			mvm.add("number-of-elements",Integer.toString(page.getNumberOfElements()));
			
			return new ResponseEntity<>(courseResultList, mvm, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// Author : Chrislafolia 各分類課程分頁功能
	@GetMapping("/page/{categoryid}")
	public ResponseEntity<?> showCoursesInPage(@PathVariable("categoryid") int categoryid,@RequestParam(name="p",defaultValue = "1") Integer pageNumber,
			@RequestParam(name="size",defaultValue = "6") Integer dataSize) {
		try {
			// course data 放body
			Page<Course> page = cService.findCourseByCategoryId(categoryid,pageNumber,dataSize);
			List<Course> courseResultList=page.getContent();			
			
			// TotalPages, numberOfElements 放header 
			MultiValueMap<String, String> mvm=new LinkedMultiValueMap<>();
			mvm.add("total-pages", Integer.toString(page.getTotalPages()));
			mvm.add("number-of-elements",Integer.toString(page.getNumberOfElements()));
			
			return new ResponseEntity<>(courseResultList, mvm, HttpStatus.OK);
			
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

	@PutMapping
	public ResponseEntity<?> updateCourse(@RequestBody Course cBean) {
		System.out.println("I am here to put");
		System.out.println(cBean.getCourseName());
		System.out.println(cBean.getCourseDescription());
		try {
			Boolean resultBoolean = cService.update(cBean);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCourse(@PathVariable("cid") int cid) {
		try {
			Course resultBean = cService.findById(cid);
			File destinationFile = new File(imagePath+resultBean.getCourseImgPath());
			
			// Delete the img file
			if (destinationFile.exists()) {
			    destinationFile.delete();
			}
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
	
	@PutMapping("/uploadImg")
	public ResponseEntity<?> uploadUpdateImg(@RequestParam(required = false) MultipartFile photoContent,
			@RequestParam(value="courseImgPath") String courseImgPath) {
		
		// ================== 資料更新後不再使用.length()<20 
		if (courseImgPath.length()<20 || courseImgPath==null) {
			courseImgPath = UUID.randomUUID().toString()+ ".jpg";
		}
		// ==================
		try {
			if (photoContent != null) {
				File destinationFile = new File(imagePath+courseImgPath);
				
				// Delete the exist img file
				if (destinationFile.exists()) {
				    destinationFile.delete();
				}
				photoContent.transferTo(destinationFile);

				return new ResponseEntity<>(courseImgPath, HttpStatus.OK);
			}
		} 
		catch (IOException  e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}
	
	// 清理image Course資料夾 刪除未使用檔案
	@DeleteMapping("/deleteImg")
	public ResponseEntity<?> deleteCourseImgNoUse() {
        File folder = new File(imagePath);

        // 確保指定path存在且是一個目錄
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                List<Course> resultList = cService.findAll();

                for (File file : files) {
                    if (file.isFile()) {
                        boolean foundInResultList = false;
                        String fileName = file.getName();

                        for (Course course : resultList) {
                            if (course.getCourseImgPath().equals(fileName)) {
                                foundInResultList = true;
                                break;
                            }
                        }

                        if (!foundInResultList) {
                            file.delete(); // 删除不在resultList中的文件
                        }
                    }
                }
            } else {
                System.out.println("No files in the folder.");
            }
        } else {
            System.out.println("Invalid folder path.");
        }
		return new ResponseEntity<>("Course folder clean!!!", HttpStatus.OK);

	}

	@GetMapping(value = "/getImg", produces = "image/*")
	public ResponseEntity<?> getCourseImg(@RequestParam int cid) {
		Course resultBean = cService.findById(cid);
		String imageName = resultBean.getCourseImgPath();
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(ResourceUtils.getFile("classpath:\\static/images/course/"+imageName)));
			byte[] imgContent = bis.readAllBytes();
			bis.close();
			return new ResponseEntity<>(imgContent, HttpStatus.OK);

		} catch (IOException  e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 

	}
	
	
	
	// Author : Chrislafolia 目前未使用
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

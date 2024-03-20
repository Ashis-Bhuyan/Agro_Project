package com.jsp.Argo_6.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Argo_6.entity.Image;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.repo.ImagesRepo;

@Repository
public class ImagesDao {

	@Autowired
	private ImagesRepo repo;
	
	@Autowired
	private UserDao userDao;

//	********UPLOAD IMAGE***************

	public Image saveImage(Image image) {
		return repo.save(image);
	}

//	************ FETCH IMAGE *************
	public Image fetchImageById(int id) {
		Optional<Image> db = repo.findById(id);
		if (!db.isEmpty()) {
			return db.get();
		} else {
			return null;
		}
	}

	// update Image
		public Image updateById(int id, MultipartFile file ) throws IOException {
			 Image image = new Image();
			 image.setData(file.getBytes());
			 image.setName(file.getOriginalFilename());
			 Optional<Image> db = repo.findById(id);
			 Image data = db.get();
			 if(db.isPresent()) {
				 if(image.getId()==0) {
					 image.setId(data.getId());
				 }
				 if(image.getData()==null) {
					 image.setData(data.getData());
				 }
				 if(image.getName()==null) {
					 image.setName(data.getName());
				 }
				 return repo.save(image);
			 }else {
				 return null;
			 }
		}
		
		//deleteImage
		public String deleteImageById(int id) {
			Optional<Image> db = repo.findById(id);
			if(db.isPresent()) {
				repo.deleteById(id);
				return "Data delete Successfully";
			}else {
			
				return null;
			}
		}

}

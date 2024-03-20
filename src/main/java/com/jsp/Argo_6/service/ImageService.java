package com.jsp.Argo_6.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Argo_6.dao.ImagesDao;
import com.jsp.Argo_6.dao.UserDao;
import com.jsp.Argo_6.entity.Image;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.exception.ImageUploadException;
import com.jsp.Argo_6.exception.UserNotFoundException;
import com.jsp.Argo_6.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImagesDao dao;

	@Autowired
	private UserDao userDao;

	//SaveImage
		public Image imageSave(int id,MultipartFile file) throws IOException {
			User userdb = userDao.fetchUserById(id);
			if(userdb!=null) {
				Image image = new Image();
				image.setData(file.getBytes());
				image.setName(file.getOriginalFilename());
				Image data = dao.saveImage(image);
				if(data!=null) {
					userdb.setImage(data);
					userDao.updateUser(userdb);
					return dao.saveImage(image);
	        	 }
	         }
			throw new UserNotFoundException(id+" : is not present");
		}
	// Fetching image
	
	public ResponseEntity<byte[]> fetchById(int id) {
		Image db = dao.fetchImageById(id);
		if (db != null) {
			byte[] imagedata = db.getData();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagedata);
		} else {
			throw new ImageUploadException(id + ":is not Present");
		}
	}

	//update image
		public ResponseEntity<ResponseStructure<Image>> updateById( int id,MultipartFile file) throws IOException {
			 Image update = dao.fetchImageById(id);
			 System.out.println(update);
			 if(update !=null) {
				Image db = dao.updateById(id,file);
				ResponseStructure<Image> m = new ResponseStructure<Image>();
				m.setData(db);
				m.setMessage("Image Updated successfully.....");
				m.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.OK) ;
			 }
			 throw new UserNotFoundException(id+" : is not present");
		}
	
	
		//deleteImageById
		public String deleteImageById(int id) {
			Image imageDelete = dao.fetchImageById(id);
			List<User> userAll = userDao.fetchAll();
			for (Iterator iterator = userAll.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				Image ud = user.getImage();
				if(ud!=null) {
					if(ud.getId() == id) {
						user.setImage(null);
						userDao.updateUser(user);
					}
				}
			}
			if(imageDelete==null) {
				 throw new ImageUploadException(id+" : is not present");
			 }
			return dao.deleteImageById(id);
		}
}

package com.jsp.Argo_6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.Argo_6.dao.EquipmentDao;
import com.jsp.Argo_6.dao.UserDao;
import com.jsp.Argo_6.entity.Equipments;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.exception.EmailNotSendException;
import com.jsp.Argo_6.exception.EquipmentNotFound;
import com.jsp.Argo_6.exception.UserNotFoundException;
import com.jsp.Argo_6.util.ResponseStructure;

@Service
public class EquipmentsService {

	@Autowired
	private EquipmentDao eDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Equipments>> saveEquipment(Equipments equipment, int uid) {
		User userdata = userDao.fetchUserById(uid);
		if (userdata != null) {
			equipment.setUser(userdata);
			Equipments equipmentdata = eDao.saveEquipment(equipment);
			ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
			m.setData(equipmentdata);
			m.setMessage("Equipments saved.....");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.CREATED);
		}
		throw new UserNotFoundException(uid + " : is not present");
	}

	public ResponseEntity<ResponseStructure<Equipments>> deleteEquipments(int id) {

		Equipments db = eDao.deleteEquipmentById(id);
		if (db != null) {

			ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
			m.setData(db);
			m.setMessage("Equipments  deleted successfully......!");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("user not found" + id);
		}

	}

	// fetch by id
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(int id) {
		Equipments db = eDao.fetchById(id);
		ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
		if (db != null) {
			m.setData(db);
			m.setMessage("fetching equipments by id ");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.OK);
		} else {
			throw new UserNotFoundException(id + ":is not Present");
		}
	}

	// fetch by all
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByAll() {
		List<Equipments> db = eDao.fetchByAll();
		ResponseStructure<List<Equipments>> m = new ResponseStructure<List<Equipments>>();
		if (db != null) {
			m.setData(db);
			m.setMessage("Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(m, HttpStatus.OK);
		}
		throw new UserNotFoundException("user not found");
	}

//		fetch by name

	public ResponseEntity<ResponseStructure<Equipments>> fetchEquipmentByName(String name) {
		Equipments edb = eDao.fetchEquipmentByName(name);
		if (edb != null) {
			ResponseStructure<Equipments> s = new ResponseStructure<Equipments>();
			s.setData(edb);
			s.setMessage("equipment found successfully");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(s, HttpStatus.FOUND);
		} else {
			throw new EquipmentNotFound("No equipment for your search:" + name);
		}
	}

//		 fetch by user id

	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByUser(int user_id) {
		User udb = userDao.fetchUserById(user_id);
		if (udb != null) {
			List<Equipments> edb = eDao.fetchEquipmentByUser(udb);
			ResponseStructure<List<Equipments>> s = new ResponseStructure<List<Equipments>>();
			s.setData(edb);
			s.setMessage("user equipments fetched successfully");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(s, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("No user for your search:" + user_id);
		}
	}

//	update

	public ResponseEntity<ResponseStructure<Equipments>> update(int id, Equipments equipment) {

		Equipments update = eDao.updateEquipmentData(equipment, id);
		if (update != null) {
			ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();

			m.setData(update);
			m.setMessage("Equipment data updated successfully.........");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.OK);
		}
		throw new EquipmentNotFound(equipment.getId() + " : is not present");

	}

}

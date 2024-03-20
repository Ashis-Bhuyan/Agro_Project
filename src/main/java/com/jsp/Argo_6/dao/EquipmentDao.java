package com.jsp.Argo_6.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Argo_6.entity.Equipments;
import com.jsp.Argo_6.entity.User;
import com.jsp.Argo_6.repo.EquipmentRepo;
import com.jsp.Argo_6.repo.UserRepo;

@Repository
public class EquipmentDao {
	@Autowired
	private EquipmentRepo Erepo;
	@Autowired
	private UserRepo urepo;

//	save
	public Equipments saveEquipment(Equipments equipments) {
		return Erepo.save(equipments);
	}

//	 delete
	public Equipments deleteEquipmentById(int id) {
		Optional<Equipments> db = Erepo.findById(null);
		if (db.isPresent()) {
			Erepo.deleteById(null);
			;
			return db.get();
		} else {
			return null;
		}
	}

	// fetchById
	public Equipments fetchById(int id) {
		Optional<Equipments> db = Erepo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}

	// fetch All
	public List<Equipments> fetchByAll() {
		return Erepo.findAll();
	}

	//fetch by equipment Name
	 public Equipments fetchEquipmentByName(String name) {
		    return Erepo.fetchEquipmentByName(name);
		  }


	//fetch by user id
	 	
	 public List<Equipments> fetchEquipmentByUser(User user) {
		    return Erepo.fetchEquipmentByUser(user);
		  }
		

//	 update
	 public Equipments  updateEquipmentData(Equipments equipment, int id) {
			Optional<Equipments> db = Erepo.findById(id);
			if(db.isPresent()) {
				 Equipments data = db.get();
				if(equipment.getName()==null) {
					equipment.setName(data.getName());
				}
				if(equipment.getCostPerHour()==0.0) {
					equipment.setCostPerHour(data.getCostPerHour());
				}
				if(equipment.getQuantity()== 0) {
					equipment.setQuantity(data.getQuantity());
				}
				if(equipment.getUser()==null) {
					equipment.setUser(data.getUser());
				}
				return Erepo.save(equipment);
			}

			return null;
		

	}

	

}

package com.jsp.Argo_6.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Argo_6.entity.Equipments;
import com.jsp.Argo_6.entity.User;



public interface EquipmentRepo extends JpaRepository<Equipments, Integer>{
	
	
	@Query("select a from Equipments a where a.name = ?1")

	public abstract List<Equipments> fetchEquipmentByUser(User user);

	public abstract Equipments fetchEquipmentByName(String name);
}


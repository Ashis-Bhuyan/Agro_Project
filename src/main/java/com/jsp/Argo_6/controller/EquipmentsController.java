package com.jsp.Argo_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Argo_6.entity.Equipments;
import com.jsp.Argo_6.service.EquipmentsService;
import com.jsp.Argo_6.util.ResponseStructure;

@RestController
public class EquipmentsController {
	
	@Autowired
	private EquipmentsService eservice;
	
	@PostMapping("/saveeq")
	public ResponseEntity<ResponseStructure<Equipments>> saveEquipment(@RequestParam int uid,@RequestBody Equipments equipments){
		return eservice.saveEquipment(equipments, uid);
	}
	
	 //fetch by id
	
		@GetMapping("/fetcheq")
		public ResponseEntity<ResponseStructure<Equipments>> fetchById(@RequestParam int id){	
			return eservice.fetchById(id);
		}
		
		// fetch By all
		
		@GetMapping("/fetchalleq")
		public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByAll(){	
			return eservice.fetchByAll();
		}
		
//		fetch by name
		
		@GetMapping("/fetchbyname")
		public ResponseEntity<ResponseStructure<Equipments>> fetchByName(@RequestParam String name){
			return eservice.fetchEquipmentByName(name);
		}
		
//		fetch by user id
		
		  @GetMapping("/equ")
		  public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByUser(@RequestParam int user_id){
		    return eservice.fetchEquipmentByUser(user_id);
		  }
	
	@DeleteMapping("/deleteeq")
	public ResponseEntity<ResponseStructure<Equipments>> deleteEquipmentById(@RequestParam int id) {
		return eservice.deleteEquipments(id);

	}
	
	@PutMapping("/updateeq")
	public ResponseEntity<ResponseStructure<Equipments>> updateEquipments(@RequestBody Equipments equipments,@RequestParam int id) {
		return eservice.update(id, equipments);

	}
	
	

}

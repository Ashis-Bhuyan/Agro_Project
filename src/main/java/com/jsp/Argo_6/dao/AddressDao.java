package com.jsp.Argo_6.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Argo_6.entity.Address;

import com.jsp.Argo_6.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address address) {
		return repo.save(address);

	}

	public Address updateAddress(Address address) {
		Optional<Address> db = repo.findById(address.getId());

		if (db.isPresent()) {
			Address d = db.get();
			if (address.getHouseNo() != null)
				d.setHouseNo(address.getHouseNo());

			if (address.getStreet() != null)
				d.setStreet(address.getStreet());

			if (address.getMandal() != null)
				d.setMandal(address.getMandal());

			if (address.getDistrict() != null)
				d.setDistrict(address.getDistrict());
			
			if (address.getState() != null)
				d.setState(address.getState());;
				
			if(address.getPinCode() != 0)
				d.setPinCode(address.getPinCode());
			
			return repo.save(d);
			

		} else {
			return null;
		}

	}

}

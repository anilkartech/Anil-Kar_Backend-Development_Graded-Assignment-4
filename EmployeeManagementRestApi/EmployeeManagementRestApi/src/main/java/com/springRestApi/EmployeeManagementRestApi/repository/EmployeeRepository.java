package com.springRestApi.EmployeeManagementRestApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springRestApi.EmployeeManagementRestApi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public List<Employee> findByfirstName(String firstName);

}

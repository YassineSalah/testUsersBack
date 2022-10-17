package com.example.machinestalk.dao;

import com.example.machinestalk.model.UserMachinestalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMachinestalkDao extends JpaRepository<UserMachinestalk, Integer> {

}

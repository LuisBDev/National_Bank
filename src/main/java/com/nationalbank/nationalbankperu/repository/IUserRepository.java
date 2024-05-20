package com.nationalbank.nationalbankperu.repository;


import com.nationalbank.nationalbankperu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByNumIdentification(String numIdentification);

}

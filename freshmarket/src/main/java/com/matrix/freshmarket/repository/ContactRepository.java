package com.matrix.freshmarket.repository;

import com.matrix.freshmarket.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends JpaRepository<ContactEntity, Long> {
}

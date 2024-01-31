package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.OrganizationMember;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember,Long>{

}

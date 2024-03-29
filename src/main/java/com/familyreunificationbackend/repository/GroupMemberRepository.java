package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long>{

}

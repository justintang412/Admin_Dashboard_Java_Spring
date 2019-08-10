package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Certification;

@Mapper
public interface CertificationDao {
	Certification selectCertificationById(Long id);

	ArrayList<Certification> selectCertifications();

	ArrayList<Certification> selectCertificationsByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getCertificationTotalCount(@Param("search") String search);

	void insertCertification(Certification certification);

	void deleteCertification(Long id);

	void updateCertification(Certification certification);

}

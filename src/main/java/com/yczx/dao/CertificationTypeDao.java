package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.CertificationType;

@Mapper
public interface CertificationTypeDao {
	CertificationType selectCertificationTypeById(Long id);

	ArrayList<CertificationType> selectCertificationTypes();

	ArrayList<CertificationType> selectCertificationTypesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getCertificationTypeTotalCount(@Param("search") String search);

	void insertCertificationType(CertificationType certificationType);

	void deleteCertificationType(Long id);

	void updateCertificationType(CertificationType certificationType);

}

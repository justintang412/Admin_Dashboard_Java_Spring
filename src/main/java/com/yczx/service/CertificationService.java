package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.CertificationDao;
import com.yczx.domain.Certification;
import com.yczx.support.OperationResult;

@Service
public class CertificationService {
	@Autowired
	private CertificationDao certificationDao;

	public Certification selectCertificationById(Long id) {
		return certificationDao.selectCertificationById(id);
	}

	public ArrayList<Certification> selectCertifications() {
		return certificationDao.selectCertifications();
	}

	public ArrayList<Certification> selectCertificationsByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return certificationDao.selectCertificationsByPageParam(search, min, max, pageSize);
	}

	public Integer getCertificationTotalCount(@Param("search") String search) {
		return certificationDao.getCertificationTotalCount(search);
	}

	public void insertCertification(Certification certificateType) {
		certificationDao.insertCertification(certificateType);
	}

	public void deleteCertification(Long id) {
		certificationDao.deleteCertification(id);
	}

	public OperationResult<Certification> insertOrUpdateCertification(Certification certification) {
		OperationResult<Certification> result = new OperationResult<Certification>();
		result.setData(certification);
		if (certification.getId() != null) {
			certificationDao.updateCertification(certification);
		} else {
			certificationDao.insertCertification(certification);
		}
		return result;
	}
}

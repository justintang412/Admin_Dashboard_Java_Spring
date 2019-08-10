package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.CertificationTypeDao;
import com.yczx.domain.CertificationType;
import com.yczx.support.OperationResult;

@Service
public class CertificationTypeService {
	@Autowired
	private CertificationTypeDao certificationTypeDao;

	public CertificationType selectCertificationTypeById(Long id) {
		return certificationTypeDao.selectCertificationTypeById(id);
	}

	public ArrayList<CertificationType> selectCertificationTypes() {
		return certificationTypeDao.selectCertificationTypes();
	}

	public ArrayList<CertificationType> selectCertificationTypesByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return certificationTypeDao.selectCertificationTypesByPageParam(search, min, max, pageSize);
	}

	public Integer getCertificationTypeTotalCount(@Param("search") String search) {
		return certificationTypeDao.getCertificationTypeTotalCount(search);
	}

	public void insertCertificationType(CertificationType certificateType) {
		certificationTypeDao.insertCertificationType(certificateType);
	}

	public void deleteCertificationType(Long id) {
		certificationTypeDao.deleteCertificationType(id);
	}

	public OperationResult<CertificationType> insertOrUpdatePosition(CertificationType certificationType) {
		OperationResult<CertificationType> result = new OperationResult<CertificationType>();
		result.setData(certificationType);
		if (certificationType.getId() != null) {
			certificationTypeDao.updateCertificationType(certificationType);
		} else {
			certificationTypeDao.insertCertificationType(certificationType);
		}
		return result;
	}
}

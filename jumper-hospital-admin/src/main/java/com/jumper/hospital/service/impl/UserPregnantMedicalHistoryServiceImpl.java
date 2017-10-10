package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.UserPregnantMedicalHistoryDao;
import com.jumper.hospital.entity.UserPregnantMedicalHistory;
import com.jumper.hospital.service.UserPregnantMedicalHistoryService;

@Service
public class UserPregnantMedicalHistoryServiceImpl extends
		BaseServiceImpl<UserPregnantMedicalHistory, Integer> implements
		UserPregnantMedicalHistoryService {
	@Autowired
	private UserPregnantMedicalHistoryDao userPregnantMedicalHistoryDao;

	@Override
	public BaseDao<UserPregnantMedicalHistory, Integer> getBaseDAO() {
		return userPregnantMedicalHistoryDao;
	}

	@Override
	public List<UserPregnantMedicalHistory> findPregnantMedicalHistory(int id, int i) {
		return userPregnantMedicalHistoryDao.findPregnantMedicalHistory(id,i);
	}

	@Override
	public String findUserPastHistoryById(int id) {
		return userPregnantMedicalHistoryDao.findUserPastHistoryById(id);
	}

	@Override
	public String findGeneticHistoryById(Integer objectId) {
		return userPregnantMedicalHistoryDao.findGeneticHistoryById(objectId);
	}

	@Override
	public String findMaternalHistoryById(Integer objectId) {
		return userPregnantMedicalHistoryDao.findMaternalHistoryById(objectId);
	}

}

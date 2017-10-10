package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.UserPregnantMedicalHistory;

public interface UserPregnantMedicalHistoryDao extends
		BaseDao<UserPregnantMedicalHistory, Integer> {

	/**
	 * 根据用户id和type查询孕妇病史
	 * @param id
	 * @param i
	 * @return
	 */
	List<UserPregnantMedicalHistory> findPregnantMedicalHistory(int id, int i);
	
	/**
	 * 根据id查询既往病史
	 */
	String findUserPastHistoryById(int id);

	/**
	 * 根据id查询遗传病史
	 * @param objectId
	 * @return
	 */
	String findGeneticHistoryById(Integer objectId);

	/**
	 * 根据id查询孕产史
	 * @param objectId
	 * @return
	 */
	String findMaternalHistoryById(Integer objectId);

}

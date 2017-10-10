package com.jumper.hospital.dao.impl;
/**
 * 退款处理Dao层
 * @author rent
 * @date 2016-04-06
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.HospitalDoctorOrderRefundDao;
import com.jumper.hospital.entity.HospitalDoctorOrderRefund;
import com.jumper.hospital.vo.VOConsultRefund;

@Repository
public class HospitalDoctorOrderRefundDaoImpl extends BaseDaoImpl<HospitalDoctorOrderRefund, Integer> implements HospitalDoctorOrderRefundDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<VOConsultRefund> getConsultRefundDataList(Integer type, Integer state, Integer hospitalId, String orderId, Page<VOConsultRefund> page) {
		String order = type == 1 ? "asc" : "desc";
		String waitHandler = state == 0 ? "(r.state=2 or r.state=8)" : "r.state="+state;
		String hasHandler = state == 0 ? "(r.state=3 or r.state=4 or r.state=6)" : "r.state="+state;
		String refundRegex = type == 1 ? waitHandler : hasHandler;
		Integer offset = page.getFirst() - 1;
		String sql = null;
		if(StringUtils.isNotEmpty(orderId)){
			sql = "from hospital_doctor_order_refund r, hospital_cons_service_order o " +
			"where o.hospital_id="+ hospitalId +" and o.order_id='"+ orderId +"' and o.id=r.cons_order_id and r.type=2";
		}else{
			 sql = "from hospital_doctor_order_refund r, hospital_cons_service_order o " +
				"where "+refundRegex+" and o.hospital_id="+ hospitalId +" and o.id=r.cons_order_id and r.type=2 order by r.add_time "+ order;
		}
		String dataSql = "select r.id id,o.order_id orderId,r.add_time time,r.refund money,r.reason reason," +
				"r.state state,r.operator operater,o.id conId,r.complaint_succ_time autoTime "+ sql +" limit ?,?;";
		String countSql = "select count(*) "+ sql +";";
		SQLQuery query = createSqlQuery(dataSql, new Object[]{offset, page.getPageSize()});
		query.addScalar("id", StandardBasicTypes.INTEGER);
		query.addScalar("orderId", StandardBasicTypes.STRING);
		query.addScalar("time", StandardBasicTypes.TIMESTAMP);
		query.addScalar("money", StandardBasicTypes.DOUBLE);
		query.addScalar("reason", StandardBasicTypes.STRING);
		query.addScalar("state", StandardBasicTypes.INTEGER);
		query.addScalar("operater", StandardBasicTypes.STRING);
		query.addScalar("conId", StandardBasicTypes.INTEGER);
		query.addScalar("autoTime", StandardBasicTypes.TIMESTAMP);
		query.setResultTransformer(Transformers.aliasToBean(VOConsultRefund.class));
		List<VOConsultRefund> list = query.list();
		int count = executeCountSql(countSql);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	
}

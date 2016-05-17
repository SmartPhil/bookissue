package com.xdf.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.xdf.dao.BS_ClassDao;
import com.xdf.dto.BS_Class;
import com.xdf.util.HibernateUtil;

public class BS_ClassDaoImpl implements BS_ClassDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List<BS_Class> search(Date beginDate, Date endDate) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String begin = sdf.format(beginDate);
		String end = sdf.format(endDate);
		List<BS_Class> resultList = new ArrayList<BS_Class>();
		try {
			String sql = "select a.sCode,"
					   + "a.dtBeginDate,"
					   + "a.dtEndDate,"
					   + "a.ClassBookDeliveryType,"
					   + "a.sPrintTime,"
					   + "a.nCurrentCount,"
					   + "a.nMaxCount,"
					   + "a.sMemo,"
					   + "b.nCount "
					   + "from NIS_DY.[NIS_DY].[dbo].BS_Class a,bookcount b "
					   + "where a.sCode in (select b.sClassCode from bookcount) "
					   + "and a.dtBeginDate >= ? and dtBeginDate <= ? "
					   + "and (a.ClassBookDeliveryType = 1 or a.ClassBookDeliveryType = 0)";
			Query query = session.createSQLQuery(sql);
			query.setString(0, begin);
			query.setString(1, end);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List list = query.list();
			if (list != null) {
				Map map = null;
				BS_Class bs_Class = null;
				for (int i = 0; i < list.size(); i++) {
					map = (Map)list.get(i);
					bs_Class = new BS_Class();
					bs_Class.setClassCode(map.get("sCode").toString());
					bs_Class.setBeginDate(sdf.parse(map.get("dtBeginDate").toString()));
					bs_Class.setEndDate(sdf.parse(map.get("dtEndDate").toString()));
					bs_Class.setBookDeliveryType(map.get("ClassBookDeliveryType").toString());
					bs_Class.setPrintTime(map.get("sPrintTime").toString());
					if (!"".equals(map.get("nCurrentCount").toString()) && map.get("nCurrentCount") != null) {
						bs_Class.setCurrentCount(Integer.parseInt(map.get("nCurrentCount").toString()));
					}else {
						bs_Class.setCurrentCount(0);
					}
					if (!"".equals(map.get("nMaxCount").toString()) && map.get("nMaxCount") != null) {
						bs_Class.setMaxCount(Integer.parseInt(map.get("nMaxCount").toString()));
					}else {
						bs_Class.setMaxCount(0);
					}
					if (!"".equals(map.get("nCount").toString()) && map.get("nCount") != null) {
						bs_Class.setDeliveryCount(Integer.parseInt(map.get("nCount").toString()));
					}else {
						bs_Class.setDeliveryCount(0);
					}
					resultList.add(bs_Class);
				}
			}
			ts.commit();
			session.close();
			return resultList;
		} catch (Exception e) {
			System.out.println("查询班级配送信息失败：" + e.getMessage());
			ts.rollback();
			session.close();
			return null;
		}
	}

}

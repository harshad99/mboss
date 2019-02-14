package com.mudra.mboss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MBossServiceImpl implements MBossService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EntityManagerFactory emf;

	private List getmapdata(String sql) {
		System.out.println(sql);
		List mapList = null;
		try (Session session = emf.createEntityManager().unwrap(Session.class);
				Connection connection = ((SessionImpl) session).connection()) {
			QueryRunner query = new QueryRunner();
			mapList = (List) query.query(connection, sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}

	@Override
	public List<Object[]> getEstimatedetail(String uniqueid, String brandid, String clientid, String estimatenumber) {

		try {
			int uid = new Integer(uniqueid);
		} catch (Exception e) {
			e.printStackTrace();
			uniqueid = "0";
		}

		String sql = "select estimatenumber, to_char(estimatedate,'dd/mm/yyyy') estimatedate, estimateamount,isestimateapproved,clientponumber, clientpodate,remarks,clientid ,hdr_brandid "
				+ " from estimateheader where uniqueid='" + uniqueid + "' and clientid='" + clientid
				+ "' and hdr_brandid='" + brandid + "' and estimatedate>'1-apr-2018' and nvl(cancelled,'N')!='Y'";
		if (null != estimatenumber) {
			try {
				long est = new Long(estimatenumber);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Estimatenumber Error", e);
				estimatenumber = "0";
			}
			sql += "and estimatenumber='" + estimatenumber + "'";
		}
		sql += " order by 1 desc";
		System.out.println(sql);
		return getmapdata(sql);
	}

	@Override
	public List<Object[]> getPOdetail(String uniqueid, String estimatenumber) {
		try {
			int uid = new Integer(uniqueid);
		} catch (Exception e) {
			e.printStackTrace();
			uniqueid = "0";
		}
		try {
			long est = new Long(estimatenumber);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Estimatenumber Error", e);
			estimatenumber = "0";
		}
		String sql = "select b.uniqueid,b.ponumber,b.podate,b.supplierid,c.suppliername,b.amount from podetailshtother a, poheader b, suppliermaster c"
				+ " where a.uniqueid=b.uniqueid and a.ponumber=b.ponumber and b.uniqueid=c.uniqueid and b.supplierid=c.supplierid"
				+ " and a.uniqueid=" + uniqueid + " and a.estimatenumber =" + estimatenumber
				+ " and nvl(b.cancelled,'N')!='Y' and nvl(b.closed,'N')!='Y'";

		System.out.println(sql);
		return getmapdata(sql);
	}

	@Override
	public List<Object[]> getInvoicedetail(String uniqueid, String estimatenumber, String ponumber) {
		try {
			int uid = new Integer(uniqueid);
		} catch (Exception e) {
			e.printStackTrace();
			uniqueid = "0";
		}
		try {
			long est = new Long(estimatenumber);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Estimatenumber Error", e);
			estimatenumber = "0";
		}
		String sql = "select a.uniqueid,a.docnumber pvnumber, a.pvdate, a.supplierid,c.suppliername,a.invoicenumber,a.invoicedate,a.passedinvoicegrossamount GrossAmount,"
				+ " a.passedinvoicenetpayable NetPayable, a.allocatedamount Paidamount from supplierinvoiceheader a, supplierinvoicedetailshtother b, suppliermaster c"
				+ " where a.uniqueid=b.uniqueid and a.docnumber=b.docnumber and a.uniqueid=c.uniqueid and a.supplierid=c.supplierid and a.uniqueid="
				+ uniqueid + " and b.estimatenumber = " + estimatenumber
				+ " and nvl(a.cancelled,'N')!='Y'  and b.ponumber in (" + ponumber + ")";
		System.out.println(sql);
		return getmapdata(sql);
	}

	@Override
	public List<Object[]> getExpensedetail(String uniqueid, String estimatenumber) {
		try {
			int uid = new Integer(uniqueid);
		} catch (Exception e) {
			e.printStackTrace();
			uniqueid = "0";
		}
		try {
			long est = new Long(estimatenumber);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Estimatenumber Error", e);
			estimatenumber = "0";
		}
		String sql = "select a.uniqueid,a.vouchernumber,decode(trantype,1,a.amount*-1,amount) amount,  remarks, TRAVELREQUISITIONNUMBER TRNO,ESTIMATENUMBER from ccdetail a "
				+ "where   uniqueid=" + uniqueid
				+ " and vouchernumber like '2018%' and substr(a.glcode,-5) between 10000 and 30000 and a.ESTIMATENUMBER="
				+ estimatenumber;
		System.out.println(sql);
		return getmapdata(sql);
	}

	@Override
	public List<Object[]> getOtherExpensedetail(String uniqueid, String estimatenumber) {
		try {
			int uid = new Integer(uniqueid);
		} catch (Exception e) {
			e.printStackTrace();
			uniqueid = "0";
		}
		try {
			long est = new Long(estimatenumber);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Estimatenumber Error", e);
			estimatenumber = "0";
		}
		String sql = "select uniqueid,vouchernumber,trantype,amount,glcode,remarks,ccgroup,cccode, TRAVELREQUISITIONNUMBER,clientid,brandid  from ccdetail where uniqueid ="
				+ uniqueid + " and plmonth>=201812 and estimatenumber =" + estimatenumber
				+ " and nvl(cancelled,'N')!='Y' ";
		System.out.println(sql);
		return getmapdata(sql);
	}
}

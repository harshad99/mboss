package com.mudra.mboss.service;

import java.util.List;

public interface MBossService {

	public List<Object[]> getEstimatedetail(String uniqueid, String brandid, String clientid, String estimatenumber);

	public List<Object[]> getPOdetail(String uniqueid, String estimatenumber);

	public List<Object[]> getInvoicedetail(String uniqueid, String estimatenumber, String ponumber);

	public List<Object[]> getExpensedetail(String uniqueid, String estimatenumber);

	public List<Object[]> getOtherExpensedetail(String uniqueid, String estimatenumber);
}

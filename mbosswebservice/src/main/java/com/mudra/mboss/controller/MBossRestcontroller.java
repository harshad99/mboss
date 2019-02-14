package com.mudra.mboss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mudra.mboss.service.MBossService;

@CrossOrigin(origins = "*")
@RestController
public class MBossRestcontroller {

	@Autowired
	MBossService service;

	@RequestMapping(value = "/estimatelist/{uniqueid}/{brandid}/{clientid}", method = RequestMethod.GET)
	public List getEstimateList(@PathVariable String uniqueid, @PathVariable String brandid,
			@PathVariable String clientid) {
		return service.getEstimatedetail(uniqueid, brandid, clientid, null);
	}

	@RequestMapping(value = "/estimatelist/{uniqueid}/{brandid}/{clientid}/{estimatenumber}", method = RequestMethod.GET)
	public List getEstimateList(@PathVariable String uniqueid, @PathVariable String brandid,
			@PathVariable String clientid, @PathVariable String estimatenumber) {
		return service.getEstimatedetail(uniqueid, brandid, clientid, estimatenumber);
	}

	@RequestMapping(value = "/polist/{uniqueid}/{estimatenumber}", method = RequestMethod.GET)
	public List getPOList(@PathVariable String uniqueid, @PathVariable String estimatenumber) {
		return service.getPOdetail(uniqueid, estimatenumber);
	}

	@RequestMapping(value = "/invoicelist/{uniqueid}/{estimatenumber}/{ponumber}", method = RequestMethod.GET)
	public List getInvoicedetail(@PathVariable String uniqueid, @PathVariable String estimatenumber,
			@PathVariable String ponumber) {
		return service.getInvoicedetail(uniqueid, estimatenumber, ponumber);
	}

	@RequestMapping(value = "/expenselist/{uniqueid}/{estimatenumber}", method = RequestMethod.GET)
	public List getExpensedetail(@PathVariable String uniqueid, @PathVariable String estimatenumber) {
		return service.getExpensedetail(uniqueid, estimatenumber);
	}

	@RequestMapping(value = "/otherexpenselist/{uniqueid}/{estimatenumber}", method = RequestMethod.GET)
	public List getOtherExpensedetail(@PathVariable String uniqueid, @PathVariable String estimatenumber) {
		return service.getOtherExpensedetail(uniqueid, estimatenumber);
	}

}

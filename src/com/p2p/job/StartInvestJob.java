package com.p2p.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.p2p.service.TBorrowService;
import com.p2p.sys.SpringContext;

public class StartInvestJob extends QuartzJobBean {
	private static Log log = LogFactory.getLog(StartInvestJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		log.info("发标定时--");
		Long id = (Long)context.getJobDetail().getJobDataMap().get("borrowId");
		TBorrowService tBorrowService = SpringContext.getSpringContext()
				.getBean(TBorrowService.class);
		tBorrowService.updateBorrowStartInvest(id);

	}

}

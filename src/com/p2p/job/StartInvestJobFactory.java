package com.p2p.job;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class StartInvestJobFactory {

	private static Log log = LogFactory.getLog(StartInvestJobFactory.class);

	private Scheduler scheduler;

	public boolean startInvestJob(Long borrowId, Date cronDate)
			throws SchedulerException {
		boolean isSuccess = false;
		String jobName = "borrow-" + borrowId;
		String jobGroup = "borrow";
		// 0 0 16 10 10 ? 2015
		Calendar cal = Calendar.getInstance();
		cal.setTime(cronDate);

		StringBuffer cronExpression = new StringBuffer();
		cronExpression.append(cal.get(Calendar.SECOND));
		cronExpression.append(" ");
		cronExpression.append(cal.get(Calendar.MINUTE));
		cronExpression.append(" ");
		cronExpression.append(cal.get(Calendar.HOUR_OF_DAY));
		cronExpression.append(" ");
		cronExpression.append(cal.get(Calendar.DATE));
		cronExpression.append(" ");
		cronExpression.append(cal.get(Calendar.MONTH) + 1);
		cronExpression.append(" ? ");
		cronExpression.append(cal.get(Calendar.YEAR));

		log.info(borrowId + "--" + cronExpression);
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(StartInvestJob.class)
					.withIdentity(jobName, jobGroup).build();
			jobDetail.getJobDataMap().put("borrowId", borrowId);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(cronExpression.toString());

			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(jobName, jobGroup)
					.withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
			isSuccess = true;
			log.info("启动借款定时发标--" + jobName);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(cronExpression.toString());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
			isSuccess = true;
		}

		return isSuccess;

	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}

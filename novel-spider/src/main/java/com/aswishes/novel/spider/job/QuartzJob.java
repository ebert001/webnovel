package com.aswishes.novel.spider.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aswishes.novel.spider.service.SpiderService;

/**
 * cron expression
 * - * ?
 * second minute hour dayofmonth month dayofweek year
 * "0 0 12 * * ?"    每天中午十二点触发 
 * "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
 * "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
 * "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发  
 * @author lizhou
 *
 */
@Component
public class QuartzJob {
	private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);
	@Autowired
	private SpiderService spiderService;

	/**
	 * 每天每隔2個小時觸發一次
	 */
	@Scheduled(cron="10 20 0/2 * * ?")
    public void doSpider() {
		try {
			logger.info("Grab website begin: {}", new Date());
			spiderService.loopWebsite();
		} catch (Exception e) {
			logger.error("Spider website error.", e);
		} finally {
			logger.info("Grab website finished: {}", new Date());
		}
    }
}

package searchengine.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class QuartzTask {
	private static final Logger log = LoggerFactory.getLogger(QuartzTask.class);
	
	private static Long init=System.currentTimeMillis();
	@Scheduled(cron = "0/30 * * * * ?")
	private void sysTask(){
		Long date = System.currentTimeMillis();
		log.debug(date-init+"");
		log.info("正在优化索引...");
		init = date;
	}
}

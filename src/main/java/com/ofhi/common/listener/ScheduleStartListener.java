package com.ofhi.common.listener;

import com.ofhi.common.exception.QuartzException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laizuan
 * Date: 2018-1-14
 * Time: 11:34
 */
public class ScheduleStartListener implements ServletContextListener {
    private static  final Logger logger = LoggerFactory.getLogger(ScheduleStartListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            recovery();
        } catch (Exception e) {
            throw new QuartzException(" ScheduleStartListener contextInitialized ERROR!!", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void recovery() {
        Scheduler scheduler = null;
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            //可以通过SchedulerFactory创建一个Scheduler实例
            scheduler = schedulerFactory.getScheduler();
            //获取调度器中所有的触发器组
            List<String> triggerGroups = scheduler.getTriggerGroupNames();
            logger.debug("调度器中所有的触发器组 size():{}" , triggerGroups.size());

            //重新恢复在triggerGroups组中所有的触发器
            if (triggerGroups != null && triggerGroups.size() != 0) {
                for (int i = 0, len = triggerGroups.size(); i < len; i++) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(triggerGroups.get(i), triggerGroups.get(i));
                    logger.debug("triggerKey:{}" ,triggerKey);
                    //获取trigger
                    Trigger tg = scheduler.getTrigger(triggerKey);
                    logger.debug( "{}  -> 执行时间 :{}" ,triggerKey , tg.getNextFireTime());
                    //按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, tg);
                }
            }
            scheduler.start();
        } catch (Exception e) {
            throw new QuartzException("ScheduleStartListener  recovery() Error!", e);
        }
    }
}

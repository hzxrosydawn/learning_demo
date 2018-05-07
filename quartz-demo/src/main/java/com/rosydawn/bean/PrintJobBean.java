package com.rosydawn.bean;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent Huang on 2017/8/15.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class PrintJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("这是定时任务的打印" + new SimpleDateFormat("yyyy年mm月dd日hh点mm分ss秒").format(new Date()));
    }
}

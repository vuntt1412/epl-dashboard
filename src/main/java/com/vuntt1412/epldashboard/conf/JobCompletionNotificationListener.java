package com.vuntt1412.epldashboard.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT COUNT(id) FROM LEAGUE",
                    (rs, row) -> rs.getLong(1))
                    .stream().findFirst()
                    .ifPresentOrElse(i -> System.out.println("Found <" + i + "> leagues in the database."), () -> System.out.println("Found nothing!"));
            jdbcTemplate.query("SELECT COUNT(id) FROM TEAM",
                    (rs, row) -> rs.getLong(1))
                    .stream().findFirst()
                    .ifPresentOrElse(i -> System.out.println("Found <" + i + "> teams in the database."), () -> System.out.println("Found nothing!"));
            jdbcTemplate.query("SELECT COUNT(id) FROM PLAYER",
                    (rs, row) -> rs.getLong(1))
                    .stream().findFirst()
                    .ifPresentOrElse(i -> System.out.println("Found <" + i + "> players in the database."), () -> System.out.println("Found nothing!"));
            jdbcTemplate.query("SELECT COUNT(id) FROM MATCH",
                    (rs, row) -> rs.getLong(1))
                    .stream().findFirst()
                    .ifPresentOrElse(i -> System.out.println("Found <" + i + "> matches in the database."), () -> System.out.println("Found nothing!"));
        }
    }
}
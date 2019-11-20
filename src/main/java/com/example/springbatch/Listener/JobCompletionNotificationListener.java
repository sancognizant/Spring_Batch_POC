package com.example.springbatch.Listener;

import com.example.springbatch.model.ResponseODM;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.COMPLETED;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == COMPLETED) {
            System.out.println("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT FIRSTNAME, AMOUNT, YEARLYINCOME, " +
                            "STATUS, MESSAGES FROM responseOdm ",
                    (rs, row) -> new ResponseODM(
                            rs.getString(1),
                            rs.getFloat(2),
                            rs.getFloat(3),
                            rs.getString(4),
                            rs.getString(5)
                    )
            ).forEach(responseODM -> System.out.println(responseODM.getAmount()));
        }
    }
}


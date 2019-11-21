package com.example.productbatch.Listener;

import com.example.productbatch.Model.ResponseDiscount;
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

            jdbcTemplate.query("SELECT discount, message" +
                            " FROM responseDiscount ",
                    (rs, row) -> new ResponseDiscount(
                            rs.getFloat(1),
                            rs.getString(2)
                    )
            ).forEach(responseODM -> System.out.println(responseODM.getDiscount()));
        }
    }
}

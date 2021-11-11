package com.vuntt1412.epldashboard.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.staging.StagLeague;
import com.vuntt1412.epldashboard.domain.staging.StagMatch;
import com.vuntt1412.epldashboard.processor.LeagueItemProcessor;
import com.vuntt1412.epldashboard.processor.MatchItemProcessor;
import com.vuntt1412.epldashboard.tasklet.DemoTasklet;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final FlatFileItemReader<StagLeague> readerLeague;
    private final LeagueItemProcessor processorLeague;
    private final JdbcBatchItemWriter<League> writerLeague;

    private final FlatFileItemReader<StagMatch> readerMatch;
    private final MatchItemProcessor processorMatch;
    private final JdbcBatchItemWriter<Match> writerMatch;

    @Autowired
    public BatchConfiguration(FlatFileItemReader<StagLeague> readerLeague,
                              LeagueItemProcessor processorLeague,
                              JdbcBatchItemWriter<League> writerLeague,
                              FlatFileItemReader<StagMatch> readerMatch,
                              MatchItemProcessor processorMatch,
                              JdbcBatchItemWriter<Match> writerMatch) {
        this.readerLeague = readerLeague;
        this.processorLeague = processorLeague;
        this.writerLeague = writerLeague;
        this.readerMatch = readerMatch;
        this.processorMatch = processorMatch;
        this.writerMatch = writerMatch;
    }

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step preStep() {
        return this.stepBuilderFactory.get("preStep")
                .tasklet(demoTasklet())
                .build();
    }

    @Bean
    public DemoTasklet demoTasklet() {
        DemoTasklet tasklet = new DemoTasklet();
        return tasklet;
    }

    // Inject bean dependencies as method parameters
    @Bean
    public Job job(JobCompletionNotificationListener listener, Step importMatchStep, Step importLeagueStep) {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(preStep())
                .next(importLeagueStep)
                .next(importMatchStep)
                .build();
    }

    @Bean
    public Step importMatchStep() {
        return stepBuilderFactory.get("importMatchStep")
                .<StagMatch, Match>chunk(10)
                .reader(readerMatch)
                .processor(processorMatch)
                .writer(writerMatch)
                .build();
    }

    @Bean
    public Step importLeagueStep() {
        return stepBuilderFactory.get("importLeagueStep")
                .<StagLeague, League>chunk(10)
                .reader(readerLeague)
                .processor(processorLeague)
                .writer(writerLeague)
                .build();
    }


}

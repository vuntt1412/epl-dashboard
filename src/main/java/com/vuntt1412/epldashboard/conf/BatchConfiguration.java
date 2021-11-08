package com.vuntt1412.epldashboard.conf;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.vuntt1412.epldashboard.domain.Match;
import com.vuntt1412.epldashboard.domain.staging.StagMatch;
import com.vuntt1412.epldashboard.processor.MatchItemProcessor;
import com.vuntt1412.epldashboard.tasklet.DemoTasklet;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final String[] COLUMNS = {
            "id",
            "country_id",
            "league_id",
            "season",
            "stage",
            "date",
            "home_team_api_id",
            "away_team_api_id",
            "home_team_goal",
            "away_team_goal",
            "home_player_1",
            "home_player_2",
            "home_player_3",
            "home_player_4",
            "home_player_5",
            "home_player_6",
            "home_player_7",
            "home_player_8",
            "home_player_9",
            "home_player_10",
            "home_player_11",
            "away_player_1",
            "away_player_2",
            "away_player_3",
            "away_player_4",
            "away_player_5",
            "away_player_6",
            "away_player_7",
            "away_player_8",
            "away_player_9",
            "away_player_10",
            "away_player_11",
            "goal"};
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<StagMatch> reader() {

        return new FlatFileItemReaderBuilder<StagMatch>()
                .name("matchItemReader")
                .resource(new ClassPathResource("Match.csv"))
                .delimited()
                .names(COLUMNS)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StagMatch>() {{
                    setTargetType(StagMatch.class);
                }})
                .build();
    }

    @Bean
    public MatchItemProcessor processor() {
        return new MatchItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO MATCH (id," +
                        "country_id," +
                        "league_id," +
                        "season," +
                        "stage," +
                        "date," +
                        "home_team_id," +
                        "away_team_id," +
                        "home_team_goal," +
                        "away_team_goal," +
                        "home_player1," +
                        "home_player2," +
                        "home_player3," +
                        "home_player4," +
                        "home_player5," +
                        "home_player6," +
                        "home_player7," +
                        "home_player8," +
                        "home_player9," +
                        "home_player10," +
                        "home_player11," +
                        "away_player1," +
                        "away_player2," +
                        "away_player3," +
                        "away_player4," +
                        "away_player5," +
                        "away_player6," +
                        "away_player7," +
                        "away_player8," +
                        "away_player9," +
                        "away_player10," +
                        "away_player11," +
                        "goal) VALUES (:id,\n" +
                        ":countryId,\n" +
                        ":leagueId,\n" +
                        ":season,\n" +
                        ":stage,\n" +
                        ":date,\n" +
                        ":homeTeamId,\n" +
                        ":awayTeamId,\n" +
                        ":homeTeamGoal,\n" +
                        ":awayTeamGoal,\n" +
                        ":homePlayer1,\n" +
                        ":homePlayer2,\n" +
                        ":homePlayer3,\n" +
                        ":homePlayer4,\n" +
                        ":homePlayer5,\n" +
                        ":homePlayer6,\n" +
                        ":homePlayer7,\n" +
                        ":homePlayer8,\n" +
                        ":homePlayer9,\n" +
                        ":homePlayer10,\n" +
                        ":homePlayer11,\n" +
                        ":awayPlayer1,\n" +
                        ":awayPlayer2,\n" +
                        ":awayPlayer3,\n" +
                        ":awayPlayer4,\n" +
                        ":awayPlayer5,\n" +
                        ":awayPlayer6,\n" +
                        ":awayPlayer7,\n" +
                        ":awayPlayer8,\n" +
                        ":awayPlayer9,\n" +
                        ":awayPlayer10,\n" +
                        ":awayPlayer11,\n" +
                        ":goal)")
                .dataSource(dataSource)
                .build();
    }

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

    @Bean
    public Job job(JobCompletionNotificationListener listener, Step importMatchStep) {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(preStep())
                .next(importMatchStep)
                .build();
    }

    @Bean
    public Step importMatchStep(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("importMatchStep")
                .<StagMatch, Match>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}

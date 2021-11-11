package com.vuntt1412.epldashboard.conf.definition;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.vuntt1412.epldashboard.domain.Team;
import com.vuntt1412.epldashboard.domain.staging.StagTeam;
import com.vuntt1412.epldashboard.processor.TeamItemProcessor;

@Configuration
public class TeamDefinition {
    private static final String[] COLUMNS_TEAM = {"id", "team_id", "team_long_name", "team_short_name"};

    @Bean
    public FlatFileItemReader<StagTeam> readerTeam() {
        return new FlatFileItemReaderBuilder<StagTeam>()
                .name("teamItemReader")
                .resource(new ClassPathResource("Team.csv"))
                .delimited()
                .names(COLUMNS_TEAM)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StagTeam>() {{
                    setTargetType(StagTeam.class);
                }})
                .build();
    }

    @Bean
    public TeamItemProcessor processorTeam() {
        return new TeamItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Team> writerTeam(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Team>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO TEAM (id,team_id,team_long_name,team_short_name) VALUES (:id,:teamId,:teamLongName,:teamShortName)").dataSource(dataSource)
                .build();
    }
}

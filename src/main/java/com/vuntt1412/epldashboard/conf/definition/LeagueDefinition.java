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

import com.vuntt1412.epldashboard.domain.League;
import com.vuntt1412.epldashboard.domain.staging.StagLeague;
import com.vuntt1412.epldashboard.processor.LeagueItemProcessor;

@Configuration
public class LeagueDefinition {
    private static final String[] COLUMNS_LEAGUE = {"id", "country_id", "name"};

    @Bean
    public FlatFileItemReader<StagLeague> readerLeague() {
        return new FlatFileItemReaderBuilder<StagLeague>()
                .name("leagueItemReader")
                .resource(new ClassPathResource("League.csv"))
                .delimited()
                .names(COLUMNS_LEAGUE)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StagLeague>() {{
                    setTargetType(StagLeague.class);
                }})
                .build();
    }

    @Bean
    public LeagueItemProcessor processorLeague() {
        return new LeagueItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<League> writerLeague(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<League>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO LEAGUE (id,country_id,name) VALUES (:id,:countryId,:name)").dataSource(dataSource)
                .build();
    }
}

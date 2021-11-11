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

import com.vuntt1412.epldashboard.domain.Player;
import com.vuntt1412.epldashboard.domain.staging.StagPlayer;
import com.vuntt1412.epldashboard.processor.PlayerItemProcessor;


@Configuration
public class PlayerDefinition {
    private static final String[] COLUMNS_PLAYER = {"id", "player_id", "player_name", "birthday"};

    @Bean
    public FlatFileItemReader<StagPlayer> readerPlayer() {
        return new FlatFileItemReaderBuilder<StagPlayer>()
                .name("playerItemReader")
                .resource(new ClassPathResource("Player.csv"))
                .delimited()
                .names(COLUMNS_PLAYER)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<StagPlayer>() {{
                    setTargetType(StagPlayer.class);
                }})
                .build();
    }

    @Bean
    public PlayerItemProcessor processorPlayer() {
        return new PlayerItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Player> writerPlayer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Player>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO PLAYER (id,player_id,player_name,birthday) VALUES (:id,:playerId,:playerName,:birthday)").dataSource(dataSource)
                .build();
    }
}

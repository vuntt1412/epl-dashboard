package com.vuntt1412.epldashboard.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.vuntt1412.epldashboard.domain.Player;
import com.vuntt1412.epldashboard.domain.staging.StagPlayer;


public class PlayerItemProcessor implements ItemProcessor<StagPlayer, Player> {

    private static final Logger log = LoggerFactory.getLogger(PlayerItemProcessor.class);

    @Override
    public Player process(final StagPlayer stagPlayer) throws Exception {
        Player player = new Player();
        player.setId(Long.valueOf(stagPlayer.getId()));
        player.setPlayerId(Long.valueOf(stagPlayer.getPlayer_id()));
        player.setPlayerName(stagPlayer.getPlayer_name());
        player.setBirthday(LocalDate.parse(stagPlayer.getBirthday(), DateTimeFormatter.ofPattern("dd.MM.yy hh:mm")));
        return player;
    }

}
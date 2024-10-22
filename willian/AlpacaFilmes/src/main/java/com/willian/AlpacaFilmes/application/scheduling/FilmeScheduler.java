package com.willian.AlpacaFilmes.application.scheduling;

import com.willian.AlpacaFilmes.application.services.FilmeServices;
import com.willian.AlpacaFilmes.application.services.TheMovieDbServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@EnableScheduling
public class FilmeScheduler {
    Logger logger = Logger.getLogger(TheMovieDbServices.class.getName());

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private FilmeServices filmeServices;

    //(cron = "0 59 23 * * *", zone = TIME_ZONE) cron para rodar a meia noite
    @Scheduled(fixedDelay = 120000)
    public void cadastraFilmes() {
        logger.info("Executando o agendamento");

        filmeServices.salvarFilmes();
    }
}

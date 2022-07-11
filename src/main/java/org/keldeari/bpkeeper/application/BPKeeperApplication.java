package org.keldeari.bpkeeper.application;

import java.util.Map;

import org.keldeari.bpkeeper.bot.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BPKeeperApplication {
	
	private static Logger log = LoggerFactory.getLogger(BPKeeperApplication.class);
	
	private static final Map<String, String> getenv = System.getenv();
	
	public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(getenv.get("BP_KEEPER_NAME"), getenv.get("BP_KEEPER_TOKEN")));
        } catch (TelegramApiException e) {
        	log.error("Could not register the bot.", e);
        }
    }
}

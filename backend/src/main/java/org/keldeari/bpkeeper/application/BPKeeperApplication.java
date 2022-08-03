package org.keldeari.bpkeeper.application;

import java.sql.SQLException;
import java.util.Map;

import org.jooq.CloseableDSLContext;
import org.jooq.impl.DSL;
import org.keldeari.bpkeeper.bot.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BPKeeperApplication {
	
	private static final Map<String, String> getenv = System.getenv();
	
	public static void main(String[] args) throws InterruptedException, TelegramApiException, ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new Bot(getenv.get("BP_KEEPER_NAME"), getenv.get("BP_KEEPER_TOKEN")));
		} catch (TelegramApiException e) {
			log.error("Could not register the bot.", e);
			throw e;
		}
    }
	
	public static CloseableDSLContext createDslContext() throws SQLException {
		String userName = "postgres";
		String password = "postgres";
		String url = "jdbc:postgresql://postgres:5432/bpk";
		return DSL.using(url, userName, password);
	}
}

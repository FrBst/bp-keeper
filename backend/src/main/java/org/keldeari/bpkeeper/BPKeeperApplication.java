package org.keldeari.bpkeeper;

import java.sql.SQLException;
import java.util.Map;

import org.jooq.CloseableDSLContext;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BPKeeperApplication {
	
	private static final String BP_KEEPER_NAME = System.getenv().get("BP_KEEPER_NAME");
	private static final String BP_KEEPER_TOKEN = System.getenv().get("BP_KEEPER_TOKEN");
	private static final String BP_KEEPER_PORT = System.getenv().get("BP_KEEPER_PORT");
	
	public BPKeeperApplication() throws SQLException, ClassNotFoundException {
	}
	
	public static void main(String[] args) 
			throws InterruptedException, TelegramApiException, ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new Bot(BP_KEEPER_NAME, BP_KEEPER_TOKEN));
		} catch (TelegramApiException e) {
			log.error("Could not register the bot.", e);
			throw e;
		}
    }
	
	public static CloseableDSLContext createDslContext() throws SQLException {
		String userName = "postgres";
		String password = "postgres";
		String url = String.format("jdbc:postgresql://postgres:%s/bpk", BP_KEEPER_PORT);
		return DSL.using(url, userName, password);
	}
}

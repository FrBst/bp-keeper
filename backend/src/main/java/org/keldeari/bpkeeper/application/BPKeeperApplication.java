package org.keldeari.bpkeeper.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Map;

import org.jooq.CloseableDSLContext;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.keldeari.bpkeeper.bot.Bot;
import org.keldeari.bpkeeper.codegen.tables.Measurements;
import org.keldeari.bpkeeper.codegen.tables.records.MeasurementsRecord;
import org.postgresql.ds.common.BaseDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import jakarta.activation.DataSource;
import lombok.Getter;

public class BPKeeperApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BPKeeperApplication.class);
	
	private static final Map<String, String> getenv = System.getenv();
	
	public static void main(String[] args) throws InterruptedException {
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new Bot(getenv.get("BP_KEEPER_NAME"), getenv.get("BP_KEEPER_TOKEN")));
		} catch (TelegramApiException e) {
			log.error("Could not register the bot.", e);
		}
    }
	
	public static CloseableDSLContext createDslContext() throws SQLException {
		String userName = "postgres";
		String password = "postgres";
		String url = "jdbc:postgresql://localhost:5432/bpk";
		return DSL.using(url, userName, password);
	}
}

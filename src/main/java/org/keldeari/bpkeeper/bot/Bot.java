package org.keldeari.bpkeeper.bot;

import org.kealdari.bpkeeper.command.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingCommandBot {
	private final String BOT_NAME;
	private final String BOT_TOKEN;
	
	public Bot(String botName, String botToken) {
		BOT_NAME = botName;
		BOT_TOKEN = botToken;
		
		register(new StartCommand("start", "Start the bot"));
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public void processNonCommandUpdate(Update update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}
	
}

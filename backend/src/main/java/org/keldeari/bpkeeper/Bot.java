package org.keldeari.bpkeeper;

import org.keldeari.bpkeeper.command.NonCommand;
import org.keldeari.bpkeeper.command.ServiceCommand;
import org.keldeari.bpkeeper.command.StartCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingCommandBot {
	private static Logger log = LoggerFactory.getLogger(Bot.class);
	
	private final String BOT_NAME;
	private final String BOT_TOKEN;
	
	public Bot(String botName, String botToken) {
		BOT_NAME = botName;
		BOT_TOKEN = botToken;
		
		register(new StartCommand("start", "Start the bot"));
//		register(new CancelCommand("cancel", "Remove the last measurement"));
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override
	public void processNonCommandUpdate(Update update) {
		SendMessage answer = new NonCommand().nonCommandExecute(update);
        
        try {
        	execute(new SendChatAction(String.valueOf(answer.getChatId()), ActionType.TYPING.toString()));
        	Thread.sleep(ServiceCommand.getTypingDelayMillis(answer.getText()));
            execute(answer);
        } catch (TelegramApiException e) {
        	log.error(String.format("Chat ID %s: API exception when sending message.", answer.getChatId()), e);
        } catch (InterruptedException e) {
        	log.error(String.format("Chat ID %s: Unexpected InterruptedException..", answer.getChatId()), e);
		}
	}
}

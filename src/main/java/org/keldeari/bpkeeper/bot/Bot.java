package org.keldeari.bpkeeper.bot;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.kealdari.bpkeeper.command.ServiceCommand;
import org.kealdari.bpkeeper.command.StartCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
		Message msg = update.getMessage();
        Long chatId = msg.getChatId();
	
		SendMessage answer = new SendMessage();
        answer.setText(saveMeasurement(msg));
        answer.setChatId(chatId.toString());
        
        try {
        	execute(new SendChatAction(String.valueOf(chatId), ActionType.TYPING.toString()));
        	Thread.sleep(ServiceCommand.getTypingDelayMillis(msg.getText()));
            execute(answer);
        } catch (TelegramApiException e) {
        	log.error(String.format("Chat ID %s: API exception when sending message.", chatId), e);
        } catch (InterruptedException e) {
        	log.error(String.format("Chat ID %s: Unexpected InterruptedException..", chatId), e);
		}
	}
	
	private String saveMeasurement(Message msg) {
		
		Long chatId = msg.getChatId();
		log.debug(String.format("Chat ID %s: Received measurements data.", chatId));
		
		String[] tokens = msg.getText().trim().split("[\\,\\.\\/\\:\\;\\_\\ \\|\\-]");
		
		if (tokens.length != 2 && tokens.length != 3) {
        	log.debug(String.format("Chat ID %s: Wrong number of measurements.", chatId));
			return "Your blood tastes foul. I think you used a wrong format for measurements. "
					+ "There is a guide in the 'help' page.";
		}
		
		List<Integer> measures = new LinkedList<>();
		for (String token : tokens) {
			try {
				measures.add(Integer.parseInt(token));
			} catch (NumberFormatException e) {
	        	log.debug(String.format("Chat ID %s: Could not parse measurements as Integers.", chatId));
				return "This isn't blood. Stop fooling around and give me the numbers already.";
			}
		}
		
		return measures.stream().map(String::valueOf).collect(Collectors.joining("delimiter"));
	}
}

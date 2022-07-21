package org.keldeari.bpkeeper.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * https://github.com/taksebe-official
 *
 *
 */
public abstract class ServiceCommand extends BotCommand {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	ServiceCommand(String identifier, String description) {
        super(identifier, description);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String commandName, User user, String text) {
    	
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        
        sendTypingAction(absSender, chatId, getTypingDelayMillis(text));

        try {
        	log.trace(String.format("Chat ID %s: Sending message: ' %s '", chatId, text));
            absSender.execute(message);
        } catch (TelegramApiException e) {
        	log.error(String.format("Chat ID %s: could not send message.", chatId), e);
        }
    }
    
    void sendTypingAction(AbsSender absSender, Long chatId, long millis) {
    	
    	SendChatAction action = new SendChatAction(String.valueOf(chatId), ActionType.TYPING.toString());

        try {
        	log.trace(String.format("Chat ID %s: Typing for %s milliseconds", chatId, millis));
            absSender.execute(action);
        } catch (TelegramApiException e) {
            log.error(String.format("Chat ID %s: could not send 'typing' action.", chatId), e);
        }
        
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        	log.error(String.format("Chat ID %s: Unexpected InterruptedException.", chatId), e);
		}
    }
    
    public static long getTypingDelayMillis(String text) {
    	return text.length() * 30;
    }
}

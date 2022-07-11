package org.kealdari.bpkeeper.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * https://github.com/taksebe-official
 *
 *
 */
public abstract class ServiceCommand extends BotCommand {
	
	ServiceCommand(String identifier, String description) {
        super(identifier, description);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String commandName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        
        sendTypingAction(absSender, chatId, text.length() * 30);
        
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            // TODO: логируем сбой Telegram Bot API, использу€ commandName и userName
        }
    }
    
    void sendTypingAction(AbsSender absSender, Long chatId, long millis) {

    	SendChatAction action = new SendChatAction(String.valueOf(chatId), ActionType.TYPING.toString());

        try {
            absSender.execute(action);
        } catch (TelegramApiException e) {
            // TODO: логируем сбой Telegram Bot API, использу€ commandName и userName
        }
        
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
			// TODO: handle exception
		}
    }
}

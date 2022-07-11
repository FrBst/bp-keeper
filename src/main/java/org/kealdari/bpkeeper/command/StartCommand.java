package org.kealdari.bpkeeper.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends ServiceCommand {
	
	public StartCommand(String identifier, String description) {
		super(identifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), user,
				"Contracts are signed with blood. I suggest that you add the first measurement right now.");
	}

}

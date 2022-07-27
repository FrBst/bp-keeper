package org.keldeari.bpkeeper.command;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jooq.CloseableDSLContext;
import org.jooq.exception.DataAccessException;
import org.keldeari.bpkeeper.application.BPKeeperApplication;
import org.keldeari.bpkeeper.codegen.tables.Measurements;
import org.keldeari.bpkeeper.codegen.tables.records.MeasurementsRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NonCommand {
	
	private static Logger log = LoggerFactory.getLogger(NonCommand.class);

	public SendMessage nonCommandExecute(Update update) {

		Message message = update.getMessage();
		Long chatId = message.getChatId();
		
		SendMessage answer = new SendMessage();
        answer.setText(saveMeasurement(message));
        answer.setChatId(chatId.toString());
        
        return answer;
	}
	
	private String saveMeasurement(Message msg) {
		
		Long chatId = msg.getChatId();
		log.debug(String.format("Chat ID %s: Received measurements data.", chatId));
		
		String[] tokens = msg.getText().trim().split("[\\,\\.\\/\\:\\;\\_\\ \\|\\-]", 4);
		
		if (tokens.length < 2) {
        	log.debug(String.format("Chat ID %s: Wrong number of measurements.", chatId));
			return "Your blood tastes foul. I think you used a wrong format for measurements. "
					+ "There is a guide in the 'help' qpage.";
		}
		
		List<Short> measures = new ArrayList<>();
		String note = tokens.length == 4 ? tokens[3] : null;
		
		for (int i = 0; i < tokens.length; i++) {
			if (i == tokens.length-1 && note != null) {
				break;
			}
			
			try {
				measures.add(Short.parseShort(tokens[i]));
			} catch (NumberFormatException e) {
	        	log.debug(String.format("Chat ID %s: Could not parse measurements as Integers.", chatId));
				return "This isn't blood. Stop fooling around and give me the numbers already.";
			}
		}
		
		try (CloseableDSLContext context = BPKeeperApplication.createDslContext()) {
			MeasurementsRecord mr = context.newRecord(Measurements.MEASUREMENTS);
			mr.setPressureSys(measures.get(0));
			mr.setPressureDia(measures.get(1));
			if (measures.size() >= 3) {
				mr.setPulse(measures.get(2));
			}
			mr.setDatetime(OffsetDateTime.now());
			mr.setNote(note);
			mr.setUserId(chatId);
			mr.insert();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "The deal has been sealed.";
	}
}

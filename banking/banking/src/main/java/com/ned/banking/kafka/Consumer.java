package com.ned.banking.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.ned.banking.model.Log;
import com.ned.banking.repository.ILocalLogRepository;

@Component
public class Consumer {

	ILocalLogRepository localLogRepository;

	@Autowired
	public Consumer(ILocalLogRepository localLogRepository) {
		this.localLogRepository = localLogRepository;
	}

	@KafkaListener(topics = "logs", groupId = "transfer_consumer_group")
	public void listenTransfer(@Payload Log log, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		// FileWriter.writeLogToFile(message);
		localLogRepository.insertLog(log);
	}
}

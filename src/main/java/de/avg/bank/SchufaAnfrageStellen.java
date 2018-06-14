package de.avg.bank;

import de.hska.iwi.avg.schufasystem.service.SchufaService.Business;
import de.hska.iwi.avg.schufasystem.service.SchufaService.Int;
import de.hska.iwi.avg.schufasystem.service.SchufaService.Person;
import de.hska.iwi.avg.schufasystem.service.SchufaService.SchufaService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class SchufaAnfrageStellen implements JavaDelegate {
	public static final String SCORE_VAR_NAME = "score";
//	public static final Long SCORE = (long) 2000;
	public static Long SCORE = 0L;


	 public void execute(DelegateExecution execution) throws Exception {
		 TTransport transport;
		 transport = new TSocket("localhost", 9090);
		 transport.open();
		 TProtocol protocol = new TBinaryProtocol(transport);
		 SchufaService.Client client = new SchufaService.Client(protocol);

		 Person p1 = new Person("Hans", "Wurst");

		 SCORE = (long) client.getScore(p1, Business.energy).getValue();


		 execution.setVariable(SCORE_VAR_NAME, SCORE);
	    }
}

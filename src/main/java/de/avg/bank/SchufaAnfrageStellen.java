package de.avg.bank;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.concurrent.ThreadLocalRandom;
 
public class SchufaAnfrageStellen implements JavaDelegate {
	int min = 1;
	int max = 3000;
	public static final String SHOULD_FAIL_VAR_NAME = "shouldFail";
	public static final String SCORE_VAR_NAME = "score";
	public static final Long SCORE = (long) 2000;
	
	 public void execute(DelegateExecution execution) throws Exception {
		 
		 if(((Boolean)execution.getVariable(SHOULD_FAIL_VAR_NAME)) == true) {
		      throw new RuntimeException("Service invocation failure!");

		    } else {
		      execution.setVariable(SCORE_VAR_NAME, SCORE);

		    }
//	      int randomScore = ThreadLocalRandom.current().nextInt(min, max + 1);
//	      execution.setVariable(SCORE_VAR_NAME, randomScore);	      
	    }

	

}

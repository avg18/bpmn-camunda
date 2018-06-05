package de.avg.bank;

import static org.junit.Assert.*;

import org.junit.Test;

public class SynchronousServiceTaskGenerateRandomScore {

	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule();
	
	@Test
    @Deployment(resources = { "process.bpmn" })
	
	final RuntimeService runtimeService = processEngineRule.getRuntimeService();
    final TaskService taskService = processEngineRule.getTaskService();

    // this invocation should NOT fail
    Map<String, Object> variables = Collections.<String, Object> singletonMap(SynchronousServiceTask.SHOULD_FAIL_VAR_NAME, false);

    // start the process instance
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process", variables);

    // the process instance is now waiting in the first wait state (user task):
    Task waitStateBefore = taskService.createTaskQuery()
      .taskDefinitionKey("waitStateBefore")
      .processInstanceId(processInstance.getId())
      .singleResult();
    assertNotNull(waitStateBefore);

    // Complete this task. This triggers the synchronous invocation of the
    // service task. This method invocation returns after the service task 
    // has been executed and the process instance has advanced to the second waitstate.
    taskService.complete(waitStateBefore.getId());

    // the process instance is now waiting in the second wait state (user task):
    Task waitStateAfter = taskService.createTaskQuery()
      .taskDefinitionKey("waitStateAfter")
      .processInstanceId(processInstance.getId())
      .singleResult();
    assertNotNull(waitStateAfter);

    // check for variable set by the service task:
    variables = runtimeService.getVariables(processInstance.getId());
    assertEquals(SynchronousServiceTask.PRICE, variables.get(SynchronousServiceTask.PRICE_VAR_NAME));

  }

}

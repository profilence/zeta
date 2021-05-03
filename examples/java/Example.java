package demo;

import java.io.File;
import java.util.Random;

import com.profilence.zeta.Connector;
import com.profilence.zeta.ILogger;
import com.profilence.zeta.LogLevel;
import com.profilence.zeta.LogStepRequest;
import com.profilence.zeta.PingResponseType;
import com.profilence.zeta.TestType;

public class Example {

    public static void main(String[] args) throws InterruptedException {
        
        Random rand = new Random(); 
        
        Connector.addLogger(new ILogger() {
            @Override
            public void onLogMessage(LogLevel level, String message) {
                print(message);
            }
        });
        

        
        Connector client = new Connector("127.0.0.1", 31312);
        Thread t = null;
        
        try {
        
            PingResponseType response = client.ping();
            print(response);
            
            if (response == PingResponseType.Ok) {
            	

                String testRunName = "demo";
                String testSetName = "dummy";
                String projectName = "AProject";
                String projectVersion = "1.1.1";
                String targetDeviceIdentifier = "emulator-5554";
                String targetDeviceType = "android";
                File profilingSettings = new File("/home/tests/profiling_settings.json");

                String testRunID = client.startRun(testRunName, testSetName, projectName, projectVersion, targetDeviceIdentifier, targetDeviceType, null, null, profilingSettings, null);
                print("testRunID: " + testRunID);
                if (testRunID != null) {
                	
                    RunPinger runPinger = new RunPinger(client, testRunID);
                    t = new Thread(runPinger);
                    t.start();
                	
                    for(int i = 1; i <= 3; i++) {
                        String useCaseName = "use case " + i;
                        String useCaseID = "id_" + i;
                        client.logTrace(testRunID, "Starting use case " + useCaseName + " from java");
                        client.onUseCaseStart(testRunID, useCaseName, useCaseID, "Dummy group", testSetName, TestType.Normal, null, null);
                        try {
                            for(int j = 1; j <= 20; j++) {
                                String stepName = "step_" + j;
                                print("case: " + i + "; " + stepName);
                                boolean stepPassed = j % 5 == 0;
                                File screenShot = stepPassed == false ? new File("/home/tests/resources/dummy.png") : null;
                                client.onLogStep(testRunID, stepName, LogStepRequest.StepType.Running, stepPassed, screenShot);
                                Thread.sleep(1000);
                            }
                        } catch(Exception e) {
                        }
                        boolean failed = i % 2 == 0;
                        long activeRunTime = i * Math.max(10, rand.nextInt(10001));
                        String cause = failed ? "Could not find item" : null;
                        boolean intendedReset = false;
                        client.onUseCaseEnd(testRunID, failed, activeRunTime, cause, intendedReset);
                    }
                    print("Successfully stopped test run: " + client.stopRun(testRunID, false));
                    runPinger.running = false;
                }
            }
        } 
        finally 
        {
          client.shutdown();
          t.join();
        }
    }
    

    public static void print(Object obj) {
        System.out.println(obj);
    }
    
    //Pings the testrun to keep it alive
    public static class RunPinger implements Runnable {

    	public boolean running = true;
        Connector client = null;
        String testRunID = "";

        public RunPinger(Connector client, String testRunID) {
            this.client = client;
            this.testRunID = testRunID;
        }

        public void run() {
        	
        	while (running)
        	{	
        		try 
        		{
        			boolean returnval = client.pingRun(testRunID);
        			print("Pinging test run: " + String.valueOf(returnval));
					Thread.sleep(30000);
				} 
        		catch (InterruptedException e) 
        		{
					e.printStackTrace();
				}
        	}
            
        }
    }


}

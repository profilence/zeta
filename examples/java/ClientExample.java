package test_import;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.profilence.zeta.Connector;
import com.profilence.zeta.ILogger;
import com.profilence.zeta.ITestRequestListener;
import com.profilence.zeta.LogLevel;
import com.profilence.zeta.PingResponseType;

interface ITestLauncher {
	void start(String project, String version);
	void stop();
}

class MonkeyResult {
	public boolean success;
	public long runTime;
}

public class ClientExample {

	private static final  String NODE_ID = "monkey_node2";
	private static final  String POOL = "monkey_pool";
	private static final  String TYPE = "Android";
	private static final  String NODE_VARIABLES = "{ \"region\" : \"FI\", \"platform\" : \"Android\", \"operator\" : \"Elisa\", \"product\" : \"Pixel2\", \"external_devices\" : \"power_measure\" }";
	
	private static final String TEST_RUN_NAME = "monkey";
	private static final String TEST_SET_NAME = "monkey";
	private static final String DEVICE_TYPE = "android";
	private static final String DEVICE_ID =  "emulator-5554";
	private static final File PROFILING_SETTINGS = null;//new File("C:\\work\\profiling_settings.json");
	
	private static final int ITERATIONS = 3;
	
	private static final String[] PACKAGES = { 
		"com.android.calculator2", 
		"com.android.contacts",
		"com.google.android.deskclock"	
	};
	
	private static List<String> _recents = new ArrayList<>();
	private static boolean _keepRunning;
	
		
	public static void main(String[] args) throws InterruptedException, IOException {
	
		Connector.addLogger(new ILogger() {
			@Override
			public void onLogMessage(LogLevel level, String message) {
				print(message);
			}
		});
		
		boolean createTestNode = true;
				
		final Connector client = new Connector("localhost", 31321);
		try {
			  PingResponseType response = client.ping();
			  if (response == PingResponseType.Ok) {
				  if (createTestNode) {
				  	  print("Start test listener");
					  startListener(client, new ITestLauncher() {
						  public void start(String x, String y) {
							  startRunner(client, x, y);
						  }
						  public void stop() {
							  stopRunner();
						  }
					  });
				  } else {
					  print("Let the monkey loose");
					  startRunner(client, "monkey", "2.0");
				  }
		      } else {
		    	  print("Service not responding");
		      }
	    } finally {
	    	print("Shutting down");
	    	client.shutdown();
	    }
    	print("Goodbye");
	}
		
	public static void startListener(Connector client, final ITestLauncher launcher) throws InterruptedException, IOException {
   		
		  if (client.addNode(NODE_ID, POOL, TYPE, NODE_VARIABLES))  {
			  client.subscribeToTestRequests(new ITestRequestListener( ) {
				  @Override
				  public void onTestStartRequested(com.profilence.zeta.TestStartRequest req) {
					  final String project = req.getProject();
					  final String version = req.getVersion();
					  final String runID = req.getRunId();
					  print("Received request " + runID + " to test version " + version + " for " + project);
					  (new Thread(new Runnable() {
						  public void run() {
							  launcher.start(project, version);
						  }
					  })).start();
					  client.respondToTestRequest(runID, "Started new monkey run", true, null, null);
				  }			  
				  @Override
				  public void onTestStopRequested(com.profilence.zeta.TestStopRequest req) {
					  launcher.stop();
				  }
				  @Override
				  public void onError(java.lang.Throwable req) {
				  }		
				  @Override
				  public void onCompleted() {
				  }
			  });
			  			  
			  final boolean[] doRun = { true };
			  
			  Thread heartBeat = new Thread(new Runnable() {
				  public void run() {
					  while(doRun[0]) {
						  try {
							Thread.sleep(1000 * 60);
						} catch (Exception e) {
						}
						if (doRun[0]) {  
							client.updateNode(NODE_ID, null, null, null, null, POOL, NODE_VARIABLES);
						}
					  }
					  print("Heartbeat stopped");
				  }
			  });
			  
			  heartBeat.start();
			  
			  print("Press any key to stop");
			  System.in.read();
			  print("Exiting ...");
			  
			  doRun[0] = false;
			  heartBeat.interrupt();
			  
			  print("Removing node ...");
			  client.removeNode(NODE_ID);
		  }
	}

	public static void startRunner(Connector client, String projectName, String projectVersion) {
		
		_keepRunning = true;
	  
		String testRunID = client.startRun(TEST_RUN_NAME, TEST_SET_NAME, projectName, projectVersion, DEVICE_ID, DEVICE_TYPE, null, null, PROFILING_SETTINGS, null);
		print("testRunID: " + testRunID);
		if (testRunID != null) {
		  for(int i = 1; i <= ITERATIONS + 1; i++) {		  
			  if (_keepRunning == false) {
				  break;
			  }
			  String pkg = randomizePackage();
			  String useCaseName = "Monkey goes bananas with " + pkg;
			  String useCaseID = "id_" + i;
			  print(useCaseName);
			  client.logTrace(testRunID, "Starting use case " + useCaseName + " from java");
			  client.onUseCaseStart(testRunID, useCaseName, useCaseID, null, null);
			  client.onLogStep(testRunID, "Let the monkey loose!", true, false);
			  MonkeyResult result = runRandomTests(pkg, client, testRunID);
			  client.onLogStep(testRunID, "capture", true, true);
			  print("Ending use case ...");
			  client.onUseCaseEnd(testRunID, result.success, result.runTime, null, false);
			  print("End.");
		  }
		  print("Stopping test run ...");
		  print("Successfully stopped test run: " + client.stopRun(testRunID, false));
		}
	}
	
	private static String randomizePackage() {
		Random rand = new Random();
		String pkg = null;
		if (_recents.size() == PACKAGES.length) {
			_recents.clear();
		}
		while (true) {
			pkg = PACKAGES[rand.nextInt(PACKAGES.length)];
			if (_recents.contains(pkg) == false) {
				_recents.add(pkg);
				break;
			}
		}
		return pkg;
	}
	
	private static void stopMonkey() {
		
        try {
        	Process process = new ProcessBuilder("adb","shell", "pkill", "-9", "monkey").start();
        	InputStream is = process.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
        	BufferedReader br = new BufferedReader(isr);
        	String line;
        	while ((line = br.readLine()) != null) {
        	  print(line);
        	}
		} catch (Exception e) {
		}
	}
	
	private static MonkeyResult runRandomTests(String pkg, Connector client, String runId) {
		
		if (pkg == null || pkg.isEmpty()) {
			pkg = randomizePackage();
		}
		
		long[] interactionCounts = { 1000, 2000, 3000 };
		
		int[] throttles = { 10, 20, 50 };
		
		Random rand = new Random();
		
		long interactionCount = interactionCounts[rand.nextInt(interactionCounts.length)];
		int throttle = throttles[rand.nextInt(throttles.length)];
		
		if (interactionCount == interactionCounts[interactionCounts.length - 1]) {
			throttle = 0;
		}
		
		MonkeyResult result = new MonkeyResult();
		long start = System.currentTimeMillis();
		result.success = runMonkey(pkg, interactionCount, throttle, client, runId);
		result.runTime = System.currentTimeMillis() - start;
		return result;
	}
	
	public static boolean runMonkey(String pkg, long interactions, int throttle, Connector client, String runId) {
    	boolean success = true;
        try {
        	Process process = new ProcessBuilder("adb","shell", "monkey", "-p", pkg, "--ignore-security-exceptions", "--throttle", Integer.toString(throttle), Long.toString(interactions)).start();
        	InputStream is = process.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
        	BufferedReader br = new BufferedReader(isr);
        	String line;
        	while ((line = br.readLine()) != null) {
        		if (line != null && line.isEmpty() == false) {
        			client.logTrace(runId, line);
              	  	print(line);
              	  	if (success && (line.contains("** Monkey aborted") || line.contains("// CRASH:") || line.contains("** System appears to have crashed"))) {
              	  		success = false;
              	  	}
        		}        	  
        	}
		} catch (Exception e) {
			success = false;
		}
        return success;
	}
	
	public static void stopRunner() {
		_keepRunning = false;
		stopMonkey();
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
}

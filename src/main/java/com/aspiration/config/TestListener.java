package com.aspiration.config;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {
	Logger log;
	String testName;

	public static ThreadLocal<ExtentTest> testR = new ThreadLocal<>();
	public static ThreadLocal<String> description = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		String testMethodNam = result.getMethod().getMethodName();
		try {
			ExtentTest test = ExtentManager.getExtent().createTest(testMethodNam, description.get());
			testR.set(test);
		}catch(Exception e){
			log.info("Exception onTestStart Method; Exception: "+e);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			String testMethodNam = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST: " + testMethodNam + " PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			testR.get().pass(m);
		}finally {
			while(description.get() != null){
				description.remove();
			}
			while(testR.get() != null){
				testR.remove();
			}
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try{
			String testMethodNam = result.getMethod().getMethodName();
			log.info("TEST FAILED" + testMethodNam);
			String exceptionMessage= Arrays.toString(result.getThrowable().getStackTrace());
			String erroMessage = Arrays.toString(new String[]{result.getThrowable().getMessage()});

			testR.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Error message:click to see"
					+ "</font>" + "</b >" + "</summary>" +erroMessage.replaceAll(",", "<br>")+"</details>"+" \n");

			testR.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception:Click to see"
					+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");

			String failureMessage="TEST FAILED: " + testMethodNam;
			Markup m = MarkupHelper.createLabel(failureMessage, ExtentColor.RED);
			testR.get().log(Status.FAIL, m);
		}finally {
			while(description.get() != null){
				description.remove();
			}
			while(testR.get() != null){
				testR.remove();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try{
			String testMethodNam = result.getMethod().getMethodName();
			String logText="<b>"+"TEST:- "+ testMethodNam+ " SKIPPED"+"</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			testR.get().skip(m);
			System.out.println("SKIPPED TEST");
		}finally {
			while(description.get() !=null){
				description.remove();
			}
			while(testR.get() !=null){
				testR.remove();
			}
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		this.testName = context.getCurrentXmlTest().getName();
		this.log = (Logger) LogManager.getLogger(testName);
		log.info("[TEST " + testName + " STARTED]");
		description.set(context.getCurrentXmlTest().getParameter("testDescription"));
		if(description.get() == null){
			description.set("NO DESCRIPTION");
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("[TEST " + testName + " FINISHED]");

		if (ExtentManager.getExtent() != null) {
			ExtentManager.getExtent().flush();
		}
	}
}

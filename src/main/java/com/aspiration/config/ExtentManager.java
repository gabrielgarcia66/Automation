package com.aspiration.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static String documentTitle = " ";

	public static ExtentReports createInstance(String fileName, String documentTitle) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(documentTitle);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(documentTitle);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Angel Garcia");
		extent.setSystemInfo("Proyecto", "Demo");

		return extent;
	}

	public static String getDocumentTile(){
		return documentTitle;
	}

	public static ExtentReports getExtent(){
		return extent;
	}

	//Info
	public static void info(String message) {
		TestListener.testR.get().info("<b>" + "<font color=" + "black>" + message + "" +
				" </font>" + "</b>");
	}
}

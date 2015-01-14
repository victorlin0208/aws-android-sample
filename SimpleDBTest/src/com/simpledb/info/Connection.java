package com.simpledb.info;

import java.util.Properties;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

public class Connection {
	public static AmazonSimpleDB awsSimpleDB;
	public static Properties properties;

	// 1. Get Simple DB connection.
	public static AmazonSimpleDB getAwsSimpleDB()
	{
		if(awsSimpleDB==null)
		{
			BasicAWSCredentials credentials= new BasicAWSCredentials(getProperties().getProperty("accessKey"), Connection.getProperties().getProperty("secreteKey"));
			awsSimpleDB= new AmazonSimpleDBClient(credentials);
		}
		return awsSimpleDB;
	}
	
	public static Properties getProperties()
	{
		if(properties == null)
		{ 
			properties = new Properties();
			properties.setProperty("accessKey", ""); 
			properties.setProperty("secreteKey", ""); 	
		}
		return properties;	
	}
}

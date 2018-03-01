package com.amazonaws.samples;

import java.io.IOException;
import java.io.File;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class RegionLatencyTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AWSCredentials credentials = null;
		 try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e)
		 {
	             throw new AmazonClientException("Cannot load the credentials from the credential profiles file. ",e);
		 }
		 
		 String region = "us-west-2";
		 String bucket= "taw-s3-bucket-1-22b39aff-84cb-4ec6-98f1-0a577a1c59c7";
		 String keyname = "MyObjectKey";
		 String filename = "C://Users//Tawseef//eclipse-workspace//Lab2AWStest//testImage.jpg";
		 
		 String region2 = "eu-west-1";
		 String bucket2= "taw-s3-bucket-2-3f04b199-4fad-4cae-8b54-75249ab60eb3";
		 String keyname2 = "MyObjectKey2";
		 //String filename2 = "C://Users//Tawseef//eclipse-workspace//Lab2AWStest//testImage.jpg";
		 
		 String region3 = "ap-northeast-1";
		 String bucket3= "taw-s3-bucket-3-86a457ca-caf1-47d9-ac9d-df88880db0d7";
		 String keyname3 = "MyObjectKey3";
		 //String filename = "C://Users//Tawseef//eclipse-workspace//Lab2AWStest//testImage.jpg";
		 
		 AmazonS3 s3reg1 = AmazonS3ClientBuilder.standard()
		            .withCredentials(new AWSStaticCredentialsProvider(credentials))
		            .withRegion(region)
		            .build();
		 AmazonS3 s3reg2 = AmazonS3ClientBuilder.standard()
		                .withCredentials(new AWSStaticCredentialsProvider(credentials))
		                .withRegion(region2)
		                .build();
		 AmazonS3 s3reg3 = AmazonS3ClientBuilder.standard()
		                .withCredentials(new AWSStaticCredentialsProvider(credentials))
		                .withRegion(region3)
		                .build();
		try {
			
			/****************************************************region 1****************************************/	
		  System.out.println("Uploading a new object to S3 in region:" + region);
		  long startTime = System.currentTimeMillis();
		  File file = new File(filename);
		  s3reg1.putObject(new PutObjectRequest(bucket, keyname, file));
          System.out.println("Done uploading!");
          
          
          System.out.println("Downloading an object");
          S3Object object = s3reg1.getObject(new GetObjectRequest(bucket, keyname));
          long stopTime = System.currentTimeMillis();
          
          System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
          // displayTextInputStream(object.getObjectContent());
          
          long elapsedTime = stopTime - startTime;
          float meanSpeed = (float)(elapsedTime);
          System.out.println("Done Downloading");
          System.out.println("total time: "+ meanSpeed +"\n");
          
          /****************************************************region 2****************************************/
          System.out.println("Uploading a new object to S3 in region:" + region2);
		  startTime = System.currentTimeMillis();
		  file = new File(filename);
		  s3reg2.putObject(new PutObjectRequest(bucket2, keyname2, file));
          System.out.println("Done uploading!");
          
          
          System.out.println("Downloading an object");
          S3Object object2 = s3reg2.getObject(new GetObjectRequest(bucket2, keyname2));
          stopTime = System.currentTimeMillis();
          
          System.out.println("Content-Type: "  + object2.getObjectMetadata().getContentType());
          // displayTextInputStream(object.getObjectContent());
          
          elapsedTime = stopTime - startTime;
          meanSpeed = (float)(elapsedTime);
          System.out.println("Done Downloading");
          System.out.println("total time: "+ meanSpeed +"\n");
          
          /****************************************************region 3****************************************/
          System.out.println("Uploading a new object to S3 in region:" + region3);
		  startTime = System.currentTimeMillis();
		  file = new File(filename);
		  s3reg3.putObject(new PutObjectRequest(bucket3, keyname3, file));
          System.out.println("Done uploading!");
          
          
          System.out.println("Downloading an object");
          S3Object object3 = s3reg3.getObject(new GetObjectRequest(bucket3, keyname3));
          stopTime = System.currentTimeMillis();
          
          System.out.println("Content-Type: "  + object3.getObjectMetadata().getContentType());
          // displayTextInputStream(object.getObjectContent());
          
          elapsedTime = stopTime - startTime;
          meanSpeed = (float)(elapsedTime);
          System.out.println("Done Downloading");
          System.out.println("total time: "+ meanSpeed +"\n");
          
          
		}
		 catch (AmazonServiceException ase) {
             System.out.println("Caught an AmazonServiceException, the request was made "
                     + "to Amazon S3, but was rejected with an error response for some reason.");
             System.out.println("Error Message:    " + ase.getMessage());
             System.out.println("HTTP Status Code: " + ase.getStatusCode());
             System.out.println("AWS Error Code:   " + ase.getErrorCode());
             System.out.println("Error Type:       " + ase.getErrorType());
             System.out.println("Request ID:       " + ase.getRequestId());
         } 
         catch (AmazonClientException ace) {
             System.out.println("Caught an AmazonClientException, The client has encountered "
                     + "a serious internal problem while trying to communicate with S3, "
                     + "such as not being able to access the network.");
             System.out.println("Error Message: " + ace.getMessage());
         }      
	}

}

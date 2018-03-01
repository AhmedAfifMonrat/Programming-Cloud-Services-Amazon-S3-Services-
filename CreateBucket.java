package monrat.asif.lab2;

import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.util.UUID;

public class CreateBucket {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 AWSCredentials credentials = null;
		 try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e)
		 {
	             throw new AmazonClientException("Cannot load the credentials from the credential profiles file. ",e);
		 }
		 
         String bucket1 = "munrat-bucket-" + UUID.randomUUID();
         String bucket2 = "munrat-bucket-" + UUID.randomUUID();
         String bucket3 = "munrat-bucket-" + UUID.randomUUID();
         String region1 = "us-west-2";
         String region2 = "ca-central-1";
         String region3 = "eu-west-2";
         
         AmazonS3 s3_region1 = AmazonS3ClientBuilder.standard()
                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                 .withRegion(region1)
                 .build();
         AmazonS3 s3_region2 = AmazonS3ClientBuilder.standard()
                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                 .withRegion(region2)
                 .build();
         AmazonS3 s3_region3 = AmazonS3ClientBuilder.standard()
                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                 .withRegion(region3)
                 .build();
         try {
         System.out.println("Creating bucket : " + bucket1 + "\n");
         s3_region1.createBucket(bucket1);
         System.out.println("Creating bucket : " + bucket2 + "\n");
         s3_region2.createBucket(bucket2);
         System.out.println("Creating bucket : " + bucket3 + "\n");
         s3_region3.createBucket(bucket3);
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

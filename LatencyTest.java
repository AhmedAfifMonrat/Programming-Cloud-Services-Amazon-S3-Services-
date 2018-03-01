package monrat.asif.lab2;
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
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class LatencyTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		AWSCredentials credentials = null;
		 try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e)
		 {
	             throw new AmazonClientException("Cannot load the credentials from the credential profiles file. ",e);
		 }
		 String region1 = "us-west-2";
		 String bucket1= "munrat-bucket-346e64e8-0b22-42e6-a9a9-17999f4b86b3";
		 String keyname1 = "MyObjectKey";
		 String filename = "C://Users//ahmed//eclipse-workspace//Lab2//src//main//java//monrat//asif//lab2//home1.jpg";
		 
		 String region2 = "ca-central-1";
		 String bucket2= "munrat-bucket-a3bd961c-6178-4b61-913a-c93e5373dda6";
		 String keyname2 = "MyObjectKey";
		 
		 
		 String region3 = "eu-west-2";
		 String bucket3= "munrat-bucket-0955ca8b-4d73-497e-83cb-74e0f18182af";
		 String keyname3 = "MyObjectKey";
		 
		 
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
			 //For Region 1
			  System.out.println("Uploading a new object to S3 in region: " + region1);
			  long uploadTime1 = System.currentTimeMillis();
			  File file = new File(filename);
			  s3_region1.putObject(new PutObjectRequest(bucket1, keyname1, file));
	          System.out.println("Done uploading!");
	          
	          
	          System.out.println("Downloading an object");
	          S3Object object1 = s3_region1.getObject(new GetObjectRequest(bucket1, keyname1));
	          long downloadTime1 = System.currentTimeMillis();
	          
	          System.out.println("Content-Type: "  + object1.getObjectMetadata().getContentType());
	         
	          
	          long elapsedTime = downloadTime1 - uploadTime1;
	          float meanSpeed = (float)(elapsedTime);
	          System.out.println("Done Downloading");
	          System.out.println("total time: "+ meanSpeed/1000 +" seconds\n");
	          
	          //For Region 2
	          System.out.println("Uploading a new object to S3 in region: " + region2);
			  long uploadTime2 = System.currentTimeMillis();
			  file = new File(filename);
			  s3_region2.putObject(new PutObjectRequest(bucket2, keyname2, file));
	          System.out.println("Done uploading!");
	          
	          
	          System.out.println("Downloading an object");
	          S3Object object2 = s3_region2.getObject(new GetObjectRequest(bucket2, keyname2));
	          long downloadTime2 = System.currentTimeMillis();
	          
	          System.out.println("Content-Type: "  + object2.getObjectMetadata().getContentType());
	         
	          
	          elapsedTime = downloadTime2 - uploadTime2;
	          meanSpeed = (float)(elapsedTime);
	          System.out.println("Done Downloading");
	          System.out.println("total time: "+ meanSpeed/1000 +" seconds\n");
	          
	          //For region 3
	          
	          System.out.println("Uploading a new object to S3 in region: " + region3);
			  long uploadTime3 = System.currentTimeMillis();
			  file = new File(filename);
			  s3_region3.putObject(new PutObjectRequest(bucket3, keyname3, file));
	          System.out.println("Done uploading!");
	          
	          
	          System.out.println("Downloading an object");
	          S3Object object3 = s3_region3.getObject(new GetObjectRequest(bucket3, keyname3));
	          long downloadTime3 = System.currentTimeMillis();
	          
	          System.out.println("Content-Type: "  + object3.getObjectMetadata().getContentType());
	         
	          
	          elapsedTime = downloadTime3 - uploadTime3;
	          meanSpeed = (float)(elapsedTime);
	          System.out.println("Done Downloading");
	          System.out.println("total time: "+ meanSpeed/1000 +" seconds\n");
	          double meanUpload = (uploadTime1 + uploadTime2 + uploadTime3)/3;
	          double SDupload = sqrt((pow((meanUpload-uploadTime1),2)+pow((meanUpload-uploadTime2),2)+pow((meanUpload-uploadTime3),2))/3); 
	          double meanDownload = (downloadTime1 + downloadTime2 + downloadTime3)/3;
	          double SDdownload = sqrt((pow((meanDownload-downloadTime1),2)+pow((meanDownload-downloadTime2),2)+pow((meanDownload-downloadTime3),2))/3);
	          System.out.printf("Standard Deviation for Upload time: ");
	          System.out.printf("%.2f", SDupload/1000);
	          System.out.printf(" seconds\n");
	          System.out.printf("Standard Deviation for Download time: ");
	          System.out.printf("%.2f",SDdownload/1000);
	          System.out.printf(" seconds");
	          
	          
	          
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


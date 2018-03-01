package monrat.asif.lab2;
import java.io.IOException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;


public class ListingBuckets {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AWSCredentials credentials = null;
		 try {
	            credentials = new ProfileCredentialsProvider("default").getCredentials();
	        } catch (Exception e)
		 {
	             throw new AmazonClientException("Cannot load the credentials from the credential profiles file. ",e);
		 }
		 AmazonS3 s3 = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion("ap-south-1")
					.build();
		 try {
		 String region="ap-south-1";
		 System.out.println("Total Number of Buckets: " + s3.listBuckets().size() );
		 System.out.println("Listing Buckets for: Asia Pacific (Mumbai)");
		 for (Bucket bucket : s3.listBuckets()) { //listing buckets in bucket variable
			 String BucketRegion = s3.getBucketLocation(bucket.getName()); // getting the location for each bucket
			 if(BucketRegion.equals(region))
				 System.out.println("-" + bucket.getName());
                      }
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

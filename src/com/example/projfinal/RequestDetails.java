package com.example.projfinal;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;  
import com.google.android.gms.maps.SupportMapFragment;




public class RequestDetails extends FragmentActivity implements OnItemClickListener{
 
  int serverResponseCode = 0;
  private static AutoCompleteTextView  location;
  static String filePath;
  private static EditText requestdesc;
  private static int RESULT_LOAD_IMAGE = 1;
  static File mediaFile;
  private Spinner requestType, city_list;
  private Button btnSubmit;
  private static final int CAMERA_REQUEST = 500;
  private ImageView imgPreview;
  private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
  //private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
  public static final int MEDIA_TYPE_IMAGE = 1;
  //public static final int MEDIA_TYPE_VIDEO = 2;
  public static int IMAGEUPLOADED=0;
  private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
  private Uri fileUri;
  String submitRequestUri="http://192.168.43.149:8086/CivicEngagement/MobileRequest";
  String imageUploadUri="http://192.168.43.149:8086/CivicEngagement/ImageUpload";
  //private VideoView videoPreview;
  private Button btnCapturePicture;
  
  SharedPreferences session;
	
  // Shared pref mode
  int PRIVATE_MODE = 0;
   
  
  String PREFER_NAME = "session";
  String user;
 

  AutoCompleteTextView atvPlaces;
 // PlacesTask placesTask;
 // ParserTask parserTask;



  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	/*
	session=getApplication().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);

	 user=session.getString("user", "none");
	 if(user.contains("none"))
	 {
		Intent i= new  Intent(getApplicationContext(), LoginOne.class);
		startActivity(i);
	 }
	
	setContentView(R.layout.req_details);
	
	atvPlaces = (AutoCompleteTextView) findViewById(R.id.requestlocation);
    atvPlaces.setThreshold(1);       
   
    atvPlaces.addTextChangedListener(new TextWatcher() {
       
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {               
            placesTask = new PlacesTask();               
            placesTask.execute(s.toString());
        }
       
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                int after) {
            // TODO Auto-generated method stub
        }
       
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub               
        }
    });
	
	
	 this is code of autocomp
	AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.req_address);
    autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.list_item));
    autoCompView.setOnItemClickListener(this);
    */
    
	
	btnCapturePicture = (Button) findViewById(R.id.camera_but);
	requestdesc=(EditText)findViewById(R.id.requestdescription);
	location=(AutoCompleteTextView) findViewById(R.id.requestlocation);
	city_list=(Spinner)findViewById(R.id.citylist);
    imgPreview = (ImageView) findViewById(R.id.imageView1);
//    videoPreview = (VideoView) findViewById(R.id.videoPreview);
    
  //  btnRecordVideo = (Button) findViewById(R.id.btnRecordVideo);

    /**
     * Capture image button click event
     * */
    
    
    
    btnCapturePicture.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // capture picture
        	captureImage();
        }
    });
        	
	
//	addItemsOnSpinner2();
	addListenerOnButton();
	addListenerOnSpinnerItemSelection();
  }
  
  
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // if the result is capturing Image
	    if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // successfully captured the image
	            // display it in image view
	            previewCapturedImage();
	        } else if (resultCode == RESULT_CANCELED) {
	            // user cancelled Image capture
	            Toast.makeText(getApplicationContext(),
	                    "User cancelled image capture", Toast.LENGTH_SHORT)
	                    .show();
	        }
	    }
	    
	    	        
	
	}
  
  
    public void addListenerOnSpinnerItemSelection() {
	requestType = (Spinner) findViewById(R.id.requesttype);
	city_list= (Spinner) findViewById(R.id.citylist);
//	requestType.setOnItemSelectedListener(new CustomOnItemSelectedListener());
  }
 
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);               

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
               
                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }
    
    
  // get the selected dropdown list value
  public void addListenerOnButton() {
 
	requestType = (Spinner) findViewById(R.id.requesttype);
//	spinner2 = (Spinner) findViewById(R.id.spinner2);
	btnSubmit = (Button) findViewById(R.id.btnSubmit);
 
	btnSubmit.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
 
//	    Toast.makeText(RequestDetails.this, String.valueOf(city_list.getSelectedItem()),Toast.LENGTH_SHORT).show();
	   int flag=0;
		 
		  
		  if(location.getText().toString().matches(""))
		  {  flag=1;
			  location.setError("Please enter location");
			  return;
		  }
		  if(String.valueOf(city_list.getSelectedItem()).contains("Select city"))
		  {  flag=1;
			  Toast.makeText(getApplicationContext(),"Plase selectcity ", Toast.LENGTH_SHORT).show();
				 return;
		  }
		  if(String.valueOf(requestType.getSelectedItem()).contains("Select request type"))
		  { flag=1;
			 Toast.makeText(getApplicationContext(),"Plase select request type", Toast.LENGTH_SHORT).show();
			 return;
		  }
		  if(requestdesc.getText().toString().matches(""))
		  { flag=1;
			requestdesc.setError("Plase write few words about request.");
			return;
		  }
		  if(mediaFile==null)
		  { flag=1;
			  Toast.makeText(getApplicationContext(), "Plase take photo.", Toast.LENGTH_SHORT).show();
			  return;
		  }
		  
		  //End of validation
		  if(flag==0)
		  {
			 
			  String fileName=mediaFile.toString();
			//Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_SHORT).show();
			  HttpURLConnection conn = null;
	          DataOutputStream dos = null; 
	          String lineEnd = "\r\n";
	          String twoHyphens = "--";
	          String boundary = "*****";
	          int bytesRead, bytesAvailable, bufferSize;
	          byte[] buffer;
	          int maxBufferSize = 50* 1024 * 1024;
	          File sourceFile = new File(fileName);
	          if (!sourceFile.isFile()) {
	               
	               
	                
	               Log.e("uploadFile", "Source File not exist :"+fileName);
	                
	               runOnUiThread(new Runnable() {
	                   public void run() {
	                       Log.e("fileerror", "file doesnt exist");
	                   }
	               });
	                
	               return ;
	            
	          }
	          else
	          {
	               try {
	                    
	            	   
	                     // open a URL connection to the Servlet
	                   FileInputStream fileInputStream = new FileInputStream(sourceFile);
	                   URL url = new URL(imageUploadUri);;
	                    
	                   // Open a HTTP  connection to  the URL
	                   conn = (HttpURLConnection) url.openConnection();
	                   conn.setDoInput(true); // Allow Inputs
	                   conn.setDoOutput(true); // Allow Outputs
	                   conn.setUseCaches(false); // Don't use a Cached Copy
	                   conn.setRequestMethod("POST");
	                   conn.setRequestProperty("Connection", "Keep-Alive");
	                   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	                   conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	                   conn.setRequestProperty("uploaded_file", fileName);
	                   
	                   dos = new DataOutputStream(conn.getOutputStream());
	          
	                   dos.writeBytes(twoHyphens + boundary + lineEnd);
	                   dos.writeBytes("Content-Disposition: form-data; name=uploaded_file"+";filename="
	                                             + fileName + ""+lineEnd);
	                    
	                   dos.writeBytes(lineEnd);
	          
	                   // create a buffer of  maximum size
	                   bytesAvailable = fileInputStream.available();
	          
	                   bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                   buffer = new byte[bufferSize];
	          
	                   // read file and write it into form...
	                   bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
	                      
	                   while (bytesRead > 0) {
	                        
	                     dos.write(buffer, 0, bufferSize);
	                     bytesAvailable = fileInputStream.available();
	                     bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                     bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
	                      
	                    }
	          
	                   // send multipart form data necesssary after file data...
	                   dos.writeBytes(lineEnd);
	                   dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	          
	                   // Responses from the server (code and message)
	                   serverResponseCode = conn.getResponseCode();
	                   String serverResponseMessage = conn.getResponseMessage();
	                     
	                   Log.i("uploadFile", "HTTP Response is : "
	                           + serverResponseMessage + ": " + serverResponseCode);
	                    
	                   if(serverResponseCode == 200){
	                     
	                	 IMAGEUPLOADED=1;
	                     Toast.makeText(getApplicationContext(),"uploaded", Toast.LENGTH_SHORT).show();     
	                   }   
	                    
	                   //close the streams //
	                   fileInputStream.close();
	                   dos.flush();
	                   dos.close();
	                     
	              } catch (MalformedURLException ex) {
	                   
	                  
	                  ex.printStackTrace();
	                   
	                  
	                   
	                  Log.e("Upload file to server", "error: " + ex.getMessage(), ex); 
	              } catch (Exception e) {
	                   
	                 
	                  e.printStackTrace();
	                   
	                 
	                  Log.e("Upload file to server Exception", "Exception : "
	                                                   + e.getMessage(), e); 
	              }
	              
	             
	               
	           } // End else block
			      
			  
			           
	           
	    
			 
		  }//if close
		  
		  if(IMAGEUPLOADED==1)
		  {
			String loc=location.getText().toString();
			String city=city_list.getSelectedItem().toString();
			String reqdesc=requestdesc.getText().toString();
			String reqtype=requestType.getSelectedItem().toString();
			String [] temp=mediaFile.toString().split("/");
			String photo=temp[temp.length-1];
			String un=session.getString("user", "none");
			
			
			try{
				
				submitRequestUri = "http://192.168.43.149:8086/CivicEngagement/MobileRequest";		 
		         
				
		         // Open a HTTP  connection to  the URL
				 
		         DefaultHttpClient httpClient = new DefaultHttpClient();
		         HttpPost httpPost = new HttpPost(submitRequestUri);
		          List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        	nameValuePairs.add(new BasicNameValuePair("reqLocation",loc));    
		          //nameValuePairs.add(new BasicNameValuePair("password", user));
		        	    nameValuePairs.add(new BasicNameValuePair("reqUser", un));
		        	    nameValuePairs.add(new BasicNameValuePair("reqCity", city));
		        	    nameValuePairs.add(new BasicNameValuePair("reqDescription", reqdesc));
		        	    nameValuePairs.add(new BasicNameValuePair("reqPhoto", photo));
		        	    nameValuePairs.add(new BasicNameValuePair("reqType", reqtype));
		        	//    nameValuePairs.add(new BasicNameValuePair("signup_confirmpassword", conpass));
		        	  //  nameValuePairs.add(new BasicNameValuePair("signup_city",city1));
		        	    
			     httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		         HttpResponse httpResponse = httpClient.execute(httpPost);
		         StatusLine statusLine = httpResponse.getStatusLine();
		         HttpResponse response;
				if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		             ByteArrayOutputStream out = new ByteArrayOutputStream();
		             httpResponse.getEntity().writeTo(out);
		             out.close();
		            String  output = out.toString();
		           System.out.println(output);
		           if(output.contains("yes"))
		           {
		        	  Toast.makeText(getApplicationContext(), "Request submitted successfully.", Toast.LENGTH_SHORT).show();
		           }
		            
		         }
		        
		          
		          
		         
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			  
		  }
		  else
		  {
			  Toast.makeText(getApplicationContext(), "Your image is not uploaded.", Toast.LENGTH_SHORT).show();
		  }
	    
	  
	  }
	});
  }
  private void captureImage() {
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	 
	    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	//  filePath=fileUri.getPath();
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 
	    // start the image capture Intent
	    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	}
  
  private boolean isDeviceSupportCamera() {
      if (getApplicationContext().getPackageManager().hasSystemFeature(
              PackageManager.FEATURE_CAMERA)) {
          // this device has a camera
          return true;
      } else {
          // no camera on this device
          return false;
      }
  }
  
  private void previewCapturedImage() {
      try {
          // hide video preview
   //       videoPreview.setVisibility(View.GONE);

          imgPreview.setVisibility(View.VISIBLE);

          // bimatp factory
          BitmapFactory.Options options = new BitmapFactory.Options();

          // downsizing image as it throws OutOfMemory Exception for larger
          // images
          options.inSampleSize = 8;

          final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                  options);

          imgPreview.setImageBitmap(bitmap);
      } catch (NullPointerException e) {
          e.printStackTrace();
      }
  }
  
  public Uri getOutputMediaFileUri(int type) {
      return Uri.fromFile(getOutputMediaFile(type));
  }

  /**
   * returning image / video
   */
  private static File getOutputMediaFile(int type) {

      // External sdcard location
      File mediaStorageDir = new File(
              Environment
                      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
              IMAGE_DIRECTORY_NAME);

      // Create the storage directory if it does not exist
      if (!mediaStorageDir.exists()) {
          if (!mediaStorageDir.mkdirs()) {
              Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                      + IMAGE_DIRECTORY_NAME + " directory");
              return null;
          }
      }

      // Create a media file name
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
              Locale.getDefault()).format(new Date());
    
      if (type == MEDIA_TYPE_IMAGE) {
         
    	  mediaFile = new File(mediaStorageDir.getPath() + File.separator
                  + "IMG_" + timeStamp + ".jpg");
          System.out.println(mediaFile);
          //filePath=mediaFile.toString();
          
  	      
      }  else {
          return null;
      }

      return mediaFile;
  }


@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	
}

/*this is code of autocomp
@Override
public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
	// TODO Auto-generated method stub
	String str = (String) adapterView.getItemAtPosition(position);
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	
}
*/
 

/*
private class PlacesTask extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... place) {
        // For storing data from web service
        String data = "";
       
        // Obtain browser key from https://code.google.com/apis/console
        String key = "key=AIzaSyCfdXATlz7jtM6MEvy9Xh_3_g_Ivc5ysXE";
       
        String input="";
       
        try {
            input = "input=" + URLEncoder.encode(place[0], "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }       
       
       
        // place type to be searched
        String types = "types=geocode";
       
        // Sensor enabled
        String sensor = "sensor=false";           
       
        // Building the parameters to the web service
        String parameters = input+"&"+types+"&"+sensor+"&"+key;
       
        // Output format
        String output = "json";
       
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;

        try{
            // Fetching the data from web service in background
            data = downloadUrl(url);
        }catch(Exception e){
            Log.d("Background Task",e.toString());
        }
        return data;       
    }
   
    @Override
    protected void onPostExecute(String result) {           
        super.onPostExecute(result);
       
        // Creating ParserTask
        parserTask = new ParserTask();
       
        // Starting Parsing the JSON string returned by Web Service
        parserTask.execute(result);
    }       
}

// A class to parse the Google Places in JSON format 
private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

    JSONObject jObject;
   
    @Override
    protected List<HashMap<String, String>> doInBackground(String... jsonData) {           
       
        List<HashMap<String, String>> places = null;
       
        PlaceJSONParser placeJsonParser = new PlaceJSONParser();
       
        try{
            jObject = new JSONObject(jsonData[0]);
           
            // Getting the parsed data as a List construct
            places = placeJsonParser.parse(jObject);

        }catch(Exception e){
            Log.d("Exception",e.toString());
        }
        return places;
    }
   
    @Override
    protected void onPostExecute(List<HashMap<String, String>> result) {           
       
            String[] from = new String[] { "description"};
            int[] to = new int[] { android.R.id.text1 };
           
            // Creating a SimpleAdapter for the AutoCompleteTextView           
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);               
           
            // Setting the adapter
            atvPlaces.setAdapter(adapter);
    }           
}   


public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.first, menu);
    return true;
}
*/
}
  

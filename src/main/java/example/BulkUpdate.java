
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONObject;

URL url = new URL("http://localhost:9200/index/_bulk/");
HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
httpCon.setDoOutput(true);
httpCon.setRequestMethod("POST");
httpCon.setRequestProperty("Content-Type", "text/javascript"); // (or text/plain)
httpCon.setRequestProperty("Accept", "application/json");
OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());


public BulkUpdate POST _Bulk {
    StringBuilder sb = new StringBuilder();
            // iterate over each record from Firehose
			for (JSONObject obj : recordList) {
                
                // Generate Bulk update String 
                // format { "update" : {"_index" : "index1", "_id" : "1"} }
                //        { "doc" : {"field" : "value"} }
                // Index on "find_by", which contains "unique_key" and append {"field":"value"} for each as the "payload" from Firehose records
				sb.append("{update\":{\"index\":\"").append(obj.get("unique_key")).append("\"}{\"_id\":\"\"}}");
				sb.append("\n");
				sb.append("{\"doc\":").append(obj.get("payload").toString());
				sb.append("\n");
			out.write(sb);	
			out.flush();
		}

		out.close();
	}

		    

 

		
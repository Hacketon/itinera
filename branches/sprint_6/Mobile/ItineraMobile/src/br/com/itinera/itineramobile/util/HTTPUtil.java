package br.com.itinera.itineramobile.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HTTPUtil {
	public static String doGet(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = null;
		try {
			httpGet = new HttpGet(url);
			HttpResponse response;
			response = httpClient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
						throw new Exception("Acesso n‹o autorizado");
					} else {
						throw new IOException("Erro HTTP " + statusCode);
					}
				}
				return EntityUtils.toString(entity);
			}
		} catch (IOException ex) {
			if (httpGet != null) {
				httpGet.abort();
			}
			throw ex;
		}
		return null;
	}
}


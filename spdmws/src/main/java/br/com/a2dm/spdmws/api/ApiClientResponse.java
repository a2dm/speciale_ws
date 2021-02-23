package br.com.a2dm.spdmws.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class ApiClientResponse {

	private int status;
	private boolean isOk;
	private String body;
	
	private ApiClientResponse() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isOk() {
		return this.status == 200 || this.status == 201;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return String.format("ApiResponse [status=%s, isOk=%s, body=%s]", status, isOk, body);
	}
	
	public static class Builder {
		
		public Builder() {
		}
		
		public ApiClientResponse build(CloseableHttpResponse response) {
			try {
				ApiClientResponse apiResponse = new ApiClientResponse();
				apiResponse.setBody(EntityUtils.toString(response.getEntity()));
				apiResponse.setStatus(response.getStatusLine().getStatusCode());
				return apiResponse;
			} catch (Exception e) {
				throw new ApiClientException(e);
			}
		}
	}
}

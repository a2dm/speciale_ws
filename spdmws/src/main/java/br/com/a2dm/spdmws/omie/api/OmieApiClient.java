package br.com.a2dm.spdmws.omie.api;

import br.com.a2dm.spdmws.api.ApiClient;
import br.com.a2dm.spdmws.api.ApiClientException;
import br.com.a2dm.spdmws.api.ApiClientResponse;
import br.com.a2dm.spdmws.utils.JsonUtils;

public class OmieApiClient {

	private static final String OMIE_ENDPOINT = "https://app.omie.com.br/api/v1";
	private static final String OMIE_APP_KEY = "1141021192311";
	private static final String OMIE_APP_SECRET = "9e106b7d3ead5daf61829c5224e3e742";
	private ApiClient apiClient;

	public OmieApiClient() {
		this.apiClient = new ApiClient(OMIE_ENDPOINT);
	}

	public ApiClientResponse post(String resource, String call, Object param) throws ApiClientException{
		try {
			OmieApiPayload apiPayload = new OmieApiPayload();
			apiPayload.setApp_key(OMIE_APP_KEY);
			apiPayload.setApp_secret(OMIE_APP_SECRET);
			apiPayload.setCall(call);
			apiPayload.addParam(param);
			ApiClientResponse response = this.apiClient.post(resource, apiPayload);
			if (!response.isOk()) {
				throwApiException(response);
			}
			return response;
		} catch (Exception e) {
			throw new ApiClientException(e);
		}
	}
	
	protected void throwApiException(ApiClientResponse apiResponse) throws ApiClientException {
		OmieApiError omieApiError = JsonUtils.fromJson(apiResponse.getBody(), OmieApiError.class);
		throw new ApiClientException(omieApiError.getFaultstring());
	}

}

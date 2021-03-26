package br.com.a2dm.spdmws.omie.payload;

public class ClienteWebhookPayload {

	private String messageId;
	private String topic;
	private EventPayload event;
	private AuthorPayload author;
	private String appKey;
	private String appHash;
	private String origin;

	public ClienteWebhookPayload() {
		super();
	}

	public ClienteWebhookPayload(String messageId, String topic, EventPayload event, AuthorPayload author,
			String appKey, String appHash, String origin) {
		super();
		this.messageId = messageId;
		this.topic = topic;
		this.event = event;
		this.author = author;
		this.appKey = appKey;
		this.appHash = appHash;
		this.origin = origin;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public EventPayload getEvent() {
		return event;
	}

	public void setEvent(EventPayload event) {
		this.event = event;
	}

	public AuthorPayload getAuthor() {
		return author;
	}

	public void setAuthor(AuthorPayload author) {
		this.author = author;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppHash() {
		return appHash;
	}

	public void setAppHash(String appHash) {
		this.appHash = appHash;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		return "ClienteWebhookPayload [messageId=" + messageId + ", topic=" + topic + ", event=" + event + ", author="
				+ author + ", appKey=" + appKey + ", appHash=" + appHash + ", origin=" + origin + "]";
	}

}

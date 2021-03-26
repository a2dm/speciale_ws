package br.com.a2dm.spdmws.omie.payload;

public class TagPayload {

	private String tag;

	public TagPayload() {
		super();
	}

	public TagPayload(String tag) {
		super();
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "TagPayload [tag=" + tag + "]";
	}
}

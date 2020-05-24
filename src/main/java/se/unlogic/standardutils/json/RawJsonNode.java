package se.unlogic.standardutils.json;

public class RawJsonNode implements JsonNode {

	private static final long serialVersionUID = 1915963798350627455L;

	public String rawJson;
	
	public RawJsonNode() { }
	
	public RawJsonNode(String rawJson) {
		
		this.rawJson = rawJson;
	}

	@Override
	public String toJson() {

		return rawJson;
	}

	@Override
	public void toJson(StringBuilder stringBuilder) {

		stringBuilder.append(rawJson);
	}

	public void setRawJson(String rawJson) {

		this.rawJson = rawJson;
	}
}
/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArray implements JsonNode {

	private static final long serialVersionUID = 1497880281195635145L;
	private final List<JsonNode> nodes;

	public JsonArray() {

		nodes = new ArrayList<JsonNode>();
	}

	public JsonArray(int initialCapacity) {

		nodes = new ArrayList<JsonNode>(initialCapacity);
	}

	@Override
	public String toJson() {

		StringBuilder builder = new StringBuilder();
		toJson(builder);
		return builder.toString();
	}

	@Override
	public void toJson(StringBuilder stringBuilder) {

		stringBuilder.append("[");

		Iterator<JsonNode> iterator = nodes.iterator();

		JsonNode node;
		while (iterator.hasNext()) {
			node = iterator.next();

			node.toJson(stringBuilder);

			if (iterator.hasNext()) {
				stringBuilder.append(",");
			}
		}
		stringBuilder.append("]");
	}

	public void addNode(JsonNode node) {

		this.nodes.add(node);
	}

	public void addNode(Object value) {

		this.nodes.add(new JsonLeaf(value));
	}

	public void removeNode(JsonNode node) {

		this.nodes.remove(node);
	}

	public void clearNodes() {

		this.nodes.clear();
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		JsonArray other = (JsonArray) obj;
		if (nodes == null) {
			if (other.nodes != null) {
				return false;
			}
		} else if (!nodes.equals(other.nodes)) {
			return false;
		}
		return true;
	}

	public int size() {

		return nodes.size();
	}
	
	public boolean isEmpty() {
		
		return nodes.isEmpty();
	}

	public List<JsonNode> getNodes() {

		return nodes;
	}
}
package org.adanimr.serialisation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MyPojo")
public class MyPojo {

	private String name;
	
	public String getName() {
		return name;
	}
	
	@XmlElement(name="name")
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyPojo [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}
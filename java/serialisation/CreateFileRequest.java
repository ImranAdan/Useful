package org.adanimr.serialisation;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CreateFileRequest")
public class CreateFileRequest {
	
	private String name;
	private byte[] content;
	
	
	public String getName() {
		return name;
	}
	
	@XmlElement(name="name")
	public void setName(final String name) {
		this.name = name;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	@XmlElement(name="content")
	public void setContent(final byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CreateFileRequest [name=" + name + ", content="
				+ Arrays.toString(content) + ", getName()=" + getName()
				+ ", getContent()=" + Arrays.toString(getContent())
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}

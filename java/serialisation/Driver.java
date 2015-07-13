package org.adanimr.serialisation;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Driver {

	public static void main(String... args) throws IOException, JAXBException {

		MyPojo request = new MyPojo();
		request.setName("Example Text");

		JAXBContext context = JAXBContext.newInstance(MyPojo.class);
		Marshaller marshaller = context.createMarshaller();

		StringWriter sw = new StringWriter();
		marshaller.marshal(request, sw);
		String xmlString = sw.toString();

		System.out.println(request.toString());
		System.out.println(xmlString);

	}
}

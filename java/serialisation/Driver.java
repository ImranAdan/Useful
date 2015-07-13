package org.adanimr.serialisation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Driver {

	public static void main(String ... args) throws IOException, JAXBException{
		
		CreateFileRequest request = new CreateFileRequest();
		request.setName("Example Text");
		byte[] content = Files.readAllBytes(Paths.get("/Users/Imran/Desktop/example.txt"));
		request.setContent(content);
		
		
		JAXBContext context = JAXBContext.newInstance(CreateFileRequest.class);
		Marshaller marshaller = context.createMarshaller();
		
		StringWriter sw = new StringWriter();
		marshaller.marshal(request, sw);
		String xmlString = sw.toString();
		
		System.out.println(request.toString());
		System.out.println(xmlString);
		
		
		
	    FileOutputStream fileOuputStream = new FileOutputStream("/Users/Imran/Desktop/example_NEW.txt"); 
	    fileOuputStream.write(request.getContent());
	    fileOuputStream.close();

		
	}
}

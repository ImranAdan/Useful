package org.useful.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Main {

    private static final String CONFIG_FILE_LOCATION = "/src/main/resources/config.xml";
    private final String whatIsInjected;

    public Main(String whatIsInjected) {
        this.whatIsInjected = whatIsInjected;
    }

    public static void main(String ... args){
        ApplicationContext apc = new FileSystemXmlApplicationContext(CONFIG_FILE_LOCATION);
        Main main = apc.getBean("example", Main.class);
        main.printWhatIsInjected();
    }

    private void printWhatIsInjected() {
        System.out.println(whatIsInjected);
    }

    public String getWhatIsInjected() {
        return whatIsInjected;
    }
}

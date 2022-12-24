package util;


import model.SummaryInfo;
import org.apache.logging.log4j.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class XmlWriter {
    private static final Logger logger = LogManager.getLogger(XmlWriter.class.getName());

    private XmlWriter() {
    }

    public static void generateXmlReq(SummaryInfo fullInfo) {

        try {
            logger.log(Level.INFO, "XML marshalling started");

            JAXBContext jaxbContext = JAXBContext.newInstance(SummaryInfo.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try {
                Path xmlReqs = Paths.get("XMLs");
                if (Files.notExists(xmlReqs)) {
                    Files.createDirectory(xmlReqs);
                    logger.log(Level.INFO, "Directory created successfully");
                }
            } catch (IOException ioEx) {
                logger.log(Level.INFO, "Directory already created", ioEx);
            }
            File requestFile = new File("XMLs/XMLSummary" + new Date().getTime() + ".xml");

            marshaller.marshal(fullInfo, requestFile);
        } catch (JAXBException jaxbEx) {
            logger.log(Level.FATAL, "XML marshalling failed", jaxbEx);
            return;
        }
        logger.log(Level.INFO, "XML marshalling finished successfully");
    }
}

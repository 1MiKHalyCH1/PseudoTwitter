package ru.urfu.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.serializers.*;
import ru.urfu.model.InMemoryMessageDao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class SerializeController {
    @Inject
    InMemoryMessageDao messagesStorage;

    @RequestMapping(value = "/serialize/{type}", method = RequestMethod.GET)
    RedirectView csv_serialize(@PathVariable("type") String type, HttpServletResponse response) {
        try
        {
            ISerializer serializer = null;
            switch (type) {
                case "csv":
                    serializer = new CsvSerializer();
                    break;
                case "xml":
                    serializer = new XmlSerializer();
                    break;
                case "xls":
                    serializer = new XlsSerializer();
                    break;
                case "binary":
                    serializer = new BinarySerializer();
                    break;
            }
            if (serializer != null) {
                String filename = serializer.serialize(messagesStorage.findAll());

                File file = new File(filename);
                InputStream inputStream = new FileInputStream(file);
                OutputStream outStream = response.getOutputStream();

                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"" + file.getName() + "\"");
                response.setHeader(headerKey, headerValue);

                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/");
    }
}

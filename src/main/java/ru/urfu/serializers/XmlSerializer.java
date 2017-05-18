package ru.urfu.serializers;

import ru.urfu.entities.Message;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class XmlSerializer implements ISerializer {
    @Override
    public String serialize(List<Message> messages) {
        String filename = LocalDate.now().toString() + ".xml";
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
            encoder.writeObject(messages);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
package ru.urfu.serializers;

import ru.urfu.entities.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;

public class BinarySerializer implements ISerializer {
    @Override
    public String serialize(List<Message> messages) {
        String filename = LocalDate.now().toString() + ".bin";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(messages);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}

package ru.urfu.serializers;

import ru.urfu.entities.Message;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;

public class CsvSerializer implements ISerializer  {
    private static final String CSV_SEPARATOR = ",";

    public String serialize(List<Message> messages)
    {
        String filename = LocalDate.now().toString() + ".csv";
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            for (Message msg : messages)
            {
                String oneLine =
                        String.valueOf(msg.getId()) +
                        CSV_SEPARATOR +
                        msg.getMessage().trim() +
                        CSV_SEPARATOR +
                        msg.getAuthorName();
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}

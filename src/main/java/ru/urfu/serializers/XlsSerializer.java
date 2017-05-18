package ru.urfu.serializers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.urfu.entities.Message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class XlsSerializer implements ISerializer{
    @Override
    public String serialize(List<Message> messages) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Messages");

        for (int i = 0; i < messages.size(); ++i){
            Message msg = messages.get(i);
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(msg.getId());
            row.createCell(1).setCellValue(msg.getMessage());
            row.createCell(2).setCellValue(msg.getAuthorName());
        }
        String filename = LocalDate.now().toString() + ".xls";
        try {
            book.write(new FileOutputStream(filename));
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}

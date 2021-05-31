package me.byungjin.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

import com.mongodb.client.FindIterable;

import me.byungjin.Managers.Chef;
import me.byungjin.Managers.DBManager;

public class XFile {
	private File xlsxfile = null;
	private String path = "./";
	private FileOutputStream outStream = null;		
	
	public void saveXlse(String channel) {
		
		xlsxfile = new File(path+channel+".xlsx");
		
		FindIterable<Document> collectionData = DBManager.getCollectionData(channel);		
		
		
		
		XSSFWorkbook xworkbook = new XSSFWorkbook();
		//Channel Sheet
		XSSFSheet channelSheet = xworkbook.createSheet("Channel");
		XSSFSheet userSheet = xworkbook.createSheet("User");
		XSSFFont font = xworkbook.createFont();
		font.setBold(true);
		CellStyle style = xworkbook.createCellStyle();
		style.setFont(font);
		style.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFRow currentRow;
		Cell cell = null;
		
		final int TIME = 0, PLAYING = 1, TOTAL_COUNTS = 2, CHATTER_COUNTS = 3, CHAT_COUNTS = 4, MATURE = 5, USER = 0, CHATS = 1, LEN = 2;		
		
		//Title
		currentRow = channelSheet.createRow(0);
		cell = currentRow.createCell(TIME);
		cell.setCellValue("Time");
		cell.setCellStyle(style);
		cell = currentRow.createCell(PLAYING);
		cell.setCellValue("Playing");
		cell.setCellStyle(style);
		cell = currentRow.createCell(TOTAL_COUNTS);
		cell.setCellValue("Total_counts");
		cell.setCellStyle(style);
		cell = currentRow.createCell(CHATTER_COUNTS);
		cell.setCellValue("Chatter_counts");
		cell.setCellStyle(style);
		cell = currentRow.createCell(CHAT_COUNTS);
		cell.setCellValue("Chat_counts");
		cell.setCellStyle(style);
		cell = currentRow.createCell(MATURE);
		cell.setCellValue("mature");
		cell.setCellStyle(style);
		
		currentRow = userSheet.createRow(0);
		cell = currentRow.createCell(USER);
		cell.setCellValue("User");
		cell.setCellStyle(style);
		cell = currentRow.createCell(CHATS);
		cell.setCellValue("Chats");
		cell.setCellStyle(style);
		cell = currentRow.createCell(LEN);
		cell.setCellValue("Length");
		cell.setCellStyle(style);
		
		
		int currentRow_count = 1, current_user_count = 1;
		Document record;		
		for(Document f : collectionData) {
			record = (Document) f.get("records");
			currentRow = channelSheet.createRow(currentRow_count);
			cell = currentRow.createCell(TIME);
			cell.setCellValue(record.getString("Time"));
			cell = currentRow.createCell(PLAYING);
			cell.setCellValue(record.getString("Playing"));
			cell = currentRow.createCell(TOTAL_COUNTS);
			cell.setCellValue(record.getInteger("Total_counts"));
			cell = currentRow.createCell(CHATTER_COUNTS);
			cell.setCellValue(record.getInteger("Chatter_counts"));
			cell = currentRow.createCell(CHAT_COUNTS);
			cell.setCellValue(record.getInteger("Chat_counts"));
			cell = currentRow.createCell(MATURE);
			cell.setCellValue(record.getBoolean("mature"));
						
			for(Document user : (List<Document>) record.get("Chatters")) {
				currentRow = userSheet.createRow(current_user_count);
				cell = currentRow.createCell(USER);
				cell.setCellValue(user.getString("user"));
				cell = currentRow.createCell(CHATS);
				cell.setCellValue(user.getInteger("chats"));
				cell = currentRow.createCell(LEN);
				cell.setCellValue(user.getInteger("len"));
				current_user_count++;
			}			
			currentRow_count++;
		}
		
		try {
			outStream = new FileOutputStream(xlsxfile);
			xworkbook.write(outStream);
			outStream.close();
		}catch(Exception e) {
			Chef.pushString("XLSX", e.getMessage(), true);
		}		
	}
}

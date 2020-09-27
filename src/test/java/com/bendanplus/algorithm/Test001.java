package com.bendanplus.algorithm;

import com.alibaba.excel.EasyExcel;


import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
public class Test001 {

	public static void main(String[] args) {
		String fileName = "/Users/moriu/Desktop/sssssss.xlsx";
		// 这⾥ 需要指定写⽤哪个class去写，然后写到第⼀个sheet，名字为模板 然后⽂件流会⾃动关闭
		// 如果这⾥想使⽤03 则 传⼊excelType参数即可
		EasyExcel
				.write(fileName, DemoData.class)
				.sheet("注解⽅式")
				.registerWriteHandler(createTableStyle())// Excel 表格样式
				.doWrite(dataList());
	}

	@Test
	public void test() {
		String fileName = "/Users/moriu/Desktop/sssssss.xlsx";
		// 这⾥ 需要指定写⽤哪个class去写，然后写到第⼀个sheet，名字为模板 然后⽂件流会⾃动关闭
		// 如果这⾥想使⽤03 则 传⼊excelType参数即可
		EasyExcel
				.write(fileName, DemoData.class)
				.sheet("注解⽅式")
				.registerWriteHandler(createTableStyle())// Excel 表格样式
				.doWrite(dataList());
	}


	/***
	 * 设置 excel 的样式
	 * @return
	 */
	private static WriteHandler createTableStyle() {
		// 头的策略
		WriteCellStyle headWriteCellStyle = new WriteCellStyle();
		// 背景设置为红⾊
		headWriteCellStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
		// 设置字体
		WriteFont headWriteFont = new WriteFont();
		headWriteFont.setFontHeightInPoints((short) 20);
		headWriteCellStyle.setWriteFont(headWriteFont);
		// 内容的策略
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
		// 这⾥需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然⽆法显示背景颜⾊.头默认了 FillPatternType所以可以不指定
		contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
		// 背景绿⾊
		contentWriteCellStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		WriteFont contentWriteFont = new WriteFont();
		// 字体⼤⼩
		contentWriteFont.setFontHeightInPoints((short) 20);
		contentWriteCellStyle.setWriteFont(contentWriteFont);
		// 设置边框的样式
		contentWriteCellStyle.setBorderBottom(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderLeft(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderRight(BorderStyle.DASHED);
		contentWriteCellStyle.setBorderTop(BorderStyle.DASHED);
		// 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以⾃⼰实现
		HorizontalCellStyleStrategy horizontalCellStyleStrategy =
				new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
		return horizontalCellStyleStrategy;
	}


	private static List dataList() {
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (int i = 0; i < 10; i++) {
			List<Object> data = new ArrayList<Object>();
			data.add("点赞+" + i);
			// date 将会安装 yyyy-MM-dd HH:mm:ss 格式化
			data.add(new Date());
			data.add(0.56);
			list.add(data);
		}
		return list;
	}
}
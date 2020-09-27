package com.bendanplus.algorithm;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

@ContentRowHeight(30)// 表体⾏⾼
@HeadRowHeight(20)// 表头⾏⾼
@ColumnWidth(35)// 列宽
@Data
public class DemoData {
	/**
	 * 单独设置该列宽度
	 */
	@ColumnWidth(50)
	@ExcelProperty("字符串标题")
	private String string;
	/**
	 * 年⽉⽇时分秒格式
	 */
	@DateTimeFormat("yyyy年MM⽉dd⽇HH时mm分ss秒")
	@ExcelProperty(value = "⽇期标题")
	private Date date;
	/**
	 * 格式化百分⽐
	 */
	@NumberFormat("#.##%")
	@ExcelProperty("数字标题")
	private Double doubleData;
	/**
	 * 忽略这个字段
	 */
	@ExcelIgnore
	private String ignore;
}

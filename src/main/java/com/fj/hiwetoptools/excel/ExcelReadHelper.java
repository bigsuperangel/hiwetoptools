/**
 * ExeclImportHelper.java类：本类位于insurance项目中的
 * com.magonchina.util包路径下，
 * 类具体的作用请参看代码中的文档注释。
 * 
 * Created By 赵海龙
 */
package com.fj.hiwetoptools.excel;

import com.fj.hiwetoptools.CollectionUtil;
import com.fj.hiwetoptools.JsonUtil;
import com.fj.hiwetoptools.ObjectUtil;
import com.fj.hiwetoptools.exception.bean.ExcelException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**.Excel导入工具类
 * @since 2016年5月13日 下午2:41:07
 */
public class ExcelReadHelper {
    private Workbook workBook = null;
    private List<String[]> excelData = null;
    private String filePath;
    
    /**.
     * 构造方法：
     * @param filePath
     * @throws IOException 
     * @throws InvalidFormatException 
     * @throws EncryptedDocumentException 
     */
    public ExcelReadHelper(String filePath) throws ExcelException {
        super();
        this.filePath = filePath;
        initWorkBook();
    }
    
    
    /**方法：获取workbook实例
\     * @since 2016年5月16日 下午3:20:20
     * @return
     */
    public Workbook getWorkBook(){
        return this.workBook;
    }
    /**.
     * 方法：初始化workBook实例
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     * @throws IOException
     */
    private void initWorkBook() throws ExcelException {
        if(filePath == null || filePath.trim().length() <= 0){
            throw new ExcelException("文件路径不能为空!");
        }
        File file = new File(filePath);
        FileInputStream fs = null;
        if(!file.exists()) throw new ExcelException("文件不存在!");
        if(!suffixCheck(filePath))  throw new ExcelException("文件不合法的excel类型!");
        try {
			fs = new FileInputStream(file);
			workBook = WorkbookFactory.create(fs);
		} catch (Exception e) {
			throw new ExcelException("初始化失败",e);
		}
    }
    /**
     * . 方法：获取导入Excel中的列数
     * @param sheetIndex
     * @return
     */
    public int getColumnNum(int sheetIndex, int columnIdex) {
        Sheet sheet = workBook.getSheetAt(sheetIndex);
        return sheet.getRow(columnIdex).getLastCellNum()
                - sheet.getRow(columnIdex).getFirstCellNum();
    }
    
    /**.
     * 方法：读取Excel内容
     * @param sheetIndex 需要读取的sheet索引
     * @param title Excel表头，即：表格第一行的数据内容
     * @param rowMapper 数据处理接口
     * @throws Exception 
     */
    public <T> List<T> readExcel(int sheetIndex, String[] title, ReadRowMapper<T> rowMapper) throws ExcelException  {
        Sheet sheet = workBook.getSheetAt(sheetIndex);
        List<T> list = new ArrayList<T>();
        for (Row row : sheet) {
            if(title == null && row.getRowNum() == 0){
                title = this.getRowContent(row);
                continue;
            }
            if (row == null){
                continue;
            }
            Map<String,Object> map = this.rowToMap(row, title);
            if (CollectionUtil.isEmpty(map) || isEmptyMap(map)) {
                continue;
            }
            list.add(rowMapper.rowMap(row,map));
        }
        return list;
    }

    private boolean isEmptyMap(Map<String,Object> map){
        boolean isEmpty = false;
        for (String s : map.keySet()) {
            boolean flag = ObjectUtil.isEmpty(map.get(s));
            if (flag) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }
    
    /**
     * . 方法：读取指定Excel的内容
     * 
     * @param sheetIndex
     * @return
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     * @throws IOException
     */
    public List<String[]> readToStringList(int sheetIndex) throws EncryptedDocumentException,
            InvalidFormatException, IOException {
        excelData = new ArrayList<String[]>(100);
        Sheet sheet = workBook.getSheetAt(sheetIndex);
        for (Row row : sheet) {
            excelData.add(this.getRowContent(row));
        }
        return excelData;
    }
    
    /**.
     * 方法：将Excel的每行数据转换为map，key为第一行数据的值
     * @param row
     * @param title 指定Excel的标题
     * @return
     */
    public Map<String,Object> rowToMap(Row row,String[] title){
        if(title == null && row.getRowNum() == 0){
            title = this.getRowContent(row);
        }
        int columnNum = title.length;
        int count = 0;
        
        Map<String,Object> map = new HashMap<String, Object>(columnNum);
        for (int i = 0; i < columnNum; i++) {
            Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                map.put(title[i], "");
                count +=1;
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                map.put(title[i], cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    map.put(title[i], cell.getDateCellValue());
                } else {
                    map.put(title[i], cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                map.put(title[i], cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                map.put(title[i], "");
                break;
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                map.put(title[i], cell.getStringCellValue());
                break;
            default:
                map.put(title[i], "");
                break;
            }
        }

        //判断是否整行都是空行,比如用del删除行，row不为空
        if (count == columnNum){
            return null;
        }
        return map;
    }
    
    /**
     * . 方法：获取每一行的内容
     * 
     * @param row
     *            列数
     * @return
     */
    public String[] getRowContent(Row row) {
        int columnNum = row.getLastCellNum() - row.getFirstCellNum();
        String[] singleRow = new String[columnNum];
        for (int i = 0; i < columnNum; i++) {
            Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                singleRow[i] = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                singleRow[i] = Boolean.toString(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (DateUtil.isCellDateFormatted(cell)) {
                    singleRow[i] = format.format(cell.getDateCellValue());
                } else {
                    singleRow[i] = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:
                singleRow[i] = cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_ERROR:
                singleRow[i] = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                singleRow[i] = cell.getStringCellValue();
                if (singleRow[i] != null) {
                    singleRow[i] = singleRow[i].replaceAll("#N/A", "").trim();
                }
                break;
            default:
                singleRow[i] = "";
                break;
            }
        }
        return singleRow;
    }
    
    /**
     * . 方法：验证文件结尾是否为xls或者xlsx
     * 
     * @param fileName
     * @return
     */
    private boolean suffixCheck(String fileName) {
        if (fileName.lastIndexOf(".") > -1) {
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            return suffix.matches("xls|xlsx$");
        }
        return false;
    }
}
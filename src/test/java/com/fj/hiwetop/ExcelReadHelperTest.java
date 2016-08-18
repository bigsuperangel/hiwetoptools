package com.fj.hiwetop;

import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fj.hiwetoptools.excel.ExcelReadHelper;
import com.fj.hiwetoptools.excel.ReadRowMapper;

public class ExcelReadHelperTest {
    
    private  ExcelReadHelper read = null;
    
    /**.
     * 方法：
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        read = new ExcelReadHelper("./src/test/resources/file.xlsx");
    }

    @Test
    public void testReadExcel() {
        try {
            List<School> list = read.readExcel(0,null, new ReadRowMapper<School>() {
                public School rowMap(Row row, Map<String, Object> map) throws Exception {
                    School school = new School();
                    if(((Double)map.get("学校标号")).intValue()==573){
                    	throw new Exception("失败");
                    }
                    school.setSchoolNumber(((Double)map.get("学校标号")).intValue());
                    school.setSchoolName((String)map.get("校名"));
                    school.setProvince((String)map.get("所在省"));
                    school.setCity((String)map.get("所在市"));
                    return school;
                }
            });
            Assert.assertArrayEquals("测试未通过", new Integer[]{81}, new Integer[]{list.size()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class School{
    private int schoolNumber;
    private String schoolName;
    private String province;
    private String city;
    public int getSchoolNumber() {
        return schoolNumber;
    }
    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "School [schoolNumber=" + schoolNumber + ", schoolName=" + schoolName
                + ", province=" + province + ", city=" + city + "]";
    }
}
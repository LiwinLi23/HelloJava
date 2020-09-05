package com.liwinli.utils.office;

import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;
import com.liwinli.app.xiaolingent.customer.analysis.model.AliBrushOrderModel;
import com.liwinli.app.xiaolingent.customer.analysis.model.LTCityLevelModel;
import com.liwinli.utils.log.Logable;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CityLevelHandler extends Logable {
    private List<String> columnNameList = new ArrayList<String>();
    private static List<LTCityLevelModel> ltCityLevelModels = new ArrayList<LTCityLevelModel>();

    public void parse(File excelFile) throws IOException {
        info("Parse citylevel file: {}", excelFile.getName());
        FileInputStream inputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        int sheetNum = workbook.getNumberOfSheets();
        info("WorkBook sheet number: {}", sheetNum);
        for (int i = 0; i < sheetNum; ++i) {
            String sheetName = workbook.getSheetName(i);
            info("Sheet[{}] name: {}", i, sheetName);
            if (!sheetName.trim().equals(Constants.CITY_LEVEL_SHEET_KEYWORD)) {
                continue;
            }

            columnNameList = new ArrayList<String>();
            Sheet curSheet = workbook.getSheetAt(i);
            int rowIndex = 0;
            Iterator<Row> rowIterator = curSheet.iterator();
            while (rowIterator.hasNext()) {
                Map<String, String> rowMap = new HashMap<String, String>();
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            String cellValue = cell.getStringCellValue();
                            if (null == cellValue) {
                                info("[{}, {}] 内容是空", rowIndex, cellIndex);
                                ++cellIndex;
                                continue;
                            }
                            if (cellIndex >= Constants.CITY_LEVEL_COLUMNS.length) {
                                warn("Cell index: {} >= 城市等级的列数量");
                                break;
                            }

                            if (0 == rowIndex) {
                                if (Arrays.asList(Constants.CITY_LEVEL_COLUMNS).contains(cellValue)) {
                                    columnNameList.add(cellValue);
                                } else {
                                    warn("列[{}] 名字: {} 不在指定的列名中", cellIndex, cellValue);
                                }
                            } else {
                                if (cellIndex < columnNameList.size()) {
                                    String columnName = columnNameList.get(cellIndex);
                                    if ((cellIndex >= 2 && cellIndex <= 4) || (6 == cellIndex)) {
                                        rowMap.put(columnName, cellValue);
                                    }

                                    if (6 == cellIndex) {
                                        info("ColumnName: {}, value: {}", columnName, cellValue);
                                    }
                                }
                            }
                            ++cellIndex;
                            break;

                        case BOOLEAN:
                            ++cellIndex;
                            break;

                        case NUMERIC:
                            if (cellIndex < columnNameList.size()) {
                                if (6 == cellIndex) {
                                    String columnName = columnNameList.get(cellIndex);
                                    double  cellDoubleValue = cell.getNumericCellValue();
                                    rowMap.put(columnName, String.format("%f", cellDoubleValue));
                                }
                            }
                            ++cellIndex;
                            break;

                        default:
                            ++cellIndex;
                            break;
                    }

                    if (cellIndex >= Constants.CITY_LEVEL_COLUMNS.length) {
                        break;
                    }
                }

                if (0 == rowIndex) {
                    info("该Excel 列为： {}", columnNameList);
                } else {
                    String province     = rowMap.get(Constants.CITY_LEVEL_COLUMNS[2]);
                    String city         = rowMap.get(Constants.CITY_LEVEL_COLUMNS[3]);
                    String district     = rowMap.get(Constants.CITY_LEVEL_COLUMNS[4]);
                    String cityLevel    = rowMap.get(Constants.CITY_LEVEL_COLUMNS[6]);
                    if (StringUtils.isBlank(province) || StringUtils.isBlank(city) ||
                            StringUtils.isBlank(district) || StringUtils.isBlank(cityLevel)) {
                        continue;
                    }

                    LTCityLevelModel ltCityLevelModel = new LTCityLevelModel();
                    ltCityLevelModel.city = city; ltCityLevelModel.district = district;
                    ltCityLevelModel.province = province; ltCityLevelModel.cityLevel = Double.parseDouble(cityLevel);

                    ltCityLevelModels.add(ltCityLevelModel);
                }

                ++rowIndex;
            }
        }

        workbook.close();
        inputStream.close();
    }
}

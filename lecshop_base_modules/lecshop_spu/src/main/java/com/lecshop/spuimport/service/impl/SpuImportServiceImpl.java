package com.lecshop.spuimport.service.impl;

import com.lecshop.spuimport.bean.SpuImport;
import com.lecshop.spuimport.mapper.SpuImportMapper;
import com.lecshop.spuimport.service.SpuImportService;
import com.lecshop.util.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dujinkai on 17/5/18.
 * 商品导入服务实现接口
 */
@Service
public class SpuImportServiceImpl implements SpuImportService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuImportServiceImpl.class);

    /**
     * 注入商品导入数据库接口
     */
    @Autowired
    private SpuImportMapper spuImportMapper;

    @Override
    public PageHelper<SpuImport> querySpuImports(PageHelper<SpuImport> pageHelper, String name) {
        logger.debug("querySpuImports and pageHelper:{} \r\n name:{}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(spuImportMapper.querySpuImports(pageHelper.getQueryParams(params, spuImportMapper.querySpuImportCount(params))));
    }

    @Override
    public int deleteSpuImport(long id) {
        logger.debug("deleteSpuImport and id :{}", id);
        return spuImportMapper.deleteSpuImport(Arrays.asList(id));
    }

    @Override
    public int deleteSpuImports(List<Long> ids) {
        logger.debug("deleteSpuImports and ids :{}", ids);
        return spuImportMapper.deleteSpuImport(ids);
    }

    @Override
    public SpuImport querySpuImprotById(Long id) {
        logger.debug("querySpuImprotById and id :{}", id);
        if (Objects.isNull(id)) {
            return SpuImport.buildEmpty();
        }
        return spuImportMapper.querySpuImprotById(id);
    }

    @Override
    public void updateToRelease(long id) {
        logger.debug("updateToRelease and id :{}", id);

        if (0 == id) {
            logger.warn("do not need to updateToRelease");
            return;
        }
        spuImportMapper.updateToRelease(id);
    }


    @Override
    public int importSpu(InputStream inputStream) {
        logger.debug("begin to importSpu....");

        List<SpuImport> spuImports = getSpuImports(inputStream);

        if (CollectionUtils.isEmpty(spuImports)) {
            logger.error("importSpu fail due to spuImports is empty....");
            return 0;
        }

        return spuImportMapper.addSpuImports(spuImports);
    }

    /**
     * 获得商品导入的实体
     *
     * @param inputStream 输入流
     * @return 返回商品导入的实体
     */
    private List<SpuImport> getSpuImports(InputStream inputStream) {
        List<SpuImport> spuImports = new ArrayList<>();
        POIFSFileSystem fs;
        HSSFWorkbook wb;
        try {
            fs = new POIFSFileSystem(inputStream);
            wb = new HSSFWorkbook(fs);
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {

                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    SpuImport spuImport = new SpuImport();
                    HSSFCell xh = hssfRow.getCell(0);
                    if (Objects.isNull(xh)) {
                        continue;
                    }
                    spuImport.setName(getValue(xh));


                    // 商品名称为空 过滤掉
                    if (StringUtils.isEmpty(spuImport.getName())) {
                        continue;
                    }

                    // 设置商品副标题
                    HSSFCell xm = hssfRow.getCell(1);
                    if (Objects.nonNull(xm)) {
                        spuImport.setSubTitle(getValue(xm));
                    }

                    // 设置商品销售价格
                    HSSFCell pri = hssfRow.getCell(2);
                    if (Objects.nonNull(pri)) {
                        spuImport.setPrice(new BigDecimal(getValue(pri)));
                    } else {
                        spuImport.setPrice(new BigDecimal(0));
                    }

                    // 设置商品SEO标题
                    HSSFCell se = hssfRow.getCell(3);
                    if (Objects.nonNull(se)) {
                        spuImport.setSeoTitle(getValue(se));
                    }

                    // 设置商品SEO关键字
                    HSSFCell sekey = hssfRow.getCell(4);
                    if (Objects.nonNull(sekey)) {
                        spuImport.setSeoKeywords(getValue(sekey));
                    }

                    // 设置商品SEO介绍
                    HSSFCell seodes = hssfRow.getCell(5);
                    if (Objects.nonNull(seodes)) {
                        spuImport.setSeoDesc(getValue(seodes));
                    }

                    spuImport.setDefalutValuesFoAdd();
                    spuImports.add(spuImport);
                }
            }

            return spuImports;
        } catch (Exception e) {
            logger.error("getSpuImports fail..", e);
            return Collections.emptyList();
        } finally {
            try {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.error("error", e);
            }
        }
    }


    /**
     * 获取值
     */
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}

package com.lecshop.spu;

import com.lecshop.spuimport.bean.SpuImport;
import com.lecshop.spuimport.service.SpuImportService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/18.
 * 商品导入控制器
 */
@Controller
public class SpuImportController {

    /**
     * 注入商品导入服务接口
     */
    @Autowired
    private SpuImportService spuImportService;

    /**
     * 跳转到商品导入页面
     *
     * @return 返回商品导入页面
     */
    @RequestMapping("/tospuimport")
    public ModelAndView toSpuImport() {
        return new ModelAndView("spu/spuimport");
    }

    /**
     * 分页查询商品导入
     *
     * @param pageHelper 分页帮助类
     * @param name       商品名称
     * @return 返回商品导入数据
     */
    @RequestMapping("/queryspuimports")
    @ResponseBody
    public BaseResponse querySpuImports(PageHelper<SpuImport> pageHelper, String name) {
        return BaseResponse.build(spuImportService.querySpuImports(pageHelper, name));
    }

    /**
     * 删除商品导入
     *
     * @param id 商品导入id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/deletespuimport")
    @ResponseBody
    public int deleteSpuImport(long id) {
        return spuImportService.deleteSpuImport(id);
    }

    /**
     * 批量删除商品导入
     *
     * @param ids 商品导入id集合
     * @return 成功返回>1  失败返回0
     */
    @RequestMapping("/deletespuimports")
    @ResponseBody
    public int deleteSpuImports(Long[] ids) {
        return spuImportService.deleteSpuImports(Arrays.asList(ids));
    }

    /**
     * 导入商品
     * @return 0 导入失败  -2  文件格式不对  >1 成功
     * @throws Exception
     */
    @RequestMapping("/importspu")
    @ResponseBody
    public int importSpu(MultipartHttpServletRequest request) throws Exception {
        MultipartFile multipartFile = request.getFile("spuimport");

        if (Objects.isNull(multipartFile)) {
            return 0;
        }

        // 上传文件不对
        if (!".xls".equals(multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().indexOf("."),
                multipartFile.getOriginalFilename().length()))) {
            return -2;
        }
        return spuImportService.importSpu(multipartFile.getInputStream());
    }
}

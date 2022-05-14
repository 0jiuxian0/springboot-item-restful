package com.web.items.controller;
import com.web.items.pojo.Items;
import com.web.items.pojo.RespBean;
import com.web.items.service.ItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "ItemsController",description = "产品后台微服务")
@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @ApiOperation("查询所有商品信息")
    @GetMapping("/findAll")
    public List<Items> findAll() {
        return itemsService.findAll();
    }

    @ApiOperation("根据id查询商品信息")
    @GetMapping("/findOne/{id}")
    public Items findOne(@PathVariable int id) {
        return itemsService.findOne(id);
    }
    //增删改
    @ApiOperation("添加商品信息")
    @PostMapping("/addItems")
    public RespBean addItems(@ApiParam("要添加的商品对象") @RequestBody Items items) {
        System.out.println(items.getName());
        System.out.println(items.getDetail());
        try {
            itemsService.addItems(items);//id回填到items对象中
            return RespBean.ok("添加成功", items);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("添加失败");
        }
    }
    @ApiOperation("根据唯一标识，修改商品信息")
    @PutMapping("/updateItems")
    public RespBean updateItems(@ApiParam("要编辑的商品对象") @RequestBody Items items) {
        System.out.println(items.getId());
        System.out.println(items.getName());
        System.out.println(items.getDetail());
        try {
            itemsService.updateItems(items);
            return RespBean.ok("修改成功", items);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("修改失败");
        }
    }
    @ApiOperation("根据唯一标识，删除商品信息")
    @DeleteMapping("/deleteItems/{id}")
    public RespBean deleteItems(@PathVariable int id) {
        try {
            itemsService.deleteItems(id);
            return RespBean.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("删除失败");

        }
    }
}


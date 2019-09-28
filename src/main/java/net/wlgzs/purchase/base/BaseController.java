package net.wlgzs.purchase.base;

import net.wlgzs.purchase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author: 胡亚星
 * @createTime 2019-09-28 9:26
 * @description:
 **/
public class BaseController {

    @Autowired
    protected IAccessoryListService iAccessoryListService;

    @Autowired
    protected IBrandService iBrandService;

    @Autowired
    protected IContractService iContractService;

    @Autowired
    protected IItemsService iItemsService;

    @Autowired
    protected IOrderService iOrderService;

    @Autowired
    protected IParameterService iParameterService;

    @Autowired
    protected IPartsService iPartsService;

    @Autowired
    protected IProductListService iProductListService;

    @Autowired
    protected IProductService iProductService;

    @Autowired
    protected IServiceListService iServiceListService;

    @Autowired
    protected IServiceValueService iServiceValueService;

    @Autowired
    protected ISortService iSortService;

    @Autowired
    protected ITypeService iTypeService;

    @Autowired
    protected IUserService iUserService;
}

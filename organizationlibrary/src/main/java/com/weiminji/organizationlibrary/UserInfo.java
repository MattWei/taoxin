package com.weiminji.organizationlibrary;

/**
 * Created by h189756 on 6/20/2016.
 */
public class UserInfo {
    private String mName;
    private String mAddress;
    private String mCellphoneNum;

    private Product[] products;
    private ProductManager.ProductCategory[] mProductCategories;

    public UserInfo(String name, String address, String cellphoneNum) {
        mName = name;
        mAddress = address;
        mCellphoneNum = cellphoneNum;
    }

    public String getName() {
        return mName;
    }
}

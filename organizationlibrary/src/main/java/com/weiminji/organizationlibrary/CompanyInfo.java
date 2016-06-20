package com.weiminji.organizationlibrary;

import android.graphics.Bitmap;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by h189756 on 6/20/2016.
 */
public class CompanyInfo {
    private String mName;
    private String mAddress;
    private String mTelephoneNum;
    private Bitmap mLogo;

    public class Department {
        private String mDepartmentName;
        private Employee mLeader;
        private Map<String, Employee> mEmployees = new HashMap<>();

        public Department(String name) {
            mDepartmentName = name;
        }

        public String getName() {
            return mDepartmentName;
        }

        public void setLeader(Employee employee) {
            mLeader = employee;
        }

        public Employee getLeader() {
            return mLeader;
        }

        public Employee getEmployee(String employeeName) {
            return mEmployees.get(employeeName);
        }

        public void addEmployee(Employee employee) {
            mEmployees.put(employee.getInfo().getName(), employee);
        }
    }
    public class Employee {
        private UserInfo mInfo;
        private Department mDepartment;

        public Employee(UserInfo userInfo) {
            mInfo = userInfo;
        }
        public UserInfo getInfo() {
            return mInfo;
        }
    }

    private Employee mManager;
    private Map<String, Department> mDepartments = new HashMap<String, Department>();

    private Product[] mProducts;
    private ProductManager.ProductCategory[] mProductCategories;

    public CompanyInfo(String companyName, String companyAddress, String telephone, Bitmap logo) {
        mName = companyName;
        mAddress = companyAddress;
        mTelephoneNum = telephone;
        mLogo = logo;
    }

    public void addDepartment(Department department) {
        mDepartments.put(department.getName(), department);
    }
}

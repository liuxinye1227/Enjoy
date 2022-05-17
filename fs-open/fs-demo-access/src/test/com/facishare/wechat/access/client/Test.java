package com.facishare.wechat.access.client;

import com.facishare.open.addressbook.model.Employee;
import com.facishare.open.addressbook.model.Gender;
import com.facishare.open.addressbook.result.AddressBookResultCodeEnum;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.wechat.access.model.TransData;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.Md5Crypt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 2015/11/20.
 */
public class Test {

    public static void main(String[] args) {

        //String json = "{\"data\":\"{\"command\":\"BUSI\"}\",\"header\":{\"command\":\"BUSI\",\"query\":\"EM1AEUBI.Messenger.queryUserCard\",\"token\":\"01cae0ba-c36a-480a-b30d-755e02b367d5\"}}";

        //System.out.println(new Gson().fromJson(json, TransData.class));
//        String json = "E.fs.11111";
//        String[] u = json.split("\\.");
//        for(String s : u) {
//            System.out.println(s);
//        }
//        ListResult<Employee> employeeList = new ListResult();
//        List<Employee> l = new ArrayList();
//
//        Employee employee = new Employee();
//        employee.setAccount("account");
//        employee.setEmail("email");
//        employee.setFullName("test");
//        employee.setEmployeeId(1);
//        employee.setGender(Gender.F);
//        employee.setMobile("1222233343");
//        employee.setName("name");
//        employee.setPost("post");
//        employee.setQq("11");
//        employee.setWeixin("weixin");
//        employee.setProfileImage("www.baidu.com");
//        List<List<Integer>> l1 = new ArrayList<>();
//        List<Integer> l2 = new ArrayList<>();
//        l2.add(1042);
//        l1.add(l2);
//        employee.setAllCircleIds(l1);
//        l.add(employee);
//
//        employeeList.setResult(l);
//        employeeList.setAddressBookResultCodeEnum(AddressBookResultCodeEnum.SUCCESS);
//
//        System.out.println(new Gson().toJson(employeeList));
         System.out.println(Md5Crypt.apr1Crypt("389446"));
    }
}

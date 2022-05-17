package org.fs.open.app.center.api;

import com.facishare.open.app.center.api.model.property.OpenAppProperties;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzengyong
 * @date on 2016/1/11.
 */
public class BasePropertiesTest extends TestCase {


    @Test
    public void testGetUserId() throws Exception {
        OpenAppProperties a = new OpenAppProperties();
        List<String> list = new ArrayList<>();
        list.add("sdfj");
        list.add("sdfj2");
        a.setDetailImageUrl(list);

        String jsonString = a.getJsonString();

        OpenAppProperties openAppProperties = a.fromJson(jsonString);

        Assert.assertTrue(a.equals(openAppProperties));

        OpenAppProperties o2 = new OpenAppProperties();
        System.out.println(o2.getJsonString());


    }

}

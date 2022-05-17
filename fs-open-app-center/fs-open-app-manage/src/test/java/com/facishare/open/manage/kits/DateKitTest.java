package com.facishare.open.manage.kits;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateKitTest extends TestCase {
    Date now = new Date(1443425079376l);// 2015-09-28 15:24:39
    Date dd20150926 = null;
    Date dd20150928 = null;
    Date dd20150930 = null;

    @Before
    public void setUp() throws Exception {
        dd20150926 = new SimpleDateFormat("yyyyMMdd").parse("20150926");
        dd20150928 = new SimpleDateFormat("yyyyMMdd").parse("20150928");
        dd20150930 = new SimpleDateFormat("yyyyMMdd").parse("20150930");
    }

    @Test
    public void testNow() {
        Date test = new Date();
        assertTrue(test.getTime() <= DateKit.now().getTime());
        assertTrue((test.getTime() + 30) > DateKit.now().getTime());
    }

    @Test
    public void testDateDate() throws ParseException {
        assertEquals(dd20150928, DateKit.date(now));
    }

    @Test
    public void testDateString() throws ParseException {
        assertEquals(dd20150928, DateKit.date("2015-09-28"));
    }

    @Test
    public void testAddDay() {
        assertEquals(dd20150930, DateKit.addDay(dd20150928, 2));
        assertEquals(dd20150926, DateKit.addDay(dd20150928, -2));
    }

    @Test
    public void testDateTime() {
        assertEquals("2015-09-26 00:00:00", DateKit.dateTime(dd20150926));
        assertEquals("2015-09-28 00:00:00", DateKit.dateTime(dd20150928));
        assertEquals("2015-09-30 00:00:00", DateKit.dateTime(dd20150930));
    }

}

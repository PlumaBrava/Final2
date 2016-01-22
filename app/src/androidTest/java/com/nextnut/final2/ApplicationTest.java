package com.nextnut.final2;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}


//public class EchoAndroidTest extends AndroidTestCase {
//    public void testVerifyEchoResponse() {
//        assertEquals("hello", Echo.echo("hello"));
//    }
//
//    public void testVerifyLoggingEchoResponse() {
//        assertEquals("hello", Echo.echo("hello", true));
//    }
//}

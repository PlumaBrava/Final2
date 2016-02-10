package com.nextnut.final2;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.CountDownLatch;

/**
 * Created by perez.juan.jose on 09/02/2016.
 */
public class JokeAsyncTaskTest2 extends InstrumentationTestCase{
    private final CountDownLatch signal = new CountDownLatch(1);
    // The target context.
    private Context mContext;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = getInstrumentation().getTargetContext();
    }





    private class TestJokeAsyncTask extends EndpointsAsyncTaskold{
        public TestJokeAsyncTask(ProgressBar spinner, Button btnSat) {
            super(spinner, btnSat);
        }

        @Override
        public void onPostExecute(String result) {
            assertTrue("Result should not be empty", result != null && result.length() > 0);
            assertEquals("This is totally a funny joke", result);
            signal.countDown(); //Release the signal and let the test end
        }
    }

    public void testAsyncTaskResponse() throws Throwable {

        final TestJokeAsyncTask testJokeAsyncTask = new TestJokeAsyncTask(
                new ProgressBar(mContext, null,android.R.attr.progressBarStyleHorizontal),
                new Button( mContext,null,android.R.attr.button));
        testJokeAsyncTask.execute(mContext);
        signal.await(); //Wait till AsyncTask finishes
    }

}

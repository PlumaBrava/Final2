import com.nextnut.Joke;


import junit.framework.TestCase;


/**
 * Created by perez.juan.jose on 11/02/2016.
 */
public class Joketest extends TestCase{


   public void testJoke() {

        Joke joke = new Joke();
        assert joke.getJoke().equals("This is totally a funny joke");
    }
}

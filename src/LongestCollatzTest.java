
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LongestCollatzTest {
	LongestCollatz longestCollatz;
	@Before
	public void setup() {

		longestCollatz = new LongestCollatz();
	}
	
	@Test
	public void testOne(){
		
		assertThat(longestCollatz.findLongest(1), is(equalTo(1)));
		
		
	}
	@Test
	public void testTen(){
		assertThat(longestCollatz.findLongest(10), is(equalTo(9)));
	}


}

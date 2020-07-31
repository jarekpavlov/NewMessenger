package messenger;

import static org.hamcrest.CoreMatchers.not;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailServiceImplTest {
	
	@Test
	void Test(){
		
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String rawPassword="asdf";
		String password=encoder.encode(rawPassword);
		System.out.println(password);
		assertThat(password, not(rawPassword));
		
	}

	private void assertThat(String password, Matcher<String> not) {
		// TODO Auto-generated method stub
		
	}


}

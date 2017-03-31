package org.feup.trains;

import org.junit.Assert;
import org.junit.Test;
import org.feup.trains.util.Luhn;

public class LuhnTest extends AbstractTest{
	
	@Test
	public void testLuhnValid(){
		
		String validCC = "4194600045339160";
		
		Assert.assertTrue("failed - expected number to be valid", Luhn.check(validCC));
		
	}
	
	@Test
	public void testLuhnInvalid(){
		
		String invalidCC = "4194610045339160";
		
		Assert.assertFalse("failed - expected number to be invalid", Luhn.check(invalidCC));
		
	}

}

package cm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TemiolaChristianaTestTask2 {

	/**
	 *  RATE CLASS TEST CASES
	 */

		// TEST FOR IF REDUCEDPERIODS AND NORMALPERIODS ARE NOT NULL
	@Test
	public void testCase1() {
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);
		
		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();
		
		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);
		
		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);
		
		//Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5), new BigDecimal(2), reducedPeriods, normalPeriods);
		//BigDecimal charge = rate.calculate(periodStay);
		//Assert.assertNotNull(charge);
	}
		// TEST FOR IF REDUCEDPERIODS IS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase2(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMALPERIODS EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase3(){
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		reducedPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, null);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMALRATE EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase4(){
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		reducedPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(0.0), new BigDecimal(2.0), reducedPeriods, null);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCEDRATE EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase5(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(0.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMALRATE IS LESS THAN ZERO
	@Test(expected = IllegalArgumentException.class)
	public void testcase6(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(-5.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCEDRATE IS LESS THAN ZERO
	@Test(expected = IllegalArgumentException.class)
	public void testcase7(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(-2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMALRATE IS EQUAL TO REDUCEDRATE
	@Test(expected = IllegalArgumentException.class)
	public void testcase8(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(2.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMALRATE IS LESS THAN REDUCEDRATE
	@Test(expected = IllegalArgumentException.class)
	public void testcase9(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(1.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCEDPERIODS IS NOT VALID
	@Test(expected = IllegalArgumentException.class)
	public void testcase10(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(1.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}


	@Test
	public void calculateTestCase1(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		Period periodStay = new Period(2, 10);
		BigDecimal payment = rate.calculate(periodStay);
		Assert.assertTrue(payment.compareTo(new BigDecimal(16)) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateTestCase2(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal payment = rate.calculate(null);

	}

	/**
	 *  CALCULATE TEST CASES FOR ALL "IF" STATEMENTS BRANCHES FOR STAFF, STUDENT, VISITOR AND MANAGEMENT.
	 */
	//TEST CASE FOR STAFF PAYMENT == 16: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase3(){
		Period reducedPeriod1 = new Period(11,15);
		Period reducedPeriod2 = new Period(7,11);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(7, 20));
		BigDecimal expectedPayment = new BigDecimal(16);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}


	//TEST CASE FOR STAFF PAYMENT > 16: IF STATEMENT BRANCH
	@Test
	public void calculateTestCase4(){
		Period reducedPeriod1 = new Period(11,15);
		Period reducedPeriod2 = new Period(6,11);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(6, 20));
		BigDecimal expectedPayment = new BigDecimal(16);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}

	//TEST CASE FOR STAFF PAYMENT < 16: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase5(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(8);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}


	//TEST CASE FOR STUDENT A
	@Test
	public void calculateTestCase6(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STUDENT, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(6);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}

	//TEST CASE FOR VISITOR A
	@Test
	public void calculateTestCase7(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.VISITOR, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(0);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}

	//TEST CASE FOR MANAGEMENT A
	@Test
	public void calculateTestCase8(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(8);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}



	/**
	 *  CALCULATE TEST CASES FOR ALL "ELSE" STATEMENTS BRANCHES FOR STAFF, STUDENT, VISITOR AND MANAGEMENT.
	 */

	//TEST CASE FOR STAFF B
	@Test
	public void calculateTestCase9(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(7,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(8);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);

	}




}




package cm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class TemiolaChristianaTestTask2 {

	/**
	 *  RATE CLASS TEST CASES
	 */

		// TEST FOR IF REDUCED PERIODS AND NORMAL PERIODS ARE NOT NULL
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
		// TEST FOR IF REDUCED PERIODS IS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase2(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMAL PERIODS EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase3(){
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		reducedPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, null);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMAL RATE EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase4(){
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		reducedPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(0.0), new BigDecimal(2.0), reducedPeriods, null);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCED RATE EQUALS NULL
	@Test(expected = IllegalArgumentException.class)
	public void testcase5(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(0.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMAL RATE IS LESS THAN ZERO
	@Test(expected = IllegalArgumentException.class)
	public void testcase6(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(-5.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCED RATE IS LESS THAN ZERO
	@Test(expected = IllegalArgumentException.class)
	public void testcase7(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(5.0), new BigDecimal(-2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMAL RATE IS EQUAL TO REDUCED RATE
	@Test(expected = IllegalArgumentException.class)
	public void testcase8(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(2.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF NORMAL RATE IS LESS THAN REDUCED RATE
	@Test(expected = IllegalArgumentException.class)
	public void testcase9(){
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		Period period1 = new Period(3, 5);

		normalPeriods.add(period1);
		Rate rate  = new Rate(CarParkKind.STAFF, new BigDecimal(1.0), new BigDecimal(2.0), null, normalPeriods);
		Assert.assertNotNull(rate);
	}

	// TEST FOR IF REDUCED PERIODS IS NOT VALID
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
	 *  CALCULATE TEST CASES.
	 */

	//************************************************************************************
	//				1.	STAFF PAYMENT
	//************************************************************************************

	//TEST CASE FOR STAFF PAYMENT > 16: IF STATEMENT BRANCH
	@Test
	public void calculateTestCase3(){
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

	//TEST CASE FOR STAFF PAYMENT == 16: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase4(){
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

	//**************************************************************************************
	//				2. STUDENT PAYMENT
	//***************************************************************************

	//TEST CASE FOR STUDENT PAYMENT > 5.5: IF STATEMENT BRANCH
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

	//TEST CASE FOR STUDENT PAYMENT < 5.5: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase7(){
		Period reducedPeriod1 = new Period(8,9);
		Period reducedPeriod2 = new Period(7,8);
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

		BigDecimal actualPayment = rate.calculate(new Period(7, 20));
		BigDecimal expectedPayment = new BigDecimal(4);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}

	//TEST CASE FOR STUDENT PAYMENT == 5.5: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase8(){
		Period reducedPeriod1 = new Period(10,12);
		Period reducedPeriod2 = new Period(8,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.STUDENT, new BigDecimal(5.0), new BigDecimal(1.37), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal payment = rate.calculate(new Period(8, 20));
		BigDecimal actualPayment;
		actualPayment = payment.setScale(1, RoundingMode.CEILING);
		BigDecimal expectedPayment = new BigDecimal(5.5);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}


	//********************************************************************************************************
	//				 3.  VISITOR PAYMENT
	//*********************************************************************************************

	//TEST CASE FOR VISITOR PAYMENT > 8: IF STATEMENT BRANCH
	@Test
	public void calculateTestCase9(){
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

		Rate rate = new Rate(CarParkKind.VISITOR, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(6, 20));
		BigDecimal expectedPayment = new BigDecimal(5);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}

	//TEST CASE FOR VISITOR PAYMENT == 8: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase10(){
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

	//TEST CASE FOR VISITOR PAYMENT < 8: ELSE STATEMENT BRANCH
	@Test
	public void calculateTestCase11(){
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


	//********************************************************************************************************
	//					4. MANAGEMENT PAYMENT
	//*******************************************************************************************************

	//TEST CASE FOR MANAGEMENT PAYMENT > 3: IF STATEMENT BRANCH
	@Test
	public void calculateTestCase12(){
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

		Rate rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(5.0), new BigDecimal(2.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(6, 20));
		BigDecimal expectedPayment = new BigDecimal(18);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}

	//TEST CASE FOR MANAGEMENT PAYMENT == 3: IF STATEMENT BRANCH
	@Test
	public void calculateTestCase13(){
		Period reducedPeriod1 = new Period(10,11);
		Period reducedPeriod2 = new Period(8,10);
		ArrayList<Period> reducedPeriods = new ArrayList<Period>();

		reducedPeriods.add(reducedPeriod1);
		reducedPeriods.add(reducedPeriod2);

		Period normalPeriod1 = new Period(4,6);
		Period normalPeriod2 = new Period(1,4);
		ArrayList<Period> normalPeriods = new ArrayList<Period>();

		normalPeriods.add(normalPeriod1);
		normalPeriods.add(normalPeriod2);

		Rate rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(5.0), new BigDecimal(1.0), reducedPeriods, normalPeriods);
		Assert.assertNotNull(rate);

		BigDecimal actualPayment = rate.calculate(new Period(8, 20));
		BigDecimal expectedPayment = new BigDecimal(3);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}

	//TEST CASE FOR MANAGEMENT PAYMENT < 3: ELSE STATEMENT BRANCH

	@Test
	public void calculateTestCase14(){
		Period reducedPeriod1 = new Period(21,22);
		Period reducedPeriod2 = new Period(9,10);
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
		BigDecimal expectedPayment = new BigDecimal(3);
		Assert.assertTrue(expectedPayment.compareTo(actualPayment) == 0);
	}


}




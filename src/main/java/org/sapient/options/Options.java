package org.sapient.options;

public enum Options {
	ADD_NDAYS,
	ADD_NWEEKS,
	ADD_NMONTHS,
	ADD_NYEARS,
	MINUS_NDAYS,
	MINUS_NWEEKS,
	MINUS_NMONTHS,
	MINUS_NYEARS,
	WEEK_OF_YEAR,
	DAY_OF_WEEK;
//	STATEMENT,
//	SET_DATE,
//	ADD_DATES,
//	MINUS_DATES;
	public static Options getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

}
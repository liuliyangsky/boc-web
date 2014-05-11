/**
 * date add function
 */
Date.prototype.dateAdd = function(interval,number)
{
	var d = this;
	var k = {"y":"FullYear", "q":"Month", "m":"Month", "w":"Date", "d":"Date", "h":"Hours", "n":"Minutes", "s":"Seconds", "ms":"MilliSeconds"};
	var n = {"q":3, "w":7};
	eval("d.set"+k[interval]+"(d.get"+k[interval]+"()+"+((n[interval]||1)*number)+")");
	return d;
};

/**
 * get current date without hours,minutes,seconds.just some day 00:00:00
 */
function getToday() {
	var today = new Date();
	today.setHours(0);
	today.setMinutes(0);
	today.setSeconds(0);
	return today;
};

function getThreeMonthsDay() {
	var threeMonths = new Date();
	threeMonths.setHours(0);
	threeMonths.setMinutes(0);
	threeMonths.setSeconds(0);
	threeMonths.dateAdd("m", -3);
	return threeMonths;
};

function getyyyyMMdd() {
	var threeMonths = new Date();
	threeMonths.setHours(0);
	threeMonths.setMinutes(0);
	threeMonths.setSeconds(0);
	//threeMonths.dateAdd("m", -3);
	return threeMonths;
};

function getyyyyMMddHH() {
	var threeMonths = new Date();
	threeMonths.setHours(23);
	threeMonths.setMinutes(59);
	threeMonths.setSeconds(0);
	//threeMonths.dateAdd("m", -3);
	return threeMonths;
};

function numToStrFormat(format, number) {
    if (!format && number) return number.toString();
    if (!format) return "";
    if (!number) return format;
    var _numberStr = number.toString();
    return format.substr(_numberStr.length) + _numberStr;
};

function setWindowTitle(window, windowName, recordName, idStr, windowIcon) {
	if (recordName && idStr) {
		window.setTitle(windowName + ": " + recordName + " ( " +  idStr + " )");
	} else if (recordName) {
		window.setTitle(windowName + ": " + recordName);
	} else if (idStr) {
		window.setTitle(windowName + ": ( " +  idStr + " )");
	} else {
		window.setTitle(windowName);
	}
	
	if (windowIcon) {
		var headerIcon = eval(window.ID + "_headerIcon");
		headerIcon.src = "../shared/jsp/viewImage.jsp?v=" + windowIcon;
	}
	window.redraw();
};

function resetWindowTitle(window, windowName) {
    window.setTitle(windowName);
//    if (eval(window.ID + "_headerIcon"))
//	eval(window.ID + "_headerIcon").src = "[SKINIMG]/Window/headerIcon.png";
    window.redraw();
};

function setGridSummaryTitle(records) {
	var recordCounts = records.length;
	return "Count: " + recordCounts;
    
};

function formatMonthYearCellValue(value) {
	if (value) {
        return value.getShortMonthName() + '-' + value.getFullYear();
    }
};

function formatDateCellValue(value) {
	if (value) {
        return value.getFullYear() + '-' + (value.getMonth() + 1) + '-' + value.getDate();
    }
};

function formatDateTimeCellValue(value) {
	if (value) {
		var month = value.getMonth();
		if(month <　10){
		    month ="0" + (value.getMonth() + 1);
		}
		
		var date =  value.getDate();
		if(date < 10){
			date ="0" + date;
		}
		
		var hours = value.getHours();
		if (hours < 10) {
			hours = "0" + hours;
		}
		
		var minutes = value.getMinutes();
		if (minutes < 10) {
			minutes = "0" + minutes;
		} 
		
		var seconds = value.getSeconds();
		if (seconds < 10) {
			seconds = "0" + seconds;
		} 
        return value.getFullYear() + '-' + month + '-' + date + " " + hours + ":" + minutes + ":" + seconds;
    }
};

function formatDateTimeWithoutSeconds(value) {
	if (value) {
		var month = value.getMonth();
		if(month <　10){
		    month ="0" + (value.getMonth() + 1);
		}
		
		var date =  value.getDate();
		if(date < 10){
			date ="0" + date;
		}
		
		var seconds = value.getSeconds();
		if (seconds < 10) {
			seconds = "0" + seconds;
		} 
        return value.getFullYear() + '-' + month + '-' + date + " " + "59" + ":" + "59" + ":" + seconds;
    }
};

// format HH:MM
function formatHHMMCellValue(value) {
	if (value) {
		var number = (value.toString()).length;
		if (number > 10) {
			var hours = value.getHours();
			if (hours < 10) {
				hours = "0" + hours;
			}
			var minutes = value.getMinutes();
			if (minutes < 10) {
				minutes = "0" + minutes;
			}
			value = hours + ":" + minutes;
		}
		return value;
	}
};

//format Currency
function formatCurrency(num) {
	if(!isNaN(num)){
		num = num.toString().replace(/\$|\,/g, '');
		if (isNaN(num))num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num * 100 + 0.50000000001);
		cents = num % 100;
		num = Math.floor(num / 100).toString();
		if (cents < 10)cents = cents;
		for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
			num = num.substring(0, num.length - (4 * i + 3)) + ','+ num.substring(num.length - (4 * i + 3));
		return (((sign) ? '' : '-') + '$' + num + '.' + cents);
	} else {
		return num;
	}
}


function formatMDValue(value) {
	if (value) {
		var month = value.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		
		var day = value.getDate();
		if (day < 10) {
			day = "0" + day;
		}
			
        return month + '-' + day;
    }
};

function getDateForMD(value) {
	if (value) {
		var monthDay = value.split("-");
		
		var month = monthDay[0];
		if (month.indexOf("0") == 0) {
			month = month.substring(1);
		}
		
		var day = monthDay[1];
		if (day.indexOf("0") == 0) {
			day = day.substring(1);
		}
		
		var specialMD = new Date();
		specialMD.setMonth(month - 1);
		specialMD.setDate(day);
		specialMD.setHours(0);
		specialMD.setMinutes(0);
		specialMD.setSeconds(0);
		
        return specialMD;
    }
};

function formatBrCellValue(value) {
	if (value) {
		return value.replaceAll(",", "<br />");
	} else {
		return null;
	}
};

function filterFromGTTo(criteria, fieldName) {
	if (!criteria) return false;
	if (criteria._constructor != "AdvancedCriteria") return false;
	if (!criteria.criteria) return false;
	for (var i = 0; i < criteria.criteria.length; i++) {
		// Level 1
		if (!criteria.criteria[i].criteria)
			continue;
		// Level 2
		var l2Criteria = criteria.criteria[i].criteria;
		var fromValue = null;
		var toValue = null;
		for (var j = 0; j < l2Criteria.length; j++) {
			var criterion = l2Criteria[j];
			if (criterion.fieldName != fieldName)
				continue;
			// Matched field
			if (criterion.operator == "greaterOrEqual") {
				if (criterion.value._constructor == "RelativeDate")
					fromValue = DateUtil.getAbsoluteDate(criterion.value);
				else
					fromValue = criterion.value;
			}
			else if (criterion.operator == "lessOrEqual") {
				if (criterion.value._constructor == "RelativeDate")
					toValue = DateUtil.getAbsoluteDate(criterion.value);
				else
					toValue = criterion.value;
			}
		}
		if (fromValue && toValue) {
			if (fromValue > toValue) {
//				criteria.criteria.removeAt(i);
				isc.warn("From can not greater than to.");
				return true;
			}
			else {
				return false;
			}
		}
		else if (fromValue || toValue) {
			return false;
		}
	}
	return false;
};


function loadScript(urls, callback){
	for (var i = 0; i < urls.length; i++) {
		var url = urls[i];
	    var script = document.createElement("script");
	    script.language = "javascript";
	    script.type = "text/javascript";
	
	    if (i == urls.length - 1) {
		    if (script.readyState){  //IE
		        script.onreadystatechange = function() {
		            if (script.readyState == "loaded" || script.readyState == "complete"){
		                script.onreadystatechange = null;
		                callback();
		            }
		        };
		    } else {  //Others
		        script.onload = function(){
		            callback();
		        };
		    }
	    }

	    script.src = url;
	    script.charset = "UTF-8";
	    document.getElementsByTagName('head')[0].appendChild(script);
	}
};
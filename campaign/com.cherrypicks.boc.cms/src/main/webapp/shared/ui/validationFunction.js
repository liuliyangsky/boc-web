// validation
function compareDateForForm(item, validator, value) {
	if (value == null || isc.is.emptyString(value) || item.isEmpty) {
		return true;
	}
	
	var form = item.form;	
	var otherFieldValue;
	if (form.getField(validator.otherField).isEmpty) {
		return true;
	}

	if (form.getValue(validator.otherField) == undefined) {
		return false;
	} else {
		// get the value of the otherField when otherField value changed	
		otherFieldValue = form.getValue(validator.otherField);
		if (otherFieldValue == null || isc.is.emptyString(otherFieldValue)) {
			return true;
		}
	} 
	// and return whether the values compare	
	return (eval("value.getTime()" + validator.operator +  "otherFieldValue.getTime()"));	
};


function compareSpecifyDate(item, validator, value) {
	//if this field have no edit value ,pass validation
	if (value == null || isc.is.emptyString(value) || item.isEmpty) {
		return true;
	}
	var otherFieldValue = eval(validator.condition);
	// and return whether the values compare	
	return (eval("value.getTime()" + validator.operator +  "otherFieldValue.getTime()"));
};


function validPassword(item, validator, value) {
	//if this field have no edit value ,pass validation
	if (value == null || isc.is.emptyString(value) || item.isEmpty) {
		return true;
	}
	
	var form = item.form;
	var changedValues = form.getChangedValues();
	if (changedValues) {
		if (changedValues[item.name]) {
			var expression = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,14}$/;
			return expression.test(value);
		}
	}
	return true;
};

function compareInt(item, validator, value) {
	if (value == null || isc.is.emptyString(value) || item.isEmpty)
		return true;
	
	var form = item.form;
	var otherFieldValue = form.getValue(validator.otherField);
	if (otherFieldValue == null || isc.is.emptyString(otherFieldValue))
		return true;

	return (eval("value" + validator.operator + "otherFieldValue"));
};

Validator.addValidator("compareDateForForm", compareDateForForm);
Validator.addValidator("compareSpecifyDate", compareSpecifyDate);
Validator.addValidator("validPassword", validPassword);
Validator.addValidator("compareInt", compareInt);

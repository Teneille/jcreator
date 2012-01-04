jQuery( function() {
	/**
	 * 
	 * http://www.blogjava.net/mashiguang/archive/2011/02/17/344518.html*/
	jQuery.validator.methods.DateValidatorFormat = function(value, element, param) {
		
		return true;
	};
	/**
	 * 
	 * http://www.blogjava.net/mashiguang/archive/2011/02/17/344518.html*/
	jQuery.validator.methods.DateValidatorMax = function(value, element, param) {
		
		return true;
	};
	/**
	 * 
	 * http://www.blogjava.net/mashiguang/archive/2011/02/17/344518.html*/
	jQuery.validator.methods.DateValidatorMin = function(value, element, param) {
		return true;
	};
	
	
	
	
	
	
	
	
	/*
	 * 
	 * 扩展验证，使用webX3的form.xml
	 * 页面只需要调用 jValidate即可
	 * 
	 * */
	jQuery.fn.extend({
	  JjValidate: function(oprions) {
		var _id = "#"+jQuery(this).attr("id");
		var val={};
	 	jQuery.ajax({
	 		async:false,
	 		type: "GET",
	 		url: oprions.groupName+"_"+oprions.count+".vjs?"+new Date().getTime(),
			dataType: "json",
			success: function(text) {
	 			alert(text)
				var json = eval('('+text+')');
				//使用验证
				val = $(_id).validate(
					json
				 );
			}
	 	})
	 	return val;
		
	  }
	});
});
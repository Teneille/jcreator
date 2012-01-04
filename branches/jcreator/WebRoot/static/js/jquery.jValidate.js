(function($) {

$.extend($.fn, {
	jValidate: function( options ) {
		// 如果么有元素被选中则
		if (!this.length) {
			options && options.debug && window.console && console.warn( "nothing selected, can't validate, returning nothing" );
			return;
		}
		//检查有没有设定验证，如果设定了验证则直接返回
		var jValidator = $.data(this[0], 'jValidator');
		if ( jValidator ) {
			return jValidator;
		}
		
		// Add novalidate tag if HTML5.
		this.attr('novalidate', 'novalidate');
		jValidator = new $.jValidator( options, this[0] );
		//为元素设定验证
		$.data(this[0], 'jValidator', jValidator);
		return jValidator;
	}
})

//定义验证
$.jValidator = function(options,element){
	var setting={};
	//远程获取验证规则
	if(options.url){
		$.ajax({
	 		async:false,
	 		type: "GET",
	 		url: options.url+"?"+new Date().getTime(),
			dataType: "json",
			success: function(json) {
				//使用验证
				setting = $.extend( true, {}, $.jValidator.defaults,json ,options );
			}
	 	})
	}else{
		setting = $.extend( true, {}, $.jValidator.defaults,options );
	}
	this.settings = setting;
	this.currentElement = element;
	this.init();
}

$.extend($.jValidator, {
	defaults:{
		/*
		 * 错误提示方式 默认 alert 其他alertAll，element
		 * */
		alertType:'alert',
		/*
		 * 如果alertType=element 则必须设置 showErrorsElement
		 * */
		showErrorsElement:'',
		/*
		 * 错误信息
		 * */
		messages: {},
		/*
		 * 验证规则
		 * */
		rules: {},
		errorClass: "jValidator-error",
		validClass: "jValidator-valid",
		/*
		 * 忽略隐藏元素
		 * */
		ignore: ":hidden",
		debug : false
	},
	/*
	 * 设置
	 * */
	setDefaults: function(settings) {
		$.extend( $.validator.defaults, settings );
	},
	findByName : function( name ) {
		return $(document.getElementsByName(name));
	},
	prototype: {
		init:function(){
			this.debug=this.settings.debug;
			this.alertType=this.settings.alertType;
			this.showErrorsElement = this.settings.showErrorsElement;
			var rules = this.settings.rules;
			var validClass = this.settings.validClass;
			var elements={};
			$.each(rules, function(key, value) {
				elements[key]=$.jValidator.findByName(key);
			});
			this.settings.elements = elements;
			
			//$.each(messages, function(key, value) {
			//	alert("msg:"+key)
			//	//messages[key] = //$.validator.normalizeRule(value);
			//});
		},
		validate:function(){
			var rules = this.settings.rules;
			var _elements = this.settings.elements
			var _this = this;
			$.each(this.settings.elements, function(name, element) {
				$.each(rules[name], function(key, value) {
					if(key=="required"){
						alert("required-validate:"+$.jValidator.methods[key].call(_this, $.trim(_elements[name].val()), element[0],value));
					}
				});
			});
		},
		defaultShowErrors:function(){
			
		},
		checkable: function(element){
			return /radio|checkbox/i.test(element.type);
		},
		getLength: function(value, element) {
			switch( element.nodeName.toLowerCase() ) {
			case 'select':
				return $("option:selected", element).length;
			case 'input':
				if( this.checkable( element) )
					return this.findByName(element.name).filter(':checked').length;
			}
			return value.length;
		},
		depend: function(param, element) {
			if(typeof param =="string" && param=="true"){
				param="true";
			}
			return this.dependTypes[typeof param]
				? this.dependTypes[typeof param](param, element)
				: true;
		},
		dependTypes: {
			"boolean": function(param, element) {
				return param;
			},
			"string": function(param, element) {
				return $(param).length >= 1;
			},
			"function": function(param, element) {
				return param(element);
			}
		}
		
	},
	methods:{
		required: function(value, element, param) {
		// check if dependency is met
		if ( !this.depend(param, element) )
			return "dependency-mismatch";
		switch( element.nodeName.toLowerCase() ) {
			case 'select':
				// could be an array for select-multiple or a string, both are fine this way
				var val = value;
				return val && val.length > 0;
			case 'input':
				if ( this.checkable(element) )
					return this.getLength(value, element) > 0;
			default:
				return $.trim(value).length > 0;
			}
		}
	}
	
})




})(jQuery);
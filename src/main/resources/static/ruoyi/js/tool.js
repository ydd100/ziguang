if ($.validator) {
	$.validator.prototype.elements = function() {
		var validator = this, rulesCache = {};
		return $(this.currentForm).find("input, select, textarea").not(
				":submit, :reset, :image, [disabled]")
				.not(this.settings.ignore).filter(
						function() {
							if (!this.name && validator.settings.debug
									&& window.console) {
								console.error("%o has no name assigned", this);
							}
							rulesCache[this.name] = true;
							return true;
						});
	}
}

//判断字段是否为空
function isEmpty(value) {
	if (value == null || value == 'null' || value == undefined || value == 'undefined' || value == '') {
		return true;
	} else {
		return false;
	}
}

//序列化form表单,返回对象
function serializeObject(form){
    var obj = {};
    form.find(".ui-select").each(function(r){
      var name=$(this).attr("name");
      var value=$(this).attr("data-value");
      obj[name]= value;
    })
    
    $.each(form.serializeArray(),function(index){
        if(obj[this['name']]){
        	obj[this['name']] = obj[this['name']] + ","+this['value'];
         }else{
        	 obj[this['name']] =  this['value'];
         }
    });
    return obj;
}

//对象赋值到表单form:$("#formId"),data:{}对象
function loadForm(form,data){
    form.each(function(index,element){
    	var forms = $(this);
    	$.each(data,function(name,val){
    		forms.find("[name="+name+"]").val(val);
        })
    	
   	})
}

//回显数据字典
function getDictLabel(datas,value){
	var dictLabel = "";
	$.each(datas,function(index,dict){
		if (dict.dictValue == ('' + value)) {
			dictLabel = dict.dictLabel;
			return false;
		}
	});
	return dictLabel;
}

//回显数据字典值
function getDictValue(datas,content){
	var dictValue = "";
	$.each(datas,function(index,dict){
		if (dict.dictLabel == ('' + content)) {
			dictValue = dict.dictValue;
			return false;
		}
	});
	return dictValue;
}

//返回日期格式yyyy-MM-dd HH:MM
function dateStrToFormatDate(dateStr) {
    var date = new Date(dateStr);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours()
    var minutes = date.getMinutes();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 1 && hours <= 9) {
    	hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
    	minutes = "0" + minutes;
    }
    
    
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hours + seperator2 + minutes;
    return currentdate;
}
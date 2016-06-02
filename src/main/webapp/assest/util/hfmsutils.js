var bmpUtil = new BmpUtil();
function BmpUtil()
{
	this.RESPONSE_STATUS = 200;
	this.RESPONSE_MSG = "Success";
	this.INVALID_LOGIN_STATUS = 3;
};
BmpUtil.prototype.isTextFieldEmpty = function(jqSelector)
{
	var isValid = true;
	$(jqSelector).each(function(){
		if($.trim($(this).val()) == '')
		{
			isValid = false;
		}
	})
	return isValid;
};
BmpUtil.prototype.chkNumber = function(jqSelector, event)
{
	if (event.which != 8 && event.which != 0 && (event.which < 48 || event.which > 57))
	{
		return false;
	}
	else
	{
		return true;
	}
};
BmpUtil.prototype.showAlert = function(msg, title)
{
	var toastCount = 0,
		$toastlast,
		shortCutFunction = "success",
		toastIndex = toastCount++,
		$toast;
	msg = msg || '';
	title = title || '';
	toastr.options =
	{
		closeButton: $('#closeButton').prop('checked'),
		debug: $('#debugInfo').prop('checked'),
		positionClass: $('#positionGroup input:radio:checked').val() || 'toast-top-right',
		onclick: null
	};
	if ($('#addBehaviorOnToastClick').prop('checked'))
	{
		toastr.options.onclick = function ()
		{
			alert('You can perform some custom action after a toast goes away');
		};
	};
	toastr.options.showDuration = "300";
	toastr.options.hideDuration = "1000";
	toastr.options.timeOut = "5000";
	toastr.options.extendedTimeOut = "1000";
	toastr.options.showEasing = "swing";
	toastr.options.hideEasing =  "linear";
	toastr.options.showMethod =  "fadeIn";
	toastr.options.hideMethod =  "fadeOut";
	$("#toastrOptions").text("Command: toastr["
						+ shortCutFunction
						+ "](\""
						+ msg
						+ (title ? "\", \"" + title : '')
						+ "\")\n\ntoastr.options = "
						+ JSON.stringify(toastr.options, null, 2)
		);
	 $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
		$toastlast = $toast;
		if ($toast.find('#okBtn').length) {
			$toast.delegate('#okBtn', 'click', function () {
				alert('you clicked me. i was toast #' + toastIndex + '. goodbye!');
				$toast.remove();
			});
		}
		if ($toast.find('#surpriseBtn').length) {
			$toast.delegate('#surpriseBtn', 'click', function () {
				alert('Surprise! you clicked me. i was toast #' + toastIndex + '. You could perform an action here.');
			});
		}
	function getLastToast(){
		return $toastlast;
	}
	$('#clearlasttoast').click(function () {
		toastr.clear(getLastToast());
	});
	$('#cleartoasts').click(function () {
		toastr.clear();
	});

};
BmpUtil.prototype.getQParamByName = function(name, url)
{
	if(url)
	{
		if(url.indexOf("?") >= 0)
		{
			url = url.substring(url.indexOf("?")+1, url.length);
		}
	}
	else
	{
		url = window.location.search;
	}
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(url);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
};
BmpUtil.prototype.getLoginPage = function()
{
	return "/bulkmail/view/jsp/v1/login.jsp";
};
BmpUtil.prototype.validateURL = function(str)
{

	var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	return regexp.test(str);
};
BmpUtil.prototype.getLastStartingURL = function()
{
	var lastPart = ((location.href.toString()).split("/")).pop();
	return lastPart;
};

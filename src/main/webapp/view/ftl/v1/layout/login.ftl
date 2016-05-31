<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

		<#assign baseURL = "${rc.getContextPath()}">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="images/favicon.png">

    <title>Login</title>

    <!--Core CSS -->
    <link href="${baseURL}/theme/bs3/css/bootstrap.min.css" rel="stylesheet">
    <link href="${baseURL}/theme/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${baseURL}/theme/font-awesome/css/font-awesome.css" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="${baseURL}/theme/css/style.css" rel="stylesheet">
    <link href="${baseURL}/theme/css/style-responsive.css" rel="stylesheet" />

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="${baseURL}/theme/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
    	.hfmsSignInForm
    	{
		    color: #000;
			font-family: verdana;
			font-weight: bold;
    	}
    	.hfmsLoader
    	{
    		display: none;
    	}
    </style>


</head>

  <body class="login-body">

    <div class="container">
      <form class="form-signin hfmsSignInForm">
        <h2 class="form-signin-heading">sign in now</h2>
        <div class="login-wrap">
            <div class="user-login-info">
                <span class="hfmsErrorMsg" style="color: red; font-size: 12px;"></span>
                <input type="text" class="form-control hfmsUserName" placeholder="User Name" autofocus required>
                <input type="password" class="form-control hfmsPassword" placeholder="Password" required>
                <img  class="hfmsLoader" src="${baseURL}/assest/img/index.gif"  />
            </div>
            <label class="checkbox">
                <!--<input type="checkbox" value="remember-me"> Remember me-->
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>
                </span>
            </label>
            <button class="btn btn-lg btn-login btn-block hfmsSignIn" type="submit">Sign in</button>

            <!--<div class="registration">
                Don't have an account yet?
                <a class="" href="registration.html">
                    Create an account
                </a>
            </div>-->

        </div>

          <!-- Modal -->
          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Forgot Password ?</h4>
                      </div>
                      <div class="modal-body">
                          <p>Enter your e-mail address below to reset your password.</p>
                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                      </div>
                      <div class="modal-footer">
                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                          <button class="btn btn-success" type="button">Submit</button>
                      </div>
                  </div>
              </div>
          </div>
          <!-- modal -->

      </form>

    </div>

    <!-- Placed js at the end of the document so the pages load faster -->

    <!--Core js-->
    <script src="${baseURL}/theme/js/jquery.js"></script>
    <script src="${baseURL}/theme/bs3/js/bootstrap.min.js"></script>
    <script src="${baseURL}/assest/plugin/validator.js"></script>
	<script src="${baseURL}/assest/dao/hfmsdao.js"></script>
	<script src="${baseURL}/assest/util/hfmsutils.js"></script>


  </body>
  <script type="text/javascript">
  		$("#form-signin").validate();
		$(document).ready(function(){
			$(".hfmsSignInForm").submit(function( event ){
				event.preventDefault();
				var param = {
					"userName": $(".hfmsUserName").val(),
					"password": $(".hfmsPassword").val()
				};
				$(".hfmsErrorMsg").html('');
				ctDAO.getLoginValidate(param, function(data){
					if(data && data.responseStatus == bmpUtil.RESPONSE_STATUS)
					{
						var contextPath = '${baseURL}';
						location.href = "http://"+ location.host + contextPath  +"/api/admin/get";
					}
					else
					{
						$(".hfmsErrorMsg").html('UserName and Password Missmatch');
					}
				});
			});
		});
  </script>
</html>
